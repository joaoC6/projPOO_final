package files;

/**
 * Interface que define operações de transformação geométrica
 * @version 01/04/2025
 */
public interface ITransform {
    /**
     * Move o objeto no espaço 2D e entre camadas
     * @param dPos Vetor de deslocamento (dx, dy)
     * @param dlayer Diferença de camada (pode ser positiva ou negativa)
     */
    void move(Ponto dPos, int dlayer);

    /**
     * Rotaciona o objeto a partir de sua orientação atual
     * @param dTheta Ângulo de rotação em graus (0-360)
     */
    void rotate(double dTheta);

    /**
     * Aplica escala ao objeto
     * @param dScale Fator de escala (1.0 = mantém tamanho original)
     */
    void scale(double dScale);

    /**
     * Obtém a posição atual do objeto
     * @return Ponto com coordenadas (x,y)
     */
    Ponto position();

    /**
     * Obtém a camada atual do objeto
     * @return Número da camada
     */
    int layer();

    /**
     * Obtém o ângulo atual de rotação
     * @return Ângulo em graus
     */
    double angle();

    /**
     * Obtém o fator de escala atual
     * @return Valor do fator de escala
     */
    double scale();
}