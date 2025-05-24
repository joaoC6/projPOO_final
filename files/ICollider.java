package files;

/**
 * Interface que define o contrato para objetos que podem detectar colisões
 * @version 01/04/2025
 */
public interface ICollider {
    /**
     * Obtém o centróide (centro geométrico) do objeto
     * @return Ponto representando o centróide do objeto
     */
    Ponto centroid();
}