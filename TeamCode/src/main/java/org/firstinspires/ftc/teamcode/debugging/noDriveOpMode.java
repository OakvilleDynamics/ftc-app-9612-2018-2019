package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "NO Drive Mode // DO NOT USE", group = "Concept")
public class noDriveOpMode extends OpMode {
    // Call Hardware Map
    noHWMap robot = new noHWMap();

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
        //boolean foreArmVal, upperArmVal, armMotor1Val, armMotor2Val;
        double left;
        double right;

        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;

        // Send telemetry messages to signify robot running and whats actively going on
        // TO DO: fix the telemetry issues with formatting
        telemetry.addData("ROBOT STATUS:", "Not on fire");
        telemetry.addData("left", "%.2f", left);
        telemetry.addData("right", "%.2f", right);
        telemetry.addData("upper arm", "NOT TRACKING");
        telemetry.addData("forearm ", "NOT TRACKING");
        telemetry.addData("claw servo", "NOT TRACKING");
    }

    // Runs when robot is stopped (no longer running opmode)
    @Override
    public void stop() {
        telemetry.addData("ROBOT STATUS:", "Stopped, OpMode killed by user");
    }
}
