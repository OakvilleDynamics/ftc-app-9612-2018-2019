package org.firstinspires.ftc.teamcode.learning.samples;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

public class HwMapConnorBuneta {
    public DcMotor LeftDrive = null;
    public DcMotor RightDrive = null;

    HardwareMap testingHWMap = null;

    public HwMapConnorBuneta() {
    }

    public void init(HardwareMap ahwmap) {
        testingHWMap = ahwmap;

        LeftDrive = testingHWMap.get(DcMotor.class, "LeftDrive");
        RightDrive = testingHWMap.get(DcMotor.class, "RightDrive");

        LeftDrive.setDirection(DcMotor.Direction.FORWARD);
        RightDrive.setDirection(DcMotor.Direction.REVERSE);

        LeftDrive.setPower(0);
        RightDrive.setPower(0);

        LeftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}

