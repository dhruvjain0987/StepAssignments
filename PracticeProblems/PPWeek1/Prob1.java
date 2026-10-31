package PracticeProblems.PPWeek1;
import java.util.*;

public class Prob1 {
    static String playRound(String player, String computer) {
        if (player.equalsIgnoreCase(computer)) return "Draw";
        if ((player.equalsIgnoreCase("Rock") && computer.equals("Scissors")) ||
            (player.equalsIgnoreCase("Paper") && computer.equals("Rock")) ||
            (player.equalsIgnoreCase("Scissors") && computer.equals("Paper")))
            return "Player Wins";
        return "Computer Wins";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        String[] moves = {"Rock", "Paper", "Scissors"};
        String[] players = new String[5];
        String[] computers = new String[5];
        String[] results = new String[5];
        int wins = 0, losses = 0, draws = 0;

        for (int i = 0; i < 5; i++) {
            System.out.print("Enter your move: ");
            players[i] = sc.next();
            computers[i] = moves[rand.nextInt(3)];
            results[i] = playRound(players[i], computers[i]);

            if (results[i].equals("Player Wins")) wins++;
            else if (results[i].equals("Computer Wins")) losses++;
            else draws++;
        }

        System.out.println("\nRound\tPlayer\tComputer\tResult");
        for (int i = 0; i < 5; i++)
            System.out.println((i + 1) + "\t" + players[i] + "\t" +
                computers[i] + "\t\t" + results[i]);

        double percentage = wins * 100.0 / 5;
        System.out.println("Wins: " + wins + " | Losses: " + losses +
            " | Draws: " + draws + " | Win % = " + percentage);
    }
}