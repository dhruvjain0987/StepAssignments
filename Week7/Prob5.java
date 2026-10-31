package Week7;

abstract class HomeDevice {
    private static int counter = 1000;
    private final String serialNumber;

    HomeDevice() {
        counter++;
        serialNumber = "HD-" + counter;
    }

    abstract String activate();

    String getSerialNumber() {
        return serialNumber;
    }
}

interface RemoteControllable {
    String connect(String appId);
}

interface EnergyTrackable {
    double getConsumptionWatts();
}

class WashingMachine extends HomeDevice
        implements RemoteControllable, EnergyTrackable {

    double consumptionWatts;

    WashingMachine(double consumptionWatts) {
        this.consumptionWatts = consumptionWatts;
    }

    String activate() {
        return "Washing machine " + getSerialNumber() +
                " started a cycle";
    }

    public String connect(String appId) {
        return getSerialNumber() + " connected to " + appId;
    }

    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}

class Refrigerator extends HomeDevice implements EnergyTrackable {
    double consumptionWatts;

    Refrigerator(double consumptionWatts) {
        this.consumptionWatts = consumptionWatts;
    }

    String activate() {
        return "Refrigerator " + getSerialNumber() + " activated";
    }

    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}

class MobileApp implements RemoteControllable {
    String appName;

    MobileApp(String appName) {
        this.appName = appName;
    }

    public String connect(String appId) {
        return appName + " connected to " + appId;
    }
}

public class Prob5 {

    static void connectAll(RemoteControllable[] items, String appId) {
        for (RemoteControllable r : items) {
            System.out.println(r.connect(appId));
        }
    }

    static double getConsumptionIfTrackable(HomeDevice d) {
        if (d instanceof EnergyTrackable) {
            EnergyTrackable e = (EnergyTrackable) d;
            return e.getConsumptionWatts();
        }

        return -1;
    }

    public static void main(String[] args) {
        WashingMachine wm = new WashingMachine(500.0);
        Refrigerator fridge = new Refrigerator(150.0);
        MobileApp app = new MobileApp("HomeConnect App");

        System.out.println(wm.activate());
        System.out.println(wm.connect("HomeConnect"));

        System.out.println(getConsumptionIfTrackable(fridge));

        System.out.println(app.connect("HomeConnect"));

        HomeDevice ref = wm;

        System.out.println(getConsumptionIfTrackable(ref));

        connectAll(
                new RemoteControllable[]{wm, app},
                "HomeConnect"
        );
    }
}