package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Drive Mode", group = "Concept")
public class DriveOpMode extends OpMode {

    // Call Hardware Map
    HWMap robot = new HWMap();

    // Init OpMode
    // This is run with the OpMode selected is initialized.
    @Override
    public void init() {
        // Initialize the hardware variables from the hardware map
        robot.init(hardwareMap);

        // Send telemetry message to signify robot is initialized
        telemetry.addData("ROBOT STATUS", "INITIALIZED");
        telemetry.update();
    }

    // Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
    @Override
    public void init_loop() {
        telemetry.addData("ROBOT STATUS", "READY");
        telemetry.update();
    }

    // Runs code when the driver hits PLAY button
    @Override
    public void start() {
        telemetry.addData("ROBOT STATUS", "RUNNING");
        telemetry.update();
    }

    // Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
    @Override
    public void loop() {
        // Declare variables for wheels
        double leftFront, rightFront, leftRear, rightRear;
        // Declare variables for calculating omni-wheel
        double leftY, leftX, rightX;

        // Initialize calculating variables
        leftY = gamepad1.left_stick_y;
        leftX = gamepad1.left_stick_x;
        rightX = gamepad1.right_stick_x;

        // Run wheels in omniwheel orientation
        leftFront = leftY + leftX - rightX;
        rightFront = leftY - leftX + rightX;
        leftRear = leftY - leftX - rightX;
        rightRear = leftY + leftX + rightX;

        // Motor controls
        robot.leftFrontDrive.setPower(leftFront);
        robot.rightFrontDrive.setPower(rightFront);
        robot.leftRearDrive.setPower(leftRear);
        robot.rightRearDrive.setPower(rightRear);

        // Send telemetry messages to signify robot running and whats actively going on
        // TO DO: fix the telemetry issues with formatting
        telemetry.addData("ROBOT STATUS:", "Not on fire");
        telemetry.addLine("Values for controller")
                .addData("leftY", leftX)
                .addData("leftX", leftY)
                .addData("rightX",rightX);
        telemetry.addLine("Values for motors")
                .addData("leftFront", leftFront)
                .addData("rightFront", rightFront)
                .addData("leftRear", leftRear)
                .addData("rightRear", rightRear);
        telemetry.update();

    }

    // Runs when robot is stopped (no longer running opmode)
    @Override
    public void stop() {
        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftRearDrive.setPower(0);
        robot.rightRearDrive.setPower(0);

        telemetry.addData("ROBOT STATUS:", "Stopped, OpMode killed by user");
        telemetry.update();
    }
}
