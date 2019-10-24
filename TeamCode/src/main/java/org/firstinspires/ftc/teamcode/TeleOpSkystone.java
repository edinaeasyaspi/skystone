package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


@TeleOp(name = "Zoom Zoom Time", group = "EAP")
public class TeleOpSkystone extends LinearOpMode {
    //DRIVE
    public DcMotor LeftA;
    public DcMotor LeftB;
    public DcMotor RightA;
    public DcMotor RightB;
    //ARM
    public DcMotor AndyMark_motor_elbow;
    public DcMotor AndyMark_motor;
    public DcMotor Tetrix_ARMSLIDE_Motor;

    //end effector
    public Servo Rotating_servo;
    public Servo Latch;
    public Servo Up_and_down;
    boolean bool = false;
    // reed switch
    public DigitalChannel ARM_SLID_CHECK_Front;
    public DigitalChannel ARM_SLID_CHECK_Back;
    public DigitalChannel Elbow_Check_Up;
    public DigitalChannel Elbow_check_Down;
    //encoders
    protected ElapsedTime runtime = new ElapsedTime();
    protected static final int Andmark_MAX_REV = 1120;
    protected static final double COUNTS_PER_MOTOR_REV = 56;
    protected static final int ARM_MAX = 1900;
    protected static final int ARM_SLIDE_MAX = 600;
    protected static final double ARM_SLIDE_SPEED = 1;
    protected  static final byte MAX_Power = 1 ;

    protected static final double DRIVE_SPEED_PRECISE = 0.6;
    protected static final double TURN_SPEED_PRECISE = 0.4;


    BNO055IMU imu;

    Orientation angles;
    Acceleration gravity;

    double currentTilt;
    boolean tilted;

    Orientation lastAngles = new Orientation();
    double globalAngle, power = .30, correction;

    private class AntiTiltIMU extends Thread {



    }

    // Reset Encoders
    public void Reset_Arm_Slide(){
        Tetrix_ARMSLIDE_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Tetrix_ARMSLIDE_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Tetrix_ARMSLIDE_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    //Extends Motor
    private void Motor(){
        AndyMark_motor.setTargetPosition(300);
    }
    //extends elbow
    private void ExtendElbow(){
        Tetrix_ARMSLIDE_Motor.setTargetPosition(300);
    }
    //retracts Motor
    public void RetractMotor() {
        AndyMark_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        AndyMark_motor.setTargetPosition(0);
    }
    //retracts elbow
    public void RetractElbow() {
        AndyMark_motor_elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        AndyMark_motor_elbow.setTargetPosition(0);
    }

    //extends arm
    public void Extender(){

        Tetrix_ARMSLIDE_Motor.setTargetPosition(300);
    }
    //retract arm
    public void Retract() {
        Tetrix_ARMSLIDE_Motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Tetrix_ARMSLIDE_Motor.setTargetPosition(0);
    }
    //Raise arm
    public void Raise_ARM(){
        AndyMark_motor.setTargetPosition(300);
    }
    public void elbow () {

        AndyMark_motor_elbow.setTargetPosition(300);
    }
    public void Latch (){
        Latch.setPosition(.5);
    }
    public void UnLatch () {
        Latch.setPosition(0);
    }

    public void Init_Juan () {
        //Drive
        LeftA = hardwareMap.get(DcMotor.class,"LA");
        LeftB =  hardwareMap.get(DcMotor.class,"LB");
        RightA = hardwareMap.get(DcMotor.class,"RA");
        RightB =  hardwareMap.get(DcMotor.class,"RB");

        RightA.setDirection(DcMotorSimple.Direction.REVERSE);
        RightB.setDirection(DcMotorSimple.Direction.REVERSE);
//Arm
        AndyMark_motor = hardwareMap.get(DcMotor.class,"AndyMark_Arm_motor");
        Tetrix_ARMSLIDE_Motor = hardwareMap.get(DcMotor.class,"ARMSLIDE");
        Rotating_servo = hardwareMap.get(Servo.class , "RS");
        Up_and_down =  hardwareMap.get(Servo.class , "U-D");
        Latch =  hardwareMap.get(Servo.class , "L");
//Arm reed switches
        ARM_SLID_CHECK_Front= hardwareMap.get(DigitalChannel.class, "ASC-F");
        ARM_SLID_CHECK_Back = hardwareMap.get(DigitalChannel.class, "ASC-B");
        Elbow_Check_Up = hardwareMap.get(DigitalChannel.class, "EBC-U");
        Elbow_check_Down = hardwareMap.get(DigitalChannel.class, "EBC-U");

        ARM_SLID_CHECK_Front.setMode(DigitalChannel.Mode.INPUT);
        ARM_SLID_CHECK_Back.setMode(DigitalChannel.Mode.INPUT);
        Elbow_Check_Up.setMode(DigitalChannel.Mode.INPUT);
        Elbow_check_Down.setMode(DigitalChannel.Mode.INPUT);

        UnLatch();



    }
    public void Reset_Arm () {
        AndyMark_motor_elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        AndyMark_motor_elbow.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        AndyMark_motor_elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        AndyMark_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        AndyMark_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        AndyMark_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void Magnetic_Limitswtiches () {

        while (ARM_SLID_CHECK_Front.getState() == true){
            Tetrix_ARMSLIDE_Motor.setPower(-0.3);
        }
        while (ARM_SLID_CHECK_Back.getState() == true){
            Tetrix_ARMSLIDE_Motor.setPower(0.3);
        }
        while (Elbow_check_Down.getState() == true) {

        }
    }
    private class AntiTiltThread extends Thread {
        public AntiTiltThread() {
            this.setName("Anti Tilt Thread");
        }

        // called when tread.start is called. thread stays in loop to do what it does until exit is
        // signaled by main code calling thread.interrupt.
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





    @Override
    public void runOpMode () throws InterruptedException{

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
        while(opModeIsActive()){
            double r = Math.hypot(-gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, -gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = -gamepad1.right_stick_x;
            final double v1 = r * Math.cos(robotAngle) + rightX;
            final double v2 = r * Math.sin(robotAngle) - rightX;
            final double v3 = r * Math.sin(robotAngle) + rightX;
            final double v4 = r * Math.cos(robotAngle) - rightX;



            LeftA.setPower(v2);
            LeftB.setPower(v4);
            RightA.setPower(v1);
            RightB.setPower(v3);

            //Arm Elbow


            //Arm



            Tetrix_ARMSLIDE_Motor.setPower(gamepad2.right_stick_y);
            if (gamepad2.a ) {
                Extender();

            }
            if  (gamepad2.b)  {
                Retract();

            }
            if (gamepad2.right_bumper) {
                Latch();
            }
            if (gamepad2.left_bumper) {
                UnLatch();
            }
            if (gamepad2.start){

            }
            if (gamepad2.x){
                ExtendElbow();
            }
            if (gamepad2.y){
                RetractElbow();
            }
            if (gamepad2.dpad_down){
                RetractMotor();
            }
            if (gamepad2.dpad_up){
                Motor();
            }





            idle();
        }





    }


}



