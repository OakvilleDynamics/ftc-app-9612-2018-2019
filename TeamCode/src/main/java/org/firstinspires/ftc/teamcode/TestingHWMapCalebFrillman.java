package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.HardwareMap;

public class TestingHWMapCalebFrillman {
    public DcMotor leftDrive = null;
    public DcMotor rightDrive = null;

    HardwareMap TestingHWMapCalebFrillman    = null;

    public TestingHWMapCalebFrillman() {

    }

    public void init(HardwareMap ahwmap) {
        TestingHWMapCalebFrillman = ahwmap;

        leftDrive = TestingHWMapCalebFrillman.get(DcMotor.class, "leftDrive");
        rightDrive = TestingHWMapCalebFrillman.get(DcMotor.class, "rightDrive");
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        leftDrive.setPower(0);
        rightDrive.setPower(0);

        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode((DcMotor.RunMode.RUN_WITHOUT_ENCODER));

    }

}



