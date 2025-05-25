package files;

import java.util.HashMap;

/**
 * The engine of the game, it's here that every calculation occurs and then is transmitted to the GUI
 * @author POO2425p1g4
 * @version 01/04/2025
 * @inv none
 */
public class GameEngine{
    private HashMap<Integer, GameObject> objects;
    private final int n;

    /**
     * @param n the ammount of game objects we are going to have in the engine
     */
    public GameEngine(int n){
        assert(n >= 0);
        this.n = n;
        this.objects = new HashMap<>(n);
    }

    /**
     * @param go tha game object we are going to add on the hashmap
     */
    public void add(GameObject go){objects.put(go.hashCode(), go);}

    /**
     * @return the string representation of the game engine
     */
    @Override
    public String toString(){ return objects.toString();}

}

