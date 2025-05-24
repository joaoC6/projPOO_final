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
    private final int n, f;

    /**
     * @param n the ammount of game objects we are going to have in the engine
     * @param f the ammount of frames it is going to handle
     */
    public GameEngine(int n, int f){
        assert(n >= 0 && f > 0);
        this.n = n;
        this.f = f;
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
     * function that runs all the calculations f times, ex if  f = 4 the it runs the loop 4 times
     */
    public void run(){
        Set<Integer> index;
        for(int j = 0; j < f; j++)
            objects.forEach( (i, go) -> go.transform());
        index = objects.keySet();
        for(int i: index){
            GameObject go1 = objects.get(i);
            GameObject[] colisions = new GameObject[objects.size()];
            colisions[0] = go1;
            int length = 1;
            for(int j: index){
                GameObject  go2 = objects.get(j);
                if(i == j || go1 == null || go2 == null) continue;
                if(go1.checkCollider(go2.collider(0)) && go2.checkCollider(go1.collider(1)))
                    colisions[length++] = go2;
            }

            if(length > 1) System.out.println(printCollision(colisions, length));
        }

//        objects.forEach((i, go1) ->{
//            GameObject[] colissions = new GameObject[objects.size()];
//            colissions[0] = go1;
//            int length = 1;
//            objects.forEach((j, go2) -> {
//            if(i != j && go1 != null && go2 != null){
//                if(go1.checkCollider((Collider) go2.collider()) && go2.checkCollider((Collider) go1.collider()))
//                    colissions[length++] = go2;
//            }
//            });
//            if(length > 1)System.out.println(printCollision(colissions, length));
//        });
    }
}



//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class GameEngine {
//    private ArrayList<GameObject> objects;
//    private final int n, f;
//
//    @SuppressWarnings("unchecked")
//    public GameEngine(int n, int f){
//        assert(n >= 0 && f > 0);
//        this.n = n;
//        this.f = f;
//        this.objects = new ArrayList<>(this.n);
//    }
//
//    public void add(GameObject go){this.objects.add(go);}
//
//    @SuppressWarnings("unchecked")
//    public void destroy(GameObject go){
//        objects.remove(go);
//    }
//
//    public void run(){
//        for(int i = 0; i < f; i++){
//            for(int j = 0; j < objects.size(); j++){
//                objects.get(j).transform();
//            }
//        }
//
////        for(int i = 0; i < objects.size(); i++){
////            System.out.println(objects.get(i).toString());
////        }
//        for(int j = 0; j < objects.size(); j++){
//            ArrayList<String> collisions = new ArrayList<>();
//            collisions.add(objects.get(j).name());
//            for(int h = 0; h < objects.size(); h++){
//                if(objects.get(j).checkCollider( objects.get(h).collider(1) ) && j != h){
//                    if(objects.get(h).checkCollider(objects.get(j).collider(1)))
//                        collisions.add(objects.get(h).name());
//                }
//            }
//            if(collisions.size() > 1) System.out.println(printCollision(collisions));
//        }
//    }
//
//    @Override
//    public String toString(){
//        StringBuilder sb = new StringBuilder();
//        for(int i = 0; i < objects.size(); i++){
//            sb.append(objects.get(i));
//            sb.append("\n");
//        }
//        return sb.toString();
//    }
//
//    private String printCollision(ArrayList<String> col){
//        StringBuilder sb = new StringBuilder();
//        for(String s : col){
//            sb.append(s + " ");
//        }
//        sb.deleteCharAt(sb.length() - 1);
//        return sb.toString();
//    }
//}
//
//
///** experimento com nodes de criar uma lista ligada, porem pela dificuldade na remoção e o pouco tempo restante fica para ser revisto para o projeto final
// * por ser mais eficiente q um array normal, talvez mais leve mas n tenho certeza sobre essa parte
// * obv: tambem é uma possibilidade de utilizar um hashmap mas traz dificuldade na função run
// * private final class Node{
// *         private GameObject go;
// *         private Node next;
// *         private int size;
// *
// *         public Node(GameObject go){
// *             this.go = go;
// *             this.next = null;
// *             this.size = 1;
// *         }
// *
// *         public Node(){
// *             this.go = null;
// *             this.next = null;
// *             this.size = 0;
// *         }
// *
// *         public void setNext(Node next){this.next = next;}
// *
// *         public void setGo(GameObject go){this.go = go;}
// *
// *         public Node getNext(){return next;}
// *
// *         public GameObject getGo(){return go;}
// *
// *         public int size(){return this.size;}
// *
// *         public void resize(int ctrl){
// *             if(ctrl >= 0) this.size++;
// *             else if(ctrl < 0){
// *                 this.size--;
// *                 if(size < 0){
// *                     System.out.println("All objects deleted!");
// *                     System.exit(0);
// *                 }
// *             }
// *         }
// *
// *         public boolean hasNext(){return this.next != null;}
// *     }
// *
// *     corpo da função add
// *     Node next = new Node(go);
// *         next.setNext(objects);
// *         objects = next;
// *         objects.resize(1);
// *
//*      corpo da função destroy (n foi finalizado antes de desistir da ideia do node)
// *      Node cur = objects;
// *         while(cur.hasNext()){
// *             if(cur.getGo().name().compareTo(go.name()) == 0)
// *         }
//**/