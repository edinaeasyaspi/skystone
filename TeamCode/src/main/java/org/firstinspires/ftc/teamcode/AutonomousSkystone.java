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
//test
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Easy As Pi", group = "EAP")

public class AutonomousSkystone extends OpModeIMU {
    private boolean phoneInLandscape = true;
    public DcMotor LeftA;
    public DcMotor LeftB;
    public DcMotor RightA;
    public DcMotor RightB;
    @Override
    public void runOpMode() {
        int angle = 90;
        String dockLocation = "Skystone";
        boolean sample = true;
        boolean claim = true;
        boolean park = true;
        String parkLocation = "Red";
        int sleepTimer = 0;
        String endlocation = "Righttape";
        boolean dpad_down = false;
        boolean dpad_up = false;
        boolean dpad_left = false;
        boolean dpad_right = false;

        String position = "";
        String lastPosition = "";
        int numberOfSampleCheck = 0;

        antiTiltThread.start();

        initRobot();
        telemetry.addLine("Mapped hardware");


        initArm();
        telemetry.addLine("Arm set to home");

        resetArmEncoders();
        telemetry.addLine("Arm encoders reset");

        //enableMineralDetector();
        telemetry.addLine("Gold detector enabled");

        telemetry.update();

        while ((!opModeIsActive()) & (!isStopRequested())) {
            if (gamepad1.x) {
                dockLocation = "Skystone";
            } else if (gamepad1.b) {
                dockLocation = "buildingsite";
            }
            else if (gamepad1.dpad_left) {
                parkLocation = "Red";
            } else if (gamepad1.dpad_right) {
                parkLocation = "Blue";
            }
            else if (gamepad1.y) {
                endlocation = "Righttape";
            }
            /*else if (gamepad2.x) {
                slants=true;
            }*/
            else if (gamepad1.a) {
                endlocation = "lefttape";
            }
        }


        if ((gamepad2.dpad_left == true) && (dpad_left == false)) {

            if (sleepTimer > 0) {
                sleepTimer = sleepTimer - 1000;
            } else if ((gamepad2.dpad_right == true) && (dpad_right == false)) {


                sleepTimer = sleepTimer + 1000;

            }
        }

        }
    /*public void Init_Juan () {
        LeftA = hardwareMap.get(DcMotor.class, "LA");
        LeftB = hardwareMap.get(DcMotor.class, "LB");
        RightA = hardwareMap.get(DcMotor.class, "RA");
        RightB = hardwareMap.get(DcMotor.class, "RB");
    }
    public void slants(){

    }*/

    }

