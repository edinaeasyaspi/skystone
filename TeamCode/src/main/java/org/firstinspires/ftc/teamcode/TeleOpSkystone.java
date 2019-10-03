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

    private DcMotor LeftA;
    private DcMotor LeftB;
    private DcMotor RightA;
    private DcMotor RightB;


    @Override
    public void runOpMode () throws InterruptedException{

        LeftA = hardwareMap.dcMotor.get("LeftA");
        LeftB = hardwareMap.dcMotor.get("LeftB");
        RightA = hardwareMap.dcMotor.get("RightA");
        RightB = hardwareMap.dcMotor.get("RightB");



        RightA.setDirection(DcMotorSimple.Direction.REVERSE);
        RightB.setDirection(DcMotorSimple.Direction.REVERSE);


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
//rotation



            idle();
        }

    }

}





























