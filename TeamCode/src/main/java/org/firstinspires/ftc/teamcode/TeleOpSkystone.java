package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name = "Zoom Zoom Time", group = "EAP")
public class TeleOpSkystone extends LinearOpMode {
    //DRIVE
    private DcMotor LeftA;
    private DcMotor LeftB;
    private DcMotor RightA;
    private DcMotor RightB;
    //ARM
    private DcMotor AndyMark_motor_elbow;
    private DcMotor AndyMark_motor;
    private DcMotor Tetrix_ARMSLIDE_Motor;
    //end effector
    private Servo Rotating_servo;
    private Servo Latch;
    private Servo Up_and_down;
    boolean bool = false;
    // reed switch
    private DigitalChannel ARM_SLID_CHECK;
    private DigitalChannel elbow_check;
    //encoders
    protected ElapsedTime runtime = new ElapsedTime();
    protected static final int Andmark_MAX_REV = 1120;
    protected static final double COUNTS_PER_MOTOR_REV = 56;
    protected static final int ARM_MAX = 1900;
    protected static final int ARM_SLIDE_MAX = 600;
    protected static final double ARM_SLIDE_SPEED = 1;
    // Reset Encoders
    private void Reset_Arm_Slide(){
        Tetrix_ARMSLIDE_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Tetrix_ARMSLIDE_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Tetrix_ARMSLIDE_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    //extends arm
    private void Extender(){

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
        Latch.setPosition(90);
    }
    public void UnLatch () {
        Latch.setPosition(0);
    }

    public void Init_Juan () {
        //Drive
        LeftA = hardwareMap.dcMotor.get("LeftA");
        LeftB = hardwareMap.dcMotor.get("LeftB");
        RightA = hardwareMap.dcMotor.get("RightA");
        RightB = hardwareMap.dcMotor.get("RightB");

        RightA.setDirection(DcMotorSimple.Direction.REVERSE);
        RightB.setDirection(DcMotorSimple.Direction.REVERSE);
//Arm
        AndyMark_motor = hardwareMap.dcMotor.get("AndyMark_Arm_motor");
        Tetrix_ARMSLIDE_Motor = hardwareMap.dcMotor.get("ARMSLIDE");
        Rotating_servo = hardwareMap.servo.get("rotating_servo");
        Up_and_down = hardwareMap.servo.get("Up and down");
        Latch = hardwareMap.servo.get("Latch servo");
//Arm reed switches
        ARM_SLID_CHECK= hardwareMap.get(DigitalChannel.class, "ARM_SlID_CHECK");
        elbow_check = hardwareMap.get(DigitalChannel.class, "ELBOW CHECK");

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

            //Arm

            while (ARM_SLID_CHECK.getState() == true){
                Tetrix_ARMSLIDE_Motor.setPower(0);
            }

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




            idle();
        }





    }


}


























