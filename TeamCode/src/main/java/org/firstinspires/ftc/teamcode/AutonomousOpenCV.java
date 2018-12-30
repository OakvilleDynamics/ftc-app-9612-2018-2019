package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.detectors.GoldAlignDetector;
import org.firstinspires.ftc.teamcode.detectors.SilverAlignDetector;

import static android.os.SystemClock.sleep;

@Autonomous(name = "Autonomous OpenCV Test", group = "Mr. Muscles Autonomous")
@Disabled
public class AutonomousOpenCV extends OpMode {

    /**
     * TODO:
     * - TELEMETRY
     * - LOGGING
     * - FIXING DRIVE
     */
    // Call Hardware Map
    HWMap robot = new HWMap();

    // Calling Detectors
    GoldAlignDetector goldDetector = new GoldAlignDetector();
    SilverAlignDetector silverDetector = new SilverAlignDetector();

    // Variables for positioning of minerals
    double goldPos, silverPos = 0;

    // Init OpMode
    // This is run with the OpMode selected is initialized
    @Override
    public void init() {

        // -- Robot Hardware initialization --
        // Initialize the hardware variables from the hardware map
        robot.init(hardwareMap);

        // -- OpenCV/DogeCV initialization --
        // Initialize the camera for mineral tracking on the detectors
        goldDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        silverDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());

        // Set the detectors to run with defaults
        goldDetector.useDefaults();
        silverDetector.useDefaults();

        // Set scoring method for tracking to 'MAX_AREA' on the detectors
        goldDetector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA;
        silverDetector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA;

        // Set the maxAreaScorer weight of scoring for the detectors to '0.005'
        goldDetector.maxAreaScorer.weight = 0.005;
        silverDetector.maxAreaScorer.weight = 0.005;

        // Set the ratioScorer weight to '5'
        goldDetector.ratioScorer.weight = 5;
        silverDetector.ratioScorer.weight = 5;

        // Set the ratioScorer weight to '1.0'
        goldDetector.ratioScorer.perfectRatio = 1.0;
        silverDetector.ratioScorer.perfectRatio = 1.0;

        // Enable the detectors with the custom settings
        goldDetector.enable();
        silverDetector.enable();


        telemetry.addData("ROBOT STATUS", "INITIALIZED");
        telemetry.update();
    }

    // Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
    @Override
    public void init_loop() {
        telemetry.addData("ROBOT STATUS", "READY");
        telemetry.update();
    }

    // Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
    @Override
    public void start() {
        telemetry.addData("ROBOT STATUS", "RUNNING");
        telemetry.update();
    }

    // Code to run REPEATEDLY after the driver hits PLAY but before the driver hits STOP
    @Override
    public void loop() {
        /**
         * This right now is continuously having issues with our new drive, this should be resolved
         * when I figure out the changes needed to make this work out as smoothly as I can possibly
         * make it
         *
         * For now, to not use this OpMode in this current form
        // Set variables to getXPosition of respected minerals
        goldPos = goldDetector.getXPosition();
        silverPos = silverDetector.getXPosition();

        // -- Gold Mineral logic detection --
        // If goldPos is less than the value of '160'...
        if (goldPos < 160) {
            // Turn left
            robot.leftDrive.setPower(1);
            robot.rightDrive.setPower(-1);
            // If goldPos is more than the value of '400'...
        } else if (goldPos > 400) {
            // Turn right
            robot.leftDrive.setPower(-1);
            robot.rightDrive.setPower(1);
            // If nothing else fits within the parameters
        } else {
            // Drive forward
            robot.leftDrive.setPower(1);
            robot.rightDrive.setPower(1);
        }

        // -- Silver Mineral logic detection --
        // If silverPos is less than the value of '160'...
        if (silverPos < 160) {
            // Turn left
            robot.leftDrive.setPower(1);
            robot.rightDrive.setPower(-1);
            // If silverPos is more than the value of '400'...
        } else if (silverPos > 160) {
            robot.leftDrive.setPower(-1);
            robot.rightDrive.setPower(1);
            // If nothing else fits within the parameters
        } else {
            // Drive forward
            robot.leftDrive.setPower(1);
            robot.rightDrive.setPower(1);
        }
        // Stop moving for '1000' ms
        sleep(1000);

        // Drive forward
        robot.leftDrive.setPower(1);
        robot.rightDrive.setPower(1);

        // Stop moving for '1000' ms
        sleep(1000);

        // Disable mineral detectors
        goldDetector.disable();
        silverDetector.disable();
        */
    }

    // Runs when robot is stopped (no longer running opmode)
    @Override
    public void stop() {
        // Kill all motors and set all servos to position '0'
        //robot.leftDrive.setPower(0);
        //robot.rightDrive.setPower(0);
        //robot.upperArm.setPower(0);
        //robot.foreArm.setPower(0);
        //robot.clawServo.setPosition(0);

        // Telemetry
        telemetry.addData("ROBOT STATUS:", "Stopped, OpMode killed by user");
        telemetry.update();
    }
}
