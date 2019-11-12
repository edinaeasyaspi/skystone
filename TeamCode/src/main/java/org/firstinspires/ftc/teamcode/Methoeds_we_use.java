package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

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


        while (AndyMark_motor_elbow.getCurrentPosition() == 520){
            AndyMark_motor.setPower(Power);


        }


    }
}
