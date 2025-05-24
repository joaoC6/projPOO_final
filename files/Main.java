package files;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main extends JPanel {

    public Main(){}

    private void mainMenu(){
        JFrame jf = new JFrame();
        JPanel jp = new JPanel();
        JLabel jl = new JLabel("Teste");
        JButton start = new JButton("Start Game"), exit = new JButton("Exit Game");
        jf.setSize(500, 500);

        jl.setFont(new Font("Pacifico", Font.BOLD, 50));
        jl.setForeground(Color.CYAN);
        jp.add(jl);

        start.addActionListener(e -> new GameEngine(4).run());
        jp.add(start);

        exit.addActionListener(e -> System.exit(0));
        jp.add(exit);
        repaint();
        jf.add(jp);
        jf.setVisible(true);
    }


    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.mainMenu();
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


