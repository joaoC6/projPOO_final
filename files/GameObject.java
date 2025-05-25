package files;

import java.util.ArrayList;

/**
 * Representa um objeto do jogo com transformações geométricas e colisões
 * @author POO2425p1g4
 * @version 01/04/2025
 * @inv O objeto deve ser válido conforme sua figura geométrica associada
 */
public class GameObject implements IGameObject{
    protected ArrayList<Ponto> fig = new ArrayList<>();
    protected String name;
    protected double vx, vy, vr, vs;
    protected int vl;
    private int hp;
    private Transform t;
    private Collider c;
    private Shape s;

    protected GameObject(){}

    /**
     * Constrói GameObject a partir de string com parâmetros
     * @param parametros String formatada com nome, transformação e vértices
     */
    public GameObject(String parametros) {
        double tx, ty, ori, sf, radius;
        int layer;
        String[] aos = parametros.split("\n");
        String[] l2 = aos[1].split(" ");
        this.name = aos[0];

        tx = Double.parseDouble(l2[0]);
        ty = Double.parseDouble(l2[1]);
        layer = Integer.parseInt(l2[2]);
        ori = Double.parseDouble(l2[3]);
        sf = Double.parseDouble(l2[4]);

        String[] l3 = aos[2].split(" ");
        if(l3.length == 3) {
            fig.add(new Ponto(Double.parseDouble(l3[0]), Double.parseDouble(l3[1])));
            radius = Double.parseDouble(l3[2]);
            this.c = new ColliderCirculo(fig, radius);
            this.t = new Transform(tx, ty, layer, ori, sf, fig, this.c, radius);
        }else{
            for(int i = 0, j= 1; j < l3.length; i += 2, j += 2)
                fig.add(new Ponto(Double.parseDouble(l3[i]), Double.parseDouble(l3[j])));
            this.c = new ColliderPoligono(fig);
            this.t = new Transform(tx, ty, layer, ori, sf, fig, this.c);
        }
//        this.aos = new String[aos.length - 3];
//        for(int i = 3, j = 0; i < aos.length; i++, j++){
//            this.aos[j] = aos[i];
//        }
//        String[] l4 = aos[3].split(" ");
//        this.vx = Double.parseDouble(l4[0]);
//        this.vy = Double.parseDouble(l4[1]);
//        this.vl = Integer.parseInt(l4[2]);
//        this.vr = Double.parseDouble(l4[3]);
//        this.vs = Double.parseDouble(l4[4]);
        liveManager();
        this.s = new Shape(this.name);
    }

    /**
     * Obtém nome do objeto
     * @return Nome do objeto
     */
    @Override
    public String name(){ return this.name;}

    /**
     * Aplica transformações e retorna objeto ITransform
     * @return Objeto ITransform com transformações aplicadas
     */
    @Override
    public ITransform transform() {
//        this.t.move(new Ponto(vx, vy), vl);
//        this.c.update(t);
//        this.t.rotate(vr);
//        this.c.update(t);
//        this.t.scale(vs);
//        this.c.update(t);
//        this.fig = this.t.vert();
        return this.t;
    }

    @Override
    public ICollider collider() {return this.c;}

    public Shape shape(){return this.s;}

    public Collider collider(int i) {return this.c;}

    public boolean checkCollider(ICollider that){ return this.c.isColliding((Collider) that);}

    /**
     * Retorna representação textual do GameObject
     * @return String com nome e transformação
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append("\n");
        sb.append(this.t);
        return sb.toString();
    }

    /**
     * @return a shallow copy of the object, only difference is the memory location
     */
    public GameObject shalowCopy(){
        String s = this.toString();
        GameObject go = new GameObject(s);
        return go;
    }

    public void setTranform(Transform t) {
        this.t = t;
        this.c.update(t);
    }

    private void liveManager(){
        switch(this.name){
            case "nave":{
                this.hp = 300;
                break;
            }

            case "nave_inimiga":{
                this.hp = 150;
                break;
            }

            case "nave_boss":{
                this.hp = 600;
                break;
            }

            case "asteroid":{
                this.hp = 50;
                break;
            }

            case "shield":{
                this.hp = 100;
                break;
            }

            case "parede":{
                this.hp = -1;
                break;
            }

            default:{
                break;
            }
        }
    }
}
