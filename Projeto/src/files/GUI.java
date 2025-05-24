package files;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class responsible for the grafical display of the game engine
 * @author poo2425p1g4
 * @version 1.0
 * */

public class GUI extends JPanel {
    private GameEngine ge;
    private final BufferedImage backGround;
    private JPanel p;

    public GUI() throws IOException {
        try{
            File f = new File(/*"src/resources/Images/background.png"*/ "C:\\Users\\masst\\Desktop\\java\\poo\\Projeto\\src\\resources\\Images\\backgorund.png");
            backGround = ImageIO.read(f);
        }catch(IOException e) {throw new RuntimeException("Nao encontrou o ficheiro");}

        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(backGround, 0, 0, backGround.getWidth(), backGround.getHeight(), null);

    }

    public void render(){
        JFrame f = new JFrame();
        repaint();
        f.add(this);
        f.setSize(500, 500);
        f.setVisible(true);
    }
}
