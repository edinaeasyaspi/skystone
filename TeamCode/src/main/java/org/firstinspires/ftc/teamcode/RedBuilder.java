package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous(name = "Red Build", group = "EAP")
public class RedBuilder extends TeleOpSkystone {

    JuanBody Part = new JuanBody();
    protected Mecanum Mecanum;


        public void runOpMode() throws InterruptedException {

            Init_Juan();

            Reset_Arm();

            Reset_Arm_Slide();

            waitForStart();
            while (opModeIsActive()){



            }
    }
}
