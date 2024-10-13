package org.firstinspires.ftc.teamcode.config.runmodes;


import static org.firstinspires.ftc.teamcode.config.util.FieldConstants.*;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.config.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.config.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.config.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.config.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.config.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.config.pedroPathing.pathGeneration.Point;
import org.firstinspires.ftc.teamcode.config.subsystem.VisionSubsystem;

public class  Auto {




    private RobotStart startLocation;



    public Follower follower;
    public Telemetry telemetry;

    public VisionSubsystem vision;

    private boolean isBlue;
    private boolean isBucket;


    public Path preload, element1, score1, element2, score2, element3, score3, park;
    private Pose startPose, preloadPose, element1Pose, element1ControlPose, element2Pose, element2ControlPose, element3Pose, element3ControlPose, elementScorePose, parkControlPose, parkPose;

    public Auto(HardwareMap hardwareMap, Telemetry telemetry, Follower follower, boolean isBlue, boolean isBucket) {

        this.follower = follower;
        this.telemetry = telemetry;

        this.isBlue = isBlue;
        this.isBucket = isBucket;

        startLocation = isBlue ? (isBucket ? RobotStart.BLUE_BUCKET : RobotStart.BLUE_OBSERVATION) : (isBucket ? RobotStart.RED_BUCKET : RobotStart.RED_OBSERVATION);

        vision = new VisionSubsystem(hardwareMap, telemetry);
        createPoses();
        buildPaths();

    }

    public void init() {

    }

    public void init_loop() {

    }

    public void start() {

    }

    public void update() {
        follower.update();

    }

    public void createPoses() {
        switch (startLocation) {
            case BLUE_BUCKET:
                startPose = blueBucketStartPose;
                preloadPose = blueBucketPreloadPose;
                element1ControlPose = blueBucketLeftSampleControlPose;
                element1Pose = blueBucketLeftSamplePose;
                element2ControlPose = blueBucketMidSampleControlPose;
                element2Pose = blueBucketMidSamplePose;
                element3ControlPose = blueBucketRightSampleControlPose;
                element3Pose = blueBucketRightSamplePose;
                elementScorePose = blueBucketScorePose;
                parkControlPose = blueBucketParkControlPose;
                parkPose = blueBucketParkPose;
                break;

            case BLUE_OBSERVATION:
                startPose = blueObservationStartPose;
                //parkPose = blueObservationPark;
                break;

            case RED_BUCKET:
                startPose = redBucketStartPose;
                //parkPose = redBucketPark;
                break;

            case RED_OBSERVATION:
                startPose = redObservationStartPose;
                //parkPose = redObservationPark;
                break;
        }

    }

    public void buildPaths() {
        follower.setStartingPose(startPose);

        preload = new Path(new BezierLine(new Point(startPose), new Point(preloadPose)));
        preload.setLinearHeadingInterpolation(startPose.getHeading(), preloadPose.getHeading());

        element1 = new Path(new BezierCurve(new Point(preloadPose), new Point(element1ControlPose), new Point(element1Pose)));
        element1.setLinearHeadingInterpolation(preloadPose.getHeading(), element1Pose.getHeading());

        score1 = new Path(new BezierLine(new Point(element1Pose), new Point(elementScorePose)));
        score1.setLinearHeadingInterpolation(element1Pose.getHeading(), elementScorePose.getHeading());

        element2 = new Path(new BezierCurve(new Point(element1Pose), new Point(element2ControlPose), new Point(element2Pose)));
        element2.setLinearHeadingInterpolation(element1Pose.getHeading(), element2Pose.getHeading());

        score2 = new Path(new BezierLine(new Point(element2Pose), new Point(elementScorePose)));
        score2.setLinearHeadingInterpolation(element2Pose.getHeading(), elementScorePose.getHeading());

        element3 = new Path(new BezierCurve(new Point(element2Pose), new Point(element3ControlPose), new Point(element3Pose)));
        element3.setLinearHeadingInterpolation(element2Pose.getHeading(), element3Pose.getHeading());

        score3 = new Path(new BezierLine(new Point(element3Pose), new Point(elementScorePose)));
        score3.setLinearHeadingInterpolation(element3Pose.getHeading(), elementScorePose.getHeading());

        park = new Path(new BezierCurve(new Point(elementScorePose), new Point(parkControlPose), new Point(parkPose)));
        park.setLinearHeadingInterpolation(elementScorePose.getHeading(), parkPose.getHeading());
    }

}