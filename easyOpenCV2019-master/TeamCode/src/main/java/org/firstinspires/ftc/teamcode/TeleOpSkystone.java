package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name = "Zoom Zoom Time", group = "EAP")
public class TeleOpSkystone extends LinearOpMode {

    private DcMotor LeftA;
    private DcMotor LeftB;
    private DcMotor RightA;
    private DcMotor RightB;
    private DcMotor armMotor;
    private DcMotor Tetrix_ARMSLIDE_Motor;
    private Servo Rotating_servo;
    private Servo Latch;
    private Servo Up_and_down;
    private DigitalChannel armSwitch1;
    private DigitalChannel armSwitch2;


    protected ElapsedTime runtime = new ElapsedTime();
    protected static final int Andmark_MAX_REV = 1120;
    protected static final double COUNTS_PER_MOTOR_REV = 56;
    protected static final int ARM_MAX = 1900;
    protected static final int ARM_SLIDE_MAX = 600;



    @Override
    public void runOpMode () throws InterruptedException{
//Drive
        LeftA = hardwareMap.dcMotor.get("LeftA");
        LeftB = hardwareMap.dcMotor.get("LeftB");
        RightA = hardwareMap.dcMotor.get("RightA");
        RightB = hardwareMap.dcMotor.get("RightB");

        RightA.setDirection(DcMotorSimple.Direction.REVERSE);
        RightB.setDirection(DcMotorSimple.Direction.REVERSE);
//Arm
        armMotor = hardwareMap.dcMotor.get("AndyMark_Arm_motor");
        Tetrix_ARMSLIDE_Motor = hardwareMap.dcMotor.get("ARMSLIDE");
        Rotating_servo = hardwareMap.servo.get("rotating_servo");
        Up_and_down = hardwareMap.servo.get("Up and down");
        Latch = hardwareMap.servo.get("Latch servo");
        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Reed switches code we can use
        armSwitch1 = hardwareMap.get(DigitalChannel.class, "armSwitch1");
        armSwitch2 = hardwareMap.get(DigitalChannel.class, "armSwitch2");

        armSwitch1.setMode(DigitalChannel.Mode.INPUT);
        armSwitch2.setMode(DigitalChannel.Mode.INPUT);



        waitForStart();


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









            armMotor.setPower(gamepad2.right_stick_y);
            if (gamepad1.a == true) {
                Extender();

            }
            if  (gamepad1.b)  {
                Retract();

            }


            idle();
        }




    }

    public void Extender(){
        while ((opModeIsActive()) && (armSwitch2.getState() == true)) {
            armMotor.setPower(3);}
    }
    public void Retract(){
        armMotor.setTargetPosition(0);
    }

}






























