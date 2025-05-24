package files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static String capital(String s) {
        if (s == null || s.isEmpty())
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
//        Locale.setDefault(Locale.US);
//        int f = sc.nextInt(), n = sc.nextInt();
//        sc.nextLine();
//        GameEngine ge = new GameEngine(n, f);
//        for(int i = 0; i < n; i++) {
//            GameObject go;
//            StringBuilder name = new StringBuilder();
//            for(int j = 0; j < 4; j++){
//                String s = sc.nextLine();
//                if(s.isEmpty()) continue;
//                name.append(s);
//                name.append("\n");
//            }
//            go = new GameObject(name.toString());
//            ge.add(go);
//        }
//        ge.run();
//        sc.close();
        GUI gui = new GUI();
        gui.render();
    }
    /**
     * apenas um contexto para perceber algumas implementações
     * implementação da colisão das figuras
     * public static void main(String[] args) {
     *         Scanner sc = new Scanner(System.in);
     *         List<FiguraGeometrica> figuras = new ArrayList<>();
     *
     *         while (sc.hasNextLine()) {
     *             String linha = sc.nextLine().trim();
     *             if (linha.isEmpty()) {
     *                 break;
     *             }
     *
     *             String[] partes = linha.split(" ", 2);
     *             String tipoFigura = partes[0];
     *             String dados = partes.length > 1 ? partes[1] : "";
     *
     *             try {
     *                 Class<?> cl = Class.forName(capital(tipoFigura));
     *                 Constructor<?> constructor = cl.getConstructor(String.class);
     *                 FiguraGeometrica figura = (FiguraGeometrica) constructor.newInstance(dados);
     *                 figuras.add(figura);
     *
     *                 for (int i = 0; i < figuras.size() - 1; i++) {
     *                     if (figura.aceitarColisao((Colisao) figuras.get(i))) {
     *                         System.out.println("Colisao na posicao " + i);
     *                         System.exit(0);
     *                     }
     *                 }
     *             } catch (ClassNotFoundException cnfe) {
     *                 System.out.println("Não foi encontrada a classe: " + cnfe.getMessage());
     *                 System.exit(0);
     *             } catch (Exception e) {
     *                 e.printStackTrace();
     *                 System.exit(0);
     *             }
     *         }
     *
     *         System.out.println("Sem colisoes");
     *         sc.close();
     *     }
     *
     *     implementação da lista de figuras geometricas
     *
     *     Scanner sc = new Scanner(System.in);
     *         Constructor<?> constructor;
     *         Class<?> cl;
     *         List<FiguraGeometrica> figuras = new ArrayList<>();
     *         String s;
     *
     *         // Ler a figura geométrica
     *         if (sc.hasNextLine()) {
     *             s = sc.nextLine(); // Lê a linha da figura geométrica
     *             String[] aos = s.split(" ", 2); // Divide a linha em nome da figura e dados
     *             try {
     *                 cl = Class.forName(capital(aos[0])); // Obtém a classe da figura
     *                 constructor = cl.getConstructor(String.class); // Obtém o construtor
     *                 FiguraGeometrica f = (FiguraGeometrica) constructor.newInstance(aos[1]); // Cria a figura
     *                 figuras.add(f); // Adiciona a figura à lista
     *             } catch (ClassNotFoundException cnfe) {
     *                 System.out.println("Não foi encontrada a classe: " + cnfe.getMessage());
     *             } catch (Exception e) {
     *                 e.printStackTrace();
     *             }
     *         }
     *
     *         // Ler os deslocamentos (dx e dy)
     *         if (sc.hasNextLine()) {
     *             String[] deslocamentos = sc.nextLine().split(" ");
     *             int dx = Integer.parseInt(deslocamentos[0]);
     *             int dy = Integer.parseInt(deslocamentos[1]);
     *
     *             // Aplicar a translação a cada figura
     *             for (FiguraGeometrica f : figuras) {
     *                 f.translacionar(dx, dy);
     *                 System.out.println(f.toString()); // Imprime a figura após a translação
     *             }
     *         }
     *
     *         sc.close();
     *     }
     * **/
}


