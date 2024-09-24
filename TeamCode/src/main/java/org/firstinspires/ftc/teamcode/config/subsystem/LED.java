package org.firstinspires.ftc.teamcode.config.subsystem;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LED {

    public RevBlinkinLedDriver blinkinLedDriver;
    public String lightmode;

    public void init (HardwareMap hardwareMap) {
        blinkinLedDriver = hardwareMap.get(RevBlinkinLedDriver.class, "blinkin");
    }

    public void lighting () {

        switch(lightmode) {
            case "intake":
                blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
            case "outake":
                blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.ORANGE);
            default:
                blinkinLedDriver.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
        }

    }
}
