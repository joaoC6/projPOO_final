package files;

import java.util.HashMap;
import java.util.Set;

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
     * @param go the game object that we are going to remove of the hash map
     */
    public void destroy(GameObject go){objects.remove(go.hashCode());}

    /**
     * @return the string representation of the game engine
     */
    @Override
    public String toString(){ return objects.toString();}

    /**
     * @param col the collection of game objects that are colliding
     * @return the string representation of the objects that are colliding
     */
    private String printCollision(GameObject[] col, int counter){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < counter; i++){
            //if(col[i] == null) break;
            sb.append(col[i].name() + " ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * function that runs all the calculations
     */
    public void run(int mode){

    }
}


//        Set<Integer> index;
//        for(int j = 0; j < f; j++)
//            objects.forEach( (i, go) -> go.transform());
//        index = objects.keySet();
//        for(int i: index){
//            GameObject go1 = objects.get(i);
//            GameObject[] colisions = new GameObject[objects.size()];
//            colisions[0] = go1;
//            int length = 1;
//            for(int j: index){
//                GameObject  go2 = objects.get(j);
//                if(i == j || go1 == null || go2 == null) continue;
//                if(go1.checkCollider(go2.collider(0)) && go2.checkCollider(go1.collider(1)))
//                    colisions[length++] = go2;
//            }
//          }
//            if(length > 1) System.out.println(printCollision(colisions, length));