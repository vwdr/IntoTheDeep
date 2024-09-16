package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.config.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.config.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.config.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.config.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.config.pedroPathing.pathGeneration.Path;
import org.firstinspires.ftc.teamcode.config.pedroPathing.pathGeneration.PathChain;
import org.firstinspires.ftc.teamcode.config.pedroPathing.pathGeneration.Point;
import org.firstinspires.ftc.teamcode.config.pedroPathing.util.Timer;

@Autonomous(name = "Auto Blue Closer Bucket", group = "Autonomous")
public class AutoBlueCloserBucket extends OpMode {

    private Follower follower;
    private Timer pathTimer;
    private int pathState;

    // Define the start pose of the robot (change as per actual start position)
    private Pose startPose = new Pose(9.757, 84.983, Math.toRadians(180));

    // Paths for each line as per provided coordinates
    private Path line1, line2, line3, line4, line5, line6, line7, line8, line9, line10;

    @Override
    public void init() {
        // Initialize the Follower and Timer
        pathTimer = new Timer();
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);

        // Build the paths using the given coordinates
        buildPaths();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void start() {
        pathTimer.resetTimer();
        setPathState(1); // Begin with the first path
    }

    @Override
    public void loop() {
        follower.update();
        autonomousPathUpdate();
        telemetry.addData("Path State", pathState);
        telemetry.addData("X", follower.getPose().getX());
        telemetry.addData("Y", follower.getPose().getY());
        telemetry.addData("Heading", follower.getPose().getHeading());
        telemetry.update();
    }

    @Override
    public void stop() {
        // Any cleanup code, if necessary
    }

    /** Build the paths based on the coordinates and control points */
    public void buildPaths() {
        // Line 1: Start to (30.3664, 71.31298) with linear heading from 180 to 180 degrees
        line1 = new Path(new BezierLine(new Point(startPose), new Point(30.36641221374046, 71.31297709923665, Point.CARTESIAN)));
        line1.setConstantHeadingInterpolation(Math.toRadians(180));

        // Line 2: (30.3664, 71.31298) to (42.3206, 77.4962) with linear heading from 180 to 0 degrees
        line2 = new Path(new BezierLine(new Point(30.3664, 71.31298, Point.CARTESIAN), new Point(42.3206106870229, 77.49618320610686, Point.CARTESIAN)));
        line2.setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(0));

        // Line 3: (42.3206, 77.4962) to (14.5649, 123.5267) with linear heading from 0 to -45 degrees
        line3 = new Path(new BezierLine(new Point(42.3206, 77.4962, Point.CARTESIAN), new Point(14.564885496183207, 123.52671755725191, Point.CARTESIAN)));
        line3.setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-45));

        // Line 4: (14.5649, 123.5267) to (36, 121.8779) with control point at (27.0687, 117.2061)
        line4 = new Path(new BezierCurve(new Point(14.5649, 123.5267, Point.CARTESIAN), new Point(27.068702290076335, 117.20610687022901, Point.CARTESIAN), new Point(36, 121.87786259541986, Point.CARTESIAN)));
        line4.setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(0));

        // Line 5: (36, 121.8779) to (18.9618, 130.1221) with control point at (27.7557, 111.7099)
        line5 = new Path(new BezierCurve(new Point(36, 121.8779, Point.CARTESIAN), new Point(27.755725190839694, 111.70992366412213, Point.CARTESIAN), new Point(18.9618320610687, 130.12213740458014, Point.CARTESIAN)));
        line5.setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-45));

        // Line 6: (18.9618, 130.1221) to (36, 131.6336) with control point at (27.2061, 120.9160)
        line6 = new Path(new BezierCurve(new Point(18.9618, 130.1221, Point.CARTESIAN), new Point(27.206106870229007, 120.91603053435115, Point.CARTESIAN), new Point(36, 131.63358778625954, Point.CARTESIAN)));
        line6.setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(0));

        // Line 7: (36, 131.6336) to (18.8244, 130.1221) with control point at (27.7557, 119.1298)
        line7 = new Path(new BezierCurve(new Point(36, 131.6336, Point.CARTESIAN), new Point(27.755725190839694, 119.12977099236642, Point.CARTESIAN), new Point(18.824427480916032, 130.12213740458014, Point.CARTESIAN)));
        line7.setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-45));

        // Line 8: (18.8244, 130.1221) to (35.0382, 142.0763) with control point at (16.62595419847328, 142.4885496183206)
        line8 = new Path(new BezierCurve(new Point(18.8244, 130.1221, Point.CARTESIAN), new Point(16.62595419847328, 142.4885496183206, Point.CARTESIAN), new Point(35.038167938931295, 142.0763358778626, Point.CARTESIAN)));
        line8.setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(0));

        // Line 9: (35.0382, 142.0763) to (18.8244, 130.1221) with control point at (16.4885, 142.9008)
        line9 = new Path(new BezierCurve(new Point(35.0382, 142.0763, Point.CARTESIAN), new Point(16.48854961832061, 142.90076335877862, Point.CARTESIAN), new Point(18.824427480916032, 130.12213740458014, Point.CARTESIAN)));
        line9.setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(-45));

        // Line 10: (18.8244, 130.1221) to (58.5344, 97.9695) with linear heading from -45 to 0 degrees
        line10 = new Path(new BezierLine(new Point(18.8244, 130.1221, Point.CARTESIAN), new Point(58.534351145038165, 97.96946564885496, Point.CARTESIAN)));
        line10.setLinearHeadingInterpolation(Math.toRadians(-45), Math.toRadians(0));
    }

    public void autonomousPathUpdate() {
        switch (pathState) {
            case 1:
                follower.followPath(line1);
                setPathState(2);
                break;
            case 2:
                if (follower.isBusy()) {
                    follower.followPath(line2);
                    setPathState(3);
                }
                break;
            case 3:
                if (follower.isBusy()) {
                    follower.followPath(line3);
                    setPathState(4);
                }
                break;
            case 4:
                if (follower.isBusy()) {
                    follower.followPath(line4);
                    setPathState(5);
                }
                break;
            case 5:
                if (follower.isBusy()) {
                    follower.followPath(line5);
                    setPathState(6);
                }
                break;
            case 6:
                if (follower.isBusy()) {
                    follower.followPath(line6);
                    setPathState(7);
                }
                break;
            case 7:
                if (follower.isBusy()) {
                    follower.followPath(line7);
                    setPathState(8);
                }
                break;
            case 8:
                if (follower.isBusy()) {
                    follower.followPath(line8);
                    setPathState(9);
                }
                break;
            case 9:
                if (follower.isBusy()) {
                    follower.followPath(line9);
                    setPathState(10);
                }
                break;
            case 10:
                if (follower.isBusy()) {
                    follower.followPath(line10);
                    setPathState(11);
                }
                break;
            case 11:
                // Path sequence is complete
                break;
        }
    }


    /** Update the path state */
    public void setPathState(int state) {
        pathState = state;
        pathTimer.resetTimer();
    }
}
