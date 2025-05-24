package files;

/**
 * Representa um círculo no plano cartesiano.
 * @author POO2425p1g4
 * @version 01/04/2025
 * @inv O raio deve ser maior que 0, o centro deve estar no primeiro quadrante
 */
public class Circulo {
    private Ponto centro;
    private double raio;

    /**
     * Constrói um círculo a partir de uma string que contém as coordenadas do centro e o raio.
     * Caso os parâmetros sejam inválidos (ex.: raio <= 0 ou centro fora do primeiro quadrante),
     * o programa imprime "Circulo:vi" e finaliza a execução.
     * @param params String no formato "cx cy r".
     */
    public Circulo(String params) {
        //super(params);
        String[] tokens = params.trim().split("\\s+");
        if (tokens.length != 3) {
            System.out.println("Circulo:vi");
            System.exit(0);
        }
        int cx = Integer.parseInt(tokens[0]);
        int cy = Integer.parseInt(tokens[1]);
        double r = Double.parseDouble(tokens[2]);
        if (validaCirculo(cx, cy, r)) {
            System.out.println("Circulo:vi");
            System.exit(0);
        }
        centro = new Ponto(cx, cy);
        raio = r;
    }

    /**
     * Constrói um círculo a partir de uma string que contém as coordenadas do centro e o raio.
     * Caso os parâmetros sejam inválidos (ex.: raio <= 0 ou centro fora do primeiro quadrante),
     * o programa imprime "Circulo:vi" e finaliza a execução.
     * @param centro center point of the circle.
     * @param raio the radius of the circle.
     */
    public Circulo(Ponto centro, double raio) {
        //super(null);
        double cx = centro.getX();
        double cy = centro.getY();
        this.centro = new Ponto(cx, cy);
        this.raio = raio;
    }

    /**
     * Verifica se as coordenadas do centro e o raio definem um círculo válido.
     * @param cx Coordenada x do centro.
     * @param cy Coordenada y do centro.
     * @param r  Raio do círculo.
     * @return true se for inválido, false caso contrário.
     */
    private static boolean validaCirculo(double cx, double cy, double r) {
        return (cx < 0 || cy < 0 || r <= 0);
    }

    /**
     * Aplica a translação ao círculo, movendo o centro.
     * @param dx deslocamento em x.
     * @param dy deslocamento em y.
     */
    public void translacao(double dx, double dy) {
        Ponto novoCentro = centro.translacaoPonto(dx, dy);
        if (validaCirculo((int) novoCentro.getX(), (int) novoCentro.getY(), this.raio)) {
            System.out.println("Circulo:vi");
            System.exit(0);
        }
        this.centro = novoCentro;
    }

    /**
     * Retorna o raio do círculo.
     * @return O valor do raio.
     */
    public double getRaio() {
        return raio;
    }
    /**
     * Retorna o centro do círculo.
     * @return Um objeto Ponto representando o centro.
     */
    public Ponto getCentro() {
        return centro;
    }

    /**
     * muda o valor do raio do circulo
     */
    public void setRaio(double raio) { this.raio = raio;}

    /**
     * Retorna uma representação textual do círculo, incluindo
     * o centro e o raio em formato "Circulo: (cx,cy) r".
     * @return A string que descreve o círculo.
     */
    @Override
    public String toString() {
        return String.format("%s %.2f", centro, raio);
    }
}
