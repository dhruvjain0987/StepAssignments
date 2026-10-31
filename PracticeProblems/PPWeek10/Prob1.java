package PracticeProblems.PPWeek10;


interface Week10P1Device {
    void turnOn();
}

interface Week10P1Dimmable {
    void dim(int level);
}

interface Week10P1Schedulable {
    void schedule(String time);
}

interface Week10P1EnergyMonitor {
    void showEnergy();
}

class Week10P1Light implements Week10P1Device, Week10P1Dimmable, Week10P1Schedulable {
    String id;
    Week10P1Light(String id) { this.id = id; }

    public void turnOn() { System.out.println(id + " is ON"); }
    public void dim(int level) { System.out.println(id + " dimmed to " + level); }
    public void schedule(String time) { System.out.println(id + " scheduled " + time); }
}

class Week10P1Fan implements Week10P1Device, Week10P1Schedulable {
    String id;
    Week10P1Fan(String id) { this.id = id; }

    public void turnOn() { System.out.println(id + " is ON"); }
    public void schedule(String time) { System.out.println(id + " scheduled " + time); }
}

class Week10P1Plug implements Week10P1Device, Week10P1EnergyMonitor {
    String id;
    int energy;
    Week10P1Plug(String id, int energy) {
        this.id = id;
        this.energy = energy;
    }

    public void turnOn() { System.out.println(id + " is ON"); }
    public void showEnergy() { System.out.println(id + " energy " + energy + " kWh"); }
}

public class Prob1 {
    public static void main(String[] args) {
        Week10P1Light light = new Week10P1Light("L1");
        Week10P1Fan fan = new Week10P1Fan("F1");
        Week10P1Plug plug = new Week10P1Plug("P1", 12);

        light.turnOn();
        light.dim(40);

        if (fan instanceof Week10P1Dimmable)
            ((Week10P1Dimmable) fan).dim(30);
        else
            System.out.println("F1 rejected: DIM unsupported");

        fan.schedule("22:00");
        plug.showEnergy();

        if (light instanceof Week10P1EnergyMonitor)
            light.showEnergy();
        else
            System.out.println("L1 rejected: ENERGY unsupported");
    }
}