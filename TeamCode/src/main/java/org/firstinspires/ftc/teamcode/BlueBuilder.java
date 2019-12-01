package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous(name = "Blue Builder",group = "EAP")
public class BlueBuilder extends TeleOpSkystone {

    String Park = "Inner" ;



    public void runOpMode() throws InterruptedException {
        Init_Juan();
        Mecanum mecanum = new Mecanum(Part.LeftA = hardwareMap.get(DcMotor.class, "LA"),
                Part.LeftB = hardwareMap.get(DcMotor.class, "LB"),
                Part.RightA = hardwareMap.get(DcMotor.class, "RA"),
                Part.RightB = hardwareMap.get(DcMotor.class, "RB"), telemetry);

        Reset_Arm_Slide();
        Reverse_wheels();
        Reset_Arm();
        while (!opModeIsActive()) {
            teleme1try.addData("Park Location",Park);

            if (gamepad1.dpad_right){
                Park = "Outer";
            }
            if (gamepad1.dpad_left){
                Park = "Inner";
            }
            telemetry.update();
        }

        waitForStart();




        mecanum.MoveBackwardsRunToPosition(.3,35,this);




        LatchFoundation();

        mecanum.MoveForwardRunToPosition(.3,5,this);





        UnLatchFoundation();

        mecanum.SlideLeftRunToPosition(.3,16,this);



        mecanum.MoveBackwardsRunToPosition(.3,36,this);
        mecanum.SlideRightRunToPosition(.3,16,this);



        mecanum.MoveForwardRunToPosition(.3,8,this);



        mecanum.SlideLeftRunToPosition(.3,14,this);


        switch (Park){
            case "Inner":
                mecanum.MoveForwardRunToPosition(.3,22,this);
            case "Outer":
                mecanum.SlideLeftRunToPosition(.3,7,this);



        }

    }


}
