package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HWMap {

    // Declaring and Initializing drive motors
    public DcMotor leftFrontDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor leftRearDrive = null;
    public DcMotor rightRearDrive = null;

    // Declaring and Initializing external motors for other functions
    public DcMotor armMotor1;
    public DcMotor armMotor2;
    public DcMotor armMotorSpool;
    public DcMotor rodMotor;

    // Declaring and Initializing servos
    public Servo paddleFront = null;
    public Servo paddleLeft = null;
    public Servo paddleRight = null;
    public Servo paddleBack = null;

    // Declaring and Initializing hardware map
    HardwareMap omniHWMap = null;

    // -- Arm speed values --
    // While this does not actually set the speed of the motors for arm control, this does set a
    // variable to a value we can use in an OpMode when the time comes, as this is what we can use
    // for speed control so we do not damage the robot in the process of having the robot arm swing
    // down and hit itself
    final double ARM_UP_HIGH_POWER_VAL = 1;
    final double ARM_DOWN_HIGH_POWER_VAL = -1;
    final double ARM_UP_LOW_POWER_VAL = 0.5;
    final double ARM_DOWN_LOW_POWER_VAL = -0.5;

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
        leftFrontDrive = omniHWMap.get(DcMotor.class, "leftFDrive");
        rightFrontDrive = omniHWMap.get(DcMotor.class, "rightFDrive");
        rightRearDrive = omniHWMap.get(DcMotor.class, "rightRDrive");
        leftRearDrive = omniHWMap.get(DcMotor.class, "leftRDrive");

        // Define and Initialize External Motors and other functions
        armMotor1 = omniHWMap.get(DcMotor.class, "armBoi1");
        armMotor2 = omniHWMap.get(DcMotor.class, "armBoi2");
        armMotorSpool = omniHWMap.get(DcMotor.class, "armSpool");
        rodMotor = omniHWMap.get(DcMotor.class, "rod");

        // Define and Initialize Servos
        paddleFront = omniHWMap.get(Servo.class, "paddleFront");
        paddleLeft = omniHWMap.get(Servo.class, "paddleLeft");
        paddleRight = omniHWMap.get(Servo.class, "paddleRight");
        paddleBack = omniHWMap.get(Servo.class, "paddleBack");

        // Sets direction of motor power for the drive motors
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftRearDrive.setDirection(DcMotor.Direction.FORWARD);
        rightRearDrive.setDirection(DcMotor.Direction.FORWARD);

        // Sets direction of motor power for the external functions
        armMotor1.setDirection(DcMotor.Direction.FORWARD);
        armMotor2.setDirection(DcMotor.Direction.REVERSE);
        armMotorSpool.setDirection(DcMotor.Direction.REVERSE);
        rodMotor.setDirection(DcMotor.Direction.FORWARD);

        // Set all motors to zero power (0)
        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftRearDrive.setPower(0);
        rightRearDrive.setPower(0);

        // Set all servos to position zero (0)
        paddleFront.setPosition(0.4);
        paddleLeft.setPosition(0.4);
        paddleRight.setPosition(0.4);
        paddleBack.setPosition(0.4);

        // Set all drive motors to run without encoders
        // May want to use RUN_USING_ENCODERS if encoders are installed
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRearDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Set all external  to run to position for use in Autonomous
        armMotor1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armMotor2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armMotorSpool.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rodMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
