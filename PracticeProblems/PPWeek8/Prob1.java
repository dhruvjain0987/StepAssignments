package PracticeProblems.PPWeek8;
import java.util.*;

abstract class Week8P1Question {
    String question;
    String correctAnswer;

    Week8P1Question(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    abstract boolean checkAnswer(String answer);
}

class Week8P1MCQ extends Week8P1Question {
    Week8P1MCQ(String question, String correctAnswer) {
        super(question, correctAnswer);
    }

    boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Week8P1TrueFalse extends Week8P1Question {
    Week8P1TrueFalse(String question, String correctAnswer) {
        super(question, correctAnswer);
    }

    boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Week8P1Examination {
    String name;
    ArrayList<Week8P1Question> questions = new ArrayList<>();

    Week8P1Examination(String name) {
        this.name = name;
    }

    void addQuestion(Week8P1Question q) {
        questions.add(q);
    }
}

class Week8P1Attempt {
    Week8P1Examination exam;
    HashMap<Integer, String> answers = new HashMap<>();
    boolean submitted = false;

    Week8P1Attempt(Week8P1Examination exam) {
        this.exam = exam;
    }

    void answerQuestion(int number, String answer) {
        if (submitted || number < 1 || number > exam.questions.size()) {
            System.out.println("Cannot answer question");
            return;
        }

        answers.put(number - 1, answer);
        System.out.println("Question " + number +
            " answered with '" + answer + "'");
    }

    void submit() {
        if (submitted) {
            System.out.println("Attempt already submitted");
            return;
        }

        submitted = true;
        int score = 0;

        for (int i = 0; i < exam.questions.size(); i++) {
            String answer = answers.get(i);

            if (answer != null &&
                exam.questions.get(i).checkAnswer(answer))
                score++;
        }

        System.out.println("Examination '" + exam.name +
            "' submitted successfully.");

        System.out.println("Result for '" + exam.name +
            "' attempt: " + score + "/" + exam.questions.size() +
            " correct");
    }
}

class Week8P1Student {
    String name;
    HashSet<String> submittedExams = new HashSet<>();

    Week8P1Student(String name) {
        this.name = name;
    }

    Week8P1Attempt startExam(Week8P1Examination exam) {
        if (submittedExams.contains(exam.name)) {
            System.out.println("Exam already submitted");
            return null;
        }

        System.out.println("Examination '" + exam.name +
            "' started by " + name + ".");
        return new Week8P1Attempt(exam);
    }

    void submitExam(Week8P1Attempt attempt) {
        if (attempt == null) return;

        attempt.submit();
        submittedExams.add(attempt.exam.name);
    }
}

public class Prob1 {
    public static void main(String[] args) {
        Week8P1Student student = new Week8P1Student("Student");

        Week8P1Examination exam =
            new Week8P1Examination("Math Quiz");

        exam.addQuestion(new Week8P1MCQ("2 + 2?", "A"));
        exam.addQuestion(new Week8P1MCQ("5 + 5?", "B"));

        Week8P1Attempt attempt = student.startExam(exam);

        attempt.answerQuestion(1, "A");
        attempt.answerQuestion(2, "C");

        student.submitExam(attempt);
    }
}