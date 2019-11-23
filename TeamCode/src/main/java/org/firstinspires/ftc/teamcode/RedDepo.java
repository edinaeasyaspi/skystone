package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "RedDepo", group = "EAP")
public class RedDepo extends  TeleOpSkystone {






    @Override
    public void runOpMode(){

        Mecanum mecanum = new Mecanum(hardwareMap.dcMotor.get("LA"),
                hardwareMap.dcMotor.get("RA"),
                hardwareMap.dcMotor.get("LB"),
                hardwareMap.dcMotor.get("RB"), telemetry);

        Init_Juan();
        Reset_Arm();
        Reset_Arm_Slide();
        waitForStart();

        mecanum.SlideRightRunToPosition(-.3,-5,this);













    }


}
