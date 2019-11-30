package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class BlueSkystone extends TeleOpSkystone {
    public enum AutonomousStates {
        START,
        DRIVE_TO_FIRST_BLOCK,
        PICK_UP_FIRST_BLOCK,
        DELIVERDFIRST_BLOCK,
        DRIVE_TO_SECOND_BLOCK,
        PICK_UP_SECOND_BLOCK,
        PARK



    }
    @Override
    public void runOpMode(){

        Skystonelocator camera = new Skystonelocator();
        Mecanum mecanum = new Mecanum(Part.LeftA = hardwareMap.get(DcMotor.class, "LA"),
                Part.LeftB = hardwareMap.get(DcMotor.class, "LB"),
                Part.RightA = hardwareMap.get(DcMotor.class, "RA"),
                Part.RightB = hardwareMap.get(DcMotor.class, "RB"),telemetry);

        Init_Juan();
        telemetry.addLine("Hardware Mapped");
        Reset_Arm_Slide();
        telemetry.addLine("Arm slide Reset");
        Reset_Arm();
        telemetry.addLine("Arm Reset");
        telemetry.addLine("Juan is ready");
        telemetry.addLine("Awaiting start");
        camera.start();
        waitForStart();
        camera.stop();


    }
}
