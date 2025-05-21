/**
 * Verifica se existe interseção de dois segmentos
 * @author POO2425p1g4
 * @version 18/03/2025
 * @see perguntei a chatgpt como podia verificar se dois segmentos se intersetam
 */
class segmentoIntersecao {
    /**
     * Verifica se dois segmentos se intersectam
     * @param r1 Primeiro segmento
     * @param r2 Segundo segmento
     * @return true se houver interseção, false caso contrário
     */
    public static boolean intersects(Segmento r1, Segmento r2) {
        Ponto p1 = r1.getpA();
        Ponto p2 = r1.getpB();
        Ponto p3 = r2.getpA();
        Ponto p4 = r2.getpB();

        int o1 = orientacao(p1, p2, p3);
        int o2 = orientacao(p1, p2, p4);
        int o3 = orientacao(p3, p4, p1);
        int o4 = orientacao(p3, p4, p2);

        if (o1 != o2 && o3 != o4) return true;

        if (o1 == 0 && onSegment(p1, p3, p2)) return true;
        if (o2 == 0 && onSegment(p1, p4, p2)) return true;
        if (o3 == 0 && onSegment(p3, p1, p4)) return true;
        return o4 == 0 && onSegment(p3, p2, p4);
    }

    /**
     * Calcula orientação de três pontos (colinear, horário ou anti-horário)
     * @param p Primeiro ponto
     * @param q Segundo ponto
     * @param r Terceiro ponto
     * @return 0 se colineares, 1 se horário, 2 se anti-horário
     */
    private static int orientacao(Ponto p, Ponto q, Ponto r) {
        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) -
                (q.getX() - p.getX()) * (r.getY() - q.getY());
        if (Math.abs(val) < 1e-9) return 0;
        return (val > 0) ? 1 : 2;
    }

    /**
     * Verifica se ponto q está no segmento pr
     * @param p Ponto inicial do segmento
     * @param q Ponto a verificar
     * @param r Ponto final do segmento
     * @return true se q estiver no segmento pr, false caso contrário
     */
    private static boolean onSegment(Ponto p, Ponto q, Ponto r) {
        return q.getX() <= Math.max(p.getX(), r.getX()) &&
                q.getX() >= Math.min(p.getX(), r.getX()) &&
                q.getY() <= Math.max(p.getY(), r.getY()) &&
                q.getY() >= Math.min(p.getY(), r.getY());
    }
}