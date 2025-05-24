package files;

import java.util.ArrayList;
import java.util.List;

public class ColliderCirculo extends Collider{
    private ArrayList<Ponto> vert = new ArrayList<>();
    private double radius;
    private Circulo c;

    public ColliderCirculo(){}
    public ColliderCirculo(ArrayList<Ponto> vert, double radius){
        this.vert = vert;
        this.radius = radius;
        this.c = new Circulo(vert.getFirst(), this.radius);
    }

    @Override
    public void update(Transform t) {
        this.t = t;
        this.vert = t.vert();
        this.c = new Circulo(vert.getFirst(), this.radius);
    }

    @Override
    public boolean isColliding(Collider that) {
        return that.isColliding((ColliderCirculo) this);
    }

    public boolean isColliding(ColliderCirculo that) {
        double dx = c.getCentro().getX() - that.getC().getCentro().getX();
        double dy = c.getCentro().getY() - that.getC().getCentro().getY();
        double distanceSq = dx * dx + dy * dy;
        double radiusSum = c.getRaio() + that.getC().getRaio();
        return distanceSq <= radiusSum * radiusSum;
    }

    public boolean isColliding(ColliderPoligono that) {
        Ponto centroCirculo = this.getC().getCentro();
        double raioCirculo = this.getC().getRaio();
        Poligono poligono = that.getP();

        // 1. Check if circle center is inside the polygon
        if (pointInPolygon(centroCirculo, poligono)) {
            return true;
        }

        List<Ponto> verticesPoligono = poligono.vertices;
        int n = verticesPoligono.size();

        // 2. Check if any edge of the polygon intersects the circle
        for (int i = 0; i < n; i++) {
            Ponto a = verticesPoligono.get(i);
            Ponto b = verticesPoligono.get((i + 1) % n);
            double distancia = distancePointSegment(centroCirculo, a, b);
            if (distancia <= raioCirculo + 1e-9) {
                return true;
            }
        }

        // 3. Check if any vertex of the polygon is inside the circle
        for (Ponto vertice : verticesPoligono) {
            double dx = vertice.getX() - centroCirculo.getX();
            double dy = vertice.getY() - centroCirculo.getY();
            double distanciaSq = dx * dx + dy * dy;
            if (distanciaSq <= raioCirculo + 1e-9) {
                return true;
            }
        }
        return false;
//        return Colisões.colideCirculoPoligono(this.c, that.getP());
    }

    @Override
    public Ponto centroid() {return new Ponto(vert.getFirst().getX(), vert.getFirst().getY());}

    public String toString(){return new Circulo(vert.getFirst(), vert.get(1).getX()).toString();}

    /**Funções auxiliares**/

    public Circulo getC(){return this.c;}

    /**
     * Verifica se ponto está dentro de um polígono usando ray-casting
     * @param pt Ponto a verificar
     * @param poly Polígono a testar
     * @return true se o ponto estiver dentro do polígono, false caso contrário
     */
    private static boolean pointInPolygon(Ponto pt, Poligono poly) {
        boolean result = false;
        List<Ponto> vertices = poly.vertices;
        int n = vertices.size();
        for (int i = 0, j = n - 1; i < n; j = i++) {
            Ponto vi = vertices.get(i);
            Ponto vj = vertices.get(j);

            // Check if point is exactly on a vertex
            if (vi.getX() == pt.getX() && vi.getY() == pt.getY()) return true;

            // Check if point is on a horizontal/vertical edge
            if ((vi.getY() == pt.getY() && vj.getY() == pt.getY()) ||
                    (vi.getX() == pt.getX() && vj.getX() == pt.getX())) {
                if (pt.getX() >= Math.min(vi.getX(), vj.getX()) &&
                        pt.getX() <= Math.max(vi.getX(), vj.getX()) &&
                        pt.getY() >= Math.min(vi.getY(), vj.getY()) &&
                        pt.getY() <= Math.max(vi.getY(), vj.getY())) {
                    return true;
                }
            }

            // Ray-casting for non-degenerate edges
            if ((vi.getY() > pt.getY()) != (vj.getY() > pt.getY())) {
                double slope = (vj.getX() - vi.getX()) / (vj.getY() - vi.getY() + 1e-9);
                double intersectX = vi.getX() + (pt.getY() - vi.getY()) * slope;
                if (pt.getX() < intersectX) {
                    result = !result;
                }
            }
        }
        return result;
    }

    /**
     * Calcula distância entre ponto e segmento de reta
     * @param p Ponto a calcular distância
     * @param a Primeiro ponto do segmento
     * @param b Segundo ponto do segmento
     * @return Distância mínima entre o ponto e o segmento
     */
    public static double distancePointSegment(Ponto p, Ponto a, Ponto b) {
        double dx = b.getX() - a.getX();
        double dy = b.getY() - a.getY();
        double segmentLengthSq = dx * dx + dy * dy;

        if (segmentLengthSq == 0) // a and b are the same point
            return Math.sqrt(
                    Math.pow(p.getX() - a.getX(), 2) +
                            Math.pow(p.getY() - a.getY(), 2)
            );

        double t = ((p.getX() - a.getX()) * dx + (p.getY() - a.getY()) * dy) / segmentLengthSq;
        t = Math.max(0, Math.min(1, t)); // Clamp t to [0,1]

        Ponto projection = new Ponto(
                a.getX() + t * dx,
                a.getY() + t * dy
        );

        return Math.sqrt(
                Math.pow(p.getX() - projection.getX(), 2) +
                        Math.pow(p.getY() - projection.getY(), 2)
        );
    }

    public double getRadius(){return this.radius;}
    public void setRadius(double radius){this.radius = radius;}
}









