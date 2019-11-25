package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous(name = "Red Build", group = "EAP")
public class RedBuilder extends TeleOpSkystone {





        public void runOpMode() throws InterruptedException {

            Mecanum mecanum = new Mecanum(hardwareMap.dcMotor.get("LA"),
                    hardwareMap.dcMotor.get("RA"),
                    hardwareMap.dcMotor.get("LB"),
                    hardwareMap.dcMotor.get("RB"), telemetry);
            Init_Juan();

            Reset_Arm();

            Reset_Arm_Slide();

            waitForStart();



         }
         }
