package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous(name = "Test", group = "EAP")
public class Test_Encoders extends TeleOpSkystone {


    @Override
    public void runOpMode() {
        Init_Juan();
        Reset_Arm();
        Reset_Arm_Slide();
        waitForStart();
       //brocken Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,200,.3,this);
        //Move_Motor_WithEncoder(Part.AndyMark_motor,200,.3,this);\
        //Move_Motor_WithEncoder(Part.Tetrix_ARMSLIDE_Motor,200,.3,this);
       // Move_Motor_WithEncoder(Part.RightB,200,.3 ,this);
        Move_Motor_WithEncoder(Part.LeftA,200,.3,this);
        Move_Motor_WithEncoder(Part.LeftB,200,.3,this);
        Move_Motor_WithEncoder(Part.RightA,200,.3,this);
        // Move_Motor_WithEncoder(Part.AndyMark_motor_Lift,200,.3,this);
        //Move_Motor_WithEncoder(Part.AndyMark_motor,200,.3,this);
        //Move_Motor_WithEncoder(Part.Tetrix_ARMSLIDE_Motor,200,.3,this);
         Move_Motor_WithEncoder(Part.RightB,200,.3 ,this);
        

    }
}
