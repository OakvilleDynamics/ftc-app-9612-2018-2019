package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HWMap {

    // Declaring and Initializing motors
    public DcMotor leftDrive = null;
    public DcMotor rightDrive = null;;

    // Declaring and Initializing servos
    // CRServo is a continuous rotational servo where Servo is just a regular servo
    // Imagine the CRServo is a lower-powered motor in the form factor of a servo
    //public Servo paddleFront = null;
    public Servo paddleLeft = null;
    public Servo paddleRight = null;
    public Servo paddleBack = null;

    // Declaring and Initializing hardware map
    HardwareMap omniHWMap = null;

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
        leftDrive = omniHWMap.get(DcMotor.class, "leftFDrive");
        rightDrive = omniHWMap.get(DcMotor.class, "rightFDrive");

        // Define and Initialize Servos
        //paddleFront = omniHWMap.get(Servo.class, "paddleFront");
        paddleLeft = omniHWMap.get(Servo.class, "paddleLeft");
        paddleRight = omniHWMap.get(Servo.class, "paddleRight");
        paddleBack = omniHWMap.get(Servo.class, "paddleBack");

        // Sets direction of motor power
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power (0)
        leftDrive.setPower(0);
        rightDrive.setPower(0);

        // Set all servos to position zero (0.4) and power zero (0)
        //paddleFront.setPosition(0.4);
        paddleLeft.setPosition(0.4);
        paddleRight.setPosition(0.4);
        paddleBack.setPosition(0.4);

        // Set all motors to run without encoders
        // May want to use RUN_USING_ENCODERS if encoders are installed
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
