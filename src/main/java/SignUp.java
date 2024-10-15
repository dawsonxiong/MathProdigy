/**
 * Authors: Dawson Xiong, Gary Wang
 * Date completed: 28 January 2022
 * Program description: This is the class that user uses to make a new account. If user chooses to sign in again, they
 * may access SignIn.java from this class. User cannot make more than 1 account with the same name.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SignUp {

    public SignUp() {

        // Declare images
        ImageIcon signUpBg = new ImageIcon("src\\main\\resources\\images\\signUpMenu.jpg");
        ImageIcon logoIcon = new ImageIcon("src\\main\\resources\\images\\logoSmall.png");
        ImageIcon logoLarge = new ImageIcon("src\\main\\resources\\images\\logo.png");
        ImageIcon logoMedium = new ImageIcon("src\\main\\resources\\images\\logoMedium.png");
        ImageIcon mathBg = new ImageIcon("src\\main\\resources\\images\\mathSymbols.png");

        // Declare JLabels (text + images)
        JLabel username = new JLabel("Username: ");
        JLabel createAccount = new JLabel("CREATE AN ACCOUNT");
        JLabel signInQuestion = new JLabel("Already a user?");
        JLabel rememberMe = new JLabel("Remember Me");
        JLabel logo = new JLabel(logoMedium);
        JLabel math = new JLabel(mathBg);
        JLabel signIn = new JLabel("Sign In");

        // Declare JFrame
        JFrame frame = new JFrame("Sign Up");

        // Set properties for label
        username.setBounds(505,210,100,100);
        username.setFont(new Font("Helvetica",Font.BOLD,15));
        username.setForeground(Color.WHITE);

        // Set bounds for logo label
        logo.setBounds(780,139,125,125);

        // Set properties for createAccount label
        createAccount.setBounds(505,180,200,100);
        createAccount.setFont(new Font("Helvetica",Font.BOLD,12));
        createAccount.setForeground(Color.WHITE);

        // Set properties for signInQuestion label
        signInQuestion.setBounds(783,340,100,100);
        signInQuestion.setFont(new Font("Helvetica",Font.BOLD,12));
        signInQuestion.setForeground(Color.WHITE);

        // Set properties for signIn label
        signIn.setBounds(875,379,40,20);
        signIn.setFont(new Font("Helvetica",Font.BOLD,12));
        signIn.setForeground(Color.CYAN);
        // Mouse listener - when hovered over sign up, changes colour
        signIn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SignIn();
                frame.dispose();
            }
            @Override
            public void mousePressed(MouseEvent e) {
                signIn.setForeground(Color.BLUE);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                signIn.setForeground(Color.CYAN);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                signIn.setForeground(Color.BLUE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                signIn.setForeground(Color.CYAN);
            }
        });

        // Set properties for rememberMe label
        rememberMe.setBounds(525,340,100,100);
        rememberMe.setForeground(Color.WHITE);
        rememberMe.setFont(new Font("Helvetica",Font.BOLD,12));

        // Set bounds for label
        math.setBounds(510,415,400,200);

        // Crates and set boundaries for text field
        JTextField usernameTxt = new JTextField();
        Font font = new Font("Helvetica", Font.BOLD, 18);
        usernameTxt.setFont(font);
        usernameTxt.setBounds(505,275,410,35);

        // Declares and sets boundaries for check box
        JCheckBox rememberMeCb = new JCheckBox();
        rememberMeCb.setBounds(505,383,15,15);
        rememberMeCb.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Removes border for check box

        // Declares and sets properties for button
        JButton signUpButton = new JButton("SIGN UP");
        JButton reset = new JButton("RESET");
        signUpButton.setFont(new Font("Helvetica",Font.BOLD,15));
        signUpButton.setBounds(505,330,200,35);
        signUpButton.setBackground(Color.RED);
        signUpButton.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // Removes border for check box

        // Mouse listener - take user's username, see if it's valid, and enter main menu/don't.
        signUpButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String input = usernameTxt.getText();
                if (!input.equals("")) { // if it's not "" and if it's not already repeated
                    if (!isRepeat(input)) {
                        new MainMenu();
                        frame.dispose();
                        Account.setUsername(input);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "There is already an account with this username! ", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Please enter a username.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                signUpButton.setBackground(new Color(247, 129, 129));
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                signUpButton.setBackground(new Color(247, 129, 129));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                signUpButton.setBackground(Color.RED);
            }
        });

        // Set properties for reset button
        reset.setFont(new Font("Helvetica",Font.BOLD,15));
        reset.setBounds(715,330,200,35);
        reset.setBackground(Color.gray);
        reset.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        // Mouse Listener - clear text field when rest button is clicked
        reset.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                usernameTxt.setText("");
            }
            // Mouse Listener - change colour of button when clicked or hovered on
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

        // Adds components to the frame
        frame.setResizable(false);
        frame.setContentPane(new JLabel(signUpBg));
        frame.add(username);
        frame.add(usernameTxt);
        frame.add(logo);
        frame.add(createAccount);
        frame.add(signInQuestion);
        frame.add(signIn);
        frame.add(rememberMe);
        frame.add(rememberMeCb);
        frame.add(math);
        frame.add(signUpButton);
        frame.add(reset);

        // Declare frame properties
        frame.setLayout(null);
        frame.setSize(1440,810);
        frame.setIconImage(logoLarge.getImage());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method will detect if the username user enters has already been entered. If it is, return true.
     * It will also write in a new username with 0 coins into another text file.
     * @param input is the username user entered
     * @return if the username the user entered is already there
     */
    private boolean isRepeat(String input) {
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\main\\resources\\data\\usersInfo.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src\\main\\resources\\data\\temp.txt"));

            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                if (words[0].equals(input)) {
                    return true;
                }
            }
            bw.write(input + " 0 ");
            for (int i = 0; i < 6; i ++) {
                bw.write("no ");
            }
            bw.newLine();
            bw.close();
            Account.transferFile();
        } catch (IOException iox) {
            iox.printStackTrace();
        }
        return false;
    }
}