package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
@Autonomous(name = "Blue Depo", group = "EAP")
public class BlueDepo extends TeleOpSkystone { String Park = "Inner";
    protected OpenCvCamera phoneCam;
    String DrivetoSkystone = "Inner";
    Mecanum mecanum;
    String Place = "How Bout No";

    public void Place_skystone_and_Park2 () {
        mecanum.TurnLeftRunToPosition(.3,_90Degree_turn,this);
        mecanum.TurnLeftRunToPosition(.3,3,this);
        mecanum.SlideLeftRunToPosition(.3,5,this);
        switch (DrivetoSkystone) {
            case "Left":
                mecanum.MoveForwardRunToPosition(.3,28,this);
                break;
            case "Middle":
                mecanum.MoveForwardRunToPosition(.3,35,this);
                break;
            case "Right":
                mecanum.MoveForwardRunToPosition(.3,45,this);
                break;
        }
        switch (Place){
            case "Yes":
                Part.AndyMark_motor_Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                Part.AndyMark_motor_Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,-400,-.3,this);
                mecanum.MoveForwardRunToPosition(.3,10,this);
                UnLatch();
                Sleep();
                mecanum.MoveBackwardsRunToPosition(.3,10,this);
                break;
            case "How Bout No":
                Part.AndyMark_motor_Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                Part.AndyMark_motor_Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,-400,-.3,this);
                UnLatch();
                break;
        }

        UnLatch();
        mecanum.MoveBackwardsRunToPosition(.3,15,this);

    }


    public void Sleep () {
        sleep(500);
    }
    private enum Automous_states {
        DRIVED_TO_SKYSTONE,PICKUP,DRIVED_UNDER_BRIGE,DROPED,DRIVED_TO_SECOND_SKYSTONE,PICKUP2,DRIVED_UNDER_BRIGE2,DROPED2


    }
    private void Drive_1st_skystone () {
        mecanum.SlideLeftRunToPosition(.3,15,this);
        mecanum.MoveBackwardsRunToPosition(.3,5,this);
        Sleep();
        Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,1500,.3,this);
        Sleep();
        mecanum.MoveForwardRunToPosition(.3,15,this);
        Sleep();
        If_Your_happy_and_you_know_it_clap_your_hands();
        Sleep();
        mecanum.MoveBackwardsRunToPosition(.3,15,this);
        Place_skystone_and_Park2();
        UnLatch();


    }
    private void Drive_2st_skystone () {
        mecanum.SlideLeftRunToPosition(.3,10,this);
        mecanum.MoveBackwardsRunToPosition(.3,3,this);
        Sleep();
        CLaw_180();
        Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,1500,.3,this);

        mecanum.MoveForwardRunToPosition(.3,15,this);
        Sleep();
        If_Your_happy_and_you_know_it_clap_your_hands();
        mecanum.MoveBackwardsRunToPosition(.3,15,this);
        Sleep();
        Place_skystone_and_Park2();
    }
    private void Drive_3st_skystone () {
        Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,1500,.3,this);
        mecanum.MoveForwardRunToPosition(.3,17,this);
        Sleep();
        If_Your_happy_and_you_know_it_clap_your_hands();
        mecanum.MoveBackwardsRunToPosition(.3,15,this);
        mecanum.TurnLeftRunToPosition(.3,_90Degree_turn,this);
        mecanum.MoveForwardRunToPosition(.3,45,this);
        UnLatch();
        mecanum.SlideLeftRunToPosition(.3,10,this);
        mecanum.MoveBackwardsRunToPosition(.3,20,this);


    }
    private  void Drive_again () {
        Claw_130();
        mecanum.TurnLeftRunToPosition(.3,4,this);
        CLaw_180();
        Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,1650,.3,this);
        Sleep();
        UnLatch();
        mecanum.MoveForwardRunToPosition(.3,15,this);

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
    private void DRIve_toSkYstone (){
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
    private void D3 () {

        mecanum.TurnRightRunToPosition(.3,_90Degree_turn,this);
    }
    private void D4 () {
        mecanum.TurnRightRunToPosition(.3,_90Degree_turn,this);
        Sleep();


    }
    private void D5 () {


        switch(DrivetoSkystone){
            case "Left":
                Drive_again();
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
        switch (Park){
            case "Inner":
                mecanum.MoveForwardRunToPosition(.3,40,this);
                Sleep();
                UnLatch();
                Sleep();
                break;
            case "Outer":
                mecanum.SlideLeftRunToPosition(.3,22,this);
                Sleep();
                mecanum.MoveForwardRunToPosition(.3,40,this);
                Sleep();
                UnLatch();
                break;
        }

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
                break;
            case "Outer":
                ParkOuter();
                break;
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
            telemetry.addData("Should we Place....", Place);

            if (gamepad2.dpad_up)
                Place = "Yes";

            if (gamepad2.dpad_down)
                Place = "How Bout No";
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

        mecanum.TurnLeftRunToPosition(.3,1,this);
        switch (DrivetoSkystone){

            case "Left":
                DRIve_toSkYstone();
                break;
            case "Middle":
                DRIve_toSkYstone();
                break;

            case "Right":
                DRIve_toSkYstone();
                break;

        }









    }
}
