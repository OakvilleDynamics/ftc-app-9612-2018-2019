package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Drive Mode", group = "Hipp0 DriveOp")
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
        // Declare variables for wheels in total
        double leftFront, rightFront, leftRear, rightRear;
        // Declare variables fpr wheels in scaled input (as these values will sometimes reach higher than the maximum motor power value
        double leftFrontScale, rightFrontScale, leftRearScale, rightRearScale;
        // Declare variables for calculating omni-wheel
        double leftStickX, leftStickY, rightStickX;

        // Initialize calculating variables
        leftStickY = gamepad1.left_stick_y;
        leftStickX = gamepad1.left_stick_x;
        rightStickX = gamepad1.right_stick_x;

        // Run wheels in omni-wheel orientation
        leftFront = leftStickY - leftStickX + rightStickX;
        rightFront = - leftStickY - leftStickX + rightStickX;
        leftRear = leftStickY + leftStickX + rightStickX;
        rightRear = - leftStickY + leftStickX + rightStickX;


        // Scale the values because values can be larger than on
        leftFrontScale = Range.clip(leftFront, -1, 1);
        rightFrontScale = Range.clip(rightFront, -1,1);
        leftRearScale = Range.clip(leftRear,-1,1);
        rightRearScale = Range.clip(rightRear, -1, 1);

        // -! CONTROLS !-
        /** -- Motor controls --
         * Rotate clockwise = All positive
         * Rotate counter-clockwise = All negative
         * Move forward = backs negative  fronts positive
         * Move backward = backs positive  fronts negative
         * Move left = rights negative  lefts positive
         * Move right = rights positive  lefts negative
         */
        robot.leftFrontDrive.setPower(leftFrontScale);
        robot.rightFrontDrive.setPower(rightFrontScale);
        robot.leftRearDrive.setPower(leftRearScale);
        robot.rightRearDrive.setPower(rightRearScale);

        /** -- Paddle controls --
         * Controls are as follows for the paddles:
         * All controls are only on gamepad2
         * - DPad up is paddleFront
         * - DPad left is paddleLeft
         * - DPad right is paddleRight
         * - DPad down is paddleBack
         * If nothing is pressed, return paddles to pos '0.4'
         */

        if (gamepad2.y == true) {
            robot.paddleFront.setPosition(0);
        } else if (gamepad2.x == true) {
            robot.paddleLeft.setPosition(0);
        } else if (gamepad2.b == true) {
            robot.paddleRight.setPosition(0);
        } else if (gamepad2.a == true) {
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

        // Return all servos to pos '0.4'
        robot.paddleFront.setPosition(0.4);
        robot.paddleLeft.setPosition(0.4);
        robot.paddleRight.setPosition(0.4);
        robot.paddleBack.setPosition(0.4);

        // Telemetry updates to signify opmode status
        telemetry.addData("ROBOT STATUS:", "Stopped, OpMode killed by user");
        telemetry.update();
    }
}
