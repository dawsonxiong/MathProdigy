/**
 * Authors: Dawson Xiong, Gary Wang
 * Date completed: 28 January 2022
 * Program description: This is the class that generates the whole game of math prodigy. It is a JPanel, with many methods to move both
 * the player and the gates. It also contains many methods for processes before, during and after the game. This program also displays
 * user's score, the objective, and user's points.
 */

/**
 * Game.java uses and repurposes some code from Matthew and Demeng's code.
 * Title: GamePanel.java source code
 * Author: Mo, Matthew and Chen, Demeng
 * Availability: private
 */

import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;
import javax.swing.*;
import javax.swing.Timer;
import java.io.*;

public class Game extends JPanel implements ActionListener {
    // Constant for possible operations. There are more negative operations to rig it a bit
    private final char[] OPERATIONS = {'+', '+', '-', '-', '-', 'x', 'x', '÷', '÷', '%'};

    // Milliseconds between each game loop
    private static final int GAME_LOOP_INTERVAL = 3;

    // Number of pixels the gate will move per game loop
    private static final int GATE_SPEED = 1;

    // Constants representing the height and width of the character
    private static final int CHARACTER_HEIGHT = 100;
    private static final int CHARACTER_WIDTH = 100;

    // Constants representing the height and width of the gate
    private static final int GATE_WIDTH = 325;
    private static final int GATE_HEIGHT = 250;

    // Constants representing the increase of the gate speed
    private static final double EASY_GATE_INCREASE = 0.03;
    private static final double HARD_GATE_INCREASE = 0.03;

    // Constants representing the increase of the character's speed
    private static final double EASY_CHARACTER_INCREASE = 0.1;
    private static final double HARD_CHARACTER_INCREASE = 0.2;

    // Number of pixels the character will move per game loop
    private double characterSpeed = 10; // not a constant because it increases

    // The starting locations of the character
    private int characterX = 685;
    private int characterY = 550;

    // The starting locations of the gates
    private int gateOneX = 380;
    private int gateOneY = 0;
    private int gateTwoX = 750;
    private int gateTwoY = 0;

    // The game loop timer
    private Timer timer;

    // The direction the character is travelling
    private CharacterDirection currentDirection = CharacterDirection.NONE;

    // Import and create images for each component
    private Image skin = new ImageIcon("images\\defaultSkin.png").getImage();
    private final Image gateOne = new ImageIcon("images\\gate.png").getImage();
    private final Image gateTwo = new ImageIcon("images\\gate.png").getImage();
    private final Image textBorder = new ImageIcon("images\\border.jpg").getImage();
    private final Image scorePointBorder = new ImageIcon("images\\scorePointBorder.jpg").getImage();

    // Background image icon
    private static ImageIcon gameImage = new ImageIcon("images\\gameBg.jpg");

    // Boolean to display the current operation
    private boolean display = true;

    // For the random operations and symbols
    private char currentSymbolOne;
    private double currentNumberOne;
    private char currentSymbolTwo;
    private double currentNumberTwo;

    // Difficulty
    private boolean easy;

    // Multiplier - increases as score increases
    private double multiplier;

    // Variables for the score and points
    private static int score;
    private static double points;

    // JFrame which will hold the game
    private JFrame frame = new JFrame("Single player");

    public Game(boolean easy, Image skin) {
        this.easy = easy;
        this.skin = skin;

        // Reset variable values
        score = 0;
        points = 10;
        multiplier = 1;

        // Set properties for game panel
        setLayout(null);
        setBounds(0, 0, 1440, 810);
        setVisible(true);

        // Import and create background
        ImageIcon logoLarge = new ImageIcon("Images\\logo.png");

        // Set properties for the JFrame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setIconImage(logoLarge.getImage());
        frame.setResizable(false);
        frame.setSize(1440, 810);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);

        // Set character's direction to LEFT when left arrow is pressed.
        bindStroke(WHEN_IN_FOCUSED_WINDOW, "pressed.left",
                KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),
                new DirectionAction(CharacterDirection.LEFT));

        // Set character's direction to NONE when left arrow is released.
        bindStroke(WHEN_IN_FOCUSED_WINDOW, "released.left",
                KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true),
                new DirectionAction(CharacterDirection.NONE));

        // Set character's direction to RIGHT when right arrow is pressed.
        bindStroke(WHEN_IN_FOCUSED_WINDOW, "pressed.right",
                KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),
                new DirectionAction(CharacterDirection.RIGHT));

        // Set character's direction to NONE when right arrow is released.
        bindStroke(WHEN_IN_FOCUSED_WINDOW, "released.right",
                KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true),
                new DirectionAction(CharacterDirection.NONE));

        // Set character's direction to RIGHT when right arrow is pressed.
        bindStroke(WHEN_IN_FOCUSED_WINDOW, "pressed.down",
                KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),
                new DirectionAction(CharacterDirection.DOWN));

        // Set character's direction to NONE when right arrow is released.
        bindStroke(WHEN_IN_FOCUSED_WINDOW, "released.down",
                KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true),
                new DirectionAction(CharacterDirection.NONE));

        // Set character's direction to RIGHT when right arrow is pressed.
        bindStroke(WHEN_IN_FOCUSED_WINDOW, "pressed.up",
                KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),
                new DirectionAction(CharacterDirection.UP));

        // Set character's direction to NONE when right arrow is released.
        bindStroke(WHEN_IN_FOCUSED_WINDOW, "released.up",
                KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true),
                new DirectionAction(CharacterDirection.NONE));

        // Start game loop
        timer = new Timer(GAME_LOOP_INTERVAL, this);
        timer.start();
    }

    // Game update task, ran every game loop.
    @Override
    public void actionPerformed(ActionEvent e) {
        // Change the character's direction.
        switch (currentDirection) {
            case LEFT:
                characterX -= characterSpeed;
                break;
            case RIGHT:
                characterX += characterSpeed;
                break;
            case UP:
                characterY -= characterSpeed;
                break;
            case DOWN:
                characterY += characterSpeed;
                break;
            case NONE:
                // Nothing is pressed, do nothing.
                break;
        }
        // If user tries to go out of bounds
        if (characterX + CHARACTER_WIDTH > 1075) {
            characterX = 1075 - CHARACTER_WIDTH;
        }
        else if (characterX < 380) {
            characterX = 380;
        }
        if (characterY + CHARACTER_HEIGHT > getHeight()) {
            characterY = getHeight() - CHARACTER_HEIGHT;
        }
        else if (characterY < 300) {
            characterY = 300;
        }
        repaint(); // repaint the frame
    }

    // To paint the components
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw background
        g.drawImage(gameImage.getImage(), 0, 0, null);

        // Increase the location of gates using multiplier
        gateOneY += GATE_SPEED * multiplier;
        gateTwoY += GATE_SPEED * multiplier;

        // Draw gates and draw character
        g.drawImage(gateOne, gateOneX, gateOneY, this);
        g.drawImage(gateTwo, gateTwoX, gateTwoY, this);
        g.drawImage(skin, characterX, characterY, this);

        // Determine if character is touching the gate
        if (!collision()) { // character is not touching
            if (display) { // if the operation needs to be displayed
                // Call two methods (randomOperation, randomNum) to generate random operation + number
                char operation = randomOperation();
                currentSymbolOne = operation;
                currentNumberOne = randomNum(operation);

                operation = randomOperation();
                currentSymbolTwo = operation;
                currentNumberTwo = randomNum(operation);

                display = false; // will stay false until user collides/touches the gate
            }
            else { // if operations not needed
                // Draw the gates. 63 and 73 are for the text to stay in the center
                g.drawImage(textBorder, gateOneX + 63, gateOneY + 73, this);
                g.drawImage(textBorder, gateTwoX + 63, gateTwoY + 73, this);

                // Display the current operations and values
                g.setColor(Color.WHITE);
                g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
                // 86 and 140 are for the text to stay in the center
                g.drawString(currentSymbolOne + String.valueOf(currentNumberOne), gateOneX + 86, gateOneY + 140);
                g.drawString(currentSymbolTwo+ String.valueOf(currentNumberTwo), gateTwoX + 86, gateTwoY + 140);
            }
        }
        else { // if not touching
            // Reset gate positioning
            gateOneX = 380; gateOneY = 0;
            gateTwoX = 750; gateTwoY = 0;

            display = true;
        }
        // Draw scoreboards
        g.drawImage(scorePointBorder, 10, 80, this);
        g.drawImage(scorePointBorder, 10, 275, this);
        g.drawImage(scorePointBorder, 10, 470, this);

        // Draw the text that goes on scoreboards
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        g.drawString("Points: " + round(points), 45, 174)  ;
        g.drawString("Score: " + score, 75, 375);

        // Draw the 3rd board's text
        if (Task.getStrTask().equals("score")) {
            g.drawString("Objective: ", 45, 540);
            g.drawString("Score: " + Task.getStrNumber(), 45, 600);
        }
        else {
            g.drawString("Objective: ", 45, 540);
            g.drawString("Points: " + Task.getStrNumber(), 45, 595);
        }
    }

    /**
     * This method will determine if user is touching either one of the gates.
     * @return if user is touching
     */
    private boolean collision() {
        boolean collide = false;
        // When user collides with gate 1
        if ((characterX <= gateOneX + GATE_WIDTH && ((characterY + CHARACTER_HEIGHT) >= gateOneY && characterY <= gateOneY + GATE_HEIGHT))) {
            points = applyOperation(currentSymbolOne, currentNumberOne, points); // apply the operation that user touched
            collide = true;
        }
        // When user collides with gate 2
        else if ((characterX + CHARACTER_WIDTH >= gateTwoX && ((characterY + CHARACTER_HEIGHT) >= gateTwoY && characterY <= gateTwoY + GATE_HEIGHT))) {
            points = applyOperation(currentSymbolTwo, currentNumberTwo, points);
            collide = true;
        }
        if (collide) {
            score ++;
            // Determine if the game is on easy mode. If it is, increase gate speed by constant EASY_GATE_INCREASE. If not, increase by HARD_GATE_INCREASE
            // Same thing applies with character speed
            if (easy) {
                multiplier = multiplier + EASY_CHARACTER_INCREASE;
                characterSpeed = characterSpeed + EASY_GATE_INCREASE;
            }
            else {
                multiplier = multiplier + HARD_CHARACTER_INCREASE;
                characterSpeed = characterSpeed + HARD_GATE_INCREASE;
            }
        }
        if (points < -25) { // user loses when they have < -25 points, and this opens game over screen
            timer.stop();
            frame.dispose();
            new GameOver();
            giveCoins(false); // no win
        }
        // Determine if the user reached objective or not. If they did, reward them and open win screen.
        if (Task.getStrTask().equals("score")) {
            if (score >= Task.getStrNumber()) {
                timer.stop();
                frame.dispose();
                new WinScreen();
                giveCoins(true); // win
            }
        }
        else {
            if (points >= Task.getStrNumber()) {
                timer.stop();
                frame.dispose();
                new WinScreen();
                giveCoins(true);
            }
        }
        return collide;
    }

    // Bind the keystroke to an action.
    public void bindStroke(int condition, String name, KeyStroke keyStroke, Action action) {
        getInputMap(condition).put(keyStroke, name);
        getActionMap().put(name, action);
    }

    // Subclass, used to change character direction
    public class DirectionAction extends AbstractAction {

        private final CharacterDirection direction;

        public DirectionAction(CharacterDirection direction) {
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Update the current character direction when this event is called.
            Game.this.currentDirection = direction;
        }
    }

    private enum CharacterDirection { // enum for 4 directions
        UP, DOWN, LEFT, RIGHT, NONE
    }

    /**
     * This method will return a char from the array of chars declared above. It will pick a random char from the array.
     * @return the random operation (char) picked from the array
     */
    private char randomOperation() {
        return OPERATIONS[ThreadLocalRandom.current().nextInt(0, 10)];
    }

    /**
     * This method will return a random number based on the operation given
     * @param operation is the operation given, it will determine the proper range for the random numbers
     * @return the random number determined
     */
    private double randomNum(char operation) {
        if (operation == '+' || operation == '-' || operation == '%') {
            return round(ThreadLocalRandom.current().nextInt(1, 50));
        }
        else if (operation == 'x') {
            return round(Math.random() * (3));
        }
        else {
            return round(ThreadLocalRandom.current().nextInt(1, 30));
        }
    }

    /**
     * This method applies the given char operation and returns the value back
     * @param op is the operation given
     * @param num is the number displayed (was randomly generated)
     * @param pt is the amount of points user has
     * @return the operation completed
     */
    private static double applyOperation(char op, double num, double pt) {
        return switch (op) {
            case '+' -> pt + num;
            case '-' -> pt - num;
            case 'x' -> pt * num;
            case '÷' -> pt / num;
            case '%' -> pt % num;
            default -> Math.pow(pt, num);
        };
    }

    /**
     * This method rounds a double to 2 decimal digits for easy display
     * @param value is the double that will be taken in
     * @return the rounded double to 2 decimal places
     */
    private static double round(double value) {
        long factor = (long) Math.pow(10, 2);
        value *= factor;
        long temp = Math.round(value);
        return (double) temp / factor;
    }

    /**
     * This method will give coins to the user's file after a round of the game. This method writes into a temporary text file,
     * which will be switched with usersInfo.txt. This method essentially copies the original file, only changing the coin number.
     * @param win represents if the user won or not. If they won, they get twice as many coins
     */
    private void giveCoins(boolean win) {
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("data\\usersInfo.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("data\\temp.txt"));
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                if (words[0].equals(Account.getUsername())) { // looking for username entered
                    if (win) {
                        bw.write(words[0] + " " + (Integer.parseInt(words[1]) + (score * 6)) + " "); // winner
                    }
                    else {
                        bw.write(words[0] + " " + (Integer.parseInt(words[1]) + (score * 3)) + " "); // loser
                    }
                    for (int i = 2; i < 8; i ++) {
                        bw.write(words[i] + " ");
                    }
                }
                else {
                    for (String str : words) {
                        bw.write(str + " ");
                    }
                }
                bw.newLine();
            }
            bw.close(); br.close(); // close reader and writer
            Account.transferFile(); // transfer temp.txt -> usersInfo.txt
        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }

    public static int getScore() {
        return score;
    }
}