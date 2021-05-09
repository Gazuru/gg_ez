import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class SpaceGameView {
    private JButton drill = new JButton(), mine = new JButton(), craft = new JButton(), putBackMaterial = new JButton(),
            pickUpGate = new JButton(), putDownGate = new JButton(), craftRobot = new JButton(), craftGate = new JButton(),
            craftBase = new JButton(), putCoal = new JButton(), putIce = new JButton(), putIron = new JButton(), putUranium = new JButton();
    private JFrame f;
    private LinkedList<JButton> buttons = new LinkedList<>(), craftButtons = new LinkedList<>(), putBackButtons = new LinkedList<>();
    private JLayeredPane LPane = new JLayeredPane();
    private boolean craftVis = false;
    private boolean putVis = false;

    public SpaceGameView(JFrame frame) {
        f = frame;
        Game.getInstance();
        Thread t = new Thread(Game.getInstance());
        t.start();
        initButtons();
        addButtonsToList();
        display();
    }

    public void initButtons() {
        try {
            drill.setIcon(new ImageIcon(ImageIO.read(new File("resources/buttons/drill.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT)));
            mine.setIcon(new ImageIcon(ImageIO.read(new File("resources/buttons/mine.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT)));
            craft.setIcon(new ImageIcon(ImageIO.read(new File("resources/buttons/craft.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT)));
            putBackMaterial.setIcon(new ImageIcon(ImageIO.read(new File("resources/buttons/putdown_material.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT)));
            pickUpGate.setIcon(new ImageIcon(ImageIO.read(new File("resources/buttons/pickupgate.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT)));
            putDownGate.setIcon(new ImageIcon(ImageIO.read(new File("resources/buttons/putdowngate.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT)));
            craftRobot.setIcon(new ImageIcon(ImageIO.read(new File("resources/buttons/craft_robot.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT)));
            craftGate.setIcon(new ImageIcon(ImageIO.read(new File("resources/buttons/craft_gate.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT)));
            craftBase.setIcon(new ImageIcon(ImageIO.read(new File("resources/buttons/craft_base.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT)));
            putCoal.setIcon(new ImageIcon(ImageIO.read(new File("resources/buttons/put_down_coal.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT)));
            putIce.setIcon(new ImageIcon(ImageIO.read(new File("resources/buttons/put_down_ice.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT)));
            putIron.setIcon(new ImageIcon(ImageIO.read(new File("resources/buttons/put_down_iron.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT)));
            putUranium.setIcon(new ImageIcon(ImageIO.read(new File("resources/buttons/put_down_uranium.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT)));
        } catch (IOException e) {
        }
        buttonInitActionListeners();
    }

    public void buttonInitActionListeners() {
        drill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.getCurrent().drill()) {
                    Vars.TURN_DONE = true;
                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERES DRILL");
                } else {
                    Vars.TURN_DONE = false;
                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERTELEN DRILL");
                }
            }
        });
        mine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.getCurrent().mine()) {

                    Vars.TURN_DONE = true;

                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERES MINE");
                } else {
                    Vars.TURN_DONE = false;
                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERTELEN MINE");
                }
            }
        });
        craft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(putVis){
                    togglePut();
                }
                toggleCraft();
            }
        });
        putBackMaterial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(craftVis){
                   toggleCraft();
               }
               togglePut();
            }
        });
        pickUpGate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.getCurrent().pickUpGate()) {

                    Vars.TURN_DONE = true;

                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERES pickUpGate");
                } else {
                    Vars.TURN_DONE = false;
                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERTELEN pickUpGate");
                }
            }
        });
        putDownGate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.getCurrent().pickUpGate()) {

                    Vars.TURN_DONE = true;

                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERES putDownGate");
                } else {
                    Vars.TURN_DONE = false;
                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERTELEN putDownGate");
                }
            }
        });
    }

    public void addButtonsToList() {
        buttons.add(drill);
        buttons.add(mine);
        buttons.add(craft);
        buttons.add(putBackMaterial);
        buttons.add(pickUpGate);
        buttons.add(putDownGate);
        craftButtons.add(craftRobot);
        craftButtons.add(craftGate);
        craftButtons.add(craftBase);
        putBackButtons.add(putCoal);
        putBackButtons.add(putIce);
        putBackButtons.add(putIron);
        putBackButtons.add(putUranium);
    }

    public void displayButtons() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setBounds(((i + 1) * 10) + i * Vars.USE_BUTTON_DIM, (Vars.WINDOW_HEIGHT - 2 * Vars.USE_BUTTON_DIM) - 10, Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM);
            LPane.add(buttons.get(i), Integer.valueOf(2));
        }
        for (int i = 0; i < craftButtons.size(); i++) {
            craftButtons.get(i).setBounds((craft.getBounds().x + (i - 1) * (Vars.USE_BUTTON_DIM + 10)), craft.getBounds().y - Vars.USE_BUTTON_DIM - 10, Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM);
            craftButtons.get(i).setVisible(false);
            LPane.add(craftButtons.get(i), Integer.valueOf(2));
        }
        for(int i = 0;i < putBackButtons.size(); i++){
            putBackButtons.get(i).setBounds((int) ((putBackMaterial.getBounds().x - 1.5*(Vars.USE_BUTTON_DIM+10)) + i * (Vars.USE_BUTTON_DIM + 10)), putBackMaterial.getBounds().y - Vars.USE_BUTTON_DIM - 10, Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM);
            putBackButtons.get(i).setVisible(false);
            LPane.add(putBackButtons.get(i), Integer.valueOf(2));
        }
    }

    public void toggleCraft() {

        for (JButton button : craftButtons) {
            if (craftVis) {
                button.setVisible(false);
            } else {
                button.setVisible(true);
            }
        }
        craftVis = !craftVis;
    }

    public void togglePut() {
        for (JButton button : putBackButtons) {
            if (putVis) {
                button.setVisible(false);
            } else {
                button.setVisible(true);
            }
        }
        putVis = !putVis;
    }


    public void displayBG() {
        JLabel BG = new JLabel();
        BG.setIcon(new ImageIcon("resources/background.png"));
        BG.setBounds(0, 0, Vars.WINDOW_WIDTH, Vars.WINDOW_HEIGHT);
        LPane.add(BG, Integer.valueOf(1));
    }

    public void displayEnding(String picName) {
    }

    public void display() {
        f.getContentPane().removeAll();
        f.revalidate();
        f.repaint();
        f.add(LPane);
        displayBG();
        displayButtons();
        f.revalidate();
        f.repaint();
    }

    public void refresh() {
    }
}
