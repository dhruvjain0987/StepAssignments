package Week7;

interface Exportable {
    String exportData();
}

class ExportCounter {
    static int count = 0;
}

class ReportGenerator implements Exportable {
    String reportName;

    ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    public String exportData() {
        ExportCounter.count++;
        return "Exported report: " + reportName;
    }

    static int getTotalExports() {
        return ExportCounter.count;
    }
}

class UserProfile implements Exportable {
    String username;

    UserProfile(String username) {
        this.username = username;
    }

    public String exportData() {
        ExportCounter.count++;
        return "Exported profile: " + username;
    }

    static int getTotalExports() {
        return ExportCounter.count;
    }
}

public class Prob2 {

    static void exportAll(Exportable[] items) {
        for (Exportable e : items) {
            System.out.println(e.exportData());
        }
    }

    public static void main(String[] args) {
        ReportGenerator r = new ReportGenerator("Sales Q1");
        UserProfile u = new UserProfile("jane_doe");

        System.out.println(r.exportData());
        System.out.println(u.exportData());

        Exportable ref = r;

        exportAll(new Exportable[]{ref, u});

        System.out.println(ReportGenerator.getTotalExports());
    }
}