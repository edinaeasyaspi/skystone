package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
//test
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Easy As Pi", group = "EAP")

public class AutonomousSkystone extends OpModeIMU {
    private boolean phoneInLandscape = true;

    @Override
    public void runOpMode() {
        String dockLocation = "Skystone";
        boolean sample = true;
        boolean claim = true;
        boolean park = true;
        String parkLocation = "Red";
        int sleepTimer = 0;
        String endlocation = "Righttape";
        boolean dpad_down = false;
        boolean dpad_up = false;
        boolean dpad_left = false;
        boolean dpad_right = false;

        String position = "";
        String lastPosition = "";
        int numberOfSampleCheck = 0;

        antiTiltThread.start();

        initRobot();
        telemetry.addLine("Mapped hardware");


        initArm();
        telemetry.addLine("Arm set to home");

        resetArmEncoders();
        telemetry.addLine("Arm encoders reset");

        //enableMineralDetector();
        telemetry.addLine("Gold detector enabled");

        telemetry.update();

        while ((!opModeIsActive()) & (!isStopRequested())) {
            if (gamepad1.x) {
                dockLocation = "Skystone";
            } else if (gamepad1.b) {
                dockLocation = "buildingsite";
            }
             else if (gamepad1.dpad_left) {
                parkLocation = "Red";
            } else if (gamepad1.dpad_right) {
                    parkLocation = "Blue";
                }
             else if (gamepad1.y) {
                 endlocation = "Righttape";
            }
             else if (gamepad1.a) {
                 endlocation = "Lefttape";
            }
            }


            if ((gamepad2.dpad_left == true) && (dpad_left == false)) {

                if (sleepTimer > 0) {
                    sleepTimer = sleepTimer - 1000;
                }
            } else if ((gamepad2.dpad_right == true) && (dpad_right == false)) {


                sleepTimer = sleepTimer + 1000;
            }

        }

    }


