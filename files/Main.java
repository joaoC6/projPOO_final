package files;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JPanel {
    private JFrame jf = new JFrame();
    private GameEngine ge;
    private Clip starterClip, backgorundMusic;
    public final static int windowWidth = 600, windowHeigth = 550;
    public final static Ponto spawnPoint = new Ponto(200, 400);

    public Main(){}

    private void mainMenu() throws IOException {
        JPanel jp = new JPanel(){
            private BufferedImage backGround = ImageIO.read(new File(
                    "src/resources/Images/background.png"
            ));
//            private BufferedImage backGround = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/Images/background.png")));

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.drawImage(backGround, 0 , 0, backGround.getWidth(), backGround.getHeight(), null);
            }
        };
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        JLabel jl = new JLabel("Meteor Run");
        JButton single = new JButton("SinglePlayer"), exit = new JButton("Exit Game"), multi = new JButton("Multiplayer");
        jf.setSize((int) windowWidth, (int) windowHeigth);

        jl.setAlignmentX(Component.CENTER_ALIGNMENT);
        jl.setFont(new Font("Pacifico", Font.BOLD, 50));
        jl.setForeground(Color.YELLOW);
        jl.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        jp.add(jl);

        //criação do butão de começo do mode de jogo single player
        single.setAlignmentX(Component.CENTER_ALIGNMENT);
        single.setMaximumSize(new Dimension(200, 60));
        single.setBorder(BorderFactory.createEmptyBorder(30, 0, 30,0));
        single.addActionListener(e -> startGame(0));
        jp.add(single);
        jp.add(Box.createRigidArea(new Dimension(0, 30)));

        //criação do butão de mode de jogo multiplayer local
        multi.setAlignmentX(Component.CENTER_ALIGNMENT);
        multi.setMaximumSize(new Dimension(200, 60));
        multi.setBorder(BorderFactory.createEmptyBorder(30, 0, 30,0));
        multi.addActionListener(e -> startGame(1));
        jp.add(multi);
        jp.add(Box.createRigidArea(new Dimension(0, 30)));

        //criação do butão de saida do jogo
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setMaximumSize(new Dimension(200, 60));
        exit.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        exit.addActionListener(e -> System.exit(0));
        jp.add(exit);

        jf.add(jp);
        try {
            AudioInputStream startStream = AudioSystem.getAudioInputStream(
                    new File("src/resources/Sounds/starter_music.wav")
            );
            AudioInputStream backgroundStream = AudioSystem.getAudioInputStream(
                    new File("src/resources/Sounds/background_music.wav")
            );
            starterClip = AudioSystem.getClip();
            starterClip.open(startStream);
            starterClip.loop(Clip.LOOP_CONTINUOUSLY);
            starterClip.start();
            backgorundMusic = AudioSystem.getClip();
            backgorundMusic.open(backgroundStream);
        } catch (Exception e) {
            System.err.println("Erro ao carregar starter_music: " + e.getMessage());
        }

        jf.setVisible(true);
    }

    private void startGame(int mode){
        GameObject wall1 = new GameObject(String.format("parede\n0 0 1 0 0\n-30 0 30 0 30 %s -30 %s", Main.windowHeigth, Main.windowHeigth));
        GameObject wall2 = new GameObject(String.format("parede\n0 0 2 0 0\n-0 0 30 0 30 %s -30 %s", Main.windowHeigth, Main.windowHeigth));
        jf.getContentPane().removeAll();
        starterClip.stop();
        backgorundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        backgorundMusic.start();
        if(mode == 0){
            String s = "nave\n0 0 1 0 0\n0 -2 -2 2 2 2";
            GameObject go = new GameObject(s);
            ge = new GameEngine(3, go.hashCode(), 0);
            ge.add(go);
            ge.add(wall1);
            ge.add(wall2);
            jf.setContentPane(ge.getGUI());
            jf.revalidate();
            jf.repaint();
            ge.start();
        }else{
            String p1 = "nave\n0 0 1 0 0\n0 -2 -2 2 2 2";
            String p2 = "nave\n0 0 1 0 0\n0 -2 -2 2 2 2";
            GameObject go1 = new GameObject(p1), go2 = new GameObject(p2);
            ge = new GameEngine(5, go1.hashCode(), 1);
            ge.setHashcodePlayer2(go2.hashCode());
            ge.add(go1);
            ge.add(go2);
            ge.add(wall1);
            ge.add(wall2);
            jf.setContentPane(ge.getGUI());
            jf.revalidate();
            jf.repaint();
            ge.run();
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.mainMenu();
    }
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


