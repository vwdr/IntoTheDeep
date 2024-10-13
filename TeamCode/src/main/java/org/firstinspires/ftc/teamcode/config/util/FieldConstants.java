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
    public static final Pose blueBucketStartPose = new Pose(8, 79.5, Math.toRadians(180));
    public static final Pose blueObservationStartPose = new Pose(8, 36, Math.toRadians(180));
    public static final Pose redBucketStartPose = new Pose(144-8, 79.5, 0);
    public static final Pose redObservationStartPose = new Pose(144-8, 36, 0);

    // Blue Preload Poses
    public static final Pose blueBucketPreloadPose = new Pose(35.75, 79.5, Math.toRadians(180));

    // Blue Bucket Sample Poses
    public static final Pose blueBucketLeftSamplePose = new Pose(40, 116, Math.toRadians(45));
    public static final Pose blueBucketLeftSampleControlPose = new Pose(28, 108);
    public static final Pose blueBucketMidSamplePose = new Pose(46, 126, Math.toRadians(90));
    public static final Pose blueBucketMidSampleControlPose = new Pose(47.5, 110);
    public static final Pose blueBucketRightSamplePose = new Pose(47.75, 130, Math.toRadians(90));
    public static final Pose blueBucketRightSampleControlPose = new Pose(46, 101);

    public static final Pose blueBucketScorePose = new Pose(16, 128, Math.toRadians(-45));

    public static final Pose blueBucketParkPose = new Pose(65, 97.75, Math.toRadians(90));
    public static final Pose blueBucketParkControlPose = new Pose(60.25, 123.5);

    //POIs
    public static final Pose blueBucketPose = new Pose(0,144);
    public static double highBucketHeight = 43; //inches
    public static double lowBucketHeight = 25.75; //inches
    public static double topChamberHeight = 26;
    public static final Pose redBucketPose = new Pose(144,0);


}