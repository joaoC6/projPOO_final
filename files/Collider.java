package files;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação concreta de colisor para figuras geométricas
 * @author POO2425p1g4
 * @version 01/04/2025
 * @inv O colisor deve estar sincronizado com a transformação da figura
 */
public abstract class Collider extends Transform implements ICollider{
    protected Transform t;

    public Collider(){}

    public abstract Ponto centroid();

    public abstract void update(Transform t);

    public abstract boolean isColliding(Collider that);
    public abstract boolean isColliding(ColliderPoligono that);
    public abstract boolean isColliding(ColliderCirculo that);


    public abstract String toString();
}







//versão antiga do collider
//public class Collider implements ICollider {
//    private Transform transform;
//    private FiguraGeometrica shape;
//
//    /**
//     * Constrói um colisor para uma figura geométrica
//     * @param vertices Lista de vértices da figura
//     * @param radius Raio (para círculos)
//     * @param transform Transformação associada à figura
//     */
//    public Collider(List<Ponto> vertices, double radius, Transform transform) {
//        this.transform = transform;
//
//        if (vertices.size() == 1) {
//            this.shape = new Circulo(vertices.get(0), radius);
//        } else if (vertices.size() >= 3) {
//            this.shape = new Poligono(vertices);
//        }
//    }
//
//
//    @Override
//    public Ponto centroid() {
//        return transform.position();
//    }
//
//    @Override
//    public String toString() {
//        return shape != null ? shape.toString() : "";
//    }
//}