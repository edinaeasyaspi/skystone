/*
 * Copyright (c) 2019 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;


public class SkyStoneAutaunomousBlueDep extends TeleOpSkystone
{
    protected OpenCvCamera phoneCam;
    protected Mecanum Mecanum;
    @Override
    public void runOpMode()
    {
        /*
         * Instantiate an OpenCvCamera object for the camera we'll be using.
         * In this sample, we're using the phone's internal camera. We pass it a
         * CameraDirection enum indicating whether to use the front or back facing
         * camera, as well as the view that we wish to use for camera monitor (on
         * the RC phone). If no camera monitor is desired, use the alternate
         * single-parameter constructor instead (commented out below)
         */
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = new OpenCvInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        // OR...  Do Not Activate the Camera Monitor View
        //phoneCam = new OpenCvInternalCamera(OpenCvInternalCamera.CameraDirection.BACK);

        /*
         * Open the connection to the camera device
         */
        phoneCam.openCameraDevice();

        /*
         * Specify the image processing pipeline we wish to invoke upon receipt
         * of a frame from the camera. Note that switching pipelines on-the-fly
         * (while a streaming session is in flight) *IS* supported.
         */
        SamplePipeline pipeline = new SamplePipeline();
        phoneCam.setPipeline(pipeline);

        /*
         * Tell the camera to start streaming images to us! Note that you must make sure
         * the resolution you specify is supported by the camera. If it is not, an exception
         * will be thrown.
         *
         * Also, we specify the rotation that the camera is used in. This is so that the image
         * from the camera sensor can be rotated such that it is always displayed with the image upright.
         * For a front facing camera, rotation is defined assuming the user is looking at the screen.
         * For a rear facing camera or a webcam, rotation is defined assuming the camera is facing
         * away from the user.
         */
        phoneCam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);

        /*
         * Wait for the user to press start on the Driver Station
         */
        Init_Juan();

        Reset_Arm();

        Reset_Arm_Slide();

        waitForStart();
















    }

    /*
     * An example image processing pipeline to be run upon receipt of each frame from the camera.
     * Note that the processFrame() method is called serially from the frame worker thread -
     * that is, a new camera frame will not come in while you're still processing a previous one.
     * In other words, the processFrame() method will never be called multiple times simultaneously.
     *
     * However, the rendering of your processed image to the viewport is done in parallel to the
     * frame worker thread. That is, the amount of time it takes to render the image to the
     * viewport does NOT impact the amount of frames per second that your pipeline can process.
     *
     * IMPORTANT NOTE: this pipeline is NOT invoked on your OpMode thread. It is invoked on the
     * frame worker thread. This should not be a problem in the vast majority of cases. However,
     * if you're doing something weird where you do need it synchronized with your OpMode thread,
     * then you will need to account for that accordingly.
     */
    class SamplePipeline extends OpenCvPipeline
    {
        private Mat mat0;
        private Mat mat1;
        private Mat mat2;

        private Mat mask0;
        private Mat mask1;
        private Mat mask2;

        private boolean madeMats = false;

        private Scalar BLACK = new Scalar(0,0,0);
        private Scalar WHITE = new Scalar(255,255,255);
        private Scalar RED = new Scalar(255, 0, 0);

        private double cx0 = 125;
        private double cy0 = 50;
        private double cx1 = 125;
        private double cy1 = 150;
        private double cx2 = 125;
        private double cy2 = 250;

        private int r = 5;
        private int strokeWidth = 3;

        public SkystoneLocation location = SkystoneLocation.right;

        /*
         * NOTE: if you wish to use additional Mat objects in your processing pipeline, it is
         * highly recommended to declare them here as instance variables and re-use them for
         * each invocation of processFrame(), rather than declaring them as new local variables
         * each time through processFrame(). This removes the danger of causing a memory leak
         * by forgetting to call mat.release(), and it also reduces memory pressure by not
         * constantly allocating and freeing large chunks of memory.
         */

        @Override
        public Mat processFrame(Mat frame)
        {
            int h = frame.height();
            int w = frame.width();

            int type = frame.type();
            if (!madeMats) {
                mask0 = new Mat(h, w, type);
                mask1 = new Mat(h, w, type);
                mask2 = new Mat(h, w, type);
                mat0 = new Mat();
                mat1 = new Mat();
                mat2 = new Mat();
                madeMats = true;
            }

            mask0.setTo(BLACK);
            mask1.setTo(BLACK);
            mask2.setTo(BLACK);

            Imgproc.circle(mask0, new Point(cx0, cy0), r, WHITE, Core.FILLED);
            Imgproc.circle(mask1, new Point(cx1, cy1), r, WHITE, Core.FILLED);
            Imgproc.circle(mask2, new Point(cx2, cy2), r, WHITE, Core.FILLED);

            Core.bitwise_and(mask0, frame, mat0);
            Core.bitwise_and(mask1, frame, mat1);
            Core.bitwise_and(mask2, frame, mat2);

            double val0 = Core.sumElems(mat0).val[0] + Core.sumElems(mat0).val[1] + Core.sumElems(mat0).val[2];
            double val1 = Core.sumElems(mat1).val[0] + Core.sumElems(mat1).val[1] + Core.sumElems(mat1).val[2];
            double val2 = Core.sumElems(mat2).val[0] + Core.sumElems(mat2).val[1] + Core.sumElems(mat2).val[2];

            if (val0 < val1 && val0 < val2) {
                location = SkystoneLocation.right;
            } else if (val1 < val0 && val1 < val2) {
                location = SkystoneLocation.middle;
            } else {
                location = SkystoneLocation.left;
            }

            /*
             * IMPORTANT NOTE: the input Mat that is passed in as a parameter to this method
             * will only dereference to the same image for the duration of this particular
             * invocation of this method. That is, if for some reason you'd like to save a copy
             * of this particular frame for later use, you will need to either clone it or copy
             * it to another Mat.
             */

            /*
             * Draw a simple box around the middle 1/2 of the entire frame
             */
            Scalar s0 = WHITE;
            Scalar s1 = WHITE;
            Scalar s2 = WHITE;

            if (location == SkystoneLocation.right) {
                s0 = RED;
            } else if (location == SkystoneLocation.left) {
                s2 = RED;
            } else {
                s1 = RED;
            }
            //SkyStoneAutonomous
            sleep(1000);
            if (s0 == RED){
                encoderDrive(0,0,0,0);
                encoderDrive(0,0,0,0);
                Part.AndyMark_motor_Lift.setTargetPosition(0);
                Latch();
                Part.AndyMark_motor_Lift.setTargetPosition(50);
                encoderDrive(0,0,0,0);
                encoderDrive(0,0,0,0);
                Part.AndyMark_motor_Lift.setTargetPosition(0);
                UnLatch();
            }else if (s2 == RED){
                encoderDrive(0,0,0,0);
                encoderDrive(0,0,0,0);
                Part.AndyMark_motor_Lift.setTargetPosition(0);
                Latch();
                Part.AndyMark_motor_Lift.setTargetPosition(50);
                encoderDrive(0,0,0,0);
                encoderDrive(0,0,0,0);
                Part.AndyMark_motor_Lift.setTargetPosition(0);
                UnLatch();
            }else{
                encoderDrive(0,0,0,0);

                encoderDrive(0,0,0,0);
                Part.AndyMark_motor_Lift.setTargetPosition(0);
                Latch();
                Part.AndyMark_motor_Lift.setTargetPosition(50);
                encoderDrive(0,0,0,0);
                encoderDrive(0,0,0,0);
                Part.AndyMark_motor_Lift.setTargetPosition(0);
                UnLatch();
            }

            Imgproc.line(frame, new Point(0, 275), new Point(300, 275), new Scalar(0, 255, 0));
            Imgproc.circle(frame, new Point(cx0, cy0), r, s0, Core.FILLED);
            Imgproc.circle(frame, new Point(cx1, cy1), r, s1, Core.FILLED);
            Imgproc.circle(frame, new Point(cx2, cy2), r, s2, Core.FILLED);

            /**
             * NOTE: to see how to get data from your pipeline to your OpMode as well as how
             * to change which stage of the pipeline is rendered to the viewport when it is
             * tapped, please see {@link PipelineStageSwitchingExample}
             */

            return frame;
        }
    }
}
