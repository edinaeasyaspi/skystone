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

            mecanum.MoveForwardRunToPosition(-.3,-16, this );
            sleep(100);

            //move arm.
            Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,-600,-.3,10);

            mecanum.MoveForwardRunToPosition(.3,16, this );
            sleep(100);
            mecanum.SlideLeftRunToPosition(-.3, -8,this);
            sleep(100);
            mecanum.MoveForwardRunToPosition(.3,15,this);

            mecanum.SlideRightRunToPosition(-.3,-6,this);

            mecanum.MoveForwardRunToPosition(.3, 6,this);
    }
}
