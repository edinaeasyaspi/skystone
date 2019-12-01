package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
@Autonomous(name = "Blue Depo", group = "EAP")
public class BlueDepo extends TeleOpSkystone {
    String Park;
    protected OpenCvCamera phoneCam;
    String DrivetoSkystone;

    public void runOpMode() {
        Init_Juan();
        Reset_Arm();
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = new OpenCvInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        // OR...  Do Not Activate the Camera Monitor View
        //phoneCam = new OpenCvInternalCamera(OpenCvInternalCamera.CameraDirection.BACK);

        /*
         * Open the connection to the camera device
         */
        phoneCam.openCameraDevice();

        LocaterPipline pipline = new LocaterPipline();
        phoneCam.setPipeline(pipline);

        phoneCam.startStreaming(320,240, OpenCvCameraRotation.UPRIGHT);

        Reset_Arm_Slide();


        while (!opModeIsActive()){

            telemetry.addData("Park Location %7d", Park);
            telemetry.addData("Skystone location : %7d",DrivetoSkystone);

            if (gamepad1.dpad_left){
                Park = "Outer";
            }
            if (gamepad1.dpad_right){
                Park = "Inner";
            }

            if (pipline.location == SkystoneLocation.left) {
                DrivetoSkystone = "Left";

            }
            else if (pipline.location == SkystoneLocation.middle) {
                DrivetoSkystone = "Middle";
            }
            else if (pipline.location ==  SkystoneLocation.right) {
                DrivetoSkystone = "Right";
            }

            telemetry.update();

        }
        phoneCam.stopStreaming();
        waitForStart();
        Mecanum mecanum = new Mecanum(
                Part.LeftA = hardwareMap.get(DcMotor.class, "LA"),
                Part.LeftB = hardwareMap.get(DcMotor.class, "LB"),
                Part.RightA = hardwareMap.get(DcMotor.class, "RA"),
                Part.RightB = hardwareMap.get(DcMotor.class, "RB"), telemetry);






        //D1
            Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,800,.3,this);
            mecanum.MoveForwardRunToPosition(.3,25,this);


            switch(DrivetoSkystone){
                case "Left":
                    mecanum.SlideLeftRunToPosition(.3,8,this);
                    mecanum.MoveForwardRunToPosition(.3,5,this);
                    Latch();
                    mecanum.MoveBackwardsRunToPosition(.3,10,this);


                    break;
                case "Middle":
                    mecanum.MoveForwardRunToPosition(.3,5,this);
                    Latch();
                    break;
                case "Right":
                    mecanum.SlideRightRunToPosition(.3,8,this);
                    mecanum.MoveForwardRunToPosition(.3,5,this);
                    Latch();
                    mecanum.MoveBackwardsRunToPosition(.3,10,this);
                    mecanum.SlideLeftRunToPosition(.3,8,this);

                    break;
            }
            //D2
            mecanum.MoveBackwardsRunToPosition(.3,8,this);

            //D3
            encoderDrive(.3,2,6);
            mecanum.MoveForwardRunToPosition(.3,22,this);
            UnLatch();

            //D4
            mecanum.MoveBackwardsRunToPosition(.3,22,this);
            encoderDrive(.3,6,2);
            mecanum.MoveForwardRunToPosition(.3,8,this);
            switch(DrivetoSkystone){
                case "Left":
                    mecanum.SlideLeftRunToPosition(.3,8,this);
                    mecanum.MoveForwardRunToPosition(.3,5,this);
                    Latch();
                    mecanum.MoveBackwardsRunToPosition(.3,10,this);

                    break;
                case "Middle":
                    mecanum.MoveForwardRunToPosition(.3,5,this);
                    Latch();
                    mecanum.MoveBackwardsRunToPosition(.3,10,this);
                    break;
                case "Right":
                    mecanum.SlideRightRunToPosition(.3,8,this);
                    mecanum.MoveForwardRunToPosition(.3,5,this);
                    Latch();
                    mecanum.MoveBackwardsRunToPosition(.3,10,this);
                    mecanum.SlideLeftRunToPosition(.3,8,this);
                    break;
            }


            //D5
            mecanum.MoveBackwardsRunToPosition(.3,8,this);
            encoderDrive(.3,2,6);
            mecanum.MoveForwardRunToPosition(.3,22,this);
            UnLatch();


            //D6
            mecanum.MoveBackwardsRunToPosition(.3,8,this);
            encoderDrive(.3,6,2);
            switch (Park){
                case "Inner":
                    mecanum.MoveForwardRunToPosition(.3,5 ,this);
                    mecanum.SlideLeftRunToPosition(.3,5,this);
                case "Outer":
                    mecanum.MoveBackwardsRunToPosition(.3,5,this);
                    mecanum.SlideLeftRunToPosition(.3,5,this);


        }








    }
}
