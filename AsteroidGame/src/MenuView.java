import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class MenuView {
    private JFrame f;

    public MenuView(JFrame f_) {
        this.f = f_;
    }


    public void Display() {
        JLayeredPane LPane = new JLayeredPane();
        f.getContentPane().add(LPane);

        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("resources/background.png"));
        background.setBounds(0, 0, Vars.WINDOW_WIDTH, Vars.WINDOW_HEIGHT);


        JButton startButton = new JButton();
        startButton.setBounds((Vars.WINDOW_WIDTH - Vars.BUTTON_WIDTH) / 2, (Vars.WINDOW_HEIGHT - 2 * Vars.BUTTON_HEIGHT) / 4, Vars.BUTTON_WIDTH, Vars.BUTTON_HEIGHT);
        try {
            Image image = ImageIO.read(new File("resources/startButton.png")).getScaledInstance(Vars.BUTTON_WIDTH, Vars.BUTTON_HEIGHT, Image.SCALE_DEFAULT);
            startButton.setIcon(new ImageIcon(image));
        } catch (Exception e) {
        }
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startButtonPressed();
            }
        });

        JButton exitButton = new JButton();
        exitButton.setBounds((Vars.WINDOW_WIDTH - Vars.BUTTON_WIDTH) / 2, (Vars.WINDOW_HEIGHT - 2 * Vars.BUTTON_HEIGHT) * 3 / 4, Vars.BUTTON_WIDTH, Vars.BUTTON_HEIGHT);
        try {
            Image image = ImageIO.read(new File("resources/exitButton.png")).getScaledInstance(Vars.BUTTON_WIDTH, Vars.BUTTON_HEIGHT, Image.SCALE_DEFAULT);
            exitButton.setIcon(new ImageIcon(image));
        } catch (Exception e) {
        }
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        LPane.add(background, Integer.valueOf(1));
        LPane.add(startButton, Integer.valueOf(2));
        LPane.add(exitButton, Integer.valueOf(3));

        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.setSize(Vars.WINDOW_WIDTH, Vars.WINDOW_HEIGHT);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private void startButtonPressed() {
        JFrame inp = new JFrame();
        inp.setName("Játékosok száma");

        inp.pack();
        String input = JOptionPane.showInputDialog(inp,"Adja meg a játékosok számát:");
        if(input != null && input.length() > 0){
            try{
            Vars.NUM_OF_PLAYERS = Integer.parseInt(input);
            if(Vars.NUM_OF_PLAYERS != 0){
                System.out.println("A JATEKOSOK SZAMA: " + Vars.NUM_OF_PLAYERS);
                GameView.setSpaceGameView(new SpaceGameView(f));
            }else{
                System.out.println("NEM MEGFELELO SZAM!");
            }
            }catch (Exception e){
                System.out.println("NEM MEGFELELO SZAM!");
            }
        }
    }
}
