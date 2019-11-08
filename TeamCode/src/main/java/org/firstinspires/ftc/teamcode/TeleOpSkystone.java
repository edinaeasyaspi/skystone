package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "Zoom Zoom Time", group = "EAP")
public class TeleOpSkystone extends AllKnowing {


    @Override
    public void runOpMode () throws InterruptedException {

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


            Drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            //   LeftA.setPower(gamepad1.left_stick_y);
            //   LeftB.setPower(gamepad1.right_stick_y);
            //   RightA.setPower(gamepad2.right_stick_y);
            //   RightB.setPower(gamepad2.left_stick_y);


            Extender(gamepad2.left_stick_x);
            AndyMark_motor.setPower(gamepad2.right_stick_x);
            //Arm Elbow

            Brake();
            //Arm



            if (gamepad2.start) {

            }
            if (gamepad2.right_bumper) {
                Latch();
            }
            if (gamepad2.left_bumper) {
                UnLatch();
            }
            if (gamepad2.dpad_down) {
                RetractMotor();
            }

            if (gamepad2.x) {
                Arm_Height(n);
                n++;

            }
            if (gamepad2.a) {
                n = 1;
                Arm_Height(n);

            }


            idle();
        }



    }


    public void Grabskystone (){

            AndyMark_motor_elbow.setTargetPosition(560);
            AndyMark_motor_elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            AndyMark_motor_elbow.setPower(.1);
    }


    public void Arm_Height (int n){

        switch (n) {
            case 1:
                Grabskystone();
            case 2:
                ArmHeight.NUM2_Lift();
            case 3:
                ArmHeight.NUM3_Lift();
            case 4:
                ArmHeight.NUM4_Lift();
            case 5:
                ArmHeight.NUM5_Lift();
            case 6:
                ArmHeight.NUM6_Lift();
            case 7:
                ArmHeight.NUM7_Lift();
            case 8:

            default:
            n = 0;
        }
    }


}//Class


