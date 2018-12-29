package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HWMap {

    // Declaring and Initializing motors
    public DcMotor  leftFrontDrive  = null;
    public DcMotor  rightFrontDrive = null;
    public DcMotor  leftRearDrive   = null;
    public DcMotor  rightRearDrive  = null;

    // Declaring and Initializing servos
    public Servo    clawServo   = null;
    
    // Declaring and Initializing hardware map
    HardwareMap omniHWMap        = null;

    // Arm speed values
    // While this does not actually set the speed of the motors, this does set the
    // variable to a value we can use in an OpMode when the time comes
    double ARM_UP_POWER     =  0.35;
    double ARM_DOWN_POWER   = -0.35;

    // Constructor class
    // This is used to create an object that can be used by other classes and can take in input
    // from other classes, for instance such as our OpMode
    public HWMap() {

    }

    // Initialize standard Hardware interfaces
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        omniHWMap = ahwMap;

        // Define and Initialize Motors
        leftFrontDrive   =   omniHWMap.get(DcMotor.class, "leftFDrive");
        rightFrontDrive  =   omniHWMap.get(DcMotor.class, "rightFDrive");
        leftRearDrive    =   omniHWMap.get(DcMotor.class, "leftRDrive");
        rightRearDrive   =   omniHWMap.get(DcMotor.class, "rightRDrive");

        // Define and Initialize Servos
        clawServo   =   omniHWMap.get(Servo.class, "claw_servo");

        // Sets direction of motor power
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftRearDrive.setPower(0);
        rightRearDrive.setPower(0);

        // Set all servos to position zero (0)
        clawServo.setPosition(0);

        // Set all motors to run without encoders
        // May want to use RUN_USING_ENCODERS if encoders are installed
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRearDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
