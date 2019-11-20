package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name = "BlueDepo", group = "EAP")
public class Bluedepo extends TeleOpSkystone {





    @Override
    public void runOpMode(){
    Init_Juan();
    Reset_Arm();
    Reset_Arm_Slide();
    waitForStart();


        encoderDrive(1,20,20,20);

        sleep(100);

        Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,-720,-.1,1.8);

        sleep(100);

        encoderDrive(.5,-5,-5,20);










    }


}
