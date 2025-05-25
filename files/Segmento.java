package files;

/**
 * Representa um segmento de reta definido por dois pontos.
 * Garante que os pontos não são idênticos.
 * @author POO2425p1g4
 * @version 01/04/2025
 * @inv Um segmento deve ter dois pontos distintos.
 */
public class Segmento {
    private final Ponto pA, pB;

    /**
     * Constrói um segmento de reta a partir de dois pontos distintos.
     * @param pA O primeiro ponto do segmento.
     * @param pB O segundo ponto do segmento.
     * @throws "Segmento:vi" se os pontos forem idênticos.
     */
    public Segmento(Ponto pA, Ponto pB) {
        if (pA.pontosIguais(pB)) {
            System.out.println("Segmento:vi");
            System.exit(0);
        }
        this.pA = pA;
        this.pB = pB;
    }

    /**
     * Retorna o primeiro ponto do segmento.
     * @return O ponto p1.
     */
    public Ponto getpA() {
        return pA;
    }

    /**
     * Retorna o segundo ponto do segmento.
     * @return O ponto p2.
     */
    public Ponto getpB() {
        return pB;
    }


    /**
     * Verifica se dois segmentos se intersectam.
     * @param r1 Primeiro segmento.
     * @param r2 Segundo segmento.
     * @return true se os segmentos se intersectam, false caso contrário.
     */
    public static boolean intersects(Segmento r1, Segmento r2) {
        return segmentoIntersecao.intersects(r1, r2);
    }
}
