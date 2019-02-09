package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Short Auto Mode", group = "Th0r Autonomous")
public class AutonomousShortOpMode extends LinearOpMode {

    // Calling and initializing hardware map
    HWMap robot = new HWMap();

    // Creating a timer for runtime
    private ElapsedTime opModeRuntime = new ElapsedTime();

    // Motor counts for the special motors we have
    static final double neverestCountsPerMotorRev = 1680;
    static final double torqenadoeCountsPerMotorRev = 1440;

    // Arm values for what hardware is connected to
    static final double armGearReduction = 2.5;
    static final double armDiameterInches = 2.6693;
    static final double armCountsPerInch = (torqenadoeCountsPerMotorRev * armGearReduction) / (armDiameterInches * 3.1415);

    // Wheel values for what hardware is connected to
    static final double wheelGearReduction = 1.0;
    static final double wheelDiameterInches = 4.0;
    static final double wheelCountsPerInch = (torqenadoeCountsPerMotorRev * wheelGearReduction) / (wheelDiameterInches * 3.1415);

    // Rod values for what hardware is connected to
    static final double rodGearReduction = 1.0;
    static final double rodDiameterInches = 1.0;
    static final double rodCountsPerInch = (neverestCountsPerMotorRev * rodGearReduction) / (rodDiameterInches * 3.1415);

    // Speed values for operation
    static final double driveSpeed = 0.6;
    static final double turnSpeed = 0.5;

    @Override
    public void runOpMode() {

        // Initialize hardware map
        robot.init(hardwareMap);

        // Reset encoders for our drive motors
        robot.leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set drive motors to run using encoders
        robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.leftRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Rest encoders for the arm
        robot.armMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.armMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set arm motors to run using encoders
        robot.armMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.armMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Reset encoder for the rod motor
        robot.rodMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set rod motor to run using an encoder
        robot.rodMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        // THIS IS UNTESTED
        // S0: Move lift down
        encoderArm(robot.ARM_DOWN_LOW_POWER_VAL, 2, 5.0);
        // S1: Forward 47 Inches with 5 Sec timeout
        encoderDrive(driveSpeed,  48,  48, 5.0);
        // S2: Turn Right 12 Inches with 4 Sec timeout
        encoderDrive(turnSpeed,   12, -12, 4.0);
        // S3: Reverse 24 Inches with 4 Sec timeout
        encoderDrive(driveSpeed, -24, -24, 4.0);
        // S4: Drop marker
        encoderRod(robot.ARM_DOWN_LOW_POWER_VAL, 2, 2.0);
    }

    // Properties to our drive encoder method
    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
        // Variables for all of our drive motors
        int newLeftFrontTarget;
        int newRightFrontTarget;
        int newLeftRearTarget;
        int newRightRearTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftFrontTarget = robot.leftFrontDrive.getCurrentPosition() + (int)(leftInches * wheelCountsPerInch);
            newRightFrontTarget = robot.rightFrontDrive.getCurrentPosition() + (int)(rightInches * wheelCountsPerInch);
            newLeftRearTarget = robot.leftRearDrive.getCurrentPosition() + (int)(leftInches * wheelCountsPerInch);
            newRightRearTarget = robot.rightRearDrive.getCurrentPosition() + (int)(leftInches * wheelCountsPerInch);

            robot.leftFrontDrive.setTargetPosition(newLeftFrontTarget);
            robot.rightFrontDrive.setTargetPosition(newRightFrontTarget);
            robot.leftRearDrive.setTargetPosition(newLeftRearTarget);
            robot.rightRearDrive.setTargetPosition(newRightRearTarget);

            // Turn On RUN_TO_POSITION
            robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.leftRearDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightRearDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            opModeRuntime.reset();
            robot.leftFrontDrive.setPower(Math.abs(speed));
            robot.rightFrontDrive.setPower(Math.abs(speed));
            robot.leftRearDrive.setPower(Math.abs(speed));
            robot.rightRearDrive.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (opModeRuntime.seconds() < timeoutS) && (robot.leftFrontDrive.isBusy() && robot.rightFrontDrive.isBusy() && robot.leftRearDrive.isBusy() && robot.rightRearDrive.isBusy())) {

                // Display it for the driver
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftFrontTarget,  newRightFrontTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d", robot.leftFrontDrive.getCurrentPosition(), robot.rightFrontDrive.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion
            robot.leftFrontDrive.setPower(0);
            robot.rightFrontDrive.setPower(0);
            robot.leftRearDrive.setPower(0);
            robot.rightRearDrive.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.leftRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightRearDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    // Properties to our arm encoder method
    public void encoderArm(double speed, double liftInches, double timeoutS) {
        int newLiftTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLiftTarget = robot.leftFrontDrive.getCurrentPosition() + (int)(liftInches * armCountsPerInch);
            robot.armMotor1.setTargetPosition(newLiftTarget);
            robot.armMotor2.setTargetPosition(newLiftTarget);

            // Turn On RUN_TO_POSITION
            robot.armMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.armMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            opModeRuntime.reset();
            robot.armMotor1.setPower(Math.abs(speed));
            robot.armMotor2.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (opModeRuntime.seconds() < timeoutS) && (robot.armMotor1.isBusy() && robot.armMotor2.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLiftTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d", robot.armMotor1.getCurrentPosition(), robot.armMotor2.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.armMotor1.setPower(0);
            robot.armMotor2.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.armMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.armMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

    }

    // Properties for our
    public void encoderRod(double speed, double rodInches, double timeoutS) {
        int newRodTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newRodTarget = robot.leftFrontDrive.getCurrentPosition() + (int)(rodInches * rodCountsPerInch);
            robot.rodMotor.setTargetPosition(newRodTarget);

            // Turn On RUN_TO_POSITION
            robot.rodMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            opModeRuntime.reset();
            robot.rodMotor.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (opModeRuntime.seconds() < timeoutS) && (robot.rodMotor.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newRodTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d", robot.rodMotor.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.rodMotor.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.rodMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}