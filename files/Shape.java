package files;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Shape {
    private BufferedImage img;
    private String name;

    public Shape(String name){
        this.name = name;
        getImage();
    }

    public BufferedImage img(){return this.img;}

    private void getImage(){
        switch(name){
            case "asteroid":{
                try{
                    img = ImageIO.read(new File("src/resources/Images/asteroid.png"));
                }catch (IOException e){
                    throw new RuntimeException();
                }
                break;
            }

            case "nave":{
                try{
                    img = ImageIO.read(new File("src/resources/Images/nave.png"));
                }catch (IOException e){
                    throw new RuntimeException("nao encontrou o ficheiro" + e.getMessage());
                }
                break;
            }

            case "nave_inimiga":{
                try{
                    img = ImageIO.read(new File("src/resources/Images/nave_inimiga.png"));
                }catch (IOException e){
                    throw new RuntimeException();
                }
                break;
            }

            case "nave_boss":{
                try{
                    img = ImageIO.read(new File("src/resources/Images/nave_boss.png"));
                }catch (IOException e){
                    throw new RuntimeException();
                }
                break;
            }

            case "nave_mae":{
                try{
                    img = ImageIO.read(new File("src/resources/Images/nave_mae.png"));
                }catch (IOException e){
                    throw new RuntimeException();
                }
                break;
            }

            case "parede":{
                try{
                    img = ImageIO.read(new File("src/resources/Images/parede.png"));
                }catch (IOException e){
                    throw new RuntimeException();
                }
                break;
            }

            case "shield":{
                try{
                    img = ImageIO.read(new File("src/resources/Images/shield.png"));
                }catch (IOException e){
                    throw new RuntimeException();
                }
                break;
            }

            case "speed_misseis":{
                try{
                    img = ImageIO.read(new File("src/resources/Images/speed_misseis.png"));
                }catch (IOException e){
                    throw new RuntimeException();
                }
                break;
            }

            case "speed_nave":{
                try{
                    img = ImageIO.read(new File("src/resources/Images/speed_nave.png"));
                }catch (IOException e){
                    throw new RuntimeException();
                }
                break;
            }

            default: throw new IllegalArgumentException("gameobject invalido");
        }
    }
}
