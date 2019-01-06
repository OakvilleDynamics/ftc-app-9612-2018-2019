package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Drive Mode", group = "Mr. Muscles DriveOp")
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

    // Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
    @Override
    public void start() {
        telemetry.addData("ROBOT STATUS", "RUNNING");
        telemetry.update();
    }

    // Code to run REPEATEDLY after the driver hits PLAY but before the driver hits STOP
    @Override
    public void loop() {
        // Declare variables for wheels
        double leftFront, rightFront, leftRear, rightRear;
        // Declare variables for calculating omni-wheel
        double leftY, leftX, rightX;
        // Declare variables for servos
        double servoFront, servoLeft, servoRight, servoBack;

        // Initialize calculating variables
        leftY = -gamepad1.left_stick_y;
        leftX = gamepad1.left_stick_x;
        rightX = -gamepad1.right_stick_x;

        // Run wheels in omni-wheel orientation
        leftFront = leftY + leftX - rightX;
        rightFront = leftY - leftX + rightX;
        leftRear = leftY - leftX - rightX;
        rightRear = leftY + leftX + rightX;

        // -! CONTROLS !-
        // -- Motor controls --
        robot.leftFrontDrive.setPower(leftFront);
        robot.rightFrontDrive.setPower(rightFront);
        robot.leftRearDrive.setPower(leftRear);
        robot.rightRearDrive.setPower(rightRear);

        /** -- Paddle controls --
         * Controls are as follows for the paddles:
         * All controls are only on gamepad2
         * - DPad up is paddleFront
         * - DPad left is paddleLeft
         * - DPad right is paddleRight
         * - DPad down is paddleBack
         * If nothing is pressed, return paddles to pos '0'
         */

        if (gamepad2.dpad_up == true) {
            robot.paddleFront.setPosition(0);
        } else if (gamepad2.dpad_left == true) {
            robot.paddleLeft.setPosition(0);
        } else if (gamepad2.dpad_right == true) {
            robot.paddleRight.setPosition(0);
        } else if (gamepad2.dpad_down == true) {
            robot.paddleBack.setPosition(0);
        } else {
            robot.paddleFront.setPosition(0.4);
            robot.paddleLeft.setPosition(0.4);
            robot.paddleRight.setPosition(0.4);
            robot.paddleBack.setPosition(0.4);
        }

        // Send telemetry messages to signify robot running and whats actively going on
        telemetry.addData("ROBOT STATUS:", "Not on fire");
        telemetry.update();
    }

    // Runs when robot is stopped (no longer running opmode)
    @Override
    public void stop() {
        // Kill all motors
        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        robot.leftRearDrive.setPower(0);
        robot.rightRearDrive.setPower(0);

        // Return all motors to pos '0'
        robot.paddleFront.setPosition(0.4);
        robot.paddleLeft.setPosition(0.4);
        robot.paddleRight.setPosition(0.4);
        robot.paddleBack.setPosition(0.4);

        // Telemetry updates to signify opmode status
        telemetry.addData("ROBOT STATUS:", "Stopped, OpMode killed by user");
        telemetry.update();
    }
}
