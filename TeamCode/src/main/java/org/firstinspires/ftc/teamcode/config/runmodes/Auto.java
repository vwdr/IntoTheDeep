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
import org.firstinspires.ftc.teamcode.config.subsystem.ArmSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystem.ClawSubsystem;

public class  Auto {




    private RobotStart startLocation;



    public Follower follower;
    public Telemetry telemetry;

    public VisionSubsystem vision;

    private boolean isBlue;
    private boolean isBucket;

    public ClawSubsystem claw;
    public ClawSubsystem.ClawGrabState clawGrabState;
    public ClawSubsystem.ClawPivotState clawPivotState;

    public ArmSubsystem arm;
    public ArmSubsystem.ArmState armState;


    public Path preload, element1, score1, element2, score2, element3, score3, park;
    private Pose startPose, preloadPose, element1Pose, element1ControlPose, element2Pose, element2ControlPose, element3Pose, element3ControlPose, elementScorePose, elementScoreControlPose, parkControlPose, parkPose;

    public Auto(HardwareMap hardwareMap, Telemetry telemetry, Follower follower, boolean isBlue, boolean isBucket) {

        this.follower = follower;
        this.telemetry = telemetry;

        this.isBlue = isBlue;
        this.isBucket = isBucket;

        startLocation = isBlue ? (isBucket ? RobotStart.BLUE_BUCKET : RobotStart.BLUE_OBSERVATION) : (isBucket ? RobotStart.RED_BUCKET : RobotStart.RED_OBSERVATION);

        vision = new VisionSubsystem(hardwareMap, telemetry);
        arm = new ArmSubsystem(hardwareMap, armState);
        claw = new ClawSubsystem(hardwareMap, clawGrabState, clawPivotState);


        createPoses();
        buildPaths();

    }

    public void init() {

    }

    public void init_loop() {

    }

    public void start() {

        vision.start(); //start limelight

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
                elementScoreControlPose = blueBucketScoreControlPose;
                elementScorePose = blueBucketScorePose;
                parkControlPose = blueBucketParkControlPose;
                parkPose = blueBucketParkPose;
                break;

            case BLUE_OBSERVATION:
                startPose = blueObservationStartPose;
                preloadPose = blueObservationPreloadPose;
                element1ControlPose = blueObservationLeftSampleControlPose;
                element1Pose = blueObservationLeftSamplePose;
                element2ControlPose = blueObservationMidSampleControlPose;
                element2Pose = blueObservationMidSamplePose;
                element3ControlPose = blueObservationRightSampleControlPose;
                element3Pose = blueObservationRightSamplePose;
                elementScoreControlPose = blueObservationScoreControlPose;
                elementScorePose = blueObservationScorePose;
                parkControlPose = blueObservationParkControlPose;
                parkPose = blueObservationParkPose;
                break;

            case RED_BUCKET:
                startPose = redBucketStartPose;
                preloadPose = redBucketPreloadPose;
                element1ControlPose = redBucketLeftSampleControlPose;
                element1Pose = redBucketLeftSamplePose;
                element2ControlPose = redBucketMidSampleControlPose;
                element2Pose = redBucketMidSamplePose;
                element3ControlPose = redBucketRightSampleControlPose;
                element3Pose = redBucketRightSamplePose;
                elementScoreControlPose = redBucketScoreControlPose;
                elementScorePose = redBucketScorePose;
                parkControlPose = redBucketParkControlPose;
                parkPose = redBucketParkPose;
                break;

            case RED_OBSERVATION:
                startPose = redObservationStartPose;
                preloadPose = redObservationPreloadPose;
                element1ControlPose = redObservationLeftSampleControlPose;
                element1Pose = redObservationLeftSamplePose;
                element2ControlPose = redObservationMidSampleControlPose;
                element2Pose = redObservationMidSamplePose;
                element3ControlPose = redObservationRightSampleControlPose;
                element3Pose = redObservationRightSamplePose;
                elementScoreControlPose = redObservationScoreControlPose;
                elementScorePose = redObservationScorePose;
                parkControlPose = redObservationParkControlPose;
                parkPose = redObservationParkPose;
                break;
        }

    }

    public void buildPaths() {
        follower.setStartingPose(startPose);

        preload = new Path(new BezierLine(new Point(startPose), new Point(preloadPose)));
        preload.setLinearHeadingInterpolation(startPose.getHeading(), preloadPose.getHeading());

        element1 = new Path(new BezierCurve(new Point(preloadPose), new Point(element1ControlPose), new Point(element1Pose)));
        element1.setLinearHeadingInterpolation(preloadPose.getHeading(), element1Pose.getHeading());

        score1 = new Path(new BezierCurve(new Point(element1Pose), new Point(elementScoreControlPose), new Point(elementScorePose)));
        score1.setLinearHeadingInterpolation(element1Pose.getHeading(), elementScorePose.getHeading());

        element2 = new Path(new BezierCurve(new Point(element1Pose), new Point(element2ControlPose), new Point(element2Pose)));
        element2.setLinearHeadingInterpolation(element1Pose.getHeading(), element2Pose.getHeading());

        score2 = new Path(new BezierCurve(new Point(element2Pose), new Point(elementScoreControlPose), new Point(elementScorePose)));
        score2.setLinearHeadingInterpolation(element2Pose.getHeading(), elementScorePose.getHeading());

        element3 = new Path(new BezierCurve(new Point(element2Pose), new Point(element3ControlPose), new Point(element3Pose)));
        element3.setLinearHeadingInterpolation(element2Pose.getHeading(), element3Pose.getHeading());

        score3 = new Path(new BezierCurve(new Point(element3Pose), new Point (elementScoreControlPose),new Point(elementScorePose)));
        score3.setLinearHeadingInterpolation(element3Pose.getHeading(), elementScorePose.getHeading());

        park = new Path(new BezierCurve(new Point(elementScorePose), new Point(parkControlPose), new Point(parkPose)));
        park.setLinearHeadingInterpolation(elementScorePose.getHeading(), parkPose.getHeading());
    }

}