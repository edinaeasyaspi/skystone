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

            mecanum.MoveForwardRunToPosition(-.3,-7, this );

            mecanum.move_arm_down(.3,600, this);

            mecanum.MoveForwardRunToPosition(.3,7, this );

            mecanum.move_arm_down(-.3,-600,this);

            mecanum.SlideLeftRunToPosition(.3, 6,this);

            mecanum.MoveForwardRunToPosition(.3,15,this);

            mecanum.SlideRightRunToPosition(.3,6,this);

            mecanum.MoveForwardRunToPosition(.3, 6,this);

        }
}
