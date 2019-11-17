package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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
