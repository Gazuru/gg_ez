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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

public class MenuView {
	JFrame f;
	public MenuView(JFrame f_) {
		this.f=f_;
	}

	public void Display() {
		JLayeredPane LPane =new JLayeredPane();
        f.getContentPane() .add(LPane);

        JLabel background= new JLabel();
        background.setIcon( new ImageIcon("resources/background.png") );
        background.setBounds(0,0,500,500);
        
        
        JButton startButton= new JButton();
        startButton.setBounds(200,100,100,40);
        try{
            Image image = ImageIO.read(new File("resources/startButton.png")).getScaledInstance(100, 40, Image.SCALE_DEFAULT);
            startButton.setIcon(new ImageIcon(image));
        } 
        catch (Exception e) {
        }
        startButton.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        	    //selectionButtonPressed();
        	  } 
        	} );

        JButton exitButton= new JButton();
        exitButton.setBounds(200,200,100,40);
        try{
            Image image = ImageIO.read(new File("resources/exitButton.png")).getScaledInstance(100, 40, Image.SCALE_DEFAULT);
            exitButton.setIcon(new ImageIcon(image));
        } 
        catch (Exception e) {
        }
        exitButton.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
      		System.exit(0);
      	  } 
      	} );

        LPane.add(background, new Integer(1));
        LPane.add(exitButton, new Integer(3));
        LPane.add(startButton, new Integer(2));
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.setSize (500,500) ;
        f.setVisible(true);
	}
}
