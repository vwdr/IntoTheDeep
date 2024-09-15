package org.firstinspires.ftc.teamcode.config.util;

import org.firstinspires.ftc.teamcode.config.pedroPathing.localization.Pose;

public class FieldConstants {

    // Blue Bucket Sample Poses
    private static final Pose blueBucketLeftSample = new Pose(2.5+9.75+10.5, 4*24+1.5);
    private static final Pose blueBucketMidSample = new Pose(2.5+9.75, 4*24+1.5);
    private static final Pose blueBucketRightSample = new Pose(2.5, 4*24+1.5);

    // Red Observation Sample Poses
    private static final Pose blueObservationLeftSample = new Pose(144-2.5, 4*24+1.5);
    private static final Pose blueObservationMidSample = new Pose(144-2.5-9.75, 4*24+1.5);
    private static final Pose blueObservationRightSample = new Pose(144-2.5-9.75-10.5, 4*24+1.5);

    // Red Observation Sample Poses
    private static final Pose redObservationLeftSample = new Pose(144-2.5-9.75-10.5, 2*24-2.5);
    private static final Pose redObservationMidSample = new Pose(144-2.5-9.75, 2*24-2.5);
    private static final Pose redObservationRightSample = new Pose(144-2.5, 2*24-2.5);

    // Red Bucket Sample Poses
    private static final Pose redBucketLeftSample = new Pose(2.5, 2*24-2.5);
    private static final Pose redBucketMidSample = new Pose(2.5+9.75, 2*24-2.5);
    private static final Pose redBucketRightSample = new Pose(2.5+9.75+10.5, 2*24-2.5);

}
