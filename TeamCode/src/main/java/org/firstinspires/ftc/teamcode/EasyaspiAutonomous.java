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
    InternalCameraExample Camera = new InternalCameraExample();
}

