import java.util.List;

public class Colisões {

    /**
     * Checks collision between a circle and a polygon.
     */
    public static boolean colideCirculoPoligono(Circulo circulo, Poligono poligono) {
        Ponto centro = circulo.getCentro();
        double raio = circulo.getRaio();
        List<Ponto> vertices = poligono.getVert();

        // 1. Check if circle center is inside the polygon
        if (pontoDentroPoligono(centro, vertices)) {
            return true;
        }

        // 2. Check if any polygon edge intersects the circle
        for (int i = 0; i < vertices.size(); i++) {
            Ponto a = vertices.get(i);
            Ponto b = vertices.get((i + 1) % vertices.size());
            double distancia = distanciaPontoSegmento(centro, a, b);
            if (distancia <= raio) {
                return true;
            }
        }

        // 3. Check if any polygon vertex is inside the circle
        for (Ponto vertice : vertices) {
            double dx = vertice.getX() - centro.getX();
            double dy = vertice.getY() - centro.getY();
            if (dx * dx + dy * dy <= raio * raio) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if a point is inside a polygon using ray-casting.
     */
    private static boolean pontoDentroPoligono(Ponto pt, List<Ponto> vertices) {
        boolean result = false;
        int n = vertices.size();
        for (int i = 0, j = n - 1; i < n; j = i++) {
            Ponto vi = vertices.get(i);
            Ponto vj = vertices.get(j);
            double xi = vi.getX(), yi = vi.getY();
            double xj = vj.getX(), yj = vj.getY();

            // Handle vertical edges
            if (Math.abs(xi - xj) < 1e-9) { // Vertical edge
                if (pt.getX() == xi) { // Point lies on the edge
                    if (pt.getY() >= Math.min(yi, yj) && pt.getY() <= Math.max(yi, yj)) {
                        return true;
                    }
                } else if (pt.getX() < xi) { // Point is left of edge
                    if ((yi > pt.getY()) != (yj > pt.getY())) {
                        result = !result;
                    }
                }
                continue;
            }

            // Standard ray-casting
            if ((yi > pt.getY()) != (yj > pt.getY())) {
                double xIntersect = (xj - xi) * (pt.getY() - yi) / (yj - yi + 1e-9) + xi;
                if (pt.getX() < xIntersect) {
                    result = !result;
                }
            }
        }
        return result;
    }

    /**
     * Calculates distance from a point to a line segment.
     */
    private static double distanciaPontoSegmento(Ponto p, Ponto a, Ponto b) {
        double dx = b.getX() - a.getX();
        double dy = b.getY() - a.getY();
        double segLengthSq = dx * dx + dy * dy;

        if (segLengthSq < 1e-9) { // a and b are the same point
            return Math.hypot(p.getX() - a.getX(), p.getY() - a.getY());
        }

        // Project point onto the segment
        double t = ((p.getX() - a.getX()) * dx + (p.getY() - a.getY()) * dy) / segLengthSq;
        t = Math.max(0, Math.min(1, t));

        // Closest point on the segment
        Ponto closest = new Ponto(
                a.getX() + t * dx,
                a.getY() + t * dy
        );
        return Math.hypot(p.getX() - closest.getX(), p.getY() - closest.getY());
    }
}