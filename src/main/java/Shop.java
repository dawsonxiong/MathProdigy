/**
 * Authors: Dawson Xiong, Gary Wang
 * Date completed: 28 January 2022
 * Program description: This is the class of the shop, where user can purchase skins with coins they earn from playing.
 * There are a catalogue of skins, and user can see their balance to see if they can afford a skin.
 */
import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Shop {

    // Constant for skin costs
    private final int[] SKIN_COSTS = {500, 600, 400, 300, 400, 300};

    // Create JFrame
    private JFrame frame = new JFrame("Shop");

    // Import and declare images
    private ImageIcon spongebobPrice = new ImageIcon("src\\main\\resources\\images\\spongebob.jpg");
    private ImageIcon franklinPrice = new ImageIcon("src\\main\\resources\\images\\franklin.jpg");
    private ImageIcon peppaPrice = new ImageIcon("src\\main\\resources\\images\\peppa.jpg");
    private ImageIcon michaelPrice = new ImageIcon("src\\main\\resources\\images\\michael.jpg");
    private ImageIcon trevorPrice = new ImageIcon("src\\main\\resources\\images\\trevor.jpg");
    private ImageIcon chopPrice = new ImageIcon("src\\main\\resources\\images\\chop.jpg");
    private ImageIcon back = new ImageIcon("src\\main\\resources\\images\\backButton.jpg");
    private ImageIcon soldImage = new ImageIcon("src\\main\\resources\\images\\sold.png");
    private ImageIcon soldImageSmall = new ImageIcon("src\\main\\resources\\images\\soldSmall.png");

    // Declare buttons
    private JButton spongebob = new JButton(spongebobPrice);
    private JButton franklin = new JButton(franklinPrice);
    private JButton peppa = new JButton(peppaPrice);
    private JButton michael = new JButton(michaelPrice);
    private JButton trevor = new JButton(trevorPrice);
    private JButton chop = new JButton(chopPrice);

    // Declare sold labels
    private JLabel soldSpongebob = new JLabel(soldImage);
    private JLabel soldFranklin = new JLabel(soldImage);
    private JLabel soldPeppa = new JLabel(soldImageSmall);
    private JLabel soldTrevor = new JLabel(soldImageSmall);
    private JLabel soldMichael = new JLabel(soldImageSmall);
    private JLabel soldChop = new JLabel(soldImageSmall);

    public Shop() {
        // Import and declare local images not needed in other methods
        ImageIcon logoLarge = new ImageIcon("src\\main\\resources\\images\\logo.png");
        ImageIcon shopBg = new ImageIcon("src\\main\\resources\\images\\shopBg.jpg");

        // Declare button
        JButton backButton = new JButton(back);

        // Declare label
        JLabel currentMoney = new JLabel();

        // Set bounds for buttons
        spongebob.setBounds(110,620,257,93);
        franklin.setBounds(480,620,270,98);
        peppa.setBounds(860,329,225,70);
        michael.setBounds(875,608,216,66);
        trevor.setBounds(1160,329,202,67);
        chop.setBounds(1150,610,204,67);

        // Set properties for label
        currentMoney.setText(String.valueOf(Account.getMoney()));
        currentMoney.setBounds(1126, 33, 200, 40);
        currentMoney.setFont(new Font("Helvetica", Font.BOLD, 40));
        currentMoney.setForeground(Color.ORANGE);

        // Set frame background
        frame.setContentPane(new JLabel(shopBg));
        frame.setIconImage(logoLarge.getImage());
        // Set frame properties
        frame.setResizable(false);
        frame.setSize(1440,810);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Add frame components
        frame.add(spongebob);
        frame.add(franklin);
        frame.add(peppa);
        frame.add(michael);
        frame.add(trevor);
        frame.add(chop);
        frame.add(backButton);
        frame.add(currentMoney);

        // Set bounds for back button
        backButton.setBounds(10,0,150,119);

        // Action listeners for each button - call buy method
        spongebob.addActionListener(e -> buy(500, 1));
        franklin.addActionListener(e -> buy(600, 2));
        peppa.addActionListener(e -> buy(400, 3));
        trevor.addActionListener(e -> buy(300, 4));
        michael.addActionListener(e -> buy(400, 5));
        chop.addActionListener(e -> buy(300, 6));

        // Action Listener - close current screen and open MainMenu class
        backButton.addActionListener(e -> {
            new MainMenu();
            frame.dispose();
        });

        updateShop(); // to update contents of the shop when something is sold
    }

    /**
     * This method will update the shop by adding "sold" signs wherever applicable
     */
    private void updateShop() {
        for (int i = 0; i < 6; i ++) {
            if (Account.getSkins()[i] != null && !Account.getSkins()[i].isEmpty()) { // so no errors occur
                if (Account.getSkins()[i].equals("yes")) { // if user has the skin
                    switch (i) {
                        // The original button will be removed, and a sold sign will replace it
                        case 0 -> {
                            frame.remove(spongebob);
                            soldSpongebob.setBounds(110, 580, 275, 183);
                            frame.add(soldSpongebob);
                        }
                        case 1 -> {
                            frame.remove(franklin);
                            soldFranklin.setBounds(475, 580, 275, 183);
                            frame.add(soldFranklin);
                        }
                        case 2 -> {
                            frame.remove(peppa);
                            soldPeppa.setBounds(880, 295, 200, 133);
                            frame.add(soldPeppa);
                        }
                        case 3 -> {
                            frame.remove(trevor);
                            soldTrevor.setBounds(1157, 295, 200, 133);
                            frame.add(soldTrevor);
                        }
                        case 4 -> {
                            frame.remove(michael);
                            soldMichael.setBounds(880, 572, 200, 133);
                            frame.add(soldMichael);
                        }
                        default -> {
                            frame.remove(chop);
                            soldChop.setBounds(1157, 572, 200, 133);
                            frame.add(soldChop);
                        }
                    }
                }
            }
        }
    }

    /**
     * This method will buy a skin for user if they can afford it.
     * @param cost is the cost of the skin
     * @param skinNum is the skin user wishes to buy
     */
    private void buy(int cost, int skinNum) {
        int coins = Account.getMoney();
        if (coins >= cost) { // if user is able to buy
            updateMoney(Account.getUsername(), coins - SKIN_COSTS[skinNum - 1]); // update shop
            updateSkins(Account.getUsername(), skinNum);
            frame.dispose();
            new Shop();
        }
        else {
            JOptionPane.showMessageDialog(null, "You don't have enough coins. " +
                    "Get more coins by playing! ", "Invalid balance", JOptionPane.WARNING_MESSAGE);
        }

    }

    /**
     * This method will update the money user has after a successful transaction. It will overwrite the current data un usersInfo.txt
     * after initially writing data to temp.txt
     * @param username is user's username
     * @param coins is the amount of coins user has
     */
    private void updateMoney(String username, int coins) {
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("src\\main\\resources\\data\\usersInfo.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src\\main\\resources\\data\\temp.txt"));
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                bw.write(words[0] + " ");
                if (!words[0].equals(username)) {
                    bw.write(words[1]);
                } else {
                    bw.write(String.valueOf(Integer.valueOf(coins)));
                }
                bw.write(" ");
                for (int i = 0; i < 6; i++) {
                    bw.write(words[i + 2] + " ");
                }
                bw.newLine();
            }
            bw.close();
            br.close();
            Account.transferFile();
        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }

    /**
     * This method will update the user's skin library
     * @param username is user's username
     * @param skin is the skin number they want to get
     */
    private void updateSkins(String username, int skin) {
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("src\\main\\resources\\data\\usersInfo.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src\\main\\resources\\data\\temp.txt"));
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                if (words[0].equals(username)) {
                    bw.write(words[0] + " " + words[1] + " ");
                    for (int i = 1; i <= 6; i++) {
                        if (i != skin) {
                            bw.write(words[i + 1] + " ");
                        } else {
                            bw.write("yes ");
                        }
                    }
                } else {
                    for (String str : words) {
                        bw.write(str + " ");
                    }
                }
                bw.newLine();
            }
            bw.close();
            br.close();
            Account.transferFile();
        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }
}
