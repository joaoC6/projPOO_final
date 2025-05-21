import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Class responsible for the grafical display of the game engine
 * @author poo2425p1g4
 * @version 1.0
 * */

public class GUI extends JPanel {
    private GameEngine ge;
    private BufferedImage backGround;

    public GUI() throws IOException {
        try{
            backGround = ImageIO.read(getClass().getResource("Images/background.png"));
        }catch(IOException e) {throw new IOException();}
        setBackground(Color.BLACK);
    }

    public void render(){
        
    }
}
