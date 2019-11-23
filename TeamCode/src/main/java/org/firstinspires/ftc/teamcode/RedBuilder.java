package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous(name = "Red Build", group = "EAP")
public class RedBuilder extends TeleOpSkystone {





        public void runOpMode() throws InterruptedException {

            Mecanum mecanum = new Mecanum(hardwareMap.dcMotor.get("LA"),
                    hardwareMap.dcMotor.get("RA"),
                    hardwareMap.dcMotor.get("LB"),
                    hardwareMap.dcMotor.get("RB"), telemetry);
            Init_Juan();

            Reset_Arm();

            Reset_Arm_Slide();

            waitForStart();



           mecanum.SlideRightRunToPosition(-.3,-8,this);
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
            mecanum.SlideLeftRunToPosition(-.3, -31,this);
            mecanum.LeftSide_Corrections(0.3,900,this);
           /* sleep(1000);
            mecanum.LeftSide_Corrections(-.3,-450,this);
            sleep(1000);
            mecanum.MoveForwardRunToPosition(-.3,-15,this);
            mecanum.LeftSide_Corrections(.3,450,this);
            sleep(1000);
            mecanum.SlideRightRunToPosition(-.3,-6,this);

            mecanum.MoveForwardRunToPosition(.3, 6,this);

            */


    }
}
