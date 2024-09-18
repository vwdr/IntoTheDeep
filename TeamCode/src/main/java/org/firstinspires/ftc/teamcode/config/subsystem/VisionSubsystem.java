package org.firstinspires.ftc.teamcode.config.subsystem;


import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.config.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.config.pedroPathing.localization.PoseUpdater;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.text.DecimalFormat;

import java.text.DecimalFormat;

public class VisionSubsystem {

    private Limelight3A limelight;

    public Pose pose;

    private NavxMicroNavigationSensor navx;

    private double robotYaw;

    private double x;

    private double y;

    private Telemetry telemetry;

    public PoseUpdater poseUpdater;

    



    public VisionSubsystem (HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        navx = hardwareMap.get(NavxMicroNavigationSensor.class, "navx");
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
    }




    public void Start () {

        limelight.pipelineSwitch(0);
        limelight.setPollRateHz(100); // This sets how often we ask Limelight for data (100 times per second)
        limelight.start();

    }






    public void LocalizationMT2 (double heading) {
        LLResult result = limelight.getLatestResult();
        robotYaw = heading;
        limelight.updateRobotOrientation(robotYaw);
        if (result != null && result.isValid()) {
            Pose3D botpose_mt2 = result.getBotpose_MT2();
            if (botpose_mt2 != null) {
                x = botpose_mt2.getPosition().x + 72;
                y = botpose_mt2.getPosition().y + 72;
                telemetry.addData("MT2 Location:", "(" + x + ", " + y + ")");

                pose.setX(x);
                pose.setY(y);
                pose.setHeading(robotYaw);
            }
        }

    }
}
