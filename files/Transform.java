package files;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Representa transformações geométricas (translação, rotação, escala) aplicáveis a figuras
 * @author POO2425p1g4
 * @version 01/04/2025
 * @inv As transformações devem manter as figuras no primeiro quadrante
 */
public class Transform extends GameObject implements ITransform {
    private double x, y, angle, scale;
    private int layer;
    private ArrayList<Ponto> vert;
    private Collider c;

    protected Transform(){}

    /**
     * Constrói objeto Transform com colisor poligono com parâmetros iniciais
     * @param x Coordenada x inicial
     * @param y Coordenada y inicial
     * @param layer Camada inicial
     * @param angle Ângulo inicial em graus
     * @param scale Fator de escala inicial
     * @param vert Lista de vértices da figura
     * @param c o colisor que foi inicializado no gameobject
     */
    public Transform(double x, double y, int layer, double angle, double scale, ArrayList<Ponto> vert, Collider c) {
        new Ponto(x, y);
        this.x = x;
        this.y = y;
        this.layer = layer;
        this.angle = angle;
        this.scale = scale;
        this.vert = vert;
        this.c = new ColliderPoligono(vert);
    }

    /**
     * Constrói objeto Transform com colisor circulo com parâmetros iniciais
     * @param x Coordenada x inicial
     * @param y Coordenada y inicial
     * @param layer Camada inicial
     * @param angle Ângulo inicial em graus
     * @param scale Fator de escala inicial
     * @param vert Lista de vértices da figura
     * @param c o colisor que foi inicializado no gameobject
     * @param radius o raio do crculo do colisor
     */
    public Transform(double x, double y, int layer, double angle, double scale, ArrayList<Ponto> vert, Collider c, double radius) {
        new Ponto(x, y);
        this.x = x;
        this.y = y;
        this.layer = layer;
        this.angle = angle;
        this.scale = scale;
        this.vert = vert;
        this.c = new ColliderCirculo(vert, radius);
    }

    @Override
    public void move(Ponto dPos, int dlayer) {
        ArrayList<Ponto> result = new ArrayList<>();

        for (Ponto p : this.vert) {
            Ponto res = new Ponto(p.getX() + dPos.getX(), p.getY() + dPos.getY());
            result.add(res);
        }
        vert = result;
        this.x += dPos.getX();
        this.y += dPos.getY();
        this.layer += dlayer;
        c.update(this);
        checkCentroid();
    }

//    public void move(Ponto dPos, int dlayer) {
//        ArrayList<Ponto> result = new ArrayList<>();
//        if(vert.size() > 2) {
//            for (Ponto p : this.vert) {
//                Ponto res = new Ponto(p.getX() + dPos.getX(), p.getY() + dPos.getY());
//                result.add(res);
//            }
//        }else{
//            if (!vert.isEmpty()) {
//                Ponto newCenter = new Ponto(vert.get(0).getX() + dPos.getX(), vert.get(0).getY() + dPos.getY());
//                result.add(newCenter);
//                result.add(vert.getLast());
//            }
//
//        }
//        vert = result;
//        if(vert.size() == 2) vert.add(vert.getLast());
//        this.x += dPos.getX();
//        this.y += dPos.getY();
//        this.layer += dlayer;
//        c.update(this);
//        checkCentroid();
//    }


    public void rotate(double dTheta) {
        this.angle += dTheta;
        ArrayList<Ponto> result = new ArrayList<>();
        if(vert.size() > 1) {
            for (Ponto p : vert) {
                double x = p.getX() - this.x;
                double y = p.getY() - this.y;
                double rad = dTheta * Math.PI / 180;
                BigDecimal newX = BigDecimal.valueOf(x * Math.cos(rad) - y * Math.sin(rad));
                BigDecimal newY = BigDecimal.valueOf(x * Math.sin(rad) + y * Math.cos(rad));
                x = newX.doubleValue();
                y = newY.doubleValue();
                x += this.x;
                y += this.y;

                result.add(new Ponto(x, y));
            }
        }else result = vert;
        vert = result;
        c.update(this);
    }

    @Override
    public void scale(double dScale) {
        this.scale += dScale;
        ArrayList<Ponto> result = new ArrayList<>();
        if (vert.size() > 1){
            for (Ponto p : vert) {
                double x = p.getX() - this.x;
                double y = p.getY() - this.y;

                x *= this.scale;
                y *= this.scale;

                x += this.x;
                y += this.y;
                result.add(new Ponto(x, y));
            }
        } else if(vert.size() == 1){
            ColliderCirculo cc = (ColliderCirculo) this.c;
            double radius = cc.getRadius();
            radius *= dScale;
            cc.setRadius(radius);
            this.c = cc;
        }
        vert = result;
        c.update(this);
    }

    /**
     * Obtém posição atual da figura
     * @return Ponto com coordenadas (x,y)
     */
    @Override
    public Ponto position() { return new Ponto(x, y); }

    /**
     * Obtém camada atual
     * @return Número da camada
     */
    @Override
    public int layer() { return this.layer; }

    /**
     * Obtém ângulo atual
     * @return Ângulo em graus
     */
    @Override
    public double angle() { return angle; }

    /**
     * Obtém fator de escala atual
     * @return Valor do fator de escala
     */
    @Override
    public double scale() { return scale; }

    protected ArrayList<Ponto> vert(){ return this.vert; }


    /**
     * Retorna representação textual da transformação
     * @return String com parâmetros e vértices transformados
     */
    @Override
    public String toString() {
        c.update(this);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%f %f %d %f %f", x, y, layer, angle, scale));
        sb.append("\n");
        sb.append(c);
        return sb.toString();
    }

    private void checkCentroid(){
        Ponto centroid = c.centroid();
        double vDCx = this.x - centroid.getX(), vDCy = this.y - centroid.getY();
        if(vDCy == 0 && vDCx == 0) return;
        ArrayList<Ponto> result = new ArrayList<>();
        if(this.vert.size() > 2){
            for (Ponto p : vert){
                Ponto res = new Ponto(p.getX() + vDCx, p.getY() + vDCy);
                result.add(res);
            }
        }else{
            Ponto res = new Ponto(this.vert.getFirst().getX() + vDCx, this.vert.getFirst().getY() + vDCy);
            result.add(res);
            result.add(this.vert.getLast());
        }
        vert = result;
        c.update(this);
    }

}