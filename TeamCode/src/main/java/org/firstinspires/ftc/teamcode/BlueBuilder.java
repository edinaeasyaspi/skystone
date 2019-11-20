package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@Autonomous(name = "Blue Builder", group = "EAP")
public class BlueBuilder extends TeleOpSkystone {

    protected LinearOpMode This ;
    protected Mecanum Mecanum = new Mecanum(hardwareMap.dcMotor.get("LA"),
   hardwareMap.dcMotor.get("RA"),
     hardwareMap.dcMotor.get("LB"),
     hardwareMap.dcMotor.get("RB"), telemetry);

        public void runOpMode() throws InterruptedException {
            Init_Juan();

            Reset_Arm();

            Reset_Arm_Slide();

            waitForStart();

            Mecanum.MoveForwardRunToPosition(1,16, This );

    }
}
