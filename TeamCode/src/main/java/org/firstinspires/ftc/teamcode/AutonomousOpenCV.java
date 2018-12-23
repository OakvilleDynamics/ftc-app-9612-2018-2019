package org.firstinspires.ftc.teamcode;

import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import static android.os.SystemClock.sleep;

@Autonomous(name = "Autonomous OpenCV Test", group = "Autonomous")
public class AutonomousOpenCV extends OpMode {

    HWMap robot = new HWMap();

    GoldAlignDetector goldDetector = new GoldAlignDetector();
    SilverDetector silverDetector = new SilverDetector();

    double goldPos, silverPos = 0;

    @Override
    public void init() {
        robot.init(hardwareMap);

        goldDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        silverDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        goldDetector.useDefaults();

        goldDetector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA; // Can also be PERFECT_AREA
        //goldDetector.perfectAreaScorer.perfectArea = 10000; // if using PERFECT_AREA scoring
        goldDetector.maxAreaScorer.weight = 0.005;

        goldDetector.ratioScorer.weight = 5;
        goldDetector.ratioScorer.perfectRatio = 1.0;

        goldDetector.enable();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        goldPos = goldDetector.getXPosition();


        if (goldPos < 160) {
            robot.leftDrive.setPower(1);
            robot.rightDrive.setPower(-1);
        } else if (goldPos > 400) {
            robot.leftDrive.setPower(-1);
            robot.rightDrive.setPower(1);
        } else {
            robot.leftDrive.setPower(1);
            robot.rightDrive.setPower(1);
        }

        sleep(1000);

        robot.leftDrive.setPower(1);
        robot.rightDrive.setPower(1);
        sleep(1000);

        goldDetector.disable();

    }

    @Override
    public void stop() {

    }
}
