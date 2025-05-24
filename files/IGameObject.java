package files;

/**
 * Interface que define o contrato básico para objetos do jogo
 * @version 01/04/2025
 */
public interface IGameObject {
    /**
     * Obtém o nome do objeto do jogo
     * @return Nome do objeto
     */
    String name();

    /**
     * Obtém a transformação associada ao objeto
     * @return Objeto ITransform com as transformações aplicadas
     */
    ITransform transform();

    /**
     * Obtém o colisor associado ao objeto
     * @return Objeto ICollider com o centróide na posição atual do objeto
     */
    ICollider collider();
}