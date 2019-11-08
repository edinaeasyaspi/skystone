package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorControllerEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
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
                Arm_Height(n);
            }

            if (gamepad2.x) {
                Arm_Height(n);
                n++;

            }
            if (gamepad2.a) {

                Arm_Height(n);

            }
            while(gamepad2.dpad_down) {
                Arm_Height(n);
                n--;
            }
            while(gamepad2.dpad_up) {
                Arm_Height(n);
                n++;
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

        }
    }





