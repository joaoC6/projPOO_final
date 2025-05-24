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
    private GameEngine ge;
    private final BufferedImage backGround;
    private CopyOnWriteArrayList<GameObject> enabledObjects;

    public GUI() throws IOException {
        enabledObjects = new CopyOnWriteArrayList<>();
        try{
            File f = new File(/*"src/resources/Images/background.png"*/ "C:\\Users\\masst\\Desktop\\java\\poo\\Projeto\\src\\resources\\Images\\backgorund.png");
            backGround = ImageIO.read(f);
        }catch(IOException e) {throw new RuntimeException("Nao encontrou o ficheiro");}

        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(backGround, 0, 0, backGround.getWidth(), backGround.getHeight(), null);

//        for(GameObject go : enabledObjects){
//            ITransform t = go.transform();
//              agr vem a parte onde iamos pegar os inputs aplicar e dps tratava-se
//        }
//        if(!enabledObjects.empty()){
//          g2.setFont(new Font("Times New Roman", Font.LAYOUT_LEFT_TO_RIGHT, 20));
//          g2.setColor(Color.white);
//          g2.drawString("Score:", 0, 0);
//        }

    }

    public void render(ArrayList<GameObject> enabled){
        enabledObjects.clear();
        enabledObjects.addAll(enabled);
        repaint();
    }
}
