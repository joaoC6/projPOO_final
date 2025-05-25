package files;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Main extends JPanel {
    private GameEngine ge;
    private Clip starterClip;    // Música do lobby (starter_music)
    private Clip backgroundClip; // Música do jogo (background_music)

    public Main(){}

    private void mainMenu() throws IOException {
        JFrame jf = new JFrame();
        JPanel jp = new JPanel() {
            private BufferedImage backGround = ImageIO.read(
                    Objects.requireNonNull(getClass().getResource("/resources/Images/background.png"))
            );

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
        jf.setSize(600, 550);

        jl.setAlignmentX(Component.CENTER_ALIGNMENT);
        jl.setFont(new Font("Pacifico", Font.BOLD, 50));
        jl.setForeground(Color.YELLOW);
        jl.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        jp.add(jl);

        // botão SinglePlayer
        single.setAlignmentX(Component.CENTER_ALIGNMENT);
        single.setMaximumSize(new Dimension(200, 60));
        single.setBorder(BorderFactory.createEmptyBorder(30, 0, 30,0));
        single.addActionListener(e -> startGame(0));
        jp.add(single);
        jp.add(Box.createRigidArea(new Dimension(0, 30)));

        // botão Multiplayer
        multi.setAlignmentX(Component.CENTER_ALIGNMENT);
        multi.setMaximumSize(new Dimension(200, 60));
        multi.setBorder(BorderFactory.createEmptyBorder(30, 0, 30,0));
        multi.addActionListener(e -> startGame(1));
        jp.add(multi);
        jp.add(Box.createRigidArea(new Dimension(0, 30)));

        // botão Exit
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setMaximumSize(new Dimension(200, 60));
        exit.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        exit.addActionListener(e -> System.exit(0));
        jp.add(exit);

        jf.add(jp);
        jf.setVisible(true);
    }

    private void startGame(int mode) {
        stopStarterMusic();   // Para a música do menu (starter_music)
        playBackgroundMusic(); // Toca a música do jogo (background_music)

        if(mode == 0){
            ge = new GameEngine(3);
            GameObject go = new GameObject();
        }else{
            ge = new GameEngine(5);
        }
    }

    // Toca a música do menu (starter_music)
    private void playStarterMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    Objects.requireNonNull(getClass().getResource("/resources/music/starter_music.wav"))
            );
            starterClip = AudioSystem.getClip();
            starterClip.open(audioInputStream);
            starterClip.loop(Clip.LOOP_CONTINUOUSLY);
            starterClip.start();
        } catch (Exception e) {
            System.err.println("Erro ao carregar starter_music: " + e.getMessage());
        }
    }

    // Para a música do menu (starter_music)
    private void stopStarterMusic() {
        if (starterClip != null && starterClip.isRunning()) {
            starterClip.stop();
            starterClip.close();
        }
    }

    // Toca a música do jogo (background_music)
    private void playBackgroundMusic() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    Objects.requireNonNull(getClass().getResource("/resources/music/background_music.wav"))
            );
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioInputStream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundClip.start();
        } catch (Exception e) {
            System.err.println("Erro ao carregar background_music: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.playStarterMusic();  // Toca a música do menu (starter_music)
        m.mainMenu();
    }
}
