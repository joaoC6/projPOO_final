//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class Testing {
//
//    // Classe interna para representar um Ponto
//    static class Ponto {
//        private final double x;
//        private final double y;
//
//        public Ponto(double x, double y) {
//            this.x = x;
//            this.y = y;
//        }
//
//        public double getX() { return x; }
//        public double getY() { return y; }
//    }
//
//    // Classe interna para representar um Círculo
//    static class Circulo {
//        private final Ponto centro;
//        private final double raio;
//
//        public Circulo(Ponto centro, double raio) {
//            this.centro = centro;
//            this.raio = raio;
//        }
//
//        public Ponto getCentro() { return centro; }
//        public double getRaio() { return raio; }
//    }
//
//    // Classe interna para representar um Polígono
//    static class Poligono {
//        private final List<Ponto> vertices;
//
//        public Poligono(List<Ponto> vertices) {
//            this.vertices = vertices;
//        }
//
//        public List<Ponto> getVertices() { return vertices; }
//    }
//
//    // ========== TESTES ==========
//
//    @Test
//    public void testCentroDentroPoligono() {
//        // Círculo no centro de um quadrado
//        Circulo c = new Circulo(new Ponto(1, 1), 0.5);
//        List<Ponto> vertices = List.of(
//                new Ponto(0, 0), new Ponto(2, 0),
//                new Ponto(2, 2), new Ponto(0, 2)
//        );
//        Poligono p = new Poligono(vertices);
//
//        assertTrue(verificarColisao(c, p));
//    }
//
//    @Test
//    public void testIntersecaoAresta() {
//        // Círculo tocando uma aresta do polígono
//        Circulo c = new Circulo(new Ponto(3, 1.5), 0.5);
//        List<Ponto> vertices = List.of(
//                new Ponto(0, 0), new Ponto(2, 0),
//                new Ponto(2, 3), new Ponto(0, 3)
//        );
//        Poligono p = new Poligono(vertices);
//
//        assertTrue(verificarColisao(c, p));
//    }
//
//    @Test
//    public void testVerticeDentroCirculo() {
//        // Vértice do polígono dentro do círculo
//        Circulo c = new Circulo(new Ponto(2, 2), 1.5);
//        List<Ponto> vertices = List.of(
//                new Ponto(3, 3), new Ponto(4, 3),
//                new Ponto(4, 4), new Ponto(3, 4)
//        );
//        Poligono p = new Poligono(vertices);
//
//        assertTrue(verificarColisao(c, p));
//    }
//
//    @Test
//    public void testSemColisao() {
//        // Círculo longe do polígono
//        Circulo c = new Circulo(new Ponto(10, 10), 1);
//        List<Ponto> vertices = List.of(
//                new Ponto(0, 0), new Ponto(2, 0),
//                new Ponto(2, 2), new Ponto(0, 2)
//        );
//        Poligono p = new Poligono(vertices);
//
//        assertFalse(verificarColisao(c, p));
//    }
//
//    // ========== LÓGICA DE DETECÇÃO ==========
//
//    private boolean verificarColisao(Circulo c, Poligono p) {
//        // 1. Verificar se o centro do círculo está dentro do polígono
//        if (pontoDentroPoligono(c.getCentro(), p.getVertices())) {
//            return true;
//        }
//
//        // 2. Verificar se o círculo intersecta alguma aresta
//        for (int i = 0; i < p.getVertices().size(); i++) {
//            Ponto a = p.getVertices().get(i);
//            Ponto b = p.getVertices().get((i + 1) % p.getVertices().size());
//            if (distanciaAresta(c.getCentro(), a, b) <= c.getRaio()) {
//                return true;
//            }
//        }
//
//        // 3. Verificar se algum vértice está dentro do círculo
//        for (Ponto v : p.getVertices()) {
//            double dx = v.getX() - c.getCentro().getX();
//            double dy = v.getY() - c.getCentro().getY();
//            if (dx * dx + dy * dy <= c.getRaio() * c.getRaio()) {
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//    private boolean pontoDentroPoligono(Ponto pt, List<Ponto> vertices) {
//        boolean resultado = false;
//        for (int i = 0, j = vertices.size() - 1; i < vertices.size(); j = i++) {
//            Ponto vi = vertices.get(i);
//            Ponto vj = vertices.get(j);
//
//            if ((vi.getY() > pt.getY()) != (vj.getY() > pt.getY())) {
//                double intersectX = (vj.getX() - vi.getX()) * (pt.getY() - vi.getY()) / (vj.getY() - vi.getY()) + vi.getX();
//                if (pt.getX() < intersectX) {
//                    resultado = !resultado;
//                }
//            }
//        }
//        return resultado;
//    }
//
//    private double distanciaAresta(Ponto p, Ponto a, Ponto b) {
//        double dx = b.getX() - a.getX();
//        double dy = b.getY() - a.getY();
//        double comprimentoAoQuadrado = dx * dx + dy * dy;
//
//        if (comprimentoAoQuadrado == 0) return Math.hypot(p.getX() - a.getX(), p.getY() - a.getY());
//
//        double t = Math.max(0, Math.min(1, ((p.getX() - a.getX()) * dx + (p.getY() - a.getY()) * dy) / comprimentoAoQuadrado));
//        Ponto projecao = new Ponto(a.getX() + t * dx, a.getY() + t * dy);
//
//        return Math.hypot(p.getX() - projecao.getX(), p.getY() - projecao.getY());
//    }
//}