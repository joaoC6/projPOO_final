package files;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class responsible for the grafical display of the game engine
 * @author poo2425p1g4
 * @version 1.0
 * */

public class GUI extends JPanel {
    private final BufferedImage backGround;
    private CopyOnWriteArrayList<GameObject> enabledObjects;

    public GUI() {
        enabledObjects = new CopyOnWriteArrayList<>();
        try{
            File f = new File("src/resources/Images/background.png");
            backGround = ImageIO.read(f);
//            backGround = ImageIO.read(getClass().getResource("/background.png"));
        }catch(IOException e) {throw new RuntimeException("Nao encontrou o ficheiro");}

        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(backGround, 0, 0, backGround.getWidth(), backGround.getHeight(), null);

        for(GameObject go : enabledObjects){
            Shape s = go.shape();
            Ponto p = go.transform().position();
            g2.drawImage(s.img(), (int) p.getX(), (int) p.getY(),(int) (Main.windowWidth * 0.1), (int) (Main.windowHeigth * 0.1), null);
        }

        g2.setFont(new Font("Times New Roman", Font.LAYOUT_LEFT_TO_RIGHT, 20));
        g2.setColor(Color.white);
        g2.drawString("Score:", 10, 20);


    }

    public void render(ArrayList<GameObject> enabled){
        JFrame jf = new JFrame();
        jf.setSize(Main.windowWidth, Main.windowHeigth);
        enabledObjects.clear();
        enabledObjects.addAll(enabled);
        repaint();
        jf.add(this);
        jf.setVisible(true);
    }
}
