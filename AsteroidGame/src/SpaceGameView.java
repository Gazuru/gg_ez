import javax.swing.*;
import java.util.LinkedList;

public class SpaceGameView {
    private JButton drill, mine, craft, pickUpMaterial, pickUpGate, putDownGate, craftRobot, craftGate, craftBase;
    private JFrame f;

    public SpaceGameView(JFrame f){
        this.f = f;
        display();
    }

    public void displayButtons(){
        LinkedList<JButton> buttons = new LinkedList<>();

    }

    public void displayEnding(String picName){}

    public void display(){
        f.getContentPane().removeAll();
        f.repaint();

    }

    public void refresh(){}

}
