/**
 * Authors: Dawson Xiong, Gary Wang
 * Date completed: 28 January 2022
 * Program description: This is the class that displays the first title screen with 2 buttons - login/sign up, exit game.
 */
import java.awt.*;
import javax.swing.*;

public class TitleScreen {

    public TitleScreen() {

        // Declare images
        ImageIcon background = new ImageIcon("src\\main\\resources\\images\\titleScreenMenu.png");
        ImageIcon logo = new ImageIcon("src\\main\\resources\\images\\logo.png");
        ImageIcon buttonOne = new ImageIcon("src\\main\\resources\\images\\suloButton.jpg");
        ImageIcon buttonTwo = new ImageIcon("src\\main\\resources\\images\\egButton.jpg");
        ImageIcon titleImage = new ImageIcon("src\\main\\resources\\images\\title.jpg");
        ImageIcon byImage = new ImageIcon("src\\main\\resources\\images\\by.jpg");

        // Declare JFrame
        JFrame frame = new JFrame("Math Prodigy");

        // Declare JLabels
        JLabel titleLogo = new JLabel(logo);
        JLabel blankOne = new JLabel();
        JLabel blankTwo = new JLabel();
        JLabel title = new JLabel(titleImage);
        JLabel by = new JLabel(byImage);

        // Declare JButtons
        JButton signUpLogIn = new JButton(buttonOne);
        JButton exitGame = new JButton(buttonTwo);

        // Set boundaries for buttons
        signUpLogIn.setBounds(504, 575, 428, 177);
        exitGame.setBounds(962, 575, 428, 177);

        // Set boundaries for button outlines
        blankLabel(blankOne);
        blankOne.setBounds(500, 572, 435, 184);
        blankLabel(blankTwo);
        blankTwo.setBounds(958, 572, 435, 184);

        // Set boundaries for logo
        titleLogo.setBounds(35, 16, 550, 550);

        // Set boundaries for text
        title.setBounds(580, 125,809, 350);
        by.setBounds(25, 570, 373, 186);

        // Set background for JFrame
        frame.setContentPane(new JLabel(background));

        // Add buttons and borders to JFrame
        frame.add(signUpLogIn);
        frame.add(exitGame);
        frame.add(blankOne);
        frame.add(blankTwo);
        frame.add(title);
        frame.add(by);

        // Add logo to JFrame
        frame.add(titleLogo);

        // Set properties for frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1440, 810);
        frame.setIconImage(logo.getImage());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        // Action Listener - exit game buttons exit program
        exitGame.addActionListener(e -> System.exit(0));

        // Action Listener - sign in menu opens if button clicked and title screen closes
        signUpLogIn.addActionListener(e -> {
            new SignIn();
            frame.dispose();
        });
    }

    /**
     * This method will create the colour of the button outline (a blank JLabel)
     * @param label is the JLabel that will change
     */
    private static void blankLabel(JLabel label) {
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
    }
}