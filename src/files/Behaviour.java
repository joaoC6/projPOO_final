package files;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

/**
 * Class responsible for receiving the input of the user and transmiting to the other classes
 * @author poo2425p1g4
 * @version 1.0
 * **/

public class Behaviour implements KeyListener {
    private Set<Integer> keysPressed = new HashSet<>();
    private  GameObject go;
    private static final double MOVE_SPEED = 5.0;
    private static final double ROTATE_SPEED = 5.0;

    public Behaviour(GameObject go){
        this.go = go;
    }

    public GameObject moveSet(Set<Integer> keys){
        Transform t = (Transform) go.transform();
        if (keysPressed.contains(KeyEvent.VK_A)) t.move(new Ponto(-MOVE_SPEED, 0), 0);
        if (keysPressed.contains(KeyEvent.VK_D)) t.move(new Ponto(MOVE_SPEED, 0), 0);
        if (keysPressed.contains(KeyEvent.VK_W)) t.move(new Ponto(0, -MOVE_SPEED), 0);
        if (keysPressed.contains(KeyEvent.VK_S)) t.move(new Ponto(0, MOVE_SPEED), 0);
        if(keysPressed.contains(KeyEvent.VK_SPACE)) ;//ainda n feito mas sera o tiro
        go.setTranform(t);
        return go;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
