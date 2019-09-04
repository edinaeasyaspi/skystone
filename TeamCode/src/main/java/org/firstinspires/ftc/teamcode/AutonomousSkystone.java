package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Easy As Pi", group = "EAP")

public class AutonomousSkystone extends OpModeIMU {
    private boolean phoneInLandscape = true;

    @Override
    public void runOpMode() {
//trying to push
        String dockLocation = "Crater";
        boolean sample = true;
        boolean claim = true;
        boolean park = true;
        String parkLocation = "Alliance";
        int sleepTimer = 0;

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
                dockLocation = "Crater";
                parkLocation = "Alliance";
            } else if (gamepad1.b) {
                dockLocation = "Depo";
            } else if (gamepad1.y) {
                sample = true;
            } else if (gamepad1.a) {
                sample = false;
            } else if (gamepad1.left_bumper) {
                claim = false;
            } else if (gamepad1.right_bumper) {
                claim = true;
            } else if (gamepad1.dpad_down) {
                park = false;
            } else if (gamepad1.dpad_up) {
                park = true;
            } else if (gamepad1.dpad_left) {
                parkLocation = "Alliance";
            } else if (gamepad1.dpad_right) {
                if (dockLocation == "Depo") {
                    parkLocation = "Opponent";
                } else {
                    parkLocation = "Alliance";
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

}
