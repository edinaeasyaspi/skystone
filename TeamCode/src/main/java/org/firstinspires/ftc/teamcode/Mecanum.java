package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Mecanum extends JuanBody {
    JuanBody Part = new JuanBody();
    private Telemetry _telemetry;

    private double _currentPower = 1.0;

    //
    // This is our class that we use to drive the robot in autonomous and teleop.  It has a bunch
    // of helper method in it to help us.  The ones that end with the number 2 use the mode
    // run with encoders whilethe others use the run to position.  Except for the move, it just
    // moves the robot with some power.
    // The methods are:
    //
    //  SlideLeft2 - Slide left a certain distance using RUN_WITH_ENCODERS
    //  Move - moves the robot with a certain power
    //  SlideRightRunWithEncoders - slide right a certain distance using RUN_WITH_ENCODERS
    //  MoveForwardRunToPosition - move forward a certain distance using RUN_TO_POSITION
    //  MoveForwardRunWithEncoders - move forward a certain distance using RUN_WITH_ENCODERS
    //  MoveBackwardsRunToPosition - move backwards a certain distance using RUN_TO_POSITION
    //  MoveBackwardsRunWithEncoders - move backwards a certain distance using RUN_WITH_ENCODERS
    //  TurnRight - turn right with RUN_TO_POSITION
    //  TurnLeft - turn left with RUN_TO_POSITION
    //  Stop - stops the motors
    //
    public Mecanum(DcMotor fl, DcMotor fr, DcMotor bl, DcMotor br,
                   Telemetry telemetry)
    {
        LeftA = fl;
        RightA= fr;
        LeftB = bl;
        RightB = br;
        _telemetry = telemetry;

        LeftA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        LeftB.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftA.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void SlideLeftRunWithEncoders(double power, int distance, LinearOpMode opMode) {
        // put the motors into run with encoders so they run with even power
        StopResetEncodersRunWithEncoderAndBrakekOn();

        int currentPosition =  Math.abs(RightB.getCurrentPosition());
        int error = Math.abs((int)(distance * 0.95));
        Move(-power, power, power, -power);

        // keep moving until we get close and the op mode is active.  close is 95% of what we want to get to
        while ((currentPosition < error) && opMode.opModeIsActive()) {
            currentPosition =  Math.abs(RightB.getCurrentPosition());
            opMode.idle();
        }

        Stop();
    }

    public void SlideRightRunWithEncoders(double power, int distance, LinearOpMode opMode) {
        // put the motors into run with encoders so they run with even power
        StopResetEncodersRunWithEncoderAndBrakekOn();

        int currentPosition =  Math.abs(RightA.getCurrentPosition());
        int error = Math.abs((int)(distance * 0.95));
        Move(power, -power, -power, power);

        // keep moving until we get close and the op mode is active.  close is 95% of what we want to get to
        while ((currentPosition < error) && opMode.opModeIsActive()) {
            currentPosition =  Math.abs(RightA.getCurrentPosition());
            opMode.idle();
        }

        Stop();
    }

    public void MoveForwardRunWithEncoders(double power, int distance, LinearOpMode opMode) {
        // put the motors into run with encoders so they run with even power
        StopResetEncodersRunWithEncoderAndBrakekOn();

        int error = Math.abs((int)(distance * 0.95));
        int currentPosition =  Math.abs(RightA.getCurrentPosition());
        double currentPower = CalculateRampPower(power, distance, currentPosition);
        Move(currentPower, currentPower, currentPower, currentPower);

        while ((currentPosition < error) && opMode.opModeIsActive()) {
            currentPosition =  Math.abs(RightA.getCurrentPosition());
            currentPower = CalculateRampPower(power, distance, currentPosition);
            Move(currentPower, currentPower, currentPower, currentPower);
            opMode.idle();
        }

        Stop();
    }

    public void MoveBackwardsRunWithEncoders(double power, int distance, LinearOpMode opMode) {
        // put the motors into run with encoders so they run with even power
        StopResetEncodersRunWithEncoderAndBrakekOn();

        int error = Math.abs((int)(distance * 0.95));
        int currentPosition =  Math.abs(RightA.getCurrentPosition());

        // ramp up and down the motor speed based on current position
        double currentPower = CalculateRampPower(power, distance, currentPosition);

        Move(-currentPower, -currentPower, -currentPower, -currentPower);

        // keep moving until we get close and the op mode is active.  close is 95% of what we want to get to
        while ((currentPosition < error) && opMode.opModeIsActive()) {
            currentPosition =  Math.abs(RightA.getCurrentPosition());
            currentPower = CalculateRampPower(power, distance, currentPosition);
            Move(-currentPower, -currentPower, -currentPower, -currentPower);
            opMode.idle();
        }

        Stop();
    }

    public void TurnRightRunWithEncoders(double power, int distance, LinearOpMode opMode) {
        StopResetEncodersRunWithEncoderAndBrakekOn();

        int error = Math.abs((int)(distance * 0.95));
        int currentPosition =  Math.abs(RightA.getCurrentPosition());

        // ramp up and down the motor speed based on current position
        double currentPower = CalculateRampPower(power, distance, currentPosition);

        Move(currentPower, -currentPower, currentPower, -currentPower);

        // keep moving until we get close and the op mode is active.  close is 95% of what we want to get to
        while ((currentPosition < error) && opMode.opModeIsActive()) {
            currentPosition =  Math.abs(RightA.getCurrentPosition());
            currentPower = CalculateRampPower(power, distance, currentPosition);
            Move(currentPower, -currentPower, currentPower, -currentPower);
            opMode.idle();
        }

        Stop();
    }

    public void TurnLeftRunWithEncoders(double power, int distance, LinearOpMode opMode) {
        StopResetEncodersRunWithEncoderAndBrakekOn();

        int error = Math.abs((int)(distance * 0.95));
        int currentPosition =  Math.abs(RightA.getCurrentPosition());

        // ramp up and down the motor speed based on current position
        double currentPower = CalculateRampPower(power, distance, currentPosition);

        Move(-currentPower, currentPower, -currentPower, currentPower);

        // keep moving until we get close and the op mode is active.  close is 95% of what we want to get to
        while ((currentPosition < error) && opMode.opModeIsActive()) {
            currentPosition =  Math.abs(RightA.getCurrentPosition());
            currentPower = CalculateRampPower(power, distance, currentPosition);
            Move(-currentPower, currentPower, -currentPower, currentPower);
            opMode.idle();
        }

        Stop();
    }

    public void MoveForwardRunToPosition(double power, int distance, LinearOpMode opMode) {
        // run with simple distance encoders as moving forward or backwards
        distance *= COUNTS_PER_INCH;

        SetDistance(distance, distance, distance, distance);
        StopResetEncodersAndRunToPosition();

        int error = Math.abs((int)(distance * 0.95));
        int currentPosition =  Math.abs(RightA.getCurrentPosition());
        Move(power, power, power, power);

        // keep moving until we get close and the op mode is active.  close is 95% of what we want to get to
        while (LeftA.isBusy() && RightA.isBusy() && LeftB.isBusy() && RightB.isBusy() && (currentPosition < error) && opMode.opModeIsActive()) {
            currentPosition =  Math.abs(RightA.getCurrentPosition());
            opMode.idle();
        }

        Stop();
    }

    public void MoveBackwardsRunToPosition(double power, int distance, LinearOpMode opMode) {
        // run with simple distance encoders as moving forward or backwards
        distance *=Part.COUNTS_PER_INCH;
        SetDistance(-distance, -distance, -distance, -distance);
        StopResetEncodersAndRunToPosition();

        int error = Math.abs((int)(distance * 0.95));
        int currentPosition =  Math.abs(RightA.getCurrentPosition());
        Move(power, power, power, power);

        // keep moving until we get close and the op mode is active.  close is 95% of what we want to get to
        while (LeftA.isBusy() && RightA.isBusy() && LeftB.isBusy() && RightB.isBusy() && (currentPosition < error) && opMode.opModeIsActive()) {
            currentPosition =  Math.abs(RightA.getCurrentPosition());
            opMode.idle();
        }

        Stop();
    }

    public void TurnRightRunToPosition(double power, int distance, LinearOpMode opMode) {
        // run with simple distance encoders as moving forward or backwards
        distance *=Part.COUNTS_PER_INCH;
        SetDistance(distance, distance, -distance, -distance);

        StopResetEncodersAndRunToPosition();

        int error = Math.abs((int)(distance * 0.95));
        int currentPosition =  Math.abs(RightA.getCurrentPosition());
        Move(power, power, power, power);

        // keep moving until we get close and the op mode is active.  close is 95% of what we want to get to
        while (LeftA.isBusy() && RightA.isBusy() && LeftB.isBusy() && RightB.isBusy() && (currentPosition < error) && opMode.opModeIsActive()) {
            currentPosition =  Math.abs(RightA.getCurrentPosition());
            opMode.idle();
        }

        Stop();
    }

    public void TurnLeftRunToPosition(double power, int distance, LinearOpMode opMode) {
        // run with simple distance encoders as moving forward or backwards
        SetDistance(-distance, -distance, distance, distance);
        StopResetEncodersAndRunToPosition();

        int error = Math.abs((int)(distance * 0.95));
        int currentPosition =  Math.abs(RightA.getCurrentPosition());
        Move(power, power, power, power);

        // keep moving until we get close and the op mode is active.  close is 95% of what we want to get to
        while (LeftA.isBusy() && RightA.isBusy() && LeftB.isBusy() && RightB.isBusy() && (currentPosition < error) && opMode.opModeIsActive()) {
            currentPosition =  Math.abs(RightA.getCurrentPosition());
            opMode.idle();
        }

        Stop();
    }

    public void SlideRightRunToPosition(double power, int distance, LinearOpMode opMode) {
        // put the motors into run with encoders so they run with even power

        SetDistance(distance, -distance, -distance, distance);
        StopResetEncodersAndRunToPosition();

        int error = Math.abs((int)(distance * 0.95));
        int currentPosition =  Math.abs(RightA.getCurrentPosition());
        Move(power, power, power, power);

        // keep moving until we get close and the op mode is active.  close is 95% of what we want to get to
        while (LeftA.isBusy() && RightA.isBusy() && LeftB.isBusy() && RightB.isBusy() && (currentPosition < error) && opMode.opModeIsActive()) {
            currentPosition =  Math.abs(RightA.getCurrentPosition());
            opMode.idle();
        }

        Stop();
    }

    public void SlideLeftRunToPosition(double power, int distance, LinearOpMode opMode) {
        // put the motors into run with encoders so they run with even power

        SetDistance(-distance, distance, distance, -distance);
        StopResetEncodersAndRunToPosition();

        int error = Math.abs((int)(distance * 0.95));
        int currentPosition =  Math.abs(RightA.getCurrentPosition());
        Move(power, power, power, power);

        // keep moving until we get close and the op mode is active.  close is 95% of what we want to get to
        while (LeftA.isBusy() && RightA.isBusy() && LeftB.isBusy() && RightB.isBusy() && (currentPosition < error) && opMode.opModeIsActive()) {
            currentPosition =  Math.abs(RightA.getCurrentPosition());
            opMode.idle();
        }

        Stop();
    }

    public void DiagonalRightAndUpRunToPosition(double power, int distance, LinearOpMode opMode) {
        // put the motors into run with encoders so they run with even power

        SetDistance(distance, distance, distance, distance);
        StopResetEncodersAndRunToPosition();

        int error = Math.abs((int)(distance * 0.95));
        int currentPosition =  Math.abs(LeftA.getCurrentPosition());
        Move(power, 0, 0, power);

        // keep moving until we get close and the op mode is active.  close is 95% of what we want to get to
        while (LeftA.isBusy() && RightA.isBusy() && LeftB.isBusy() && RightB.isBusy() && (currentPosition < error) && opMode.opModeIsActive()) {
            currentPosition =  Math.abs(LeftA.getCurrentPosition());
            opMode.idle();
        }

        Stop();
    }

    public void DiagonalRightAndDownRunToPosition(double power, int distance, LinearOpMode opMode) {
        // put the motors into run with encoders so they run with even power

        SetDistance(-distance, -distance, -distance, -distance);
        StopResetEncodersAndRunToPosition();

        int error = Math.abs((int)(distance * 0.95));
        int currentPosition =  Math.abs(RightA.getCurrentPosition());
        Move(0, power, power, 0);

        // keep moving until we get close and the op mode is active.  close is 95% of what we want to get to
        while (LeftA.isBusy() && RightA.isBusy() && LeftB.isBusy() && RightB.isBusy() && (currentPosition < error) && opMode.opModeIsActive()) {
            currentPosition =  Math.abs(RightA.getCurrentPosition());
            opMode.idle();
        }

        Stop();
    }

    public void DiagonalLeftAndDownRunToPosition(double power, int distance, LinearOpMode opMode) {
        // put the motors into run with encoders so they run with even power

        SetDistance(-distance, -distance, -distance, -distance);
        StopResetEncodersAndRunToPosition();

        int error = Math.abs((int)(distance * 0.95));
        int currentPosition =  Math.abs(LeftA.getCurrentPosition());
        Move(power, 0, 0, power);

        // keep moving until we get close and the op mode is active.  close is 95% of what we want to get to
        while (LeftA.isBusy() && RightA.isBusy() && LeftB.isBusy() && RightB.isBusy() && (currentPosition < error) && opMode.opModeIsActive()) {
            currentPosition =  Math.abs(LeftA.getCurrentPosition());
            opMode.idle();
        }

        Stop();
    }

    public void DiagonalLeftAndUpRunToPosition(double power, int distance, LinearOpMode opMode) {
        // put the motors into run with encoders so they run with even power

        SetDistance(distance, distance, distance, distance);
        StopResetEncodersAndRunToPosition();

        int error = Math.abs((int)(distance * 0.95));
        int currentPosition =  Math.abs(RightA.getCurrentPosition());
        Move(0, power, power, 0);

        // keep moving until we get close and the op mode is active.  close is 95% of what we want to get to
        while (LeftA.isBusy() && RightA.isBusy() && LeftB.isBusy() && RightB.isBusy() && (currentPosition < error) && opMode.opModeIsActive()) {
            currentPosition =  Math.abs(RightA.getCurrentPosition());
            opMode.idle();
        }

        Stop();
    }

    public void Move(double left, double right){
        LeftA.setPower(left);
        RightA.setPower(right);
        LeftB.setPower(left);
        RightB.setPower(right);
    }

    public void Move(double fl, double fr, double bl, double br) {
        LeftA.setPower(fl);
        RightA.setPower(fr);
        LeftB.setPower(bl);
        RightB.setPower(br);
    }

    public void Stop() {
        LeftA.setPower(0);
        RightA.setPower(0);
        LeftB.setPower(0);
        RightB.setPower(0);
    }

    public void setCurrentPower(double cp){
        _currentPower = Math.min(1.0, Math.abs(cp));
    }

    //
    // This method will calculate a power based on the current position and our maximum distance.
    // This a very simple motion profile method that uses distance to figure out different power
    // levels. More complex systems use derivitaves and time.
    // We want to ramp up the speed to a flat maxPower and then ramp down to zero.  We did this
    // to prevent the robot from losing traction and jerking.  We only use this when the robot motors are in
    // the run with encoders mode as the internal PID is being used for that instead of helping us
    // with distance.  So this is our real simple PID.  To learn more about what PID is, visit
    // https://en.wikipedia.org/wiki/PID_controller and to learn more about motion profiling visit
    //
    //
    private double CalculateRampPower(double maxPower, int distance, double currentDistance) {
        // out cutoffs on distance for this step up are:
        //  0-10% - .6 of power
        // 10-20% - .85 of power
        // 20-80% - full power
        // 80-90% - .85 power
        // 90-100% - .6 power
        if (currentDistance <= (distance * .10)) {
            return .6 * maxPower;
        } else if (currentDistance <= (distance * .20)) {
            return  .85 * maxPower;
        } else if (currentDistance <= (distance * .80)) {
            return maxPower;
        } else if (currentDistance <= (distance * .90)) {
            return .85 * maxPower;
        } else {
            return .6 * maxPower;
        }
    }

    //
    // This is our simple drive method that allows us to drive the robot in teleop
    //
    public void Drive(double leftStickX, double leftStickY, double rightStickY) {
        if (RightA.getMode() != DcMotor.RunMode.RUN_USING_ENCODER) {
            LeftA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        final double x = Math.pow(-leftStickX, 3.0);
        final double y = Math.pow(leftStickY, 3.0);

        final double rotation = Math.pow(-rightStickY, 3.0);
        final double direction = Math.atan2(x, y);
        final double speed = Math.min(1.0, Math.sqrt(x * x + y * y));

        final double fl = speed * Math.sin(direction + Math.PI / 4.0) + rotation;
        final double fr = speed * Math.cos(direction + Math.PI / 4.0) - rotation;
        final double bl = speed * Math.cos(direction + Math.PI / 4.0) + rotation;
        final double br = speed * Math.sin(direction + Math.PI / 4.0) - rotation;

        LeftA.setPower(-fl * _currentPower);
        RightA.setPower(-fr * _currentPower);
        LeftB.setPower(-bl * _currentPower);
        RightB.setPower(-br * _currentPower);
    }

    public DcMotor getFL() {
        return LeftA;
    }

    public DcMotor getFR() {
        return RightA;
    }

    public DcMotor getBL() {
        return LeftB;
    }

    public DcMotor getBR() {
        return RightB;
    }

    public void assistedDrive(double x, double y, double rot, double heading){

//        Reversing the polarities because of the way joysticks work
        y *= -1;
        rot *= -1;
        rot = (rot > 1)? 1: (rot < -1)? -1 : rot;

//        The + Math.PI / 4.0 is to account for the strafing wheels
        final double direction = Math.atan2(y, x) + Math.PI / 4.0;

//        The local direction is to account for the direction the robot is pointing in relative to the field
        final double localDirection = Math.toRadians(heading) - direction;
        final double speed = Math.min(1.0, Math.sqrt(x * x + y * y));

//        motorMax is to have a max requested power value that the code can
//        use to divert power from moving to rotate as well without
//        going past the max limit for power
        final double motorMax = (speed + Math.abs(rot));

//        Setting moving values while regulating them to save power for rotation
        double fl = ((motorMax - Math.abs(rot)) / motorMax) * speed * Math.sin(localDirection);
        double fr = ((motorMax - Math.abs(rot)) / motorMax) * speed * Math.cos(localDirection);
        double bl = ((motorMax - Math.abs(rot)) / motorMax) * speed * Math.cos(localDirection);
        double br = ((motorMax - Math.abs(rot)) / motorMax) * speed * Math.sin(localDirection);

//        Adding rotation values while regulating them to save power for moving
        fl += ((motorMax - speed) / motorMax) * rot;
        fr -= ((motorMax - speed) / motorMax) * rot;
        bl += ((motorMax - speed) / motorMax) * rot;
        br -= ((motorMax - speed) / motorMax) * rot;

        fl *= _currentPower;
        fr *= _currentPower;
        bl *= _currentPower;
        br *= _currentPower;

//        Capping the values to keep them from going over max speed
        fl = fl > 1? 1 : fl < -1? -1 : fl;
        fr = fr > 1? 1 : fr < -1? -1 : fr;
        bl = bl > 1? 1 : bl < -1? -1 : bl;
        br = br > 1? 1 : br < -1? -1 : br;

        LeftA.setPower(-fl);
        RightA.setPower(-fr);
        LeftB.setPower(-bl);
        RightB.setPower(-br);

    }

    //
    // These are our helper method to set the motors to what we need for the other steps
    // They are the three different ways you can run a motor
    //
    public void StopResetEncodersAndRunToPosition() {
        LeftA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RightA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LeftB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RightB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void StopResetEncodersRunWithEncoderAndBrakekOn() {
        LeftA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void StopResetEncodersAndRunWithoutEncoders() {
        LeftA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RightA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        LeftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    //
    // This method helps us set the distance on all the motors for things like turning and
    // moving forward.
    //
    private void SetDistance(int lf, int lb, int rf, int rb) {
        LeftA.setTargetPosition(lf);
        RightA.setTargetPosition(rf);
        LeftB.setTargetPosition(lb);
        RightB.setTargetPosition(rb);
    }
}
