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
    }

    // Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
    @Override
    public void init_loop() {
        telemetry.addData("ROBOT STATUS", "READY");
    }

    // Runs code when the driver hits PLAY button
    @Override
    public void start() {
        telemetry.addData("ROBOT STATUS", "RUNNING");
    }

    // Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
    @Override
    public void loop() {
        boolean foreArmVal, upperArmVal, armMotor1Val, armMotor2Val;
        double left;
        double right;

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;

        robot.leftDrive.setPower(left);
        robot.rightDrive.setPower(right);

        // Arm controls

        // Use gamepad buttons to move the upper arm up (right bumper) and down (left bumper)
        if (gamepad2.right_bumper) {
            robot.upperArm.setPower(robot.ARM_UP_POWER);
            upperArmVal = true;
        } else if (gamepad2.left_bumper) {
            robot.upperArm.setPower(robot.ARM_DOWN_POWER);
            upperArmVal = true;
        } else {
            robot.upperArm.setPower(0.0);
            upperArmVal = false;
        }

        // Use gamepad buttons to move the forearm up (right trigger) and down (left trigger)
        if (gamepad2.right_trigger > 0.25) {
            robot.foreArm.setPower(robot.ARM_UP_POWER);
            foreArmVal = true;
        } else if (gamepad2.left_trigger > 0.25) {
            robot.foreArm.setPower(robot.ARM_DOWN_POWER);
            foreArmVal = true;
        } else {
            robot.foreArm.setPower(0.0);
            foreArmVal = false;
        }

        // Use gamepad buttons to move the clawServo up (dpad up) and down (dpad down)
        //If the servos do not work as planned blame Ryan for his input
        if (gamepad2.dpad_up) {
            robot.clawServo.setPosition(1);
        } else if (gamepad2.dpad_down) {
            robot.clawServo.setPosition(-1);
        } else {
            robot.clawServo.setPosition(0);
        }

        // Send telemetry messages to signify robot running and whats actively going on
        /*
        telemetry.addData("ROBOT STATUS:", "Not on fire");
        telemetry.addData("left", "%.2f", left);
        telemetry.addData("right", "%.2f", right);
        telemetry.addData("upper arm", "NOT TRACKING");
        telemetry.addData("forearm ", "NOT TRACKING");
        telemetry.addData("claw servo", "NOT TRACKING");
        */
    }

    // Runs when robot is stopped (no longer running opmode)
    @Override
    public void stop() {
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
        robot.upperArm.setPower(0);
        robot.foreArm.setPower(0);
        robot.clawServo.setPosition(0);

        telemetry.addData("ROBOT STATUS:", "Stopped, OpMode killed by user");
    }
}
