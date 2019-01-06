package org.firstinspires.ftc.teamcode.debugging;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class noHWMap {
    // This hardware map is for testing and should not at all be used in competition
    // as this is hardware map has literally nothing in it at all.

    // Declaring and Initializing hardware map
    HardwareMap noHWMap = null;
    private ElapsedTime timePeriod = new ElapsedTime();

    // Constructor class
    // This is used to create an object that can be used by other classes and can take in input
    // from other classes, for instance such as our OpMode
    public noHWMap() {

    }

    // Initialize standard Hardware interfaces
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        noHWMap = ahwMap;
    }
}
