import static java.lang.Math.*;

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
     * Cria e retorna um novo ponto, resultado da translação deste ponto em dx no eixo x e dy no eixo y.
     * @param dx Deslocamento em x.
     * @param dy Deslocamento em y.
     * @return Um novo ponto, resultado da translação.
     */
    public Ponto translacaoPonto(double dx, double dy){
        double novoX = x + dx;
        double novoY = y + dy;
        return new Ponto(novoX, novoY);

    }

    /**
     * Calcula a distância entre dois pontos no espaço polar.
     * @param that O ponto de referência para o cálculo da distância.
     * @return A distância entre os dois pontos.
     */
    double dist(Ponto that) {
        return sqrt((this.x * this.x) + (that.getX() * that.getX()) - (2 * this.x * that.getX() * cos(toRadians(this.y - that.getY()) ) ) );
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

    // Vector operations
    public Ponto sub(Ponto other) {
        return new Ponto(this.x - other.getX(), this.y - other.getY());
    }

    public Ponto neg() {
        return new Ponto(-this.x, -this.y);
    }

    public double dotProduct(Ponto other) {
        return this.x * other.getX() + this.y * other.getY();
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Ponto normalize() {
        double mag = magnitude();
        return new Ponto(x / mag, y / mag);
    }

    // Perpendicular directions (normal vectors)
    public Ponto[] perpendicularDirections() {
        return new Ponto[] {
                new Ponto(-this.y, this.x),
                new Ponto(this.y, -this.x)
        };
    }

    // Add setters
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    // Optional: Set both coordinates at once
    public void set(Ponto p) {
        this.x = p.getX();
        this.y = p.getY();
    }
}

