package Week8;
import java.util.*;

interface ScoringRule {
    double calculate(int idea, int execution, int presentation);
}

class Innovation implements ScoringRule {
    public double calculate(int i, int e, int p) {
        return i * 0.5 + e * 0.3 + p * 0.2;
    }
}

class OpenTrack implements ScoringRule {
    public double calculate(int i, int e, int p) {
        return (i + e + p) / 3.0;
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }
}

class Project {
    String name;
    Team team;
    double score;
    boolean scored;

    Project(String name, Team team) {
        this.name = name;
        this.team = team;
    }
}

class Team {
    String name;
    ArrayList<Student> members;
    ScoringRule rule;
    Project project;

    Team(String name, ArrayList<Student> members, ScoringRule rule) {
        this.name = name;
        this.members = members;
        this.rule = rule;
    }
}

class Hackathon {
    ArrayList<Team> teams = new ArrayList<>();
    HashSet<String> registeredStudents = new HashSet<>();
    boolean published = false;

    void register(Team team) {
        if (published) {
            System.out.println("Registration closed.");
            return;
        }

        if (team.members.size() < 2 || team.members.size() > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return;
        }

        for (Student s : team.members) {
            if (registeredStudents.contains(s.name)) {
                System.out.println("Registration failed: Student already belongs to a team.");
                return;
            }
        }

        teams.add(team);
        for (Student s : team.members) {
            registeredStudents.add(s.name);
        }

        System.out.println("Team " + team.name + " registered (" +
                team.members.size() + " members, " +
                (team.rule instanceof Innovation ? "Innovation" : "Open") + " track).");
    }

    void submit(Team team, String name) {
        if (published || !teams.contains(team) || team.project != null) {
            System.out.println("Submission failed.");
            return;
        }

        team.project = new Project(name, team);
        System.out.println("Project '" + name + "' submitted by " + team.name + ".");
    }

    void score(Project project, int i, int e, int p) {
        if (published) {
            System.out.println("Rescore rejected: Results have already been published.");
            return;
        }

        if (project == null || i < 0 || i > 10 ||
                e < 0 || e > 10 || p < 0 || p > 10) {
            System.out.println("Invalid score.");
            return;
        }

        project.score = project.team.rule.calculate(i, e, p);
        project.scored = true;
        System.out.println("Score recorded for '" + project.name + "'.");
    }

    void publish() {
        published = true;

        for (Team t : teams) {
            if (t.project != null && t.project.scored) {
                System.out.printf("Final score: %.2f%n", t.project.score);
            }
        }

        System.out.println("Results published.");
    }
}

public class Prob1 {
    public static void main(String[] args) {
        Hackathon h = new Hackathon();

        Team t1 = new Team("ByteBusters",
                new ArrayList<>(Arrays.asList(
                        new Student("Asha"),
                        new Student("Ravi"),
                        new Student("Neha"))),
                new Innovation());

        Team t2 = new Team("SoloCoder",
                new ArrayList<>(Arrays.asList(new Student("Kiran"))),
                new OpenTrack());

        h.register(t1);
        h.register(t2);

        h.submit(t1, "SmartAttend");

        h.score(t1.project, 8, 7, 9);
        h.publish();
        h.score(t1.project, 10, 7, 9);
    }
}