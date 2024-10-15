/**
 * Authors: Dawson Xiong, Gary Wang
 * Date completed: 28 January 2022
 * Program description: This is the class that is responsible for the main menu, which has 4 options. Play game, instructions,
 * shop, and exit game. Instructions are a JOptionpane, while the others are classes.
 */

import java.awt.*;
import javax.swing.*;

public class MainMenu{

    // Frame which holds everything. Public because it is accessed outside of the class
    public static JFrame frame = new JFrame("Main Menu");

    public MainMenu() {

        // Import and declare images
        ImageIcon mainMenuBg = new ImageIcon("src\\main\\resources\\images\\mainMenuBg.jpg");
        ImageIcon logoBg = new ImageIcon("src\\main\\resources\\images\\logoMedium.png");
        ImageIcon logoLarge = new ImageIcon("src\\main\\resources\\images\\logo.png");
        ImageIcon playImage = new ImageIcon("src\\main\\resources\\images\\playLogo.png");
        ImageIcon instructionsImage = new ImageIcon("src\\main\\resources\\images\\instructionsLogo.png");
        ImageIcon shopImage = new ImageIcon("src\\main\\resources\\images\\shopLogo.png");
        ImageIcon exitImage = new ImageIcon("src\\main\\resources\\images\\exitLogo.png");

        // Declare and set bounds for labels
        JLabel logo = new JLabel(logoBg);
        JLabel playLogo = new JLabel(playImage);
        JLabel instructionsLogo = new JLabel(instructionsImage);
        JLabel shopLogo = new JLabel(shopImage);
        JLabel exitLogo = new JLabel(exitImage);
        logo.setBounds(1295,645,125,125);
        playLogo.setBounds(75,230,150,100);
        instructionsLogo.setBounds(75,340,150,100);
        shopLogo.setBounds(100,430,100,100);
        exitLogo.setBounds(100,540,100,100);

        // Declare and set properties for buttons
        JButton playButton = new JButton("PLAY");
        JButton instructionsButton = new JButton("INSTRUCTIONS");
        JButton exitButton = new JButton("EXIT");
        JButton shopButton = new JButton("SHOP");
        playButton.setBounds(200,250,200,50);
        playButton.setOpaque(false);
        playButton.setContentAreaFilled(false);
        playButton.setBorderPainted(false);
        playButton.setForeground(Color.white);
        playButton.setFont(new Font("Monospaced",Font.BOLD, 50));
        instructionsButton.setBounds(190,355,450,50);
        instructionsButton.setOpaque(false);
        instructionsButton.setContentAreaFilled(false);
        instructionsButton.setBorderPainted(false);
        instructionsButton.setForeground(Color.white);
        instructionsButton.setFont(new Font("Monospaced",Font.BOLD, 50));
        shopButton.setBounds(195,460,200,50);
        shopButton.setOpaque(false);
        shopButton.setContentAreaFilled(false);
        shopButton.setBorderPainted(false);
        shopButton.setForeground(Color.white);
        shopButton.setFont(new Font("Monospaced",Font.BOLD, 50));
        exitButton.setBounds(200,560,200,50);
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setForeground(Color.white);
        exitButton.setFont(new Font("Monospaced",Font.BOLD, 50));

        // Set frame background
        frame.setContentPane(new JLabel(mainMenuBg));

        // Add components to frame
        frame.add(playButton);
        frame.add(instructionsButton);
        frame.add(shopButton);
        frame.add(exitButton);
        frame.add(logo);
        frame.add(playLogo);
        frame.add(instructionsLogo);
        frame.add(shopLogo);
        frame.add(exitLogo);

        // Set frame properties
        frame.setLayout(null);
        frame.setSize(1440,810);
        frame.setIconImage(logoLarge.getImage());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Action Listener - open SelectDifficulty when play is pressed
        playButton.addActionListener(e -> new SelectDifficulty());

        // Action Listener - Open option pane with game info when instructions are pressed
        instructionsButton.addActionListener(e -> {
            JOptionPane instructions = new JOptionPane("""
                    Welcome to Math Prodigy, a quick thinking math game.

                    You begin with a default character which you can control using arrow keys.\s
                    Your job is to choose the correct gates to pass through in order to achieve the target score/points.\s
                    There will be a randomized objective each round. To beat the game, you have to reach this objective.\s
                    Each time you beat the round, coins will be rewarded.\s
                    Once you reach -25 points or lower, you lose but will still be rewarded the respective coins.\s
                    Collect these coins to purchase various unique skins. Have fun!""",JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = instructions.createDialog("INSTRUCTIONS");
            dialog.setIconImage(logoLarge.getImage());
            dialog.setVisible(true);
        });

        // Action Listener - open shop and close current frame when shop is pressed
        shopButton.addActionListener(e -> {
            new Shop();
            frame.dispose();
        });

        // Action Listener - open option pane to ask for user confirmation to close program
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

