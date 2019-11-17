package org.firstinspires.ftc.teamcode;




import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


@TeleOp(name = "Zoom Zoom Time", group = "EAP")
public class TeleOpSkystone extends LinearOpMode {


JuanBody Part = new JuanBody();
    // Reset Encoders
    public void Reset_Arm_Slide() {
       Part.Tetrix_ARMSLIDE_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Part.Tetrix_ARMSLIDE_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Part.Tetrix_ARMSLIDE_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Part.Tetrix_ARMSLIDE_Motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    // Claw Angle Changer


    //extends arm

    //retract arm

    public void Latch() {
        Part.Latch.setPosition(.69);
    }

    public void UnLatch() {
        Part.Latch.setPosition(1);
    }


    public void Reset_Arm() {
        Part.AndyMark_motor_Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Part.AndyMark_motor_Lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Part.AndyMark_motor_Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Part.AndyMark_motor_Lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        Part.AndyMark_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Part.AndyMark_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Part.AndyMark_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Part.AndyMark_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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
            newLeftATarget = Part.LeftA.getCurrentPosition() + (int)(leftInches * Part.COUNTS_PER_INCH);
            newRightATarget = Part.RightA.getCurrentPosition() + (int)(rightInches * Part.COUNTS_PER_INCH);
            newLeftBTarget = Part.LeftB.getCurrentPosition() + (int)(leftInches * Part.COUNTS_PER_INCH);
            newRightBTarget = Part.RightB.getCurrentPosition() + (int)(rightInches * Part.COUNTS_PER_INCH);
            Part.LeftA.setTargetPosition(newLeftATarget);
            Part.LeftB.setTargetPosition(newLeftBTarget);
            Part.RightA.setTargetPosition(newRightATarget);
            Part.RightB.setTargetPosition(newRightBTarget);

            // Turn On RUN_TO_POSITION
            Part.LeftA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Part.LeftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Part.RightA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            Part.RightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            Part.runtime.reset();
            Part.LeftA.setPower(Math.abs(speed));
            Part.LeftB.setPower(Math.abs(speed));
            Part.RightA.setPower(Math.abs(speed));
            Part.RightB.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (Part.runtime.seconds() < timeoutS) &&
                    (Part.LeftA.isBusy() && Part.LeftB.isBusy() && Part.RightA.isBusy() && Part.RightB.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d :%7d :%7d", newLeftATarget, newLeftBTarget,newRightATarget,newRightBTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d :%7d :%7d",
                        Part.LeftA.getCurrentPosition(),
                        Part.LeftB.getCurrentPosition(),
                        Part.RightA.getCurrentPosition(),
                        Part.RightB.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            Part.LeftA.setPower(0);
            Part.LeftB.setPower(0);
            Part.RightA.setPower(0);
            Part.RightB.setPower(0);

            // Turn off RUN_TO_POSITION
            Part.LeftA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Part.LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Part.RightA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Part.RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            //  sleep(250);   // optional pause after each move
        }
    }
    public void Magnetic_Limitswtiches() {

        while (Part.ARM_SLID_CHECK_Front.getState() == true) {
            Part.Tetrix_ARMSLIDE_Motor.setPower(-0.3);
        }
        while (Part.ARM_SLID_CHECK_Back.getState() == true) {
            Part.Tetrix_ARMSLIDE_Motor.setPower(0.3);
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
                Part.currentTilt = Part.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).thirdAngle;

                if ((lastTilt <= -7) & (Part.currentTilt > -7)) {
                    Part.tilted = false;
                    Part.LeftA.setPower(0);
                    Part.LeftB.setPower(0);
                    Part.RightA.setPower(0);
                    Part.RightB.setPower(0);
                }
                if (Part.currentTilt <= -7) {
                    Part.tilted = true;
                    Part.LeftB.setPower(0.5);
                    Part.RightB.setPower(0.5);
                }

                lastTilt = Part.currentTilt;

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

         double fl = speed * Math.sin(direction + Math.PI / 4.0) + rotation;
         double fr = speed * Math.cos(direction + Math.PI / 4.0) - rotation;
         double bl = speed * Math.cos(direction + Math.PI / 4.0) + rotation;
         double br = speed * Math.sin(direction + Math.PI / 4.0) - rotation;

         fl *= .5;
        fr *= .5;
        br *= .5;
        bl *= .5;
        Part.LeftA.setPower(fl);
        Part.RightA.setPower(fr);
        Part.LeftB.setPower(br);
        Part.RightB.setPower(bl);
    }

    public void Init_Juan() {
//Drive
        Part.LeftA = hardwareMap.get(DcMotor.class, "LA");
        Part.LeftB = hardwareMap.get(DcMotor.class, "LB");
        Part.RightA = hardwareMap.get(DcMotor.class, "RA");
        Part.RightB = hardwareMap.get(DcMotor.class, "RB");
//Arm
        Part.AndyMark_motor_Lift = hardwareMap.get(DcMotor.class, "AME");
        Part.AndyMark_motor = hardwareMap.get(DcMotor.class, "AAM");
        Part.Tetrix_ARMSLIDE_Motor = hardwareMap.get(DcMotor.class, "AS");
        Part.Rotating_servo = hardwareMap.get(Servo.class, "RS");
        Part.Latch = hardwareMap.get(Servo.class, "L");
//Arm reed switches
        Part.ARM_SLID_CHECK_Front = hardwareMap.get(DigitalChannel.class, "ASC-F");
        Part.ARM_SLID_CHECK_Back = hardwareMap.get(DigitalChannel.class, "ASC-B");
        Part.Elbow_Check_Up = hardwareMap.get(DigitalChannel.class, "EBC-U");
        Part.Elbow_check_Down = hardwareMap.get(DigitalChannel.class, "EBC-D");

        Part.ARM_SLID_CHECK_Front.setMode(DigitalChannel.Mode.INPUT);
        Part.ARM_SLID_CHECK_Back.setMode(DigitalChannel.Mode.INPUT);
        Part.Elbow_Check_Up.setMode(DigitalChannel.Mode.INPUT);
        Part.Elbow_check_Down.setMode(DigitalChannel.Mode.INPUT);

        Part.LeftA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Part.LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Part.RightA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Part.RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Part.RightA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Part.RightB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Part.LeftA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Part.LeftB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        Part.LeftA.setDirection(DcMotor.Direction.REVERSE);
        Part.LeftB.setDirection(DcMotor.Direction.REVERSE);


        UnLatch();


    }


    protected void Move_Motor_WithEncoder(DcMotor Motor, int TargetPos , double speed ,int timeout ) {
        Motor.setTargetPosition(TargetPos);
        Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Motor.setPower(speed);

        while((Motor.isBusy())&& (Part.runtime.seconds() < timeout)){
            telemetry.addData("Motor Postion", "Position at %7d", Motor.getCurrentPosition());
        }
        Motor.setPower(0);
        Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    public void ArmCrap (float ArmPow, float ElPow){
        ElPow = ElPow * 0.1f;

        while((Part.AndyMark_motor_Lift.getCurrentPosition()< 1000) &&(ElPow > 0)){
            Part.AndyMark_motor_Lift.setPower(0);
        }
        Part.AndyMark_motor_Lift.setPower(ElPow);
        Part.AndyMark_motor.setPower(-ArmPow);
    }

    @Override
    public void runOpMode() throws InterruptedException {

        Init_Juan();
        telemetry.addLine("Hardware Mapped");
        Reset_Arm_Slide();
        telemetry.addLine("Arm slide Reset");
        Reset_Arm();
        telemetry.addLine("Arm Reset");
        telemetry.addLine("Juan is ready");
        telemetry.addLine("Awaiting start");

        waitForStart();


//Drive chain
        while (opModeIsActive()) {



            Drive(gamepad1.left_stick_x, gamepad1.left_stick_y , gamepad1.right_stick_x);


            ArmCrap(gamepad2.left_stick_y,-gamepad2.right_stick_y);

            //Arm Elbow
//hell


            //Arm
            if (gamepad2.start) {

            }
            if (gamepad2.right_bumper) {
                Latch();
            }
            if (gamepad2.left_bumper) {
                UnLatch();
            }
            if (gamepad2.dpad_left) {
                Part.Rotating_servo.setPosition(.5);
            }
            if (gamepad2.dpad_right){
            Part.Rotating_servo.setPosition(1);
            }

            if (gamepad2.a) {
                Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,500,.1,4);
                Part.Rotating_servo.setPosition(1);

            }





            idle();
        }


        }








}





