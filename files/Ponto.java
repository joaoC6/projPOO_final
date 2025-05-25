package files;

/**
 * Representa um ponto no plano cartesiano.
 * @author POO2425p1g4
 * @version 18/03/2025
 * @inv As coordenadas devem estar no primeiro quadrante e a conversão polar deve respeitar os limites definidos.
 */
public class Ponto {
    private double x, y;

    /**
     * Construtor padrão que inicializa o ponto na origem.
     */
    public Ponto() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Constrói um ponto a partir de coordenadas cartesianas.
     * @param x Coordenada x do ponto.
     * @param y Coordenada y do ponto.
     */
    public Ponto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Retorna a coordenada x do ponto.
     * @return A coordenada x.
     */
    public double getX() {
        return x;
    }

    /**
     * Retorna a coordenada y do ponto.
     * @return A coordenada y.
     */
    public double getY() {
        return y;
    }

    /**
     * Verifica se dois pontos são iguais
     * @param that O ponto de referência para a verificacao
     * @return um booleano que diz se os dois pontos se intersetam
     */
    boolean pontosIguais(Ponto that){
        return (Math.abs(this.getX() - that.getX()) < 1e-9 && Math.abs(this.getY() - that.getY()) < 1e-9);
    }

    /**
     * Retorna a representação textual do ponto no formato "(x,y)".
     * @return Uma string com as coordenadas x e y do ponto.
     */
    @Override
    public String toString() {
        return String.format("(%.2f,%.2f)", getX(), getY());
    }


}

