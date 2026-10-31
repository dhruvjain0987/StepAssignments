package PracticeProblems.PPWeek7;


interface Week7P2Alertable {
    String sendAlert(String message);
}

class Week7P2SecuritySensor {
    protected String zoneName;

    Week7P2SecuritySensor(String zoneName) {
        this.zoneName = zoneName;
    }

    String getZoneName() {
        return zoneName;
    }
}

class Week7P2MotionSensor extends Week7P2SecuritySensor
    implements Week7P2Alertable {

    Week7P2MotionSensor(String zoneName) {
        super(zoneName);
    }

    @Override
    public String sendAlert(String message) {
        return "[" + zoneName + "] " + message;
    }
}

class Week7P2DualZoneMotionSensor extends Week7P2MotionSensor {
    String secondZoneName;

    Week7P2DualZoneMotionSensor(String zoneName, String secondZoneName) {
        super(zoneName);
        this.secondZoneName = secondZoneName;
    }

    @Override
    public String sendAlert(String message) {
        return super.sendAlert(message) +
            " [also covering " + secondZoneName + "]";
    }
}

class Week7P2SmokeDetector implements Week7P2Alertable {
    String deviceId;

    Week7P2SmokeDetector(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String sendAlert(String message) {
        return "[" + deviceId + "] " + message;
    }
}

public class Prob2 {
    static void broadcastAll(Week7P2Alertable[] devices, String message) {
        for (Week7P2Alertable d : devices)
            System.out.println(d.sendAlert(message));
    }

    static String getZoneIfMotionSensor(Week7P2Alertable a) {
        if (a instanceof Week7P2MotionSensor) {
            Week7P2MotionSensor m = (Week7P2MotionSensor) a;
            return m.getZoneName();
        }
        return "Not a motion sensor";
    }

    public static void main(String[] args) {
        Week7P2MotionSensor m =
            new Week7P2MotionSensor("Living Room");

        Week7P2DualZoneMotionSensor d =
            new Week7P2DualZoneMotionSensor("Hallway", "Stairwell");

        Week7P2SmokeDetector s =
            new Week7P2SmokeDetector("SD-01");

        Week7P2Alertable[] devices = {m, d, s};

        broadcastAll(devices, "Motion detected");

        System.out.println(getZoneIfMotionSensor(m));
        System.out.println(getZoneIfMotionSensor(s));
    }
}