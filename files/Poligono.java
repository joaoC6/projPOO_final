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
     * Constrói um polígono a partir de uma string de parâmetros.
     * A string deve conter o número de vértices seguido das coordenadas inteiras de cada vértice.
     * Exemplo: "4 5 5 8 6 8 7 5 7" representa um polígono com 4 vértices nas coordenadas:
     * (5,5), (8,6), (8,7) e (5,7).
     * @param parametros String contendo os dados do polígono.
     */
//    public Poligono(String parametros) {
//        //super(parametros);
//        String[] tokens = parametros.trim().split("\\s+");
//        int n = Integer.parseInt(tokens[0]);
//
//        if (n < 3 || tokens.length != 1 + 2 * n) {
//            System.out.println("Poligono:vi");
//            System.exit(0);
//        }
//
//        List<Ponto> tempVertices = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            int x = Integer.parseInt(tokens[1 + 2 * i]);
//            int y = Integer.parseInt(tokens[1 + 2 * i + 1]);
//            tempVertices.add(new Ponto(x, y));
//        }
//
//        List<Segmento> tempSegments = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            Ponto p1 = tempVertices.get(i);
//            Ponto p2 = tempVertices.get((i + 1) % n);
//            tempSegments.add(new Segmento(p1, p2));
//        }
//
//        if (areVerticesInvalid(tempVertices, tempSegments)) {
//            System.out.println("Poligono:vi");
//            System.exit(0);
//        }
//
//        this.vertices = new ArrayList<>(tempVertices);
//    }

    /**
     * Constrói polígono a partir de lista de vértices
     * @param vertices Lista de pontos representando os vértices
     */
    public Poligono(List<Ponto> vertices) {
        //super(vertices.toString());
//        int n = vertices.size();
//
//        List<Segmento> tempSegments = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            Ponto p1 = vertices.get(i);
//            Ponto p2 = vertices.get((i + 1) % n);
//            tempSegments.add(new Segmento(p1, p2));
//        }
//
//        if (n < 3 || areVerticesInvalid(vertices, tempSegments)) {
//            System.out.println("Poligono:vi");
//            System.exit(0);
//        }

        this.vertices = new ArrayList<>(vertices);
    }

    private static boolean areVerticesInvalid(List<Ponto> vertices, List<Segmento> segments) {
        return hasCollinearVertices(vertices) || hasIntersectingEdges(segments);
    }

    private static boolean hasCollinearVertices(List<Ponto> vertices) {
        int n = vertices.size();
        for (int i = 0; i < n; i++) {
            Ponto a = vertices.get(i);
            Ponto b = vertices.get((i + 1) % n);
            Ponto c = vertices.get((i + 2) % n);
            if (Segmento.saoColineares(a, b, c)) return true;
        }
        return false;
    }

    private static boolean hasIntersectingEdges(List<Segmento> segments) {
        int n = segments.size();
        for (int i = 0; i < n; i++) {
            Segmento seg1 = segments.get(i);
            for (int j = i + 1; j < n; j++) {
                if (j == i || j == (i + 1) % n || ((j + 1) % n) == i) continue;
                Segmento seg2 = segments.get(j);
                if (Segmento.intersects(seg1, seg2)) return true;
            }
        }
        return false;
    }


    /**
     * Aplica translação ao polígono
     * @param dx Deslocamento no eixo x
     * @param dy Deslocamento no eixo y
     */
    public void translacao(double dx, double dy) {
        for (int i = 0; i < vertices.size(); i++) {
            Ponto p = vertices.get(i);
            vertices.set(i, new Ponto(p.getX() + dx, p.getY() + dy));
        }
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