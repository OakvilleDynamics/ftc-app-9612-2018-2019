package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Drive Mode", group="Concept")
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

    }

    // Runs code when the driver hits PLAY button
    @Override
    public void start() {

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

        // Use gamepad left & right Bumpers to open and close the claw
        // Current hardware config we do not have a claw, however there is plans
        // to add an arm sooner or later, so this code segment will be left in but
        // commented out

        /*
        if (gamepad1.right_bumper)
            clawOffset += CLAW_SPEED;
        else if (gamepad1.left_bumper)
            clawOffset -= CLAW_SPEED;
        */

        // Arm controls

        // Use gamepad buttons to move the upper arm up (right bumper) and down (left bumper)
        if (gamepad1.right_bumper) {
            robot.upperArm.setPower(robot.ARM_UP_POWER);
            upperArmVal = true;
        } else if (gamepad1.left_bumper) {
            robot.upperArm.setPower(robot.ARM_DOWN_POWER);
            upperArmVal = true;
        } else {
            robot.upperArm.setPower(0.0);
            upperArmVal = false;
        }

        // Use gamepad buttons to move the forearm up (right trigger) and down (left trigger)
        if (gamepad1.right_trigger > 0.25) {
            robot.foreArm.setPower(robot.ARM_UP_POWER);
            foreArmVal = true;
        } else if (gamepad1.left_trigger > 0.25) {
            robot.foreArm.setPower(robot.ARM_DOWN_POWER);
            foreArmVal = true;
        } else {
            robot.foreArm.setPower(0.0);
            foreArmVal = false;
        }

        // Use gamepad buttons to move the armMotor1 up (dpad up) and down (dpad down)
        if (gamepad1.dpad_up) {
            robot.armMotor1.setPower(robot.ARM_UP_POWER);
            armMotor1Val = true;
        } else if (gamepad1.dpad_down) {
            robot.armMotor1.setPower(robot.ARM_DOWN_POWER);
            armMotor1Val = true;
        } else {
            robot.armMotor1.setPower(0);
            armMotor1Val = false;
        }

        // Use gamepad buttons to move the armMotor2 up (Y) and down (A)
        if (gamepad1.y) {
            robot.armMotor2.setPower(robot.ARM_UP_POWER);
            armMotor2Val = true;
        } else if (gamepad1.a) {
            robot.armMotor2.setPower(robot.ARM_DOWN_POWER);
            armMotor2Val = true;
        } else {
            robot.armMotor2.setPower(0);
            armMotor2Val = false;
        }

        // Send telemetry messages to signify robot running and whats actively going on
        telemetry.addData("ROBOT STATUS:","Not on fire");
        telemetry.addData("left",  "%.2f", left);
        telemetry.addData("right", "%.2f", right);
        telemetry.addData("upper arm", "%.2f", upperArmVal);
        telemetry.addData("forearm ", "%.2f", foreArmVal);
        telemetry.addData("armMotor1","%.2f", armMotor1Val);
        telemetry.addData("armMotor2", "%.2f", armMotor2Val);
    }

    // Runs when robot is stopped (no longer running opmode)
    @Override
    public void stop() {
        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);
        robot.upperArm.setPower(0);
        robot.foreArm.setPower(0);
        robot.armMotor1.setPower(0);
        robot.armMotor2.setPower(0);

        telemetry.addData("ROBOT STATUS:", "Stopped, OpMode killed by user");
    }
}
