package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name = "Red Depo", group = "EAP")
public class RedDepo extends TeleOpSkystone {
    String Park;
    protected OpenCvCamera phoneCam;
    String DrivetoSkystone;
    Mecanum mecanum;

    public void Sleep () {
        sleep(500);
    }
    private enum Automous_states {
        DRIVED_TO_SKYSTONE,PICKUP,DRIVED_UNDER_BRIGE,DROPED,DRIVED_TO_SECOND_SKYSTONE,PICKUP2,DRIVED_UNDER_BRIGE2,DROPED2


    }
    private void Drive_1st_skystone () {
        mecanum.SlideLeftRunToPosition(.3,5,this);
        Sleep();
        mecanum.RightSide_Corrections(3,2,this);
        Sleep();
        Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,1650,.3,this);
        Sleep();
        mecanum.MoveForwardRunToPosition(.3,15,this);
        Sleep();
        Latch();
        Sleep();
        mecanum.MoveBackwardsRunToPosition(.3,15,this);
        mecanum.SlideRightRunToPosition(.3,5,this);


    }
    private void Drive_2st_skystone () {
        mecanum.SlideLeftRunToPosition(.3,10,this);
        Sleep();
        mecanum.RightSide_Corrections(3,2,this);
        Sleep();
        Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,1650,.3,this);
        Sleep();
        mecanum.MoveForwardRunToPosition(.3,15,this);
        Sleep();
        Latch();
        Sleep();
        mecanum.MoveBackwardsRunToPosition(.3,15,this);
        mecanum.SlideRightRunToPosition(.3,10,this);


    }
    private void Drive_3st_skystone () {
        mecanum.SlideLeftRunToPosition(.3,15,this);
        Sleep();
        Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,1650,.3,this);
        Sleep();
        mecanum.MoveForwardRunToPosition(.3,15,this);
        Sleep();
        Latch();
        Sleep();
        mecanum.MoveBackwardsRunToPosition(.3,15,this);
        mecanum.SlideRightRunToPosition(.3,15,this);


    }
    private  void ParkInner () {
        mecanum.MoveForwardRunToPosition(.3,5 ,this);
        Sleep();
        mecanum.SlideRightRunToPosition(.3,5,this);
        Sleep();
    }
    private  void ParkOuter () {
        mecanum.MoveBackwardsRunToPosition(.3,5,this);
        Sleep();
        mecanum.SlideRightRunToPosition(.3,5,this);
        Sleep();
    }
    private void D1 () {


    }
    private void D2 (){
        switch(DrivetoSkystone){
            case "Right":
                Drive_1st_skystone();
                break;
            case "Middle":
                Drive_2st_skystone();
                break;
            case "Left":
                Drive_3st_skystone();
                break;
        }
    }
    private void D3 () {
        mecanum.MoveBackwardsRunToPosition(.3,8,this);
    }
    private void D4 () {
        mecanum.TurnRightRunToPosition(.3,_90Degree_turn,this);
        Sleep();
        mecanum.MoveForwardRunToPosition(.3,22,this);
        Sleep();
        UnLatch();
        Sleep();
    }
    private void D5 () {
        mecanum.MoveBackwardsRunToPosition(.3,22,this);
        Sleep();
        encoderDrive(.3,6,2);
        Sleep();
        mecanum.MoveForwardRunToPosition(.3,8,this);
        Sleep();
        switch(DrivetoSkystone){
            case "Left":
                Drive_1st_skystone();
                break;
            case "Middle":
                Drive_2st_skystone();
                break;
            case "Right":
                Drive_3st_skystone();
                break;
        }

    }
    private  void D6 () {
        mecanum.MoveBackwardsRunToPosition(.3,8,this);
        Sleep();
        mecanum.TurnLeftRunToPosition(.3,_90Degree_turn,this);
        Sleep();
        mecanum.MoveForwardRunToPosition(.3,22,this);
        Sleep();
        UnLatch();

    }
    private void D7 () {
        mecanum.MoveBackwardsRunToPosition(.3,8,this);
        Sleep();
        mecanum.TurnRightRunToPosition(.3,_90Degree_turn,this);
        switch (Park){
            case "Inner":
                ParkInner();
            case "Outer":
                ParkOuter();
        }

    }
    public void runOpMode() {
        Init_Juan();
        Reset_Arm();
        mecanum = new Mecanum(
                Part.LeftA = hardwareMap.get(DcMotor.class, "LA"),
                Part.LeftB = hardwareMap.get(DcMotor.class, "LB"),
                Part.RightA = hardwareMap.get(DcMotor.class, "RA"),
                Part.RightB = hardwareMap.get(DcMotor.class, "RB"), telemetry);
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
                DrivetoSkystone = "Right";

            }
            else if (pipline.location == SkystoneLocation.middle) {
                DrivetoSkystone = "Middle";
            }
            else if (pipline.location ==  SkystoneLocation.right) {
                DrivetoSkystone = "Left";
            }

            telemetry.update();

        }
        phoneCam.stopStreaming();
        waitForStart();
        D1();
        sleep(500);
        D2();
        sleep(500);
        D3();
        sleep(500);
        D4();
        sleep(500);
        D5();
        sleep(500);
        D6();
        sleep(500);
        D7();








    }
}
