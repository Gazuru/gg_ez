import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import static javax.swing.SwingUtilities.invokeLater;

public class SpaceGameView implements Runnable {
    private JButton drill = new JButton(), mine = new JButton(), craft = new JButton(), putBackMaterial = new JButton(),
            pickUpGate = new JButton(), putDownGate = new JButton(), craftRobot = new JButton(), craftGate = new JButton(),
            craftBase = new JButton(), putCoal = new JButton(), putIce = new JButton(), putIron = new JButton(), putUranium = new JButton();
    private JFrame f;
    private LinkedList<JButton> buttons = new LinkedList<>(), craftButtons = new LinkedList<>(), putBackButtons = new LinkedList<>();
    private JLayeredPane LPane = new JLayeredPane();
    private LinkedList<JLabel> field = new LinkedList<>();
    private boolean craftVis = false;
    private boolean putVis = false;
    static ArrayList<Object> objects = new ArrayList<>();

    public SpaceGameView(JFrame frame) {
        f = frame;
        Game.getInstance();
        Thread t = new Thread(Game.getInstance());
        t.start();
        initButtons();
        addButtonsToList();
        Thread t1 = new Thread(this);
        t1.start();
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
                if (putVis) {
                    togglePut();
                }
                toggleCraft();
            }
        });
        putBackMaterial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (craftVis) {
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
        for (int i = 0; i < putBackButtons.size(); i++) {
            putBackButtons.get(i).setBounds((int) ((putBackMaterial.getBounds().x - 1.5 * (Vars.USE_BUTTON_DIM + 10)) + i * (Vars.USE_BUTTON_DIM + 10)), putBackMaterial.getBounds().y - Vars.USE_BUTTON_DIM - 10, Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM);
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
        JLabel inv = new JLabel();
        BG.setIcon(new ImageIcon("resources/background.png"));
        BG.setBounds(0, 0, Vars.WINDOW_WIDTH, Vars.WINDOW_HEIGHT);
        try {
            Image img = ImageIO.read(new File("resources/inventory.png"));
            inv.setIcon(new ImageIcon(img));
            inv.setIcon(new ImageIcon(img.getScaledInstance(inv.getPreferredSize().width / 2, inv.getPreferredSize().height / 2, Image.SCALE_DEFAULT)));
        } catch (Exception e) {
        }
        inv.setBounds(-20, -10, inv.getPreferredSize().width, inv.getPreferredSize().height);
        LPane.add(BG, Integer.valueOf(1));
        LPane.add(inv, Integer.valueOf(2));
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
        while (true) {
            displayObjects();
            f.revalidate();
            f.repaint();
            try {
                Thread.sleep(40);
            } catch (Exception e) {
            }
        }
    }

    public void loadAsteroidImage(Asteroid a, JLabel label) {
        int layer = a.getLayer();
        boolean sunprox = a.getInSunProximity();
        Material core = a.getCore();

        try {
            if (layer != 0) {
                if (sunprox) {
                    label.setIcon(new ImageIcon(ImageIO.read(new File("resources/fields/asteroid_sunprox.png")).getScaledInstance(Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE, Image.SCALE_DEFAULT)));
                } else {
                    label.setIcon(new ImageIcon(ImageIO.read(new File("resources/fields/asteroid.png")).getScaledInstance(Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE, Image.SCALE_DEFAULT)));
                }
                return;
            } else {
                if (core == null) {
                    if (sunprox) {
                        label.setIcon(new ImageIcon(ImageIO.read(new File("resources/fields/asteroid_empty_sunprox.png")).getScaledInstance(Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE, Image.SCALE_DEFAULT)));

                    } else {
                        label.setIcon(new ImageIcon(ImageIO.read(new File("resources/fields/asteroid_empty.png")).getScaledInstance(Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE, Image.SCALE_DEFAULT)));
                    }
                    return;
                } else if (Ice.class.equals(core.getClass())) {
                    if (sunprox) {
                        label.setIcon(new ImageIcon(ImageIO.read(new File("resources/fields/asteroid_ice_sunprox.png")).getScaledInstance(Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE, Image.SCALE_DEFAULT)));
                    } else {
                        label.setIcon(new ImageIcon(ImageIO.read(new File("resources/fields/asteroid_ice.png")).getScaledInstance(Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE, Image.SCALE_DEFAULT)));
                    }
                    return;
                } else if (Iron.class.equals(core.getClass())) {
                    if (sunprox) {
                        label.setIcon(new ImageIcon(ImageIO.read(new File("resources/fields/asteroid_iron_sunprox.png")).getScaledInstance(Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE, Image.SCALE_DEFAULT)));
                    } else {
                        label.setIcon(new ImageIcon(ImageIO.read(new File("resources/fields/asteroid_iron.png")).getScaledInstance(Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE, Image.SCALE_DEFAULT)));
                    }
                    return;
                } else if (Coal.class.equals(core.getClass())) {
                    if (sunprox) {
                        label.setIcon(new ImageIcon(ImageIO.read(new File("resources/fields/asteroid_coal_sunprox.png")).getScaledInstance(Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE, Image.SCALE_DEFAULT)));

                    } else {
                        label.setIcon(new ImageIcon(ImageIO.read(new File("resources/fields/asteroid_coal.png")).getScaledInstance(Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE, Image.SCALE_DEFAULT)));
                    }
                    return;
                } else if (Uranium.class.equals(core.getClass())) {
                    if (sunprox) {
                        label.setIcon(new ImageIcon(ImageIO.read(new File("resources/fields/asteroid_uranium_sunprox.png")).getScaledInstance(Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE, Image.SCALE_DEFAULT)));
                    } else {
                        label.setIcon(new ImageIcon(ImageIO.read(new File("resources/fields/asteroid_uranium.png")).getScaledInstance(Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE, Image.SCALE_DEFAULT)));
                    }
                    return;
                }
            }
        } catch (Exception e) {
        }
    }

    public void displayObjects() {
        if (objects.isEmpty())
            return;

        float n = (float) objects.size();

        if(!field.isEmpty()){
            for(int i = 0 ; i < field.size(); i++){
                LPane.remove(field.get(i));
            }
            field.clear();
        }

        for (float i = 0; i < n; i++) {
            JLabel asteroid = new JLabel();
            if (i != 0) {
                asteroid.setBounds((int) ((Vars.WINDOW_WIDTH / 2 - Vars.ASTEROID_SIZE / 2) + 250 * Math.cos(((i - 1) * 2 * Math.PI) / (n - 1.0f))), (int) ((Vars.WINDOW_HEIGHT / 2 - Vars.ASTEROID_SIZE / 2) + 200 * Math.sin(((i - 1) * 2 * Math.PI) / (n - 1.0f))), Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE);
            } else {
                asteroid.setBounds((Vars.WINDOW_WIDTH - Vars.ASTEROID_SIZE) / 2, Vars.WINDOW_HEIGHT / 2 - Vars.ASTEROID_SIZE / 2, Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE);
            }
            loadAsteroidImage((Asteroid) objects.get((int) i), asteroid);
            LPane.add(asteroid, Integer.valueOf(2));
            field.add(asteroid);
        }
    }

    public static void addObjects(ArrayList<Object> l) {
        objects.clear();
        objects = l;
    }

    public void refresh() {
        display();
    }

    @Override
    public void run() {
        refresh();
    }
}
