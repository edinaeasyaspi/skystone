package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous(name = "Red Builder",group = "EAP")
public class RedBuilderSide extends TeleOpSkystone {

    String Park = "Inner" ;

    public void Sleep () {
        sleep(1000);
    }

    public void runOpMode() throws InterruptedException {
        Init_Juan();
        Mecanum mecanum = new Mecanum(Part.LeftA = hardwareMap.get(DcMotor.class, "LA"),
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




        mecanum.MoveBackwardsRunToPosition(.3,30,this);

        Sleep();


        LatchFoundation();
        Sleep();
        mecanum.MoveForwardRunToPosition(.3,18,this);

        Sleep();

        mecanum.TurnRightRunToPosition(.3,16,this);

        UnLatchFoundation();
        Sleep();
        mecanum.MoveForwardRunToPosition(.3,20,this);
        Sleep();
        switch (Park){
            case "Inner":
                mecanum.SlideRightRunToPosition(.3,10,this);
                Sleep();
                mecanum.MoveForwardRunToPosition(.3,5,this);
                break;
            case "Outer":
                mecanum.SlideLeftRunToPosition(.3,10,this);
                Sleep();
                mecanum.SlideLeftRunToPosition(.3,7,this);
                    break;


        }

    }


}
