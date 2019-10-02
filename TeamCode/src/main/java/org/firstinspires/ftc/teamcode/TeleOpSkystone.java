package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name = "Zoom Zoom Time yet", group = "EAP")
public class TeleOpSkystone extends LinearOpMode {

    private DcMotor LeftA;
    private DcMotor LeftB;
    private DcMotor RightA;
    private DcMotor RightB;

    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 3.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.14159265358979);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;
    @Override
    public void runOpMode () throws InterruptedException{

        LeftA = hardwareMap.dcMotor.get("LeftA");
        LeftB = hardwareMap.dcMotor.get("LeftB");
        RightA = hardwareMap.dcMotor.get("RightA");
        RightB = hardwareMap.dcMotor.get("RightB");



        RightA.setDirection(DcMotorSimple.Direction.REVERSE);
        RightB.setDirection(DcMotorSimple.Direction.REVERSE);

        LeftA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();


        while(opModeIsActive()){
//front and back
            LeftA.setPower(gamepad1.left_stick_y);
            LeftB.setPower(gamepad1.left_stick_y);
            RightA.setPower(gamepad1.left_stick_y);
            RightB.setPower(gamepad1.left_stick_y);

//side to side
            LeftA.setPower(-gamepad1.left_stick_x);
            LeftB.setPower(gamepad1.left_stick_x);
            RightA.setPower(gamepad1.left_stick_x);
            RightB.setPower(-gamepad1.left_stick_x);
//rotation
            LeftA.setPower(gamepad1.right_stick_x);
            LeftB.setPower(gamepad1.right_stick_x);
            RightA.setPower(-gamepad1.right_stick_x);
            RightB.setPower(-gamepad1.right_stick_x);



            idle();
        }

    }

}
