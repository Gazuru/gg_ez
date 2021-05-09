import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class SpaceGameView {
    private JButton drill = new JButton(), mine = new JButton(), craft = new JButton(), putBackMaterial = new JButton(), pickUpGate = new JButton(), putDownGate = new JButton(), craftRobot = new JButton(), craftGate = new JButton(), craftBase = new JButton();
    private JFrame f;
    private LinkedList<JButton> buttons = new LinkedList<>();
    private JLayeredPane LPane = new JLayeredPane();

    public SpaceGameView(JFrame frame) {
        f = frame;
        initButtons();
        addButtonsToList();
        display();
    }

    public void initButtons() {
        try {
            Image drillImage = ImageIO.read(new File("resources/drill.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT);
            Image mineImage = ImageIO.read(new File("resources/mine.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT);
            Image craftImage = ImageIO.read(new File("resources/craft.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT);
            Image putBackMaterialImage = ImageIO.read(new File("resources/putdown_material.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT);
            Image pickUpGateImage = ImageIO.read(new File("resources/pickupgate.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT);
            Image putDownGateImage = ImageIO.read(new File("resources/putdowngate.png")).getScaledInstance(Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM, Image.SCALE_DEFAULT);
            drill.setIcon(new ImageIcon(drillImage));
            mine.setIcon(new ImageIcon(mineImage));
            craft.setIcon(new ImageIcon(craftImage));
            putBackMaterial.setIcon(new ImageIcon(putBackMaterialImage));
            pickUpGate.setIcon(new ImageIcon(pickUpGateImage));
            putDownGate.setIcon(new ImageIcon(putDownGateImage));
        } catch (IOException e) {
        }
    }

    public void addButtonsToList() {
        buttons.add(drill);
        buttons.add(mine);
        buttons.add(craft);
        buttons.add(putBackMaterial);
        buttons.add(pickUpGate);
        buttons.add(putDownGate);
    }

    public void displayButtons() {

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setBounds(((i + 1) * 10) + i * Vars.USE_BUTTON_DIM, (Vars.WINDOW_HEIGHT - 2 * Vars.USE_BUTTON_DIM) - 10, Vars.USE_BUTTON_DIM, Vars.USE_BUTTON_DIM);
            LPane.add(buttons.get(i), Integer.valueOf(2));
        }
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
