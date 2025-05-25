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
    private Clip starterClip;
    private Clip backgroundClip;
    private JFrame jf;

    public Main() {}

    private void mainMenu() throws IOException {
        jf = new JFrame("Meteor Run");
        JPanel jp = new JPanel() {
            private BufferedImage backGround = ImageIO.read(
                    Objects.requireNonNull(getClass().getResource("/resources/Images/background.png"))
            );

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.drawImage(backGround, 0, 0, backGround.getWidth(), backGround.getHeight(), null);
            }
        };

        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        JLabel jl = new JLabel("Meteor Run");
        JButton single = new JButton("SinglePlayer");
        JButton exit = new JButton("Exit Game");
        JButton multi = new JButton("Multiplayer");

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(600, 550);
        jf.setLocationRelativeTo(null);

        jl.setAlignmentX(Component.CENTER_ALIGNMENT);
        jl.setFont(new Font("Pacifico", Font.BOLD, 50));
        jl.setForeground(Color.YELLOW);
        jl.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        jp.add(jl);

        single.setAlignmentX(Component.CENTER_ALIGNMENT);
        single.setMaximumSize(new Dimension(200, 60));
        single.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        single.addActionListener(e -> startGame(0));
        jp.add(single);
        jp.add(Box.createRigidArea(new Dimension(0, 30)));

        multi.setAlignmentX(Component.CENTER_ALIGNMENT);
        multi.setMaximumSize(new Dimension(200, 60));
        multi.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        multi.addActionListener(e -> startGame(1));
        jp.add(multi);
        jp.add(Box.createRigidArea(new Dimension(0, 30)));

        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setMaximumSize(new Dimension(200, 60));
        exit.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        exit.addActionListener(e -> System.exit(0));
        jp.add(exit);

        jf.setContentPane(jp);
        jf.setVisible(true);
    }

    private void startGame(int mode) {
        stopStarterMusic();
        playBackgroundMusic();

        jf.getContentPane().removeAll(); // Limpa o menu

        if (mode == 0) {
            ge = new GameEngine(3); // SinglePlayer
            ge.run(0);
        } else {
            ge = new GameEngine(5); // Multiplayer
            ge.run(1);
        }

        jf.setContentPane(ge.getGUI()); // Corrigido!
        jf.revalidate();
        jf.repaint();
        ge.getGUI().requestFocusInWindow(); // Corrigido!
    }


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

    private void stopStarterMusic() {
        if (starterClip != null && starterClip.isRunning()) {
            starterClip.stop();
            starterClip.close();
        }
    }

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
        m.playStarterMusic();
        m.mainMenu();
    }
}
