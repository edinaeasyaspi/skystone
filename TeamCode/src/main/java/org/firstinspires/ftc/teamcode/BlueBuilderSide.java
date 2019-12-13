package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Blue Builder",group = "EAP")
public class BlueBuilderSide extends TeleOpSkystone {
//code works
    Mecanum mecanum;
    String Park = "Outer";


    public void Sleep () {
        sleep(1000);
    }

    private void Drive_to_foundation_and_Latch () {
        mecanum.SlideRightRunToPosition(.3,5,this);
        mecanum.MoveBackwardsRunToPosition(.3,24,this);
        Sleep();
        LatchFoundation();
    }
    private void Pull_foundation_Unlatch (){
        Sleep();
        mecanum.MoveForwardRunToPosition(.3,25,this);
        Sleep();
        UnLatchFoundation();
        Sleep();
    }
    private void Strafe_out_and_prep_to_Secure_foundation () {
        mecanum.SlideLeftRunToPosition(.3,23,this);
        Sleep();
        mecanum.MoveBackwardsRunToPosition(.3,13,this);
        Sleep();
    }
    private void Push_foundation_in_Place () {
        mecanum.SlideRightRunToPosition(.3,14,this);
        Sleep();
        mecanum.SlideLeftRunToPosition(.3,14,this);
        Sleep();
    }
    private void Prep_For_Park () {
        mecanum.MoveBackwardsRunToPosition(.3,5,this);
        Sleep();
        Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,1758,.3,this);
        Sleep();
        mecanum.TurnLeftRunToPosition(.3,_90Degree_turn,this);
    }
    public void runOpMode() throws InterruptedException {
        Init_Juan();
         mecanum = new Mecanum(Part.LeftA = hardwareMap.get(DcMotor.class, "LA"),
                Part.LeftB = hardwareMap.get(DcMotor.class, "LB"),
                Part.RightA = hardwareMap.get(DcMotor.class, "RA"),
                Part.RightB = hardwareMap.get(DcMotor.class, "RB"), telemetry);

        Reset_Arm_Slide();

        Reset_Arm();
        while (!opModeIsActive()) {
            teleme1try.addData("Park Location : %7d",Park);

            if (gamepad1.dpad_right){
                Park = "Outer";
            }
            if (gamepad1.dpad_left){
                Park = "Inner";
            }

            telemetry.update();
        }

        waitForStart();



        Drive_to_foundation_and_Latch();

        Pull_foundation_Unlatch();

        Strafe_out_and_prep_to_Secure_foundation();

        Push_foundation_in_Place();

        Prep_For_Park();

        switch (Park){
            case "Inner":
                mecanum.MoveForwardRunToPosition(.3,10,this);
                break;
            case "Outer":
                mecanum.SlideRightRunToPosition(.3,25,this);
                Sleep();
                mecanum.MoveForwardRunToPosition(.3,5,this);
                    break;


        }

    }


}
