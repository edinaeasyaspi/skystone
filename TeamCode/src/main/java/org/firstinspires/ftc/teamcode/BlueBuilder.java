package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@Autonomous(name = "Blue Builder", group = "EAP")
public class BlueBuilder extends TeleOpSkystone {


        public void runOpMode() throws InterruptedException {
            Mecanum mecanum = new Mecanum(hardwareMap.dcMotor.get("LA"),
                    hardwareMap.dcMotor.get("RA"),
                    hardwareMap.dcMotor.get("LB"),
                    hardwareMap.dcMotor.get("RB"), telemetry);
            Init_Juan();

            Reset_Arm();

            Reset_Arm_Slide();

            waitForStart();
            //Drive to lander
            mecanum.SlideRightRunToPosition(.3,8,this);
            sleep(1000);
            mecanum.LeftSide_Corrections(-.3,-450,this);
            sleep(1000);
            mecanum.MoveForwardRunToPosition(-.3,-10, this );
            sleep(1000);
            mecanum.LeftSide_Corrections(-.3,-450,this);
            sleep(1000);
            Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,-600,-1,15);
            sleep(500);
            mecanum.MoveForwardRunToPosition(.3,14, this );
            sleep(1000);
            mecanum.LeftSide_Corrections(0.3,450,this);
            sleep(1000);
            Part.AndyMark_motor_Lift.setPower(.3);
            sleep(1000);
            mecanum.SlideLeftRunToPosition(.3, 31,this);
            mecanum.LeftSide_Corrections(0.3,900,this);
        }
}
