package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;




@TeleOp(name = "Zoom Zoom Time", group = "EAP")
public class TeleOpSkystone extends All_Knowning_WaterSheep{
int n = 0;
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

            }

            if (gamepad2.x) {
               Grabskystone();

            }
            if (gamepad2.a) {



            }
            while(gamepad2.dpad_down) {

            }
            while(gamepad2.dpad_up) {

            }

            idle();
        }



    }


    public void Grabskystone (){

            AndyMark_motor_elbow.setTargetPosition(560);
            AndyMark_motor_elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            AndyMark_motor_elbow.setPower(1);

            while (AndyMark_motor_elbow.isBusy()) {

            }
            AndyMark_motor_elbow.setPower(0);
    }


    public void Arm (){
         final double rotation = Math.pow(+gamepad2.left_stick_y,3.0);
         final double rotation2 = Math.pow(-gamepad2.left_stick_y,3.0);
         final double Fl2 = n + rotation;
         final double Fl1 = n - rotation2;
         AndyMark_motor.setPower(Fl2);
         AndyMark_motor.setPower(Fl1);



        }
    }





