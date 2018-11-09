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
        telemetry.addData("ROBOT", "INITIALIZED");
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

        // Use gamepad buttons to move the arm up (Y) and down (A)
        // When the time is good, please change the button binds to be accurate to how we want
        // to control the bot, as this is giving me arthritis looking at it
        if (gamepad1.y) {
            robot.upperArm.setPower(robot.ARM_UP_POWER);
        } else if (gamepad1.a) {
            robot.upperArm.setPower(robot.ARM_DOWN_POWER);
        } else {
            robot.upperArm.setPower(0.0);
        }

        if (gamepad1.right_trigger > 0.25) {
            robot.foreArm.setPower(robot.ARM_UP_POWER);
        } else if (gamepad1.left_trigger > 0.25) {
            robot.foreArm.setPower(robot.ARM_DOWN_POWER);
        } else {
            robot.foreArm.setPower(0.0);
        }

        // Send telemetry message to signify robot running
        telemetry.addData("ROBOT STATUS:","Not on fire");
        telemetry.addData("left",  "%.2f", left);
        telemetry.addData("right", "%.2f", right);

        // This telemetry is not working entirely, but this will be operational soonish
        //telemetry.addData("upper arm", "%.2f", upperArm);
        //telemetry.addData("forearm ", "%.2f", foreArm);
    }

    // Runs when robot is stopped (no longer running opmode)
    @Override
    public void stop() {
    }
}
