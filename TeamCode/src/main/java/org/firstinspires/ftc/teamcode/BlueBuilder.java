package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class BlueBuilder extends TeleOpSkystone {

    String Park;
    public enum RedBuildStates {
        DRIVE_TO_F,LATCH_AND_PULL_F,UNLATCH_AND_STRAFE,DRIVE_AROUND,PUSH_INTO_DEPO,STRAFE_AND_PARK


    }

    Mecanum mecanum = new Mecanum(Part.LeftA = hardwareMap.get(DcMotor.class, "LA"),
            Part.LeftB = hardwareMap.get(DcMotor.class, "LB"),
            Part.RightA = hardwareMap.get(DcMotor.class, "RA"),
            Part.RightB = hardwareMap.get(DcMotor.class, "RB"), telemetry);



    private void B1 () {
        mecanum.MoveBackwardsRunToPosition(.3,35,this);


    }

    private  void  B2 () {
        LatchFoundation();

        mecanum.MoveForwardRunToPosition(.3,5,this);


    }

    private void   B3 () {
        UnLatchFoundation();

        mecanum.SlideLeftRunToPosition(.3,16,this);


    }
    private void B4 () {
        mecanum.MoveBackwardsRunToPosition(.3,36,this);
        mecanum.SlideRightRunToPosition(.3,16,this);

    }
    private void B5 () {
        mecanum.MoveForwardRunToPosition(.3,8,this);
    }

    private  void B6 (String Park) {
        mecanum.SlideLeftRunToPosition(.3,14,this);


        switch (Park){
            case "Inner":
                mecanum.MoveForwardRunToPosition(.3,22,this);
            case "Outer":
                mecanum.SlideLeftRunToPosition(.3,7,this);
        }


    }

    public void runOpMode() throws InterruptedException {
        Init_Juan();

        Reset_Arm_Slide();

        Reset_Arm();
        while (!opModeIsActive()) {
            teleme1try.addData("Park Location %7d",Park);

            if (gamepad1.dpad_right){
                Park = "Outer";
            }
            if (gamepad1.dpad_left){
                Park = "Inner";
            }
            telemetry.update();
        }

        waitForStart();




        B1();
        sleep(500);
        B2();
        sleep(500);
        B3();
        sleep(500);
        B4();
        sleep(500);
        B5();
        sleep(500);
        B6(Park);

    }
}
