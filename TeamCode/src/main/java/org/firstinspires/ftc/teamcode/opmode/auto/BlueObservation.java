package org.firstinspires.ftc.teamcode.opmode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.config.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.config.runmodes.Auto;
import org.firstinspires.ftc.teamcode.config.util.action.Actions;
import org.firstinspires.ftc.teamcode.config.util.action.SequentialAction;
import org.firstinspires.ftc.teamcode.config.subsystem.VisionSubsystem;
import org.firstinspires.ftc.teamcode.config.subsystem.LEDSubsystem;

@Autonomous(name="BlueObservation", group="B")
public class BlueObservation extends OpMode {


    public int pathState;
    public Auto auto;
    public VisionSubsystem visionSubsystem;
    public LEDSubsystem ledSubsystem;

    @Override
    public void init() {
        auto = new Auto(hardwareMap, telemetry, new Follower(hardwareMap), true, false);
    }

    @Override
    public void start() {
        auto.start();
        visionSubsystem.start();
        ledSubsystem.lighting();
        setPathState(0);
    }

    @Override
    public void loop() {
        auto.update();
        pathUpdate();

        telemetry.addData("state", pathState);
        telemetry.addData("x", auto.follower.getPose().getX());
        telemetry.addData("y", auto.follower.getPose().getY());
        telemetry.addData("h", auto.follower.getPose().getHeading());
    }

    public void pathUpdate() {
        switch (pathState) {
            case 0:
                Actions.runBlocking(auto.claw.release);
                Actions.runBlocking(auto.arm.toDefault);
                auto.follower.followPath(auto.preload);
                setPathState(1);
                break;
            case 1:
                if(!auto.follower.isBusy()) {
                    auto.follower.followPath(auto.element1);
                    Actions.runBlocking(auto.claw.intake);
                    setPathState(2);
                }
                break;
            case 2:
                if(!auto.follower.isBusy()) {
                    auto.follower.followPath(auto.score1);
                    Actions.runBlocking(
                            new SequentialAction(
                                    auto.arm.toBlueHighBasket,
                                    auto.claw.release
                            )
                    );

                    setPathState(3);
                }
                break;
            case 3:
                if(!auto.follower.isBusy()) {
                    Actions.runBlocking(
                            new SequentialAction(
//                                    auto.transfer,
//                                    auto.lift.toLowBucket
                            )
                    );
//
//                    if (auto.lift.isAtTarget()) {
//                        Actions.runBlocking(auto.box.toScoring);
//                    }

                    auto.follower.followPath(auto.element2);
                    setPathState(4);
                }
                break;
            case 4:
                if(!auto.follower.isBusy()) {
                    auto.follower.followPath(auto.score2);
                    setPathState(5);
                }
                break;
            case 5:
                if(!auto.follower.isBusy()) {
                    auto.follower.followPath(auto.element3);
                    setPathState(6);
                }
                break;
            case 6:
                if(!auto.follower.isBusy()) {
                    auto.follower.followPath(auto.score3);
                    setPathState(7);
                }
                break;
            case 7:
                if(!auto.follower.isBusy()) {
                    auto.follower.followPath(auto.park);
                    setPathState(-1);
                }
                break;
        }
    }

    public void setPathState(int x) {

        pathState = x;

    }
}