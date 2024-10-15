/**
 * Authors: Dawson Xiong, Gary Wang
 * Date completed: 28 January 2022
 * Program description: This is the class that the user will see after they achieve the objective. It has 3 buttons.
 * Play again, main menu, quit game, quite self-explanatory. It also shows user how many coins they won.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WinScreen {

    public WinScreen(){

        // Create frame
        JFrame frame = new JFrame("Win");

        // Import and declare images
        ImageIcon bgImage = new ImageIcon("src\\main\\resources\\images\\winScreenBg.jpg");
        ImageIcon playAgainImage = new ImageIcon("src\\main\\resources\\images\\playAgainButton.jpg");
        ImageIcon mainMenuImage = new ImageIcon("src\\main\\resources\\images\\mainMenuButton.jpg");
        ImageIcon exitImage = new ImageIcon("src\\main\\resources\\images\\exitButton.jpg");
        ImageIcon coinImage = new ImageIcon("src\\main\\resources\\images\\coin.png");
        ImageIcon logoLarge = new ImageIcon("src\\main\\resources\\images\\logo.png");

        // Declare labels
        JLabel bg = new JLabel(bgImage);
        JLabel coin = new JLabel(coinImage);
        JLabel earnings = new JLabel("+" + Game.getScore() * 6 + " (X2!)");

        // Set bounds for coin
        coin.setBounds(515, 375, 100, 100);
        // Set bounds for earnings (amount of coins user earned)
        earnings.setBounds(620,400,300,69);
        earnings.setForeground(Color.WHITE);
        earnings.setBackground(Color.BLACK);
        earnings.setFont(new Font("Helvetica",Font.BOLD, 60));

        // Declare buttons
        JButton mainMenuButton = new JButton(mainMenuImage);
        JButton exitButton = new JButton(exitImage);
        JButton playAgainButton = new JButton(playAgainImage);

        // Set bounds for buttons
        mainMenuButton.setBounds(580,500,200,114);
        exitButton.setBounds(900,500,200,114);
        playAgainButton.setBounds(260,500,200,114);

        // Set frame background
        frame.setContentPane(bg);
        // Add frame components
        frame.add(mainMenuButton);
        frame.add(exitButton);
        frame.add(playAgainButton);
        frame.add(coin);
        frame.add(earnings);
        // Set frame properties
        frame.setIconImage(logoLarge.getImage());
        frame.setLayout(null);
        frame.setSize(1400,810);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        // Action Listener - open mainMenu class and close current screen when main menu button is clicked
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenu();
                frame.dispose();
            }
        });

        // Action Listener - open SinglePlayerGame class and close current screen when retry button is clicked
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Task();
                frame.dispose();
            }
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
