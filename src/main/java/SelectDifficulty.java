/**
 * Authors: Dawson Xiong, Gary Wang
 * Date completed: 28 January 2022
 * Program description: This is the class that lets user choose between easy and hard mode in preparation for the game.
 * There are 2 buttons present.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SelectDifficulty {

    // User's decision declared in a boolean
    private static boolean gameEasy;

    public SelectDifficulty() {
        // Import and declare images
        ImageIcon logoLarge = new ImageIcon("src\\main\\resources\\images\\logo.png");
        ImageIcon choose = new ImageIcon("src\\main\\resources\\images\\choose.jpg");

        // Create frame
        JFrame frame = new JFrame("Select Difficulty");

        // Declare buttons
        JButton easy = new JButton("Easy");
        JButton hard = new JButton("Hard");

        // Declare label
        JLabel chooseDifficulty = new JLabel(choose);

        // Set properties for easy button
        easy.setBackground(Color.GREEN);
        easy.setBounds(30,175,150,50);
        easy.setFont(new Font("Helvetica",Font.BOLD,18));
        easy.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Removes border for button
        // Mouse Listener - open SelectSkin class and dispose current frame
        easy.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameEasy = true;
                new SelectSkin();
                frame.dispose();
            }
            // Mouse Listener - change easy button's colour when clicked or hovered on
            @Override
            public void mousePressed(MouseEvent e) {
                easy.setBackground(new Color(0, 255, 155));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                easy.setBackground(new Color(0, 255, 155));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                easy.setBackground(Color.GREEN);
            }
        });

        // Set properties for hard button
        hard.setBackground(Color.RED);
        hard.setBounds(203,175,150,50);
        hard.setFont(new Font("Helvetica",Font.BOLD,18));
        hard.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Removes border for button
        // Mouse Listener - open SelectSkin class and close current frame
        hard.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameEasy = false;
                new SelectSkin();
                frame.dispose();
            }
            // Mouse Listener - change colour of hard button when clicked or hovered on
            @Override
            public void mousePressed(MouseEvent e) {
                hard.setBackground(new Color(247, 129, 129));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                hard.setBackground(new Color(247, 129, 129));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                hard.setBackground(Color.RED);
            }
        });

        // Set bounds for label
        chooseDifficulty.setBounds(30, 15, 325, 138);

        // Add frame components
        frame.add(easy);
        frame.add(hard);
        frame.add(chooseDifficulty);

        // Declare frame properties
        frame.setLayout(null);
        frame.setSize(400,290);
        frame.setIconImage(logoLarge.getImage());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public static boolean getGameEasy() {
        return gameEasy;
    }
}
