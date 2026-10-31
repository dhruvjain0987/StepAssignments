package PracticeProblems.PPWeek5;
import java.util.*;

class Week5P3PatientVitals {
    private ArrayList<Double> readings = new ArrayList<>();

    Week5P3PatientVitals(double[] initialReadings) {
        for (double r : initialReadings)
            recordReading(r);
    }

    void recordReading(double reading) {
        if (reading > 0 && reading <= 45)
            readings.add(reading);
    }

    double getAverage() {
        if (readings.isEmpty()) return 0;

        double sum = 0;
        for (double r : readings)
            sum += r;

        return sum / readings.size();
    }

    double[] getAllReadings() {
        double[] result = new double[readings.size()];

        for (int i = 0; i < readings.size(); i++)
            result[i] = readings.get(i);

        return result;
    }
}

public class Prob3 {
    public static void main(String[] args) {
        Week5P3PatientVitals v =
            new Week5P3PatientVitals(new double[]{36.5, -2, 37.1});

        System.out.println(Arrays.toString(v.getAllReadings()));

        double[] copy = v.getAllReadings();
        copy[0] = 999;

        System.out.println(v.getAllReadings()[0]);
        System.out.println("Average: " + v.getAverage());
    }
}