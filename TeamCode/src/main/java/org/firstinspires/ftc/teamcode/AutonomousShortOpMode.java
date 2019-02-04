package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "Short Auto Mode", group = "Autonomous")
public class AutonomousShortOpMode extends OpMode {
    HWMap robot = new HWMap();

    @Override
    public void init() {

        // -- Robot Hardware initialization --
        // Initialize the hardware variables from the hardware map
        robot.init(hardwareMap);

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
        telemetry.addData("ROBOT STATUS", "Not on fire");
        telemetry.update();
    }

    // Runs when robot is stopped (no longer running opmode)
    @Override
    public  void stop() {
        // Telemetry
        telemetry.addData("ROBOT STATUS", "Stopped, OpMode killed by user");
        telemetry.update();
    }
}