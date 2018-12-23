package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name = "Drive", group = "TestingTutorial")
public class TestingDriveOpModeConnor extends OpMode {

    HwMapConnor robot = new HwMapConnor();

    //lol i have no clue what im doing
    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        double left;
        double right;

        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;

        robot.LeftDrive.setPower(-(left));
        robot.RightDrive.setPower(-(right));

    }
}
