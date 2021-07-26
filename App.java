package dev.narlotl;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

public class App extends JFrame implements ActionListener {

    JButton breedButton;
    JButton breedCloseButton;
    JButton tigerBreedButton = new JButton();
    JButton elephantBreedButton = new JButton();
    JButton elephantBuyButton;
    JButton zebraBuyButton;
    JButton giraffeBuyButton;
    JButton tigerBuyButton;
    JButton monkeyBuyButton;
    JButton buyCloseButton;
    JButton elephantSellButton;
    JButton zebraSellButton;
    JButton giraffeSellButton;
    JButton tigerSellButton;
    JButton monkeySellButton;
    JButton sellCloseButton;
    JButton sellButton;
    JButton resetButton;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel shopLabel;
    JLabel shopLabel1;
    JLabel shopLabel2;
    JLabel shopLabel3;
    JLabel shopLabel4;
    JLabel shopLabel5;
    JLabel shopLabel6;
    JButton monkeyBreedButton = new JButton();
    JButton giraffeBreedButton = new JButton();
    JButton zebraBreedButton = new JButton();
    JLabel label4;
    JButton buyButton;
    JLabel label5;
    JButton exitButton;
    JButton helpButton;
    JLabel mainLabel;
    JLabel label6;
    JLabel label7;
    JLabel label8;
    JPanel mainPanel;

    Timer timer = new Timer();

    Random random = new Random();

    Color buttonColor = new Color(227, 204, 100);

    int tigerClicks = 0;
    int zebraClicks = 0;
    int elephantClicks = 0;
    int giraffeClicks = 0;
    int monkeyClicks = 0;
    int tigers = 2;
    int zebras = 2;
    int giraffes = 2;
    int monkeys = 2;
    int elephants = 2;
    int balance = rand.fromTo(5000, 10000);

    protected void exportSave() {
        File directory = new File("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\ZooWorld");
        if (!directory.exists()) {
            if (directory.mkdir()) {  
            }
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter(directory + "\\ZooWorldSave");
            writer.write(
                         Base64.getEncoder().encodeToString(Integer.toString(balance).getBytes()) + "\n" + 
                         Base64.getEncoder().encodeToString(Integer.toString(tigers).getBytes()) + "\n" + 
                         Base64.getEncoder().encodeToString(Integer.toString(zebras).getBytes()) + "\n" + 
                         Base64.getEncoder().encodeToString(Integer.toString(giraffes).getBytes()) + "\n" + 
                         Base64.getEncoder().encodeToString(Integer.toString(monkeys).getBytes()) + "\n" + 
                         Base64.getEncoder().encodeToString(Integer.toString(elephants).getBytes()) + "\n" +
                         Base64.getEncoder().encodeToString(Boolean.toString(tigerBreedButton.isEnabled()).getBytes()) + "\n" +
                         Base64.getEncoder().encodeToString(Boolean.toString(zebraBreedButton.isEnabled()).getBytes()) + "\n" +
                         Base64.getEncoder().encodeToString(Boolean.toString(giraffeBreedButton.isEnabled()).getBytes()) + "\n" +
                         Base64.getEncoder().encodeToString(Boolean.toString(monkeyBreedButton.isEnabled()).getBytes()) + "\n" +
                         Base64.getEncoder().encodeToString(Boolean.toString(elephantBreedButton.isEnabled()).getBytes())
                          );
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        };
    }

    protected String decode(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }

    protected void loadSave() {
        try {
            File directory = new File("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\ZooWorld");
            File reader = new File(directory + "\\ZooWorldSave");
            Scanner fileReader = new Scanner(reader);
            String balanceData = decode(fileReader.nextLine());
            String tigerData = decode(fileReader.nextLine());
            String zebraData = decode(fileReader.nextLine());
            String giraffeData = decode(fileReader.nextLine());
            String monkeyData = decode(fileReader.nextLine());
            String elephantData = decode(fileReader.nextLine());
            boolean tigerEnabled = !Boolean.parseBoolean(decode(fileReader.nextLine()));
            boolean zebraEnabled = !Boolean.parseBoolean(decode(fileReader.nextLine()));
            boolean giraffeEnabled = !Boolean.parseBoolean(decode(fileReader.nextLine()));
            boolean monkeyEnabled = !Boolean.parseBoolean(decode(fileReader.nextLine()));
            boolean elephantEnabled = !Boolean.parseBoolean(decode(fileReader.nextLine()));
            balance = Integer.parseInt(balanceData);
            tigers = Integer.parseInt(tigerData);
            zebras = Integer.parseInt(zebraData);
            giraffes = Integer.parseInt(giraffeData);
            monkeys = Integer.parseInt(monkeyData);
            elephants = Integer.parseInt(elephantData);
            if (tigerEnabled) {
                tigerBreedEnDis();
            }
            if (zebraEnabled) {
                zebraBreedEnDis();
            }
            if (giraffeEnabled) {
                giraffeBreedEnDis();
            }
            if (monkeyEnabled) {
                monkeyBreedEnDis();
            }
            if (elephantEnabled) {
                elephantBreedEnDis();
            }
            fileReader.close();
        } catch (Exception e) {
            
        }
    }


    private void resetMainText() {
        mainLabel.setText("You have " + tigers + " tigers, " + zebras + " zebras, " + giraffes + " giraffes, " + monkeys + " monkeys, and " + elephants + " elephants." + "Your balance is $" + balance);
        mainLabel.setLocation(0,130);
    }

    private void setTooltip(JButton button, String text) {
        button.setToolTipText(text);
    }

    App() {
        loadSave();

        BufferedImage icon = null;
        try {
            icon = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("dev/narlotl/zooworldicon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image bg = null;
        try {
            bg = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("dev/narlotl/background.png")));
            bg = bg.getScaledInstance(715, 500, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setIconImage(icon);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Zoo World");
        this.setResizable(false);
        assert bg != null;
        this.setContentPane(new JLabel(new ImageIcon(bg)));
        this.getContentPane().setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setBounds(10, 500, 500, 50);
        mainPanel.setVisible(true);

        mainLabel = new JLabel();
        mainLabel.setText("You have " + tigers + " tigers, " + zebras + " zebras, " + giraffes + " giraffes, " + monkeys + " monkeys, and " + elephants + " elephants." + "Your balance is $" + balance);
        mainLabel.setVisible(true);
        mainLabel.setFont(new Font("Papyrus", Font.PLAIN, 18));
        mainLabel.setSize(715, 250);
        mainLabel.setLocation(10, 130);
        mainLabel.setForeground(new Color(250,250,250));

        breedButton = new JButton();
        breedButton.setBounds(10, 10, 200, 100);
        breedButton.setText("Breed");
        breedButton.addActionListener(this);
        breedButton.setBackground(buttonColor);
        breedButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        breedButton.setVisible(true);
        breedButton.setToolTipText("Breed your animals");

        resetButton = new JButton();
        resetButton.setBounds(10, 140, 200, 100);
        resetButton.setText("Reset");
        resetButton.addActionListener(this);
        resetButton.setBackground(buttonColor);
        resetButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        resetButton.setVisible(true);
        resetButton.setToolTipText("Reset your progress");

        buyButton = new JButton();
        buyButton.setBounds(250, 10, 200, 100);
        buyButton.setText("Shop");
        buyButton.addActionListener(this);
        buyButton.setBackground(buttonColor);
        buyButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        buyButton.setVisible(true);
        buyButton.setToolTipText("Shop for animals");

        sellButton = new JButton();
        sellButton.setBounds(490, 10, 200, 100);
        sellButton.setText("Sell");
        sellButton.addActionListener(this);
        sellButton.setBackground(buttonColor);
        sellButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        sellButton.setVisible(true);
        sellButton.setToolTipText("Sell your animals");

        buyCloseButton = new JButton();
        buyCloseButton.setBounds(10, 10, 200, 100);
        buyCloseButton.setText("Close");
        buyCloseButton.addActionListener(this);
        buyCloseButton.setBackground(new Color(227, 204, 100));
        buyCloseButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        buyCloseButton.setVisible(true);
        buyCloseButton.setToolTipText("Close the shop");

        sellCloseButton = new JButton();
        sellCloseButton.setBounds(10, 10, 200, 100);
        sellCloseButton.setText("Close");
        sellCloseButton.addActionListener(this);
        sellCloseButton.setBackground(new Color(227, 204, 100));
        sellCloseButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        sellCloseButton.setVisible(true);
        sellCloseButton.setToolTipText("Close the selling menu");

        exitButton = new JButton();
        exitButton.setBounds(490, 140, 200, 100);
        exitButton.setText("Exit");
        exitButton.addActionListener(this);
        exitButton.setBackground(new Color(232, 28, 9));
        exitButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        exitButton.setVisible(true);
        exitButton.setToolTipText("Exit the game");

        helpButton = new JButton();
        helpButton.setBounds(250, 140, 200, 100);
        helpButton.setText("Help");
        helpButton.addActionListener(this);
        helpButton.setBackground(buttonColor);
        helpButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        helpButton.setVisible(true);
        helpButton.setToolTipText("How to play");

        breedCloseButton = new JButton();
        breedCloseButton.setBounds(10, 10, 200, 100);
        breedCloseButton.setText("Close");
        breedCloseButton.addActionListener(this);
        breedCloseButton.setBackground(new Color(227, 204, 100));
        breedCloseButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        breedCloseButton.setToolTipText("Close the breeding menu");

        tigerBreedButton.addActionListener(this);
        tigerBreedButton.setBounds(250, 10, 200, 100);
        tigerBreedButton.setText("Tigers");
        tigerBreedButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        tigerBreedButton.setBackground(new Color(178, 64, 0));
        tigerBreedButton.setVisible(false);
        tigerBreedButton.setToolTipText("Breed tigers");

        elephantBreedButton.setBounds(490, 10, 200, 100);
        elephantBreedButton.setText("Elephants");
        elephantBreedButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        elephantBreedButton.setBackground(new Color(132, 132, 132));
        elephantBreedButton.setVisible(false);
        elephantBreedButton.addActionListener(this);
        elephantBreedButton.setToolTipText("Breed elephants");

        monkeyBreedButton.setBounds(10, 140, 200, 100);
        monkeyBreedButton.setText("Monkeys");
        monkeyBreedButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        monkeyBreedButton.setBackground(new Color(97, 61, 28));
        monkeyBreedButton.setVisible(false);
        monkeyBreedButton.addActionListener(this);
        monkeyBreedButton.setToolTipText("Breed monkeys");

        giraffeBreedButton.setBounds(250, 140, 200, 100);
        giraffeBreedButton.setText("Giraffes");
        giraffeBreedButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        giraffeBreedButton.setBackground(new Color(109, 81, 42));
        giraffeBreedButton.setVisible(false);
        giraffeBreedButton.addActionListener(this);
        giraffeBreedButton.setToolTipText("Breed giraffes");

        zebraBreedButton.setBounds(490, 140, 200, 100);
        zebraBreedButton.setText("Zebras");
        zebraBreedButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        zebraBreedButton.setBackground(new Color(212, 198, 198));
        zebraBreedButton.setVisible(false);
        zebraBreedButton.addActionListener(this);
        setTooltip(zebraBreedButton, "Breed zebras");

        tigerBuyButton = new JButton();
        tigerBuyButton.addActionListener(this);
        tigerBuyButton.setBounds(250, 10, 200, 100);
        tigerBuyButton.setText("Tigers");
        tigerBuyButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        tigerBuyButton.setBackground(new Color(178, 64, 0));
        tigerBuyButton.setVisible(false);
        setTooltip(tigerBuyButton, "Buy tigers");

        elephantBuyButton = new JButton();
        elephantBuyButton.setBounds(490, 10, 200, 100);
        elephantBuyButton.setText("Elephants");
        elephantBuyButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        elephantBuyButton.setBackground(new Color(132, 132, 132));
        elephantBuyButton.setVisible(false);
        elephantBuyButton.addActionListener(this);
        elephantBuyButton.setToolTipText("Buy elephants");

        monkeyBuyButton = new JButton();
        monkeyBuyButton.setBounds(10, 140, 200, 100);
        monkeyBuyButton.setText("Monkeys");
        monkeyBuyButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        monkeyBuyButton.setBackground(new Color(97, 61, 28));
        monkeyBuyButton.setVisible(false);
        monkeyBuyButton.addActionListener(this);
        setTooltip(monkeyBuyButton, "Buy monkeys");

        giraffeBuyButton = new JButton();
        giraffeBuyButton.setBounds(250, 140, 200, 100);
        giraffeBuyButton.setText("Giraffes");
        giraffeBuyButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        giraffeBuyButton.setBackground(new Color(109, 81, 42));
        giraffeBuyButton.setVisible(false);
        giraffeBuyButton.addActionListener(this);
        setTooltip(giraffeBuyButton, "Buy giraffes");

        zebraBuyButton = new JButton();
        zebraBuyButton.setBounds(490, 140, 200, 100);
        zebraBuyButton.setText("Zebras");
        zebraBuyButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        zebraBuyButton.setBackground(new Color(212, 198, 198));
        zebraBuyButton.setVisible(false);
        zebraBuyButton.addActionListener(this);
        setTooltip(zebraBuyButton, "Buy zebras");

        tigerSellButton = new JButton();
        tigerSellButton.addActionListener(this);
        tigerSellButton.setBounds(250, 10, 200, 100);
        tigerSellButton.setText("Tigers");
        tigerSellButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        tigerSellButton.setBackground(new Color(178, 64, 0));
        tigerSellButton.setVisible(false);
        setTooltip(tigerSellButton, "Sell tigers");

        elephantSellButton = new JButton();
        elephantSellButton.setBounds(490, 10, 200, 100);
        elephantSellButton.setText("Elephants");
        elephantSellButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        elephantSellButton.setBackground(new Color(132, 132, 132));
        elephantSellButton.setVisible(false);
        elephantSellButton.addActionListener(this);
        elephantSellButton.setToolTipText("Sell elephants");

        monkeySellButton = new JButton();
        monkeySellButton.setBounds(10, 140, 200, 100);
        monkeySellButton.setText("Monkeys");
        monkeySellButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        monkeySellButton.setBackground(new Color(97, 61, 28));
        monkeySellButton.setVisible(false);
        monkeySellButton.addActionListener(this);
        setTooltip(monkeySellButton, "Sell monkeys");

        giraffeSellButton = new JButton();
        giraffeSellButton.setBounds(250, 140, 200, 100);
        giraffeSellButton.setText("Giraffes");
        giraffeSellButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        giraffeSellButton.setBackground(new Color(109, 81, 42));
        giraffeSellButton.setVisible(false);
        giraffeSellButton.addActionListener(this);
        setTooltip(giraffeSellButton, "Sell giraffes");

        zebraSellButton = new JButton();
        zebraSellButton.setBounds(490, 140, 200, 100);
        zebraSellButton.setText("Zebras");
        zebraSellButton.setFont(new Font("Papyrus", Font.PLAIN, 25));
        zebraSellButton.setBackground(new Color(212, 198, 198));
        zebraSellButton.setVisible(false);
        zebraSellButton.addActionListener(this);
        setTooltip(zebraSellButton, "Sell zebras");

        UIManager.put("ToolTip.background", new ColorUIResource(buttonColor));
        UIManager.put("ToolTip.font", new FontUIResource("Papyrus", Font.PLAIN, 14));

        label1 = new JLabel();
        label1.setBounds(0, 0, 720, 500);
        label1.setVisible(false);

        label2 = new JLabel();
        label2.setBounds(0, 0, 720, 500);
        label2.setVisible(false);

        label3 = new JLabel();
        label3.setBounds(0, 0, 720, 500);
        label3.setVisible(false);

        label4 = new JLabel();
        label4.setBounds(0, 0, 720, 500);
        label4.setVisible(false);

        label5 = new JLabel();
        label5.setBounds(0, 0, 720, 500);
        label5.setVisible(true);

        label6 = new JLabel();
        label6.setBounds(0, 0, 720, 500);
        label6.setVisible(true);

        label7 = new JLabel();
        label7.setBounds(0, 0, 720, 500);
        label7.setVisible(true);

        label8 = new JLabel();
        label8.setBounds(0, 0, 720, 500);
        label8.setVisible(true);

        shopLabel = new JLabel();
        shopLabel.setBounds(0, 0, 720, 500);
        shopLabel.setVisible(true);

        shopLabel1 = new JLabel();
        shopLabel1.setBounds(0, 0, 720, 500);
        shopLabel1.setVisible(true);

        shopLabel2 = new JLabel();
        shopLabel2.setBounds(0, 0, 720, 500);
        shopLabel2.setVisible(true);

        shopLabel3 = new JLabel();
        shopLabel3.setBounds(0, 0, 720, 500);
        shopLabel3.setVisible(true);

        shopLabel4 = new JLabel();
        shopLabel4.setBounds(0, 0, 720, 500);
        shopLabel4.setVisible(true);

        shopLabel5 = new JLabel();
        shopLabel5.setBounds(0, 0, 720, 500);
        shopLabel5.setVisible(true);

        shopLabel6 = new JLabel();
        shopLabel6.setBounds(0, 0, 720, 500);
        shopLabel6.setVisible(true);

        this.add(breedButton);
        this.add(mainLabel);
        this.add(breedCloseButton);
        this.add(tigerBreedButton);
        this.add(elephantBreedButton);
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);
        this.add(label8);
        this.add(monkeyBreedButton);
        this.add(buyButton);
        this.add(exitButton);
        this.add(label6);
        this.add(label7);
        this.add(mainPanel);
        this.add(shopLabel);
        this.add(shopLabel1);
        this.add(shopLabel2);
        this.add(shopLabel3);
        this.add(shopLabel4);
        this.add(shopLabel5);
        this.add(shopLabel6);
        this.add(buyCloseButton);
        this.add(sellCloseButton);
        this.add(label7);
        label5.add(buyButton);
        label5.add(helpButton);
        label5.add(sellButton);
        shopLabel.add(tigerBuyButton);
        label6.add(exitButton);
        label6.add(resetButton);
        label1.add(elephantBreedButton);
        label2.add(monkeyBreedButton);
        label3.add(giraffeBreedButton);
        label4.add(zebraBreedButton);
        shopLabel1.add(elephantBuyButton);
        shopLabel2.add(monkeyBuyButton);
        shopLabel3.add(giraffeBuyButton);
        shopLabel4.add(zebraBuyButton);
        label7.add(zebraSellButton);
        label7.add(monkeySellButton);
        label7.add(elephantSellButton);
        label7.add(giraffeSellButton);
        label7.add(tigerSellButton);
        this.setSize(714, 501);
        this.setSize(715, 500);
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            File directory = new File("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\ZooWorld\\ZooWorldSave");
            if (directory.delete()) {
                dispose();
                new App();
            }
        }
        
        if (e.getSource() == breedButton) {
            breedButton.setVisible(false);
            breedCloseButton.setVisible(true);
            tigerBreedButton.setVisible(true);
            elephantBreedButton.setVisible(true);
            label1.setVisible(true);
            monkeyBreedButton.setVisible(true);
            label2.setVisible(true);
            label3.setVisible(true);
            label4.setVisible(true);
            zebraBreedButton.setVisible(true);
            giraffeBreedButton.setVisible(true);
            label5.setVisible(false);
            label6.setVisible(false);
            buyButton.setVisible(false);
            mainLabel.setLocation(10,130);
            mainLabel.setText("Choose an animal to breed to begin");
            tigerClicks = 0;
            elephantClicks = 0;
            zebraClicks = 0;
            giraffeClicks = 0;
            monkeyClicks = 0;
        }

        if (e.getSource() == sellButton) {
            breedButton.setVisible(false);
            sellButton.setVisible(false);
            sellCloseButton.setVisible(true);
            tigerSellButton.setVisible(true);
            elephantSellButton.setVisible(true);
            label1.setVisible(true);
            monkeySellButton.setVisible(true);
            label2.setVisible(true);
            label3.setVisible(true);
            label4.setVisible(true);
            zebraSellButton.setVisible(true);
            giraffeSellButton.setVisible(true);
            label5.setVisible(false);
            label6.setVisible(false);
            buyButton.setVisible(false);
            breedCloseButton.setVisible(false);
            buyCloseButton.setVisible(false);
            mainLabel.setLocation(10,130);
            mainLabel.setText("Choose an animal to sell to begin");
            tigerClicks = 0;
            elephantClicks = 0;
            zebraClicks = 0;
            giraffeClicks = 0;
            monkeyClicks = 0;
        }

        if (e.getSource() == sellCloseButton) {
            breedButton.setVisible(true);
            sellButton.setVisible(true);
            sellCloseButton.setVisible(false);
            tigerSellButton.setVisible(false);
            elephantSellButton.setVisible(false);
            label1.setVisible(false);
            monkeySellButton.setVisible(false);
            label2.setVisible(false);
            label3.setVisible(false);
            label4.setVisible(false);
            zebraSellButton.setVisible(false);
            giraffeSellButton.setVisible(false);
            label5.setVisible(true);
            label6.setVisible(true);
            buyButton.setVisible(true);
            breedCloseButton.setVisible(true);
            resetMainText();
        }

        if (e.getSource() == breedCloseButton) {
            breedCloseButton.setVisible(false);
            breedButton.setVisible(true);
            tigerBreedButton.setVisible(false);
            elephantBreedButton.setVisible(false);
            label1.setVisible(false);
            monkeyBreedButton.setVisible(false);
            label2.setVisible(false);
            label4.setVisible(false);
            zebraBreedButton.setVisible(false);
            label5.setVisible(true);
            label6.setVisible(true);
            giraffeBreedButton.setVisible(false);
            buyButton.setVisible(true);
            resetMainText();
        }

        if (e.getSource() == tigerBreedButton) {
            if (tigers > 1) {
                double tigerBreedSuccess = random.nextDouble();
                if (tigerBreedSuccess >= 0.66) {
                    int tigerLitterSize = random.nextInt(5) + 2;
                    tigers = tigers + tigerLitterSize;
                    mainLabel.setText("Congratulations! You now have " + tigerLitterSize + " more tigers! You currently have " + tigers + " tigers.");
                } else {
                    mainLabel.setText("Your tigers did not have any cubs.");
                }

                tigerBreedEnDis();
            }
        }

        if (e.getSource() == elephantBreedButton) {
            if (elephants > 1) {
                double elephantBreedSuccess = random.nextDouble();
                if (elephantBreedSuccess >= 0.66) {
                    int elephantLitterSize = random.nextInt(1) + 1;
                    elephants += elephantLitterSize;
                    mainLabel.setText("Congratulations! You now have " + elephantLitterSize + " more elephants! You currently have " + elephants + " elephants.");
                } else {
                    mainLabel.setText("Your elephants did not have any calves.");
                }
                elephantBreedEnDis();
            }
        }

        if (e.getSource() == monkeyBreedButton) {
            if (elephants > 1) {
                double monkeyBreedSuccess = random.nextDouble();
                if (monkeyBreedSuccess >= 0.25) {
                    double monkeyLitterSize = random.nextDouble() + .05;
                    if (monkeyLitterSize >= 1) {
                        monkeys += 2;
                        mainLabel.setText("Congratulations! You now have 2 more monkeys! You currently have " + monkeys + " monkeys.");
                    } else{
                        monkeys += 1;
                        mainLabel.setText("Congratulations! You now have 1 more monkey! You currently have " + monkeys + " monkeys.");
                    }

                } else {
                    mainLabel.setText("Your monkeys did not have any babies.");
                }
                monkeyBreedEnDis();
            }
        }

        if (e.getSource() == giraffeBreedButton) {
            if (giraffes > 1) {
                double giraffeBreedSuccess = random.nextDouble();
                if (giraffeBreedSuccess >= 0.5) {
                    double giraffeLitterSize = random.nextDouble() + .1;
                    if (giraffeLitterSize >= 2) {
                        giraffes += 2;
                        mainLabel.setText("Congratulations! You now have 2 more giraffes! You currently have " + giraffes + " giraffes.");
                    } else {
                        giraffes += 1;
                        mainLabel.setText("Congratulations! You now have 1 more giraffe! You currently have " + giraffes + " giraffes.");
                    }

                } else {
                    mainLabel.setText("Your giraffes did not have any calves.");
                }
                giraffeBreedEnDis();
            }
        }

        if (e.getSource() == zebraBreedButton) {
            if (zebras > 1) {
                double zebraBreedSuccess = random.nextDouble();
                if (zebraBreedSuccess >= 0.5) {
                    double zebraLitterSize = random.nextDouble() + .1;
                    if (zebraLitterSize >= 2) {
                        zebras += 2;
                        mainLabel.setText("Congratulations! You now have 2 more zebras! You currently have " + zebras + " zebras.");
                    } else {
                        zebras += 1;
                        mainLabel.setText("Congratulations! You now have 1 more zebra! You currently have " + zebras + " zebras.");
                    }

                } else {
                    mainLabel.setText("Your zebras did not have any foals.");
                }
                zebraBreedEnDis();
            }
        }

        if (e.getSource() == exitButton) {
            exportSave();
            System.exit(123456789);
        }

        if (e.getSource() == buyButton) {
            shopLabel.setVisible(true);
            breedButton.setVisible(false);
            buyCloseButton.setVisible(true);
            tigerBuyButton.setVisible(true);
            elephantBuyButton.setVisible(true);
            monkeyBuyButton.setVisible(true);
            zebraBuyButton.setVisible(true);
            giraffeBuyButton.setVisible(true);
            label5.setVisible(false);
            label6.setVisible(false);
            buyButton.setVisible(false);
            breedCloseButton.setVisible(false);
            label7.setVisible(true);
            mainLabel.setLocation(10,130);
            mainLabel.setText("Choose an animal to buy to begin");
            tigerClicks = 0;
            elephantClicks = 0;
            zebraClicks = 0;
            giraffeClicks = 0;
            monkeyClicks = 0;
        }

        if (e.getSource() == buyCloseButton) {
            shopLabel.setVisible(false);
            breedButton.setVisible(true);
            buyCloseButton.setVisible(false);
            buyButton.setVisible(true);
            tigerBuyButton.setVisible(false);
            elephantBuyButton.setVisible(false);
            monkeyBuyButton.setVisible(false);
            zebraBuyButton.setVisible(false);
            giraffeBuyButton.setVisible(false);
            label5.setVisible(true);
            label6.setVisible(true);
            buyButton.setVisible(true);
            tigerClicks = 0;
            elephantClicks = 0;
            zebraClicks = 0;
            giraffeClicks = 0;
            monkeyClicks = 0;
            resetMainText();
        }

        if (e.getSource() == tigerBuyButton) {
            int price = rand.fromTo(6000, 8000);
            mainLabel.setText("A tiger will cost $" + price + ". Press the button marked \"Tigers\" to continue.");
                if(tigerClicks == 1) {
                    if(balance >= price) {
                        balance -= price;
                        mainLabel.setText("You now have 1 more tiger! Your balance is $" + balance + ".");
                        tigers++;
                    }
                    else {
                        mainLabel.setText("Not enough money! $" + (price - balance) + " more needed.");
                    }
                    tigerClicks = 0;
                }
                tigerClicks++;
        }

        if (e.getSource() == elephantBuyButton) {
            int price = rand.fromTo(13000, 40000);
            mainLabel.setText("An elephant will cost $" + price + ". Press the button marked \"Elephants\" to continue.");
            if(elephantClicks == 1) {
                if(balance >= price) {
                    balance -= price;
                    mainLabel.setText("You now have 1 more elephant! Your balance is $" + balance + ".");
                    elephants++;
                }
                else {
                    mainLabel.setText("Not enough money! $" + (price - balance) + " more needed.");
                }
                elephantClicks = 0;
            }
            elephantClicks++;
        }

        if (e.getSource() == giraffeBuyButton) {
            int price = rand.fromTo(10000, 55000);
            mainLabel.setText("A giraffe will cost $" + price + ". Press the button marked \"Giraffes\" to continue.");
            if(giraffeClicks == 1) {
                if(balance >= price) {
                    balance -= price;
                    mainLabel.setText("You now have 1 more giraffe! Your balance is $" + balance + ".");
                    giraffes++;
                }
                else {
                    mainLabel.setText("Not enough money! $" + (price - balance) + " more needed.");
                }
                giraffeClicks = 0;
            }
            giraffeClicks++;
        }

        if (e.getSource() == zebraBuyButton) {
            int price = rand.fromTo(3000, 7000);
            mainLabel.setText("A zebra will cost $" + price + ". Press the button marked \"Zebras\" to continue.");
            if(zebraClicks == 1) {
                if(balance >= price) {
                    balance -= price;
                    mainLabel.setText("You now have 1 more zebra! Your balance is $" + balance + ".");
                    zebras++;
                }
                else {
                    mainLabel.setText("Not enough money! $" + (price - balance) + " more needed.");
                }
                zebraClicks = 0;
            }
            zebraClicks++;
        }

        if (e.getSource() == monkeyBuyButton) {
            int price = rand.fromTo(1500, 6000);
            mainLabel.setText("A monkey will cost $" + price + ". Press the button marked \"Monkeys\" to continue.");
            if(monkeyClicks == 1) {
                if(balance >= price) {
                    balance -= price;
                    mainLabel.setText("You now have 1 more monkey! Your balance is $" + balance + ".");
                    monkeys++;
                }
                else {
                    mainLabel.setText("Not enough money! $" + (price - balance) + " more needed.");
                }
                monkeyClicks = 0;
            }
            monkeyClicks++;
        }

        if (e.getSource() == monkeySellButton) {
            int price = rand.fromTo(1000, 4000);
            mainLabel.setText("A monkey will sell for $" + price + ". Press the button marked \"Monkeys\" to continue.");
            if(monkeyClicks == 1) {
                if(monkeys > 0) {
                    balance += price;
                    monkeys--;
                    mainLabel.setText("You sold a monkey for $" + price + ". You now have " + monkeys + " monkeys.");
                    monkeyClicks = 0;
                }
                else {
                    mainLabel.setText("You need to have monkeys for that.");
                }
            }
            monkeyClicks++;
        }

        if (e.getSource() == tigerSellButton) {
            int price = rand.fromTo(4000, 5333);
            mainLabel.setText("A tiger will sell for $" + price + ". Press the button marked \"Tigers\" to continue.");
            if(tigerClicks == 1) {
                if(tigers > 0) {
                    balance += price;
                    tigers--;
                    mainLabel.setText("You sold a tiger for $" + price + ". You now have " + tigers + " tigers.");
                    tigerClicks = 0;
                }
                else {
                    mainLabel.setText("You need to have tigers for that.");
                }
            }
            tigerClicks++;
        }

        if (e.getSource() == elephantSellButton) {
            int price = rand.fromTo(8666, 26666);
            mainLabel.setText("An elephant will sell for $" + price + ". Press the button marked \"Elephants\" to continue.");
            if(elephantClicks == 1) {
                if(tigers > 0) {
                    balance += price;
                    elephants--;
                    mainLabel.setText("You sold an elephant for $" + price + ". You now have " + elephants + " elephants.");
                    elephantClicks = 0;
                }
                else {
                    mainLabel.setText("You need to have elephants for that.");
                }
            }
            elephantClicks++;
        }

        if (e.getSource() == giraffeSellButton) {
            int price = rand.fromTo(6666, 36666);
            mainLabel.setText("A giraffe will sell for $" + price + ". Press the button marked \"Giraffes\" to continue.");
            if(giraffeClicks == 1) {
                if(giraffes > 0) {
                    balance += price;
                    giraffes--;
                    mainLabel.setText("You sold a giraffe for $" + price + ". You now have " + giraffes + " giraffes.");
                    giraffeClicks = 0;
                }
                else {
                    mainLabel.setText("You need to have giraffes for that.");
                }
            }
            giraffeClicks++;
        }

        if (e.getSource() == zebraSellButton) {
            int price = rand.fromTo(2000, 4666);
            mainLabel.setText("A zebra will sell for $" + price + ". Press the button marked \"Zebras\" to continue.");
            if(zebraClicks == 1) {
                if(zebras > 0) {
                    balance += price;
                    zebras--;
                    mainLabel.setText("You sold a zebra for $" + price + ". You now have " + zebras + " zebras.");
                    zebraClicks = 0;
                }
                else {
                    mainLabel.setText("You need to have zebras for that.");
                }
            }
            zebraClicks++;
        }

        if (e.getSource() == helpButton) {
            mainLabel.setLocation(10,175);
            mainLabel.setText("<html><body>You can shop for animals with the \"Shop\" button<br>Sell animals for a slightly lower price with the \"Sell\" button<br>And breed two animals for a chance to get another with the \"Breed\" button<br>Background image by MuchMania on iStock</body></html>");
        }
    }

    public void tigerBreedEnDis() {
        TimerTask tigerEnable = new TimerTask() {
            public void run() {
                tigerBreedButton.setEnabled(true);
            }
        };
        tigerBreedButton.setEnabled(false);
        timer.schedule(tigerEnable, 16 * 2000);
    }

    public void elephantBreedEnDis() {
        TimerTask elephantEnable = new TimerTask() {
            public void run() {
                elephantBreedButton.setEnabled(true);
            }
        };
        elephantBreedButton.setEnabled(false);
        timer.schedule(elephantEnable, 95 * 2000);
    }

    public void monkeyBreedEnDis() {
        TimerTask monkeyEnable = new TimerTask() {
            public void run() {
                monkeyBreedButton.setEnabled(true);
            }
        };
        monkeyBreedButton.setEnabled(false);
        timer.schedule(monkeyEnable, (35 * 2000));
    }

    public void giraffeBreedEnDis() {
        TimerTask giraffeEnable = new TimerTask() {
            public void run() {
                giraffeBreedButton.setEnabled(true);
            }
        };
        giraffeBreedButton.setEnabled(false);
        timer.schedule(giraffeEnable, (16 * 2000));
    }

    public void zebraBreedEnDis() {
        TimerTask zebraEnable = new TimerTask() {
            public void run() {
                zebraBreedButton.setEnabled(true);
            }
        };
        zebraBreedButton.setEnabled(false);
        timer.schedule(zebraEnable, (13 * 2000));
    }

    public static void main(String[] args) {
            new App();
    }
}