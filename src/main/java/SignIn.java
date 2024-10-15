/**
 * Authors: Dawson Xiong, Gary Wang
 * Date completed: 28 January 2022
 * Program description: This is the class that user uses to log into their account. They may access signUp.java to make an
 * account if they don't have one. This class also determines if user can enter (they cannot enter invalid account names).
 */
import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class SignIn {

    public SignIn() {

        // Declare images
        ImageIcon mainMenuBg = new ImageIcon("src\\main\\resources\\images\\signInMenu.jpg");
        ImageIcon logo = new ImageIcon("src\\main\\resources\\images\\logoSmall.png");
        ImageIcon logoLarge = new ImageIcon("src\\main\\resources\\images\\logo.png");
        ImageIcon mathBg = new ImageIcon("src\\main\\resources\\images\\mathBook.jpg");

        // Creates a frame
        JFrame frame = new JFrame("Sign In");

        // Declares and sets properties for labels
        JLabel logoIcon = new JLabel(logo);
        JLabel username = new JLabel("Username: ");
        JLabel signUpQuestion = new JLabel("Not an existing user?");
        JLabel rememberMe = new JLabel("Remember Me");
        JLabel math = new JLabel(mathBg);
        JLabel signUp = new JLabel("Sign Up");

        // Set properties for labels
        logoIcon.setBounds(770,130,110,110);
        username.setBounds(550,180,125,100);
        username.setFont(new Font("Helvetica",Font.BOLD, 18));
        username.setForeground(Color.white);
        signUpQuestion.setBounds(720,320,200,200);
        signUpQuestion.setFont(new Font("Helvetica",Font.BOLD,12));
        signUpQuestion.setForeground(Color.white);
        signUp.setBounds(843,410,47,20);
        signUp.setFont(new Font("Helvetica",Font.BOLD,12));
        signUp.setForeground(Color.cyan);
        // Mouse listener - when hovered over sign up, changes colour
        signUp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SignUp();
                frame.dispose();
            }
            // Mouse listener - change button colour when clicked or hovered on
            @Override
            public void mousePressed(MouseEvent e) {
                signUp.setForeground(Color.BLUE);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                signUp.setForeground(Color.CYAN);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                signUp.setForeground(Color.BLUE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                signUp.setForeground(Color.CYAN);
            }
        });

        // Set properties for label
        rememberMe.setBounds(570,320,200,200);
        rememberMe.setFont(new Font("Helvetica",Font.BOLD,12));
        rememberMe.setForeground(Color.white);
        math.setBounds(563,440,300,200);

        // Declares and sets bounds for text field
        JTextField LIUsernameTxt = new JTextField();
        Font font = new Font("Helvetica", Font.BOLD, 18);
        LIUsernameTxt.setFont(font);
        LIUsernameTxt.setBounds(550,250,330,40);

        // Declares and sets properties for button
        JButton signIn = new JButton("SIGN IN");
        JButton reset = new JButton("RESET");
        signIn.setBackground(Color.RED);
        signIn.setBounds(550,330,155,40);
        signIn.setFont(new Font("Helvetica",Font.BOLD,18));
        signIn.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Removes border for button

        // Mouse listener - check data files for username and display respective messages
        signIn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String input = LIUsernameTxt.getText();
                if (!input.equals("")) {
                    // Move onto main menu if user's credentials are valid
                    if (isValid(input)) {
                        new MainMenu();
                        frame.dispose();
                        Account.setUsername(input);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Invalid username!", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Please enter a username.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
            // Mouse Listener - change button colour when clicked or hovered on
            @Override
            public void mousePressed(MouseEvent e) {
                signIn.setBackground(new Color(247, 129, 129));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                signIn.setBackground(new Color(247, 129, 129));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                signIn.setBackground(Color.RED);
            }
        });

        // Set properties for button
        reset.setBackground(Color.gray);
        reset.setBounds(720,330,155,40);
        reset.setFont(new Font("Helvetica",Font.BOLD,18));
        reset.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // remove border

        // Mouse Listener - reset text field when reset button is clicked
        reset.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LIUsernameTxt.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {
                reset.setBackground(Color.LIGHT_GRAY);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                reset.setBackground(Color.LIGHT_GRAY);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                reset.setBackground(Color.GRAY);
            }
        });

        // Declares and sets properties for check box
        JCheckBox rememberMeCb = new JCheckBox();
        rememberMeCb.setBounds(550,414,15,15);
        rememberMeCb.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Removes border for check box

        // Adds all the components to frame
        frame.setContentPane(new JLabel(mainMenuBg)); // Sets background of frame
        frame.add(LIUsernameTxt);
        frame.add(signIn);
        frame.add(username);
        frame.add(signUpQuestion);
        frame.add(signUp);
        frame.add(rememberMeCb);
        frame.add(rememberMe);
        frame.add(logoIcon);
        frame.add(math);
        frame.add(reset);

        // Declare frame properties
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setSize(1440,810);
        frame.setIconImage(logoLarge.getImage());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method will determine if the username user entered is valid (user signed up with it already)
     * @param input is the user's entered username
     * @return if the username entered is valid
     */
    private boolean isValid (String input) {
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\main\\resources\\data\\usersInfo.txt"));
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                if (words[0].equals(input)) {
                    return true;
                }
            }
        } catch (IOException iox) {
            iox.printStackTrace();
        }
        return false;
    }
}