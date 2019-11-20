package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "Blue Builder", group = "EAP")
public class BlueBuilder extends TeleOpSkystone {

    protected LinearOpMode This ;
    protected Mecanum Mecanum;

        public void runOpMode() throws InterruptedException {
            Init_Juan();

            Reset_Arm();

            Reset_Arm_Slide();

            waitForStart();

            Mecanum.MoveForwardRunToPosition(1,16, This );

    }
}
