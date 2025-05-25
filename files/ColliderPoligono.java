package files;

import java.util.ArrayList;
import java.util.List;

public class ColliderPoligono extends Collider{
    private ArrayList<Ponto> vert = new ArrayList<>();
    private Poligono p;

    public ColliderPoligono(){}
    public ColliderPoligono(ArrayList<Ponto> vert){ this.vert = vert;}

    @Override
    public Ponto centroid(){
        Ponto centroid;
        double tx = 0, ty = 0, area = getArea();
        for (int i = 0; i < vert.size() - 1; i++) {
            Ponto cur = vert.get(i), aft = vert.get(i + 1);
            tx += (cur.getX() + aft.getX()) * (cur.getX() * aft.getY() - aft.getX() * cur.getY());
            ty += (cur.getY() + aft.getY()) * (cur.getX() * aft.getY() - aft.getX() * cur.getY());
        }
        tx = tx / (6 * area);
        ty = ty / (6 * area);
        tx += ((vert.getLast().getX() + vert.getFirst().getX()) * (vert.getLast().getX() * vert.getFirst().getY() - vert.getFirst().getX() * vert.getLast().getY())) / (6 * area);
        ty += ((vert.getLast().getY() + vert.getFirst().getY()) * (vert.getLast().getX() * vert.getFirst().getY() - vert.getFirst().getX() * vert.getLast().getY())) / (6 * area);
        centroid = new Ponto(tx, ty);
        return centroid;
    }

    private double getArea(){
        double area = 0;
        for(int i = 0; i < vert.size() - 1; i++){
            Ponto cur = vert.get(i) , aft = vert.get(i + 1);
            area += (cur.getX() * aft.getY() - aft.getX() * cur.getY());
        }
        area = area / 2;
        area += (vert.getLast().getX() * vert.getFirst().getY() - vert.getFirst().getX() * vert.getLast().getY()) / 2;
        return area;
    }

    @Override
    public void update(Transform t) {
        this.t = t;
        this.vert = t.vert();
        this.p = new Poligono(vert);
    }

    @Override
    public boolean isColliding(Collider that) {
        return that.isColliding(this);
    }

    public boolean isColliding(ColliderCirculo that) {

        return Colisões.colideCirculoPoligono(that.getC(), this.p);
    }

    public boolean isColliding(ColliderPoligono that) {
        // Early exit if bounding boxes do not overlap
        double[] box1 = getBoundingBox(this.vert);
        double[] box2 = getBoundingBox(that.vert);

        boolean overlapX = (box1[2] >= box2[0]) && (box1[0] <= box2[2]);
        boolean overlapY = (box1[3] >= box2[1]) && (box1[1] <= box2[3]);

        if (!(overlapX && overlapY)) {
            return false; // No collision possible
        }

        // Proceed with detailed checks for overlapping bounding boxes
        List<Ponto> v1 = this.vert;
        List<Ponto> v2 = that.vert;
        int n1 = v1.size(), n2 = v2.size();

        // Check for intersecting edges
        for (int i = 0; i < n1; i++) {
            Ponto a1 = v1.get(i);
            Ponto b1 = v1.get((i + 1) % n1);
            Segmento seg1 = new Segmento(a1, b1);
            for (int j = 0; j < n2; j++) {
                Ponto a2 = v2.get(j);
                Ponto b2 = v2.get((j + 1) % n2);
                Segmento seg2 = new Segmento(a2, b2);
                if (Segmento.intersects(seg1, seg2)) {
                    return true;
                }
            }
        }

        // Check if any vertex is inside the other polygon
        if (pointInPolygon(v1.get(0), that.p)) return true;
        if (pointInPolygon(v2.get(0), this.p)) return true;

        return false;
    }

    private double[] getBoundingBox(List<Ponto> vertices) {
        double minX = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;

        for (Ponto p : vertices) {
            double x = p.getX();
            double y = p.getY();
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }

        return new double[]{minX, minY, maxX, maxY};
    }

    public String toString(){return new Poligono(vert).toString();}


    /**Funções auxiliares**/

    public Poligono getP(){return this.p;}

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

}
