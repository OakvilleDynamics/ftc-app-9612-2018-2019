package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HWMap {

    // Declaring and Initializing motors
    public DcMotor  leftDrive   = null;
    public DcMotor  rightDrive  = null;
    public DcMotor  upperArm    = null;
    public DcMotor  foreArm     = null;

    // Declaring and Initializing hardware map
    HardwareMap ConceptHWMap        =  null;
    private ElapsedTime timePeriod  = new ElapsedTime();

    // Arm speed values
    // While this does not actually set the speed of the motors, this does set the
    // variable to a value we can use in an OpMode when the time comes
    double ARM_UP_POWER     =  0.35;
    double ARM_DOWN_POWER   = -0.35;

    // Constructor class
    // This is used to create an object that can be used by other classes and can take in input
    // from other classes, for instance such as our OpMode
    public HWMap(){

    }

    // Initialize standard Hardware interfaces
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        ConceptHWMap = ahwMap;

        // Define and Initialize Motors
        leftDrive = ConceptHWMap.get(DcMotor.class, "left_drive");
        rightDrive = ConceptHWMap.get(DcMotor.class, "right_drive");
        upperArm = ConceptHWMap.get(DcMotor.class, "upper_arm");
        foreArm = ConceptHWMap.get(DcMotor.class, "upperArm");

        // Sets direction of motor power
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        upperArm.setPower(0);
        foreArm.setPower(0);

        // Set all motors to run without encoders
        // May want to use RUN_USING_ENCODERS if encoders are installed
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        upperArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        foreArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
