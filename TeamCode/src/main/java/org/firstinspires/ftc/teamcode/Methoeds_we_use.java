package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class Methoeds_we_use extends JuanBody {
    public void Brake() {
        float x = -gamepad1.right_stick_x;
        float y = gamepad1.left_stick_y;
        boolean preciseDrive = false;

        if ((gamepad2.left_stick_x != 0) || (gamepad2.left_stick_y != 0)) {
            x = -gamepad2.left_stick_x;
            y = gamepad2.left_stick_y;
            preciseDrive = true;
        }

        if (y == 0) {
            if (lastSpeed != 0) {
                LeftA.setPower(0);
                LeftB.setPower(0);
                RightA.setPower(0);
                RightB.setPower(0);
                sleep(300);
            }
            driveStarted = 0;
        } else if (((lastSpeed > 0) && (y < 0)) || ((lastSpeed < 0) && (y > 0))) {
            LeftA.setPower(0);
            RightA.setPower(0);
            LeftB.setPower(0);
            RightB.setPower(0);
            sleep(300);
            driveStarted = 0;
        } else {
            driveStarted++;
        }

        while (gamepad2.dpad_down) {

        }

    }

    // Reset Encoders
    public void Reset_Arm_Slide() {
        Tetrix_ARMSLIDE_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Tetrix_ARMSLIDE_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Tetrix_ARMSLIDE_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Tetrix_ARMSLIDE_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    // Claw Angle Changer
    public void ClawAngle(){
        Up_and_down.setPosition(AndyMark_motor_elbow.getCurrentPosition()/servo_to_elbow_ratio);
    }
    //Extends Motor
  /*  private void Motor(){
        AndyMark_motor.setTargetPosition(300);
   *///}
    //extends elbow
  /*  private void ExtendElbow(){
        Tetrix_ARMSLIDE_Motor.setTargetPosition(300);
    }*/
    //retracts Motor
    public void RetractMotor() {


    }
    //retracts elbow


    //extends arm
    public void Extender(double setPos) {
        Tetrix_ARMSLIDE_Motor.setPower(setPos);

    }
    //retract arm

    public void Latch() {
        Latch.setPosition(.69);
    }

    public void UnLatch() {
        Latch.setPosition(1);
    }


    public void Reset_Arm() {
        AndyMark_motor_elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        AndyMark_motor_elbow.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        AndyMark_motor_elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        AndyMark_motor_elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        AndyMark_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        AndyMark_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        AndyMark_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        AndyMark_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
    protected  void encoderDrive(double speed,
                            double leftInches, double rightInches,
                            double timeoutS) {
        int newLeftBTarget;
        int newLeftATarget;
        int  newRightATarget;
        int  newRightBTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftATarget = LeftA.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightATarget = RightA.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftBTarget = LeftB.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightBTarget = RightB.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            LeftA.setTargetPosition(newLeftATarget);
            LeftB.setTargetPosition(newLeftBTarget);
            RightA.setTargetPosition(newRightATarget);
            RightB.setTargetPosition(newRightBTarget);

            // Turn On RUN_TO_POSITION
            LeftA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LeftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            LeftA.setPower(Math.abs(speed));
            LeftB.setPower(Math.abs(speed));
            RightA.setPower(Math.abs(speed));
            RightB.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (LeftA.isBusy() && LeftB.isBusy() && RightA.isBusy() && RightB.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d :%7d :%7d", newLeftATarget, newLeftBTarget,newRightATarget,newRightBTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d :%7d :%7d",
                        LeftA.getCurrentPosition(),
                        LeftB.getCurrentPosition(),
                        RightA.getCurrentPosition(),
                        RightB.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            LeftA.setPower(0);
            LeftB.setPower(0);
            RightA.setPower(0);
            RightB.setPower(0);

            // Turn off RUN_TO_POSITION
           LeftA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
           LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
           RightA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
           RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            //  sleep(250);   // optional pause after each move
        }
    }
    public void Magnetic_Limitswtiches() {

        while (ARM_SLID_CHECK_Front.getState() == true) {
            Tetrix_ARMSLIDE_Motor.setPower(-0.3);
        }
        while (ARM_SLID_CHECK_Back.getState() == true) {
            Tetrix_ARMSLIDE_Motor.setPower(0.3);
        }
        while (Elbow_check_Down.getState() == true) {
            AndyMark_motor_elbow.setPower(0);
        }
    }

    public class AntiTiltThread extends Thread {
        public AntiTiltThread() {
            this.setName("Anti Tilt Thread");
        }


        @Override
        public void run() {
            double lastTilt = 0;
            while (!isInterrupted()) {
                currentTilt = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).thirdAngle;

                if ((lastTilt <= -7) & (currentTilt > -7)) {
                    tilted = false;
                    LeftA.setPower(0);
                    LeftB.setPower(0);
                    RightA.setPower(0);
                    RightB.setPower(0);
                }
                if (currentTilt <= -7) {
                    tilted = true;
                    LeftB.setPower(0.5);
                    RightB.setPower(0.5);
                }

                lastTilt = currentTilt;

                idle();
            }

        }


    }


    public void Drive(double leftStickX, double leftStickY, double rightStickY) {
        final double x = Math.pow(-leftStickX, 3.0);
        final double y = Math.pow(leftStickY, 3.0);

        final double rotation = Math.pow(-rightStickY, 3.0);
        final double direction = Math.atan2(x, y);
        final double speed = Math.min(1.0, Math.sqrt(x * x + y * y));

        final double fl = speed * Math.sin(direction + Math.PI / 4.0) + rotation;
        final double fr = speed * Math.cos(direction + Math.PI / 4.0) - rotation;
        final double bl = speed * Math.cos(direction + Math.PI / 4.0) + rotation;
        final double br = speed * Math.sin(direction + Math.PI / 4.0) - rotation;

        LeftA.setPower(fl);
        RightA.setPower(fr);
        LeftB.setPower(br);
        RightB.setPower(bl);
    }

    public void Init_Juan() {
//Drive
        LeftA = hardwareMap.get(DcMotor.class, "LA");
        LeftB = hardwareMap.get(DcMotor.class, "LB");
        RightA = hardwareMap.get(DcMotor.class, "RA");
        RightB = hardwareMap.get(DcMotor.class, "RB");
//Arm
        AndyMark_motor_elbow = hardwareMap.get(DcMotor.class, "AME");
        AndyMark_motor = hardwareMap.get(DcMotor.class, "AAM");
        Tetrix_ARMSLIDE_Motor = hardwareMap.get(DcMotor.class, "AS");
        Rotating_servo = hardwareMap.get(Servo.class, "RS");
        Up_and_down = hardwareMap.get(Servo.class, "U-D");
        Latch = hardwareMap.get(Servo.class, "L");
//Arm reed switches
        ARM_SLID_CHECK_Front = hardwareMap.get(DigitalChannel.class, "ASC-F");
        ARM_SLID_CHECK_Back = hardwareMap.get(DigitalChannel.class, "ASC-B");
        Elbow_Check_Up = hardwareMap.get(DigitalChannel.class, "EBC-U");
        Elbow_check_Down = hardwareMap.get(DigitalChannel.class, "EBC-D");

        ARM_SLID_CHECK_Front.setMode(DigitalChannel.Mode.INPUT);
        ARM_SLID_CHECK_Back.setMode(DigitalChannel.Mode.INPUT);
        Elbow_Check_Up.setMode(DigitalChannel.Mode.INPUT);
        Elbow_check_Down.setMode(DigitalChannel.Mode.INPUT);

        LeftA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        RightA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        LeftA.setDirection(DcMotor.Direction.REVERSE);
        LeftB.setDirection(DcMotor.Direction.REVERSE);


        UnLatch();


    }
    protected void arm (double Power ){

            AndyMark_motor_elbow.setPower(Power);


        while (AndyMark_motor_elbow.getCurrentPosition() == 520 + 400 ){
            AndyMark_motor.setPower(Power);


        }


    }
    protected void PickupSkystone (int timeoutS) {
        AndyMark_motor_elbow.setTargetPosition(400);
        AndyMark_motor_elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        AndyMark_motor_elbow.setPower(MAX_SPEED);

        while ((AndyMark_motor_elbow.isBusy()) && (runtime.seconds() < timeoutS)){
            telemetry.addData("Arm Pos.", "Postion at %7d",AndyMark_motor_elbow.getCurrentPosition());

        }
        AndyMark_motor_elbow.setPower(0);
        AndyMark_motor_elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    protected void Move_Motor_WithEncoder(DcMotor Motor, int TargetPos , double speed ,int timeout ) {
        Motor.setTargetPosition(TargetPos);
        Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Motor.setPower(speed);

        while((Motor.isBusy())&& (runtime.seconds() < timeout)){
            telemetry.addData("Motor Postion", "Position at %7d", Motor.getCurrentPosition());
        }
        Motor.setPower(0);
        Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
}
