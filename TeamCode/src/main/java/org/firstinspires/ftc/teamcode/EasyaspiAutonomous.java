package org.firstinspires.ftc.teamcode;


import android.os.Bundle;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous
public class EasyaspiAutonomous extends All_Knowning_WaterSheep {
    Thread Antitilt = new AntiTiltThread();








    @Override
    public void runOpMode () throws InterruptedException {
    Antitilt.start();


    }
    /*public static void Camera(){
        InternalCameraExample c = new InternalCameraExample();
        SkystoneLocation location = c.FindSkyStone();
        if (location == SkystoneLocation.right) {
            s0 = InternalCameraExample.RED;
        } else if (InternalCameraExample.location == SkystoneLocation.left) {
            s2 = InternalCameraExample.RED;
        } else {
            s1 = InternalCameraExample.RED;
        }

        if (InternalCameraExample.RED == s0){

        }
        else if (InternalCameraExample.RED == s2){

        }
        else{

        }
    }*/
    InternalCameraExample Camera = new InternalCameraExample();
}

