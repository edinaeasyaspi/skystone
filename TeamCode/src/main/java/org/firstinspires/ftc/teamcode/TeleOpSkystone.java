package org.firstinspires.ftc.teamcode;




import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


@TeleOp(name = "Zoom Zoom Time", group = "EAP")
public class TeleOpSkystone extends LinearOpMode {

Methods_we_use Methods = new Methods_we_use();
JuanBody Part = new JuanBody();

    @Override
    public void runOpMode() throws InterruptedException {

        Methods.Init_Juan();
        telemetry.addLine("Hardware Mapped");
        Methods.Reset_Arm_Slide();
        telemetry.addLine("Arm slide Reset");
        Methods.Reset_Arm();
        telemetry.addLine("Arm Reset");
        telemetry.addLine("Juan is ready");
        telemetry.addLine("Awaiting start");

        waitForStart();


//Drive chain
        while (opModeIsActive()) {



            Methods.Drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

            Methods.arm(gamepad2.left_stick_y);

            Methods.Extender(gamepad2.left_stick_x);
            Methods.Magnetic_Limitswtiches();
            //Arm Elbow



            //Arm
            if (gamepad2.start) {

            }
            if (gamepad2.right_bumper) {
                Methods.Latch();
            }
            if (gamepad2.left_bumper) {
                Methods.UnLatch();
            }
            if (gamepad2.dpad_down) {

            }

            if (gamepad2.x) {
                Methods.Move_Motor_WithEncoder(Part.LeftA,400,.25,4);


            }
            if (gamepad2.a) {


            }





            idle();
        }


        }








}





