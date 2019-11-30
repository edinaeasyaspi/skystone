package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class JuanBody extends LinearOpMode {



    int n = 0;

    protected DcMotor LeftA;
    protected DcMotor LeftB;
    protected DcMotor RightA;
    protected DcMotor RightB;
    //ARM
    protected DcMotor AndyMark_motor_Lift;
    protected DcMotor AndyMark_motor;
    protected DcMotor Tetrix_ARMSLIDE_Motor;
    //end effector
    protected Servo Rotating_servo;
    protected Servo Latch;
    protected Servo FoundationLatchL;
    protected Servo FoundationLactchR;

    // reed switch
    public DigitalChannel ARM_SLID_CHECK_Front;
    public DigitalChannel ARM_SLID_CHECK_Back;
    public DigitalChannel Elbow_Check_Up;
    public DigitalChannel Elbow_check_Down;
    //encoders
    protected ElapsedTime runtime = new ElapsedTime();
    protected static final int Andmark_MAX_REV = 1120;
    protected static final float COUNTS_PER_MOTOR_REV = 1440;
    protected static final int ARM_MAX = 1900;
    protected static final int ARM_SLIDE_MAX = 600;
    protected static final byte MAX_SPEED = 1;
    protected static final byte MAX_Power = 1;
    protected static final int Tetrix_MAX = 1440;
    public static final int WHEEL_DIAMETER_INCHES = 3;
    public static final double DRIVE_GEAR_REDUCTION = 0.5;
    protected static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * Math.PI);
    public int driveStarted = 0;
    public double lastSpeed;
    protected static final double DRIVE_SPEED_PRECISE = 0.6;
    protected static final double TURN_SPEED_PRECISE = 0.4;
    protected double RA;
    protected double LA;
    protected double RB;
    protected double LB;
    protected double X1;
    protected double Y1;
    protected double X2;
    protected double Y2;
    protected double joyScale = 0.5;
    protected double motorMax = 0.6;
    //
    protected static final float NEW_P = 2.5F;
    protected static final float NEW_I = 0.1F;
    protected static final float NEW_D = 0.2F;


    BNO055IMU imu;

    protected Orientation angles;
    protected Acceleration gravity;

    protected double currentTilt;
    protected boolean tilted;

    protected Orientation lastAngles = new Orientation();
    protected double globalAngle, power = .30, correction;
    public void runOpMode() throws InterruptedException {

    }
}
