package Week8;
import java.util.*;

interface Capability {
    boolean apply(int value);
}

class PowerCapability implements Capability {
    boolean on = false;

    public boolean apply(int value) {
        if (value != 0 && value != 1) return false;
        on = value == 1;
        return true;
    }
}

class BrightnessCapability implements Capability {
    int brightness;

    public boolean apply(int value) {
        if (value < 0 || value > 100) return false;
        brightness = value;
        return true;
    }
}

class TemperatureCapability implements Capability {
    int temperature;

    public boolean apply(int value) {
        if (value < 16 || value > 30) return false;
        temperature = value;
        return true;
    }
}

class Device {
    String name;
    HashMap<String, Capability> capabilities = new HashMap<>();

    Device(String name) {
        this.name = name;
    }

    void addCapability(String type, Capability capability) {
        capabilities.put(type, capability);
        System.out.println(name + ": " + type + " capability added.");
    }

    boolean execute(String type, int value) {
        Capability c = capabilities.get(type);

        if (c == null) return false;

        if (!c.apply(value)) {
            System.out.println("Rejected: Invalid " + type + " value for " + name + ".");
            return false;
        }

        if (type.equals("Power")) {
            System.out.println(name + ": " + (value == 1 ? "ON" : "OFF") + ".");
        } else if (type.equals("Brightness")) {
            System.out.println(name + ": brightness set to " + value + "%.");
        } else {
            System.out.println(name + ": temperature set to " + value + "°C.");
        }

        return true;
    }
}

class Scene {
    String name;
    ArrayList<String[]> steps = new ArrayList<>();

    Scene(String name) {
        this.name = name;
    }

    void addStep(String type, int value) {
        steps.add(new String[]{type, String.valueOf(value)});
    }

    void run(ArrayList<Device> devices) {
        System.out.println("Scene '" + name + "' started.");
        int count = 0;

        for (String[] step : steps) {
            String type = step[0];
            int value = Integer.parseInt(step[1]);

            for (Device d : devices) {
                if (d.capabilities.containsKey(type)) {
                    if (d.execute(type, value)) {
                        count++;
                    }
                }
            }
        }

        System.out.println("Scene '" + name +
                "' completed: " + count + " actions applied.");
    }
}

public class Prob3 {
    public static void main(String[] args) {
        ArrayList<Device> devices = new ArrayList<>();

        Device ac = new Device("Lab AC");
        ac.addCapability("Power", new PowerCapability());
        ac.addCapability("Temperature", new TemperatureCapability());

        Device lights = new Device("Ceiling Lights");
        lights.addCapability("Power", new PowerCapability());
        lights.addCapability("Brightness", new BrightnessCapability());

        Device projector = new Device("Projector");
        projector.addCapability("Power", new PowerCapability());

        devices.add(ac);
        devices.add(lights);
        devices.add(projector);

        Scene scene = new Scene("Lecture Mode");
        scene.addStep("Power", 1);
        scene.addStep("Brightness", 40);
        scene.addStep("Temperature", 24);

        scene.run(devices);

        ac.execute("Temperature", 12);

        projector.addCapability("Brightness", new BrightnessCapability());
        projector.execute("Brightness", 70);
    }
}