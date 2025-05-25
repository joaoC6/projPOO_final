package files;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class GameEngine {
    private HashMap<Integer, GameObject> objects;
    private final int n;
    private int hashcodePlayer;
    private GUI g;
    private Ponto spawnPoint = new Ponto(100, 100);

    public GameEngine(int n) {
        assert (n >= 0);
        this.n = n;
        this.objects = new HashMap<>(n);
        this.g = new GUI(this); // Corrigido aqui
        this.hashcodePlayer = 0;
    }

    public GUI getGUI() {
        return this.g;
    }

    public void add(GameObject go) {
        objects.put(go.hashCode(), go);
    }

    public void destroy(GameObject go) {
        objects.remove(go.hashCode());
    }

    public ArrayList<GameObject> enabled() {
        return new ArrayList<>(objects.values());
    }

    private void singlePlayer() {
        for (GameObject go : objects.values()) {
            if (go != null) {
                ITransform t = go.transform();
                t.move(spawnPoint, 0);
                go.setTranform((Transform) t);
            }
        }

        Set<Integer> index = objects.keySet();
        for (int i : index) {
            GameObject go1 = objects.get(i);
            GameObject[] colisions = new GameObject[objects.size()];
            colisions[0] = go1;
            int length = 1;
            for (int j : index) {
                GameObject go2 = objects.get(j);
                if (i == j || go1 == null || go2 == null) continue;
                if (go1.checkCollider(go2.collider(0)) && go2.checkCollider(go1.collider(1))) {
                    colisions[length++] = go2;
                }
            }
            checkCollision(colisions, length);
        }

        this.g.render(enabled()); // Corrigido aqui
    }

    private void checkCollision(GameObject[] colisions, int length) {
        if (length > 1) {
            System.out.print("Collision: ");
            for (int i = 0; i < length; i++) {
                System.out.print(colisions[i].name() + " ");
            }
            System.out.println();
        }
    }

    public void run(int mode) {
        if (mode == 0) {
            singlePlayer();
        } else if (mode == 1) {
            System.out.println("Modo multiplayer ainda não implementado.");
        }
    }
}
