/**
 * Authors: Dawson Xiong, Gary Wang
 * Date completed: 28 January 2022
 * Program description: This is the class that allows user to select a skin they want to use in the game. There are 6 skins,
 * and if user doesn't have any, it will give them the default skin (minion) and not display this class.
 */
import java.awt.*;
import java.util.Arrays;
import javax.swing.*;
import static javax.swing.JOptionPane.*;

public class SelectSkin {

    // Create frame
    public static JFrame frame = new JFrame("Select Skin");

    // Skin's image file
    private static Image skin;

    public SelectSkin() {

        // Import and declare images
        ImageIcon spongeBobImage = new ImageIcon("src\\main\\resources\\images\\spongeBobSkinButton.jpg");
        ImageIcon chopImage = new ImageIcon("src\\main\\resources\\images\\chopSkinButton.jpg");
        ImageIcon franklinImage = new ImageIcon("src\\main\\resources\\images\\franklinSkinButton.jpg");
        ImageIcon trevorImage = new ImageIcon("src\\main\\resources\\images\\trevorSkinButton.jpg");
        ImageIcon peppaImage = new ImageIcon("src\\main\\resources\\images\\peppaSkinButton.jpg");
        ImageIcon michaelImage = new ImageIcon("src\\main\\resources\\images\\michaelSkinButton.jpg");
        ImageIcon bgImage = new ImageIcon("src\\main\\resources\\images\\selectSkinBg.jpg");
        ImageIcon logoLarge = new ImageIcon("src\\main\\resources\\images\\logo.png");

        // Declare and validate the buttons for choosing skins
        JButton spongeBobSkin = new JButton(spongeBobImage);
        spongeBobSkin.addActionListener(e -> validate(0));

        JButton franklinSkin = new JButton(franklinImage);
        franklinSkin.addActionListener(e -> validate(1));

        JButton peppaSkin = new JButton(peppaImage);
        peppaSkin.addActionListener(e -> validate(2));

        JButton trevorSkin = new JButton(trevorImage);
        trevorSkin.addActionListener(e -> validate(3));

        JButton michaelSkin = new JButton(michaelImage);
        michaelSkin.addActionListener(e -> validate(4));

        JButton chopSkin = new JButton(chopImage);
        chopSkin.addActionListener(e -> validate(5));

        // Set boundaries for the buttons
        spongeBobSkin.setBounds(50,65,125,70);
        chopSkin.setBounds(50,165,125,70);
        franklinSkin.setBounds(50,265,125,70);
        trevorSkin.setBounds(210,65,125,70);
        peppaSkin.setBounds(210,165,125,70);
        michaelSkin.setBounds(210,265,125,70);

        // Set background
        frame.setContentPane(new JLabel(bgImage));

        // Add components to JFrame
        frame.add(spongeBobSkin);
        frame.add(chopSkin);
        frame.add(franklinSkin);
        frame.add(trevorSkin);
        frame.add(peppaSkin);
        frame.add(michaelSkin);

        // Set frame properties
        frame.setLayout(null);
        frame.setIconImage(logoLarge.getImage());
        frame.setSize(400,390);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        // if the user is new and needs the default skin
        if (isNew()) {
            skin = new ImageIcon("src\\main\\resources\\images\\defaultSkin.png").getImage();
            frame.dispose();
            new Task();
        }
    }

    /**
     * This method will determine if the user has no skins. In this case, give them the default skin.
     */
    private boolean isNew() {
        if (Arrays.equals(Account.getSkins(), new String[]{"no", "no", "no", "no", "no", "no"})) {
            return true;
        }
        return false;
    }

    /**
     * This method will see if the user has the skin they chose
     * @param index is the index of the skin they try to choose
     */
    private void validate(int index) {
        if (Account.getSkins()[index].equals("yes")) {
            switch (index) {
                case 0 -> skin = new ImageIcon("src\\main\\resources\\images\\spongebobSkin.png").getImage();
                case 1 -> skin = new ImageIcon("src\\main\\resources\\images\\franklinSkin.png").getImage();
                case 2 -> skin = new ImageIcon("src\\main\\resources\\images\\peppaSkin.png").getImage();
                case 3 -> skin = new ImageIcon("src\\main\\resources\\images\\trevorSkin.png").getImage();
                case 4 -> skin = new ImageIcon("src\\main\\resources\\images\\michaelSkin.png").getImage();
                case 5 -> skin = new ImageIcon("src\\main\\resources\\images\\chopSkin.png").getImage();
                default -> skin = new ImageIcon("src\\main\\resources\\images\\defaultSkin.png").getImage();
            }
            new Task();
            frame.dispose();
        }
        else {
            showMessageDialog(null, "You don't have this skin. " +
                    "Purchase it from the shop!", "No skin", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static Image getSkin() {
        return skin;
    }
}
