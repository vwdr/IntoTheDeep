package org.firstinspires.ftc.teamcode.config.subsystem;


import org.firstinspires.ftc.teamcode.config.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.config.pedroPathing.localization.PoseUpdater;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;


public class VisionSubsystem {

    private Limelight3A limelight;

    public Pose pose;

    public PoseUpdater poseUpdater;

    public VisionSubsystem (HardwareMap, hardwareMap) {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100); // This sets how often we ask Limelight for data (100 times per second)
        limelight.start();
    }

    public void AprilTagPipeline () {

        limelight.pipelineSwitch(0);
        limelight.start();

    }

    public void LocalizationMT2 () {
        LLResult result = limelight.getLatestResult();
        double robotYaw = imu.getAngularOrientation().firstAngle;
        limelight.updateRobotOrientation(robotYaw);
        if (result != null && result.isValid()) {
            Pose3D botpose_mt2 = result.getBotpose_MT2();
            if (botpose_mt2 != null) {
                double x = botpose_mt2.getPosition().x;
                double y = botpose_mt2.getPosition().y;
                telemetry.addData("MT2 Location:", "(" + x + ", " + y + ")");
            }
        }

        poseUpdater.setPose(botpose_mt2);
    }
}
