package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name = "BlueDepo", group = "EAP")
public class Bluedepo extends TeleOpSkystone {

    SkyStoneAutaunomousBlueDep Camera;



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

    mecanum.SlideRightRunToPosition(.3,26,this);













    }


}
