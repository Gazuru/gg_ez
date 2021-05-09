import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static javax.swing.SwingUtilities.invokeLater;

public class SpaceGameView implements Runnable {
    private JButton drill = new JButton(), mine = new JButton(), craft = new JButton(), putBackMaterial = new JButton(),
            pickUpGate = new JButton(), putDownGate = new JButton(), craftRobot = new JButton(), craftGate = new JButton(),
            craftBase = new JButton(), putCoal = new JButton(), putIce = new JButton(), putIron = new JButton(), putUranium = new JButton();
    private static JFrame f;
    private LinkedList<JButton> buttons = new LinkedList<>(), craftButtons = new LinkedList<>(), putBackButtons = new LinkedList<>();
    private static JLayeredPane LPane = new JLayeredPane();
    private static LinkedList<JLabel> field = new LinkedList<>();
    private boolean craftVis = false;
    private boolean putVis = false;
    private static JLabel playerId = null;
    private static ArrayList<Field> objects = new ArrayList<>();
    private static ArrayList<JLabel> surface = new ArrayList<>();
    private static HashMap<JLabel, ArrayList<FlyingObject>> shownFLO = new HashMap<>();
    private static ArrayList<JLabel> materialNums = new ArrayList<>();


    public SpaceGameView(JFrame frame) {
        f = frame;
        Game.getInstance();
        Thread t = new Thread(Game.getInstance());
        t.start();
        initButtons();
        addButtonsToList();
        display();
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
                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERTELEN pickUpGate");
                }
            }
        });
        putDownGate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.getCurrent().placeGate()) {

                    Vars.TURN_DONE = true;

                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERES putDownGate");
                } else {
                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERTELEN putDownGate");
                }
            }
        });
        craftRobot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.getCurrent().buildRobot()) {
                    Vars.TURN_DONE = true;
                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERES ROBOT ÉPÍTÉS");
                } else {
                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERTELEN ROBOT ÉPÍTÉS");
                }
            }
        });
        craftBase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.getCurrent().buildBase()) {
                    Vars.TURN_DONE = true;
                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERES BÁZISÉPÍTÉS");
                    //TODO FINISH GAME
                } else {
                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERTELEN BÁZISÉPÍTÉS");
                }
            }
        });
        craftGate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.getCurrent().craftGatePair()) {
                    Vars.TURN_DONE = true;

                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERES KAPUPÁRÉPÍTÉS");
                } else {
                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERTELEN KAPUPÁRÉPÍTÉS");
                }
            }
        });
        putCoal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.getCurrent().putMaterial(new Coal())) {
                    Vars.TURN_DONE = true;

                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERES VISSZARAKAS");
                } else {
                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERTELEN VISSZARAKAS");
                }
            }
        });
        putIce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.getCurrent().putMaterial(new Ice())) {
                    Vars.TURN_DONE = true;

                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERES VISSZARAKAS");
                } else {
                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERTELEN VISSZARAKAS");
                }
            }
        });
        putIron.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.getCurrent().putMaterial(new Iron())) {
                    Vars.TURN_DONE = true;
                    
                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERES VISSZARAKAS");
                } else {
                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERTELEN VISSZARAKAS");
                }
            }
        });
        putUranium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Game.getCurrent().putMaterial(new Uranium())) {
                    Vars.TURN_DONE = true;

                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERES VISSZARAKAS");
                } else {
                    System.out.println(Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
                    System.out.println("SIKERTELEN VISSZARAKAS");
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

    public static void displayStats() {
        if (playerId != null) {
            LPane.remove(playerId);
            for (int i = 0; i < materialNums.size(); i++) {
                LPane.remove(materialNums.get(i));
            }
            materialNums.clear();
        }

        playerId = new JLabel("Selected Player: " + Game.getInstance().getGameObjects().indexOf(Game.getCurrent()));
        playerId.setFont(new Font(playerId.getFont().getName(), Font.BOLD, 24));
        playerId.setBounds((Vars.WINDOW_WIDTH - Vars.STATS_WIDTH - 40), (Vars.WINDOW_HEIGHT - 2 * Vars.USE_BUTTON_DIM) - 10, Vars.STATS_WIDTH, Vars.USE_BUTTON_DIM);
        playerId.setBackground(new Color(255, 192, 0));
        playerId.setOpaque(true);
        playerId.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));

        String gateN = Integer.toString(Game.getCurrent().sumGates());
        String iceN = Integer.toString(Game.getCurrent().sumMaterial(new Ice()));
        String ironN = Integer.toString(Game.getCurrent().sumMaterial(new Iron()));
        String uraniumN = Integer.toString(Game.getCurrent().sumMaterial(new Uranium()));
        String coalN = Integer.toString(Game.getCurrent().sumMaterial(new Coal()));
        JLabel numIce = new JLabel();
        JLabel numIron = new JLabel();
        JLabel numUranium = new JLabel();
        JLabel numCoal = new JLabel();
        JLabel numGate = new JLabel();
        numIce.setText(iceN);
        numIron.setText(ironN);
        numUranium.setText(uraniumN);
        numCoal.setText(coalN);
        numGate.setText(gateN);
        materialNums.add(numIce);
        materialNums.add(numIron);
        materialNums.add(numUranium);
        materialNums.add(numCoal);
        materialNums.add(numGate);

        for (int i = 0; i < materialNums.size(); i++) {
            materialNums.get(i).setBounds(40, Vars.USE_BUTTON_DIM - 20 + i * Vars.USE_BUTTON_DIM + 20, Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM);
            materialNums.get(i).setFont(new Font(materialNums.get(i).getFont().getName(), Font.BOLD, 24));
            materialNums.get(i).setForeground(Color.BLACK);
            LPane.add(materialNums.get(i), Integer.valueOf(3));
        }
        LPane.add(playerId, Integer.valueOf(2));
    }

    public static void displayEnding() {
    	Game.getInstance().setEnd(true);
    	if(Game.getInstance().getEnd()==true) {
    		JLabel label=new JLabel();
    		if(Game.getInstance().getNumShips()==0) {
    			
    			try {
    				label.setIcon(new ImageIcon(ImageIO.read(new File("resources/fields/asteroid_sunprox.png")).getScaledInstance(Vars.WINDOW_WIDTH, Vars.WINDOW_HEIGHT, Image.SCALE_DEFAULT)));
    	            System.out.println("L");
    	        } catch (IOException e) {
    	            e.printStackTrace();
    	        }
    		}else {
    			f.removeAll();
    			try {
    				label.setIcon(new ImageIcon(ImageIO.read(new File("resources/fields/asteroid_sunprox.png")).getScaledInstance(Vars.WINDOW_WIDTH, Vars.WINDOW_HEIGHT, Image.SCALE_DEFAULT)));
    				System.out.println("W");
    	        } catch (IOException e) {
    	            e.printStackTrace();
    	        }
    		}
    		//ezt hogy showolom xd
    		
    	}
    }

    public void display() {
        f.getContentPane().removeAll();
        f.revalidate();
        f.repaint();
        LPane.setDoubleBuffered(true);
        f.add(LPane);
        displayBG();
        displayButtons();
        
        /*while (true) {
            displayStats();
            displayObjects();
            f.revalidate();
            f.repaint();
            try {
                Thread.sleep(40);
            } catch (Exception e) {
            }
        }*/
    }

    public static void loadAsteroidImage(Field f, JLabel label) {
        if (f.getClass().equals(Asteroid.class)) {
            Asteroid a = (Asteroid) f;
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
        } else {
            Gate g = (Gate) f;
            try {
                if (g.getWorking() && !g.getWild()) {
                    label.setIcon(new ImageIcon(ImageIO.read(new File("resources/fields/gate_active.png")).getScaledInstance(Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE, Image.SCALE_DEFAULT)));
                } else if (g.getWild()) {
                    label.setIcon(new ImageIcon(ImageIO.read(new File("resources/fields/gate_mad.png")).getScaledInstance(Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE, Image.SCALE_DEFAULT)));
                } else if (!g.getWorking()) {
                    label.setIcon(new ImageIcon(ImageIO.read(new File("resources/fields/gate_inactive.png")).getScaledInstance(Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE, Image.SCALE_DEFAULT)));
                }
            } catch (Exception e) {
            }
        }
    }

    public static void loadFlyingObjectImage(JLabel label, FlyingObject fl) {
        try {
            if (fl.getClass().equals(Ship.class)) {
                label.setIcon(new ImageIcon(ImageIO.read(new File("resources/entities/space_man.png")).getScaledInstance(Vars.ENTITY_SIZE, Vars.ENTITY_SIZE, Image.SCALE_DEFAULT)));
            } else if (fl.getClass().equals(Robot.class)) {
                label.setIcon(new ImageIcon(ImageIO.read(new File("resources/entities/robot.png")).getScaledInstance(Vars.ENTITY_SIZE, Vars.ENTITY_SIZE, Image.SCALE_DEFAULT)));
            } else {
                label.setIcon(new ImageIcon(ImageIO.read(new File("resources/entities/UFO.png")).getScaledInstance(Vars.ENTITY_SIZE, Vars.ENTITY_SIZE, Image.SCALE_DEFAULT)));
            }
        } catch (Exception e) {
        }
    }

    public static void displayObjects() {
        if (objects.isEmpty())
            return;

        float n = (float) objects.size();

        if (!field.isEmpty()) {
            for (int i = 0; i < field.size(); i++) {
                LPane.remove(field.get(i));
            }
            field.clear();
        }

        if (!surface.isEmpty()) {
            for (int i = 0; i < surface.size(); i++) {
                LPane.remove(surface.get(i));
            }
            surface.clear();
            shownFLO.clear();
        }

        for (float i = 0; i < n; i++) {
            JLabel number = new JLabel();
            int index = Game.getInstance().getFields().indexOf(objects.get((int) i));
            JLabel asteroid = new JLabel();
            if (i != 0) {
                asteroid.setBounds((int) ((Vars.WINDOW_WIDTH / 2 - Vars.ASTEROID_SIZE / 2) + 250 * Math.cos(((i - 1) * 2 * Math.PI) / (n - 1.0f))), (int) ((Vars.WINDOW_HEIGHT / 2 - Vars.ASTEROID_SIZE / 2) + 200 * Math.sin(((i - 1) * 2 * Math.PI) / (n - 1.0f))), Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE);
                float finalI = i;
                asteroid.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Vars.DESTINATION = objects.get((int) finalI);
                        Game.getCurrent().move();
                        System.out.println("SIKERES MOVE");
                        Vars.TURN_DONE = true;
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
            } else {
                asteroid.setBounds((Vars.WINDOW_WIDTH - Vars.ASTEROID_SIZE) / 2, Vars.WINDOW_HEIGHT / 2 - Vars.ASTEROID_SIZE / 2, Vars.ASTEROID_SIZE, Vars.ASTEROID_SIZE);
                if(objects.get((int) i).getClass().equals(Gate.class)){
                    asteroid.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if(Game.getCurrent().useGate()){
                                System.out.println("SIKERES TP");
                                Vars.TURN_DONE = true;
                            }else{
                                System.out.println("SIKERTELEN TP");
                            }
                        }
                        @Override
                        public void mousePressed(MouseEvent e) {
                        }
                        @Override
                        public void mouseReleased(MouseEvent e) {
                        }
                        @Override
                        public void mouseEntered(MouseEvent e) {
                        }
                        @Override
                        public void mouseExited(MouseEvent e) {
                        }
                    });
                }
            }
            loadAsteroidImage(objects.get((int) i), asteroid);
            number.setFont(new Font(number.getFont().getName(), Font.BOLD, 16));
            number.setBounds(asteroid.getBounds().x + 20, asteroid.getBounds().y + 20, 80, 40);
            number.setOpaque(false);
            number.setText(String.valueOf(index));
            number.setForeground(new Color(255, 255, 255, 255));
            field.add(number);
            LPane.add(number, Integer.valueOf(3));
            LPane.add(asteroid, Integer.valueOf(2));
            field.add(asteroid);
            shownFLO.put(asteroid, objects.get((int) i).onSurface);
        }

        for (Map.Entry me : shownFLO.entrySet()) {
            JLabel asteroid = (JLabel) me.getKey();
            ArrayList<FlyingObject> flo = (ArrayList<FlyingObject>) me.getValue();
            if (!flo.isEmpty()) {
                for (float i = 0; i < flo.size(); i++) {
                    JLabel flyingobject = new JLabel();
                    flyingobject.setBounds((int) ((asteroid.getBounds().x + Vars.ENTITY_SIZE) + 40 * Math.cos((2.0f * Math.PI * i) / (float) flo.size())), (int) ((asteroid.getBounds().y + Vars.ENTITY_SIZE) + 40 * Math.sin(2.0f * i * Math.PI / (float) flo.size())), Vars.ENTITY_SIZE, Vars.ENTITY_SIZE);
                    loadFlyingObjectImage(flyingobject, flo.get((int) i));
                    LPane.add(flyingobject, Integer.valueOf(3));
                    surface.add(flyingobject);
                }
            }
        }

    }

    public static void addObjects(ArrayList<Field> l) {
        objects.clear();
        objects = l;
    }

    public static void refresh() {
    	
        displayStats();
        displayObjects();
        f.revalidate();
        f.repaint();
        displayEnding();
    }

    @Override
    public void run() {
        refresh();
    }
}
