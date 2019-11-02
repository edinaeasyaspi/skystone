package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorControllerEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;



@TeleOp(name = "Zoom Zoom Time", group = "EAP")
public class TeleOpSkystone extends LinearOpMode {
    //DRIVE
    public DcMotor LeftA;
    public DcMotor LeftB;
    public DcMotor RightA;
    public DcMotor RightB;
    //ARM
    public DcMotor AndyMark_motor_elbow;
    public DcMotor AndyMark_motor;
    public DcMotor Tetrix_ARMSLIDE_Motor;
    //end effector
    public Servo Rotating_servo;
    public Servo Latch;
    public Servo Up_and_down;
    boolean bool = false;
    // reed switch
    public DigitalChannel ARM_SLID_CHECK_Front;
    public DigitalChannel ARM_SLID_CHECK_Back;
    public DigitalChannel Elbow_Check_Up;
    public DigitalChannel Elbow_check_Down;
    //encoders
    protected ElapsedTime runtime = new ElapsedTime();
    protected static final int Andmark_MAX_REV = 1120;
    protected static final float COUNTS_PER_MOTOR_REV = 56F;
    protected static final int ARM_MAX = 1900;
    protected static final int ARM_SLIDE_MAX = 600;
    protected static final byte MAX_SPEED = 1;
    protected  static final byte MAX_Power = 1 ;
    protected static final int Tetrix_MAX =1440;
    public static final int WHEEL_DIAMETER_INCHES = 3;
    public static final double DRIVE_GEAR_REDUCTION = 0.5;

    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    public double driveStarted;
    public double lastSpeed;
    protected static final double DRIVE_SPEED_PRECISE = 0.6;
    protected static final double TURN_SPEED_PRECISE = 0.4;
    double RA; double LA; double RB; double LB;
    double X1; double Y1; double X2; double Y2;
    double joyScale = 0.5;
    double motorMax = 0.6;

    public static final float NEW_P = 2.5F;
    public static final float NEW_I = 0.1F;
    public static final float NEW_D = 0.2F;

    int n = 1 ;
            BNO055IMU imu;

    Orientation angles;
    Acceleration gravity;

    double currentTilt;
    boolean tilted;

    Orientation lastAngles = new Orientation();
    double globalAngle, power = .30, correction;




    public void Brake () {
        float x = -gamepad1.right_stick_x;
        float y = gamepad1.left_stick_y;
        boolean preciseDrive = false;

        if ((gamepad2.left_stick_x != 0) || (gamepad2.left_stick_y != 0)) {
            x = -gamepad2.left_stick_x;
            y = gamepad2.left_stick_y;
            preciseDrive = true;
        }

        if (y == 0) {
            if (lastSpeed != 0) {
                LeftA.setPower(0);
               LeftB.setPower(0);
               RightA.setPower(0);
               RightB.setPower(0);
                sleep(300);
            }
            driveStarted = 0;
        } else if (((lastSpeed > 0) && (y < 0)) || ((lastSpeed < 0) && (y > 0))) {
            LeftA.setPower(0);
            RightA.setPower(0);
            LeftB.setPower(0);
            RightB.setPower(0);
            sleep(300);
            driveStarted = 0;
        } else {
            driveStarted++;
        }

    }






        // Reset Encoders
    public void Reset_Arm_Slide(){
        Tetrix_ARMSLIDE_Motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Tetrix_ARMSLIDE_Motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Tetrix_ARMSLIDE_Motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    public void Latch (){
        Latch.setPosition(.5);
    }
    public void UnLatch () {
        Latch.setPosition(.5);
    }

    public void Init_Juan () {
        //Drive
        LeftA = hardwareMap.get(DcMotor.class,"LA");
        LeftB =  hardwareMap.get(DcMotor.class,"LB");
        RightA = hardwareMap.get(DcMotor.class,"RA");
        RightB =  hardwareMap.get(DcMotor.class,"RB");


//Arm
        AndyMark_motor_elbow = hardwareMap.get(DcMotor.class,"AME");
        AndyMark_motor = hardwareMap.get(DcMotor.class,"AAM");
        Tetrix_ARMSLIDE_Motor = hardwareMap.get(DcMotor.class,"AS");
        Rotating_servo = hardwareMap.get(Servo.class , "RS");
        Up_and_down =  hardwareMap.get(Servo.class , "U-D");
        Latch =  hardwareMap.get(Servo.class , "L");
//Arm reed switches
        ARM_SLID_CHECK_Front= hardwareMap.get(DigitalChannel.class, "ASC-F");
        ARM_SLID_CHECK_Back = hardwareMap.get(DigitalChannel.class, "ASC-B");
        Elbow_Check_Up = hardwareMap.get(DigitalChannel.class, "EBC-U");
        Elbow_check_Down = hardwareMap.get(DigitalChannel.class, "EBC-D");

        ARM_SLID_CHECK_Front.setMode(DigitalChannel.Mode.INPUT);
        ARM_SLID_CHECK_Back.setMode(DigitalChannel.Mode.INPUT);
        Elbow_Check_Up.setMode(DigitalChannel.Mode.INPUT);
        Elbow_check_Down.setMode(DigitalChannel.Mode.INPUT);

        LeftA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        LeftA.setDirection(DcMotor.Direction.REVERSE);
        LeftB.setDirection(DcMotor.Direction.REVERSE);




        UnLatch();



    }
    public void Drive(double leftStickX, double leftStickY, double rightStickY) {
        final double x = Math.pow(-leftStickX, 3.0);
        final double y = Math.pow(leftStickY, 3.0);

        final double rotation = Math.pow(-rightStickY, 3.0);
        final double direction = Math.atan2(x, y);
        final double speed = Math.min(1.0, Math.sqrt(x * x + y * y));

        final double fl = speed * Math.sin(direction + Math.PI / 4.0) + rotation;
        final double fr = speed * Math.cos(direction + Math.PI / 4.0) - rotation;
        final double bl = speed * Math.cos(direction + Math.PI / 4.0) + rotation;
        final double br = speed * Math.sin(direction + Math.PI / 4.0) - rotation;

        LeftA.setPower(fl);
        RightA.setPower(fr);
        LeftB.setPower(br);
        RightB.setPower(bl);
    }
    public void Reset_Arm () {
        AndyMark_motor_elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        AndyMark_motor_elbow.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        AndyMark_motor_elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        AndyMark_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        AndyMark_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        AndyMark_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);




    }

    public void Magnetic_Limitswtiches () {

        while (ARM_SLID_CHECK_Front.getState() == true){
            Tetrix_ARMSLIDE_Motor.setPower(-0.3);
        }
        while (ARM_SLID_CHECK_Back.getState() == true){
            Tetrix_ARMSLIDE_Motor.setPower(0.3);
        }
        while (Elbow_check_Down.getState() == true) {
            AndyMark_motor_elbow.setPower(0);
        }
    }
    public class AntiTiltThread extends Thread {
        public AntiTiltThread() {
            this.setName("Anti Tilt Thread");
        }


        @Override
        public void run() {
            double lastTilt = 0;
            while (!isInterrupted()) {
                currentTilt = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).thirdAngle;

                if ((lastTilt <= -7) & (currentTilt > -7)) {
                    tilted = false;
                    LeftA.setPower(0);
                    LeftB.setPower(0);
                    RightA.setPower(0);
                    RightB.setPower(0);
                }
                if (currentTilt <= -7) {
                    tilted = true;
                    LeftB.setPower(0.5);
                    RightB.setPower(0.5);
                }

                lastTilt = currentTilt;

                idle();
            }

        }


    }



    @Override
    public void runOpMode () throws InterruptedException {

        Init_Juan();
        telemetry.addLine("Hardware Mapped");
        Reset_Arm_Slide();
        telemetry.addLine("Arm slide Reset");
        Reset_Arm();
        telemetry.addLine("Arm Reset");
        telemetry.addLine("Juan is ready");
        telemetry.addLine("Awaiting start");
        waitForStart();


//Drive chain
        while (opModeIsActive()) {


            Drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            //   LeftA.setPower(gamepad1.left_stick_y);
            //   LeftB.setPower(gamepad1.right_stick_y);
            //   RightA.setPower(gamepad2.right_stick_y);
            //   RightB.setPower(gamepad2.left_stick_y);


            Tetrix_ARMSLIDE_Motor.setPower(gamepad2.left_stick_x);
            AndyMark_motor.setPower(gamepad2.right_stick_x);
            //Arm Elbow

            Brake();
            //Arm


            if (gamepad2.right_bumper) {
                Latch();
            }
            if (gamepad2.left_bumper) {
                UnLatch();
            }
            if (gamepad2.start) {

            }



            if (gamepad2.x) {
                Arm_Height(n);
                n++;

            }
            if (gamepad2.a) {
                n = 1;
                Arm_Height(n);

            }


            idle();
        }



    }



    public void Grabskystone () {
        AndyMark_motor_elbow.setTargetPosition(560);
        AndyMark_motor_elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        AndyMark_motor_elbow.setPower(.1);

    }


    public void Arm_Height (int n){

        switch (n) {
            case 1:
                Grabskystone();
                break;
            case 2:

            case 3:

            case 4:

            case 5:

            case 6:

            case 7:

            case 8:

            default:
            n = 0;
        }
    }


}//Class



