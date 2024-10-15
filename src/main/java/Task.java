/**
 * Authors: Dawson Xiong, Gary Wang
 * Date completed: 28 January 2022
 * Program description: This is the class that is responsible for generating the objectives that user has to reach in order to win the game.
 * Once user presses the only button, the game starts.
 */
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;

public class Task {

    // Declare the task that will be chosen and the number
    private static String strTask;
    private static int strNumber;

    public Task() {
        // Create frame
        JFrame frame = new JFrame("Task");

        // Call method for random task
        strTask = randomTask();
        strNumber = randomNumber(strTask);

        // Import and declare images
        ImageIcon logoLarge = new ImageIcon("src\\main\\resources\\images\\logo.png");
        ImageIcon okImage = new ImageIcon("src\\main\\resources\\images\\okButton.jpg");
        ImageIcon bgImage = new ImageIcon("src\\main\\resources\\images\\taskBg.jpg");
        ImageIcon objectiveImage = new ImageIcon("src\\main\\resources\\images\\objectiveBg.jpg");

        // Declare label
        JLabel objective;

        // Set objective text depending on outcome
        if (strTask.equals("score")) {
            objective = new JLabel("Your objective is to get a score of " + strNumber);
        }
        else {
            objective = new JLabel("Your objective is to get " + strNumber + " points");
        }

        // Set properties for label
        objective.setBounds(82,85,225,40);
        objective.setForeground(Color.white);
        objective.setBackground(Color.black);
        objective.setFont(new Font("Helvetica",Font.BOLD, 13));

        // Declare and set bounds for label
        JLabel objectiveBg = new JLabel(objectiveImage);
        objectiveBg.setBounds(70,40,250,141);

        // Declare and set bounds for button
        JButton okButton = new JButton(okImage);
        okButton.setBounds(140,200,75,44);

        // Set frame background and add frame components
        frame.setContentPane(new JLabel(bgImage));
        frame.add(objective);
        frame.add(okButton);
        frame.add(objectiveBg);

        // Declare frame properties
        frame.setResizable(false);
        frame.setIconImage(logoLarge.getImage());
        frame.setLayout(null);
        frame.setSize(400,290);
        frame.setIconImage(logoLarge.getImage());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Action Listener - close current and MainMenu class and open SinglePlayer class
        okButton.addActionListener(e -> {
            Game game = new Game(SelectDifficulty.getGameEasy(), SelectSkin.getSkin());
            game.setVisible(true);
            frame.dispose();
            MainMenu.frame.dispose();
        });
    }

    /**
     * This method generates a random task (points or score)
     * @return the random task
     */
    private static String randomTask() {
        int random = (int) (Math.random() * 2);
        if (random == 1) {
            return "points";
        }
        else {
            return "score";
        }
    }

    /**
     * This method generates a random number based on the task and the difficulty
     * @param task is the task chosen prior
     * @return the number based on the factors
     */
    private static int randomNumber(String task) {
        if (task.equals("points")) {
            if (SelectDifficulty.getGameEasy()) {
                return ThreadLocalRandom.current().nextInt(400, 601);
            }
            return ThreadLocalRandom.current().nextInt(600, 851);
        }
        else {
            if (SelectDifficulty.getGameEasy()) {
                return ThreadLocalRandom.current().nextInt(30, 51);
            }
            return ThreadLocalRandom.current().nextInt(50, 76);
        }
    }
    public static String getStrTask() {
        return strTask;
    }
    public static int getStrNumber() {
        return strNumber;
    }
}