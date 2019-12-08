package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Test", group = "EAP")
public class Test_Encoders extends TeleOpSkystone {

    public void Sleep () {
        sleep(500);
    }

    @Override
    public void runOpMode() {
        Init_Juan();
        Reset_Arm();
        Reset_Arm_Slide();
        waitForStart();


        Mecanum mecanum = new Mecanum(Part.LeftA = hardwareMap.get(DcMotor.class, "LA"),
                Part.LeftB = hardwareMap.get(DcMotor.class, "LB"),
                Part.RightA = hardwareMap.get(DcMotor.class, "RA"),
                Part.RightB = hardwareMap.get(DcMotor.class, "RB"), telemetry);

        /*mecanum.TurnLeftRunToPosition(.3,19,this);
        Sleep();
        CLaw_180();

        Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,1500,.3,this);
        Sleep();
        mecanum.MoveForwardRunToPosition(.3,17,this);

         *//*
        Claw_100();
        Sleep();
        Claw_130();
        Sleep();
        CLaw_180();
        Sleep();
*/        Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,1550,.3,this);
        Sleep();
        Part.AndyMark_motor_Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Part.AndyMark_motor_Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,-400,-.3,this);

       // mecanum.SlideLeftRunToPosition(.3,14,this);
        //mecanum.TurnLeftRunToPosition(.3,_90Degree_turn,this);
       // mecanum.TurnRightRunToPosition(.3,14,this);
        //Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,1758,.3,this);
        //sleep(500);
        //mecanum.MoveForwardRunToPosition(.3,10,this);
        //sleep(500);
        //Latch();
        //mecanum.SlideLeftRunToPosition(.3,44,this);

        //Move_Motor_WithEncoder(Part.AndyMark_motor,200,.3,this);\
        //Move_Motor_WithEncoder(Part.Tetrix_ARMSLIDE_Motor,200,.3,this);
   /*     Move_Motor_WithEncoder(Part.RightB,200,.3 ,this);
        Move_Motor_WithEncoder(Part.LeftA,200,.3,this);
      */ // Move_Motor_WithEncoder(Part.LeftB,200,.3,this);
       /* Move_Motor_WithEncoder(Part.RightA,200,.3,this);
        // Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,200,.3,this);
        //Move_Motor_WithEncoder(Part.AndyMark_motor,200,.3,this);
        //Move_Motor_WithEncoder(Part.Tetrix_ARMSLIDE_Motor,200,.3,this);
         Move_Motor_WithEncoder(Part.RightB,200,.3 ,this);


    */
sleep(15000);


    }
}
