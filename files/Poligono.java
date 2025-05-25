package files;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um polígono no plano cartesiano, definido por uma sequência de vértices.
 * @author POO2425p1g4
 * @version 01/04/2025
 * @inv O polígono deve ter pelo menos 3 vértices, não pode possuir três vértices consecutivos colineares e nenhuma de suas arestas pode se intersectar.
 */
public class Poligono {
    protected ArrayList<Ponto> vertices;

    /**
     * Constrói polígono a partir de lista de vértices
     * @param vertices Lista de pontos representando os vértices
     */
    public Poligono(List<Ponto> vertices) {

        this.vertices = new ArrayList<>(vertices);
    }


    /**
     * Retorna uma representação textual do polígono.
     * @return Uma string representando o polígono e as suas coordenadas.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Ponto p : vertices) {
            sb.append(String.format("(%.2f,%.2f) ", p.getX(), p.getY()));
        }
        return sb.toString().trim();
    }

    public List<Ponto> getVert() {return this.vertices;}
}