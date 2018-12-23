package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

public class testinghwmapk {
    public DcMotor leftDrive = null;
    public DcMotor rightDrive = null;

    HardwareMap testinghwmapk = null;

    public testinghwmapk() {

    }

    public void init(HardwareMap ahwmap) {
        testinghwmapk = ahwmap;

        leftDrive = testinghwmapk.get(DcMotor.class, "leftDrive");
        rightDrive = testinghwmapk.get(DcMotor.class, "rightDrive");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        leftDrive.setPower(0);
        rightDrive.setPower(0);

        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
