package org.firstinspires.ftc.teamcode.config.subsystem;


import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.config.RobotConstants;
import org.firstinspires.ftc.teamcode.config.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.config.pedroPathing.localization.PoseUpdater;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.RobotConstants;

public class VisionSubsystem {

    private Limelight3A limelight;
    public Pose pose;
    private double robotYaw;
    private double x;
    private double y;
    private Telemetry telemetry;
    public PoseUpdater poseUpdater;

    public VisionSubsystem (HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
    }


    public void start () {
        limelight.pipelineSwitch(0);
        limelight.setPollRateHz(100); // This sets how often we ask Limelight for data (100 times per second)
        limelight.start();
    }

    public void switchPipeline (String pipelineName) {
        switch (pipelineName.toLowerCase()) {
            case "apriltag":
                limelight.pipelineSwitch(RobotConstants.apriltag);
                telemetry.addData("AprilTag", "Switched to AprilTag Pipeline");
                localizationMT2(poseUpdater.getPose().getHeading());
                break;
            case "blue":
                limelight.pipelineSwitch(RobotConstants.blue);
                telemetry.addData("Pipeline", "Switched to Blue Pipeline");
                distanceCalc();
                break;
            case "red":
                limelight.pipelineSwitch(RobotConstants.red);
                telemetry.addData("Pipeline", "Switched to Red Pipeline");
                distanceCalc();
                break;
            case "yellow":
                limelight.pipelineSwitch(RobotConstants.yellow);
                telemetry.addData("Pipeline", "Switched to Red Pipeline");
                distanceCalc();
                break;
            default:
                limelight.pipelineSwitch(RobotConstants.obstacleDetection);
                obstacleDetection();
        }
        telemetry.update();
    }

    public double distanceCalc () {

        LLResult result = limelight.getLatestResult();
        if (result != null && result.isValid()) {
            double ty = result.getTy(); // How far up or down the target is (degrees)

            telemetry.addData("Target Y", ty);

            double angleToGoalDegrees = RobotConstants.limelightMountAngleDegrees + ty;
            double angleToGoalRadians = angleToGoalDegrees * (3.14159 / 180.0);

            //calculate distance
            double distanceFromLimelightToGoalInches = (RobotConstants.goalHeightInches - RobotConstants.limelightLensHeightInches) / Math.tan(angleToGoalRadians);

            return distanceFromLimelightToGoalInches;

        } else {
            telemetry.addData("Limelight", "No Targets");
        }

        //if nothing is detected return 0
        return 0;

    }

    public void obstacleDetection () {



    }

    public void localizationMT2 (double heading) {
        LLResult result = limelight.getLatestResult();
        robotYaw = heading;
        limelight.updateRobotOrientation(robotYaw);
        if (result != null && result.isValid()) {
            Pose3D botpose_mt2 = result.getBotpose_MT2();
            if (botpose_mt2 != null) {
                x = botpose_mt2.getPosition().x + 72;
                y = botpose_mt2.getPosition().y + 72;
                robotYaw = botpose_mt2.getOrientation().getYaw();
                telemetry.addData("MT2 Location:", "(" + x + ", " + y + ")");

                pose.setX(x);
                pose.setY(y);
                pose.setHeading(robotYaw);
            }
        }

        else {
            telemetry.addData("Limelight", "No Targets");
        }

    }

    public void stop () {
        limelight.stop();
    }
}
