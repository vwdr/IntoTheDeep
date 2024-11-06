package org.firstinspires.ftc.teamcode.config.util;

import org.firstinspires.ftc.teamcode.config.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.config.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.config.pedroPathing.pathGeneration.Path;

public class FieldConstants {

    public enum RobotStart {
        BLUE_BUCKET,
        BLUE_OBSERVATION,
        RED_BUCKET,
        RED_OBSERVATION
    }


    //StartPoses
    public static final Pose blueBucketStartPose = new Pose(8, 79.5);
    public static final Pose blueObservationStartPose = new Pose(8, 60);
    public static final Pose redBucketStartPose = new Pose(144-8, 60, Math.toRadians(180));
    public static final Pose redObservationStartPose = new Pose(144-8, 79.5,  Math.toRadians(180));

    // Blue Preload Poses
    public static final Pose blueBucketPreloadPose = new Pose(35.75, 79.5, Math.toRadians(0));
    public static final Pose blueObservationPreloadPose = new Pose(35.75, 60, Math.toRadians(0));

    // Blue Bucket Sample Poses
    public static final Pose blueBucketLeftSamplePose = new Pose(40, 116, Math.toRadians(45));
    public static final Pose blueBucketLeftSampleControlPose = new Pose(28, 108);
    public static final Pose blueBucketMidSamplePose = new Pose(46, 124, Math.toRadians(90));
    public static final Pose blueBucketMidSampleControlPose = new Pose(47.5, 110);
    public static final Pose blueBucketRightSamplePose = new Pose(47.75, 133, Math.toRadians(90));
    public static final Pose blueBucketRightSampleControlPose = new Pose(46, 101);

    public static final Pose blueBucketScorePose = new Pose(16, 128, Math.toRadians(135));
    public static final Pose blueBucketScoreControlPose = new Pose(16,128);

    public static final Pose blueBucketParkPose = new Pose(65, 97.75, Math.toRadians(270));
    public static final Pose blueBucketParkControlPose = new Pose(60.25, 123.5);

    // Blue Observation Sample Poses
    public static final Pose blueObservationLeftSamplePose = new Pose(40, 144-116, Math.toRadians(-45));
    public static final Pose blueObservationLeftSampleControlPose = new Pose(28, 144-108);
    public static final Pose blueObservationMidSamplePose = new Pose(46, 144-124, Math.toRadians(270));
    public static final Pose blueObservationMidSampleControlPose = new Pose(47.5, 144-110);
    public static final Pose blueObservationRightSamplePose = new Pose (47.75, 144-133, Math.toRadians(270));
    public static final Pose blueObservationRightSampleControlPose = new Pose(46, 144-101);

    public static final Pose blueObservationScorePose = new Pose(16, 128, Math.toRadians(-45));
    public static final Pose blueObservationScoreControlPose = new Pose (0, 0);

    public static final Pose blueObservationParkPose = new Pose(65,97.75, Math.toRadians(270));
    public static final Pose blueObservationParkControlPose = new Pose(60.25, 123.5);

    // Red Preload Poses
    public static final Pose redBucketPreloadPose = new Pose(144-35.75, 60, Math.toRadians(180));
    public static final Pose redObservationPreloadPose = new Pose(144-35.75, 79.5, Math.toRadians(180));

    // Red Bucket Sample Poses
    public static final Pose redBucketLeftSamplePose = new Pose(144-40, 144-116, Math.toRadians(225));
    public static final Pose redBucketLeftSampleControlPose = new Pose(144-28, 144-108);
    public static final Pose redBucketMidSamplePose = new Pose(144-46, 144-124, Math.toRadians(270));
    public static final Pose redBucketMidSampleControlPose = new Pose(144-47.5, 144-110);
    public static final Pose redBucketRightSamplePose = new Pose (144-47.75, 144-133, Math.toRadians(270));
    public static final Pose redBucketRightSampleControlPose = new Pose(144-46, 144-101);

    public static final Pose redBucketScorePose = new Pose(144-16, 144-128, Math.toRadians(-45));
    public static final Pose redBucketScoreControlPose = new Pose(144-16, 144-128);

    public static final Pose redBucketParkPose = new Pose(144-65, 144-97.75, Math.toRadians(90));
    public static final Pose redBucketParkControlPose = new Pose(144-60.25, 144-123.5);

    // Red Observation Sample Poses
    public static final Pose redObservationLeftSamplePose = new Pose(144-40, 116, Math.toRadians(135));
    public static final Pose redObservationLeftSampleControlPose = new Pose(144-28, 108);
    public static final Pose redObservationMidSamplePose = new Pose(144-46, 124, Math.toRadians(90));
    public static final Pose redObservationMidSampleControlPose = new Pose (144-47.5,110);
    public static final Pose redObservationRightSamplePose = new Pose(144-47.75, 133, Math.toRadians(90));
    public static final Pose redObservationRightSampleControlPose = new Pose (144-46, 101);

    public static final Pose redObservationScorePose = new Pose(144-16, 144-128, Math.toRadians(-45));
    public static final Pose redObservationScoreControlPose = new Pose(144,144);

    public static final Pose redObservationParkPose = new Pose(144-65, 144-97.75, Math.toRadians(90));
    public static final Pose redObservationParkControlPose = new Pose(144-60.25, 144-123.5);


    //POIs
    public static final Pose blueBucketPose = new Pose(0,144);
    public static double highBucketHeight = 43; //inches
    public static double lowBucketHeight = 25.75; //inches
    public static double topChamberHeight = 26;
    public static final Pose redBucketPose = new Pose(144,0);


}