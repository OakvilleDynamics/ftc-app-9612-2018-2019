package org.firstinspires.ftc.teamcode.learning.samples;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

public class testingHWMapConnorBuneta {
    public DcMotor leftDrive = null;
    public DcMotor rightDrive = null;

    HardwareMap testingHWMap = null;

    public testingHWMapConnorBuneta() {

    }

    public void init(HardwareMap ahwmap) {
        testingHWMap = ahwmap;

        leftDrive = testingHWMap.get(DcMotor.class, "leftDrive");
        rightDrive = testingHWMap.get(DcMotor.class, "rightDrive");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        leftDrive.setPower(0);
        rightDrive.setPower(0);

        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
