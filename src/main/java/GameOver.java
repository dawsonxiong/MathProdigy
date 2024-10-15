/**
 * Authors: Dawson Xiong, Gary Wang
 * Date completed: 28 January 2022
 * Program description: This is the screen that will show after the user has failed to reach the objective.
 * It will display the amount of coins the user has earned. It will display 3 buttons - one to retry, one to return to
 * main menu, and one to exit the program.
 */

import java.awt.*;
import javax.swing.*;

public class GameOver {

    public GameOver() {

        // Create new frame
        JFrame frame = new JFrame("Game Over");

        // Import and declare images
        ImageIcon gOBg = new ImageIcon("src\\main\\resources\\images\\gameOver.jpg");
        ImageIcon retryBg = new ImageIcon("src\\main\\resources\\images\\retryButton.jpg");
        ImageIcon mainMenuBg = new ImageIcon("src\\main\\resources\\images\\mainMenuButton.jpg");
        ImageIcon exitBg = new ImageIcon("src\\main\\resources\\images\\exitButton.jpg");
        ImageIcon logoLarge = new ImageIcon("src\\main\\resources\\images\\logo.png");
        ImageIcon coinImage = new ImageIcon("src\\main\\resources\\images\\coin.png");

        // Declare buttons
        JButton retryButton = new JButton(retryBg);
        JButton mainMenuButton = new JButton(mainMenuBg);
        JButton exitButton = new JButton(exitBg);

        // Declare labels
        JLabel coin = new JLabel(coinImage);
        JLabel earnings = new JLabel("+" + Game.getScore() * 3);

        // Set bounds for coin
        coin.setBounds(620, 435, 100, 100);

        // Set bounds for earnings (amount of coins user earned)
        earnings.setBounds(720,455,300,75);
        earnings.setForeground(Color.WHITE);
        earnings.setBackground(Color.BLACK);
        earnings.setFont(new Font("Helvetica",Font.BOLD, 75));

        // Set bounds for buttons
        retryButton.setBounds(400,290,200,114);
        mainMenuButton.setBounds(400,430,200,114);
        exitButton.setBounds(400,570,200,114);

        // Set background for frame
        frame.setContentPane(new JLabel(gOBg));

        // Add frame components
        frame.add(retryButton);
        frame.add(mainMenuButton);
        frame.add(exitButton);
        frame.add(coin);
        frame.add(earnings);

        // Set frame properties
        frame.setIconImage(logoLarge.getImage());
        frame.setLayout(null);
        frame.setSize(1440,810);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        // Action Listener - open SinglePlayerGame and close current screen when retry button is clicked
        retryButton.addActionListener(e -> {
            new Task();
            frame.dispose();
        });

        // Action Listener - open MainMenu class and close current screen when main menu button is clicked
        mainMenuButton.addActionListener(e -> {
            new MainMenu();
            frame.dispose();
        });

        // Action Listener - closes program when exit button is clicked
        exitButton.addActionListener(e -> {
            int selectedOption = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to quit?",
                    "Exit game",
                    JOptionPane.YES_NO_OPTION);
            if (selectedOption == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
}
