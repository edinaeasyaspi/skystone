package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

public class BlueDepo extends TeleOpSkystone {
    String Park;
    protected OpenCvCamera phoneCam;
    String DrivetoSkystone;
    Mecanum mecanum = new Mecanum(Part.LeftA = hardwareMap.get(DcMotor.class, "LA"),
            Part.LeftB = hardwareMap.get(DcMotor.class, "LB"),
            Part.RightA = hardwareMap.get(DcMotor.class, "RA"),
            Part.RightB = hardwareMap.get(DcMotor.class, "RB"), telemetry);
    public enum Automous_states {
        DRIVED_TO_SKYSTONE,PICKUP,DRIVED_UNDER_BRIGE,DROPED,DRIVED_TO_SECOND_SKYSTONE,PICKUP2,DRIVED_UNDER_BRIGE2,DROPED2


    }
    public void Drive_1st_skystone () {



    }
    public void Drive_2st_skystone () {



    }
    public void Drive_3st_skystone () {



    }
    private void D1 () {

    }
    private void D2 (){

    }
    private void D3 () {

    }
    private void D4 () {

    }
    private void D5 () {

    }
    private  void D6 () {

    }
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



        }
        waitForStart();

        switch(DrivetoSkystone){
            case "Left":
                Drive_1st_skystone();

            case "Middle":
                Drive_2st_skystone();
            case "Right":
                Drive_3st_skystone();
        }



        switch (Park){
            case "Inner":

            case "Outer":

        }




    }
}
