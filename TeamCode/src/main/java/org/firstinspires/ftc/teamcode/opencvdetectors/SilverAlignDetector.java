package org.firstinspires.ftc.teamcode.opencvdetectors;

import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.DogeCVDetector;
import com.disnodeteam.dogecv.filters.DogeCVColorFilter;
import com.disnodeteam.dogecv.filters.HSVRangeFilter;
import com.disnodeteam.dogecv.scoring.MaxAreaScorer;
import com.disnodeteam.dogecv.scoring.PerfectAreaScorer;
import com.disnodeteam.dogecv.scoring.RatioScorer;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victo on 9/10/2018.
 */

public class SilverAlignDetector extends DogeCVDetector {

    /**
     * TODO:
     *  - DOCUMENTATION
     *  - CLEANUP OF CODE
     *  - LOGGING
     */

    // Defining Mats to be used.
    private Mat displayMat = new Mat(); // Display debug info to the screen (this is what is returned)
    private Mat workingMat = new Mat(); // Used for pre-processing and working with (blurring as an example)
    private Mat maskWhite = new Mat(); // White Mask returned by color filter
    private Mat hierarchy = new Mat(); // hierarchy used by contours

    // Results of the detector
    private boolean found = false; // Is the silver mineral found
    private boolean aligned = false; // Is the silver mineral aligned
    private Point screenPosition = new Point(); // Screen position of the mineral
    private Rect foundRect = new Rect(); // Found rect
    private double silverXPos = 0;

    // Detector settings
    public boolean debugAlignment = true; // Show debug lines to show alignment settings
    public double alignPosOffset = 0;    // How far from center frame is aligned
    public double alignSize = 100;  // How wide is the margin of error for alignment

    public DogeCV.AreaScoringMethod areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Setting to decide to use MaxAreaScorer or PerfectAreaScorer

    //Create the default filters and scorers
    public DogeCVColorFilter whiteFilter = new HSVRangeFilter(new Scalar(0, 0, 200), new Scalar(50, 40, 255));

    public RatioScorer ratioScorer = new RatioScorer(1.0, 3);          // Used to find perfect squares
    public MaxAreaScorer maxAreaScorer = new MaxAreaScorer(0.01);                    // Used to find largest objects
    public PerfectAreaScorer perfectAreaScorer = new PerfectAreaScorer(5000, 0.05); // Used to find objects near a tuned area value

    /**
     * Simple constructor
     */
    public SilverAlignDetector() {
        super();
        detectorName = "Silver Align Detector"; // Set the detector name
    }


    @Override
    public Mat process(Mat input) {

        // Copy the input mat to our working mats, then release it for memory
        input.copyTo(displayMat);
        input.copyTo(workingMat);
        input.release();


        //Pre-process the working Mat (blur it then apply a white filter)
        Imgproc.GaussianBlur(workingMat, workingMat, new Size(5, 5), 0);
        whiteFilter.process(workingMat.clone(), maskWhite);

        //Find contours of the yellow mask and draw them to the display mat for viewing

        List<MatOfPoint> contoursYellow = new ArrayList<>();
        Imgproc.findContours(maskWhite, contoursYellow, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        Imgproc.drawContours(displayMat, contoursYellow, -1, new Scalar(230, 70, 70), 2);

        // Current result
        Rect bestRect = null;
        double bestDifference = Double.MAX_VALUE; // MAX_VALUE since less difference = better

        // Loop through the contours and score them, searching for the best result
        for (MatOfPoint cont : contoursYellow) {
            double score = calculateScore(cont); // Get the difference score using the scoring API

            // Get bounding rect of contour
            Rect rect = Imgproc.boundingRect(cont);
            Imgproc.rectangle(displayMat, rect.tl(), rect.br(), new Scalar(0, 0, 255), 2); // Draw rect

            // If the result is better then the previously tracked one, set this rect as the new best
            if (score < bestDifference) {
                bestDifference = score;
                bestRect = rect;
            }
        }

        // Vars to calculate the alignment logic.
        double alignX = (getAdjustedSize().width / 2) + alignPosOffset; // Center point in X Pixels
        double alignXMin = alignX - (alignSize / 2); // Min X Pos in pixels
        double alignXMax = alignX + (alignSize / 2); // Max X pos in pixels
        double xPos; // Current Silver X Pos

        if (bestRect != null) {
            // Show chosen result
            Imgproc.rectangle(displayMat, bestRect.tl(), bestRect.br(), new Scalar(255, 0, 0), 4);
            Imgproc.putText(displayMat, "Chosen", bestRect.tl(), 0, 1, new Scalar(255, 255, 255));

            // Set align X pos
            xPos = bestRect.x + (bestRect.width / 2);
            silverXPos = xPos;

            Imgproc.circle(displayMat, new Point(xPos, bestRect.y + (bestRect.height / 2)), 5, new Scalar(0, 255, 0));

            // Check if the mineral is aligned
            aligned = xPos < alignXMax && xPos > alignXMin;

            screenPosition = new Point(bestRect.x, bestRect.y);
            foundRect = bestRect;
            found = true;
        } else {
            found = false;
            aligned = false;
        }

        if (debugAlignment) {
            if (isFound()) {
                Imgproc.line(displayMat, new Point(silverXPos, getAdjustedSize().height), new Point(silverXPos, getAdjustedSize().height - 30), new Scalar(255, 255, 0), 2);
            }
            Imgproc.line(displayMat, new Point(alignXMin, getAdjustedSize().height), new Point(alignXMin, getAdjustedSize().height - 40), new Scalar(0, 255, 0), 2);
            Imgproc.line(displayMat, new Point(alignXMax, getAdjustedSize().height), new Point(alignXMax, getAdjustedSize().height - 40), new Scalar(0, 255, 0), 2);
        }
        //Print result
        Imgproc.putText(displayMat, "Result: " + screenPosition.x + "/" + screenPosition.y, new Point(10, getAdjustedSize().height - 30), 0, 1, new Scalar(255, 255, 0), 1);


        return displayMat;

    }

    @Override
    public void useDefaults() {
        addScorer(ratioScorer);

        // Add different scorers depending on the selected mode
        if (areaScoringMethod == DogeCV.AreaScoringMethod.MAX_AREA) {
            addScorer(maxAreaScorer);
        }

        if (areaScoringMethod == DogeCV.AreaScoringMethod.PERFECT_AREA) {
            addScorer(perfectAreaScorer);
        }

    }

    /**
     * Set the alignment settings for GoldAlign
     *
     * @param offset - How far from center frame (in pixels)
     * @param width  - How wide the margin is (in pixels, on each side of offset)
     */
    public void setAlignSettings(int offset, int width) {
        alignPosOffset = offset;
        alignSize = width;
    }

    /**
     * Returns if the gold element is aligned
     *
     * @return if the gold element is alined
     */
    public boolean getAligned() {
        return aligned;
    }

    /**
     * Returns the silver element's last position in screen pixels
     *
     * @return position in screen pixels
     */
    public Point getScreenPosition() {
        return screenPosition;
    }

    /**
     * Returns silver element last x-position
     *
     * @return last x-position in screen pixels of gold element
     */
    public double getXPosition() {
        return silverXPos;
    }

    /**
     * Returns the silver element's found rectangle
     *
     * @return silver element rect
     */

    public Rect getFoundRect() {
        return foundRect;
    }

    /**
     * Returns if a silver mineral is being tracked/detected
     *
     * @return if a silver mineral is being tracked/detected
     */
    public boolean isFound() {
        return found;
    }
}
