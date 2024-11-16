//package org.firstinspires.ftc.teamcode.config.subsystem;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import org.opencv.android.CameraBridgeViewBase;
//import org.opencv.android.OpenCVLoader;
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.MatOfRect;
//import org.opencv.core.Rect;
//import org.opencv.core.Scalar;
//import org.opencv.core.Size;
//import org.opencv.imgproc.Imgproc;
//import org.opencv.objdetect.CascadeClassifier;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//
//@Autonomous(name = "Shadow", group = "Linear Opmode")
//public class Shadow extends LinearOpMode implements CameraBridgeViewBase.CvCameraViewListener2 {
//
//    private CameraBridgeViewBase cameraView;
//    private CascadeClassifier robotDetector;  // Classifier for robot detection
//    private Mat grayscaleImage;
//    private int absoluteRobotSize;
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        // Load the robot detector model (Haar Cascade or similar)
//        loadRobotDetector();
//
//        // Set camera view and start detection
//        cameraView = hardwareMap.get(CameraBridgeViewBase.class, "Webcam 1");
//        cameraView.setCvCameraViewListener(this);
//        cameraView.enableView();
//
//
//        // Disable camera view when done
//        if (cameraView != null) {
//            cameraView.disableView();
//        }
//    }
//
//    // Load robot detection model
//    private void loadRobotDetector() {
//        try {
//            // Haar cascade for robots to a temporary directory
//            InputStream is = hardwareMap.appContext.getResources().openRawResource(R.raw.robot_cascade);  // cascade XML in res/raw
//            File cascadeDir = hardwareMap.appContext.getDir("cascade", android.content.Context.MODE_PRIVATE);
//            File cascadeFile = new File(cascadeDir, "robot_cascade.xml");
//
//            FileOutputStream os = new FileOutputStream(cascadeFile);
//            byte[] buffer = new byte[4096];
//            int bytesRead;
//            while ((bytesRead = is.read(buffer)) != -1) {
//                os.write(buffer, 0, bytesRead);
//            }
//            is.close();
//            os.close();
//
//            // Load the cascade classifier for robot detection
//            robotDetector = new CascadeClassifier(cascadeFile.getAbsolutePath());
//
//            if (robotDetector.empty()) {
//                telemetry.addData("Error", "Failed to load robot detector.");
//                robotDetector = null;
//            } else {
//                telemetry.addData("Info", "Robot detector loaded successfully.");
//            }
//
//            cascadeDir.delete();
//
//        } catch (Exception e) {
//            telemetry.addData("Error", "Failed to load cascade: " + e.getMessage());
//        }
//    }
//
//    // OpenCV frame callback for processing camera frames
//    @Override
//    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
//        Mat rgbaFrame = inputFrame.rgba();  // RGB frame from camera
//        grayscaleImage = inputFrame.gray();  // Grayscale image for detection
//
//        // Set the minimum robot size for detection
//        if (absoluteRobotSize == 0) {
//            int height = grayscaleImage.rows();
//            if (Math.round(height * 0.2f) > 0) {
//                absoluteRobotSize = Math.round(height * 0.2f);
//            }
//        }
//
//        // Detect robots using the classifier
//        MatOfRect robots = new MatOfRect();
//        if (robotDetector != null) {
//            robotDetector.detectMultiScale(grayscaleImage, robots, 1.1, 2, 2,
//                    new Size(absoluteRobotSize, absoluteRobotSize), new Size());
//        }
//
//        // Draw rectangles around detected robots and estimate distance
//        Rect[] robotsArray = robots.toArray();
//        for (Rect robot : robotsArray) {
//            Imgproc.rectangle(rgbaFrame, robot.tl(), robot.br(), new Scalar(0, 255, 0, 255), 3);
//            telemetry.addData("Robot Detected", "Size: " + robot.width + "x" + robot.height);
//
//            // Estimate distance from the robot
//            double distance = estimateDistance(robot.width, robot.height);
//            telemetry.addData("Estimated Distance", distance + " cm");
//
//            // Stop the robot if it's too close
//            if (distance < 50) {  // Example threshold
//                haltRobot();
//            } else {
//                continueRobot();
//            }
//        }
//
//        telemetry.update();
//        return rgbaFrame;  // Return the modified frame for display
//    }
//
//    // Function to estimate distance based on bounding box size (simple heuristic)
//    private double estimateDistance(int width, int height) {
//        // Larger bounding boxes mean the robot is closer; adjust the formula as needed
//        double referenceWidth = 100.0;  // Adjust based on known distance and size
//        double referenceDistance = 100.0;  // Known distance in cm for reference width
//        return (referenceWidth / width) * referenceDistance;
//    }
//
//    // Halt the robot by stopping the motors
//    private void haltRobot() {
//        telemetry.addData("Status", "Robot halted due to proximity");
//    }
//
//    // Continue robot movement by setting motors to full speed
//    private void continueRobot() {
//
//        telemetry.addData("Status", "Robot moving");
//    }
//
//    // Override onCameraViewStarted and onCameraViewStopped
//    @Override
//    public void onCameraViewStarted(int width, int height) {
//        grayscaleImage = new Mat();
//    }
//
//    @Override
//    public void onCameraViewStopped() {
//        if (grayscaleImage != null) {
//            grayscaleImage.release();
//        }
//    }
//}
