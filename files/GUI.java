package files;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class responsible for the graphical display of the game engine
 * @author poo2425p1g4
 * @version 1.0
 */
public class GUI extends JPanel {
    private GameEngine ge;
    private final BufferedImage backGround;
    private CopyOnWriteArrayList<GameObject> enabledObjects;

    // Construtor recebe GameEngine para ter referência
    public GUI(GameEngine ge) {
        this.ge = ge;
        enabledObjects = new CopyOnWriteArrayList<>();
        try {
            File f = new File("src/resources/Images/background.png");
            backGround = ImageIO.read(f);
            // Alternativa se usar recurso embutido:
            // backGround = ImageIO.read(getClass().getResource("/background.png"));
        } catch (IOException e) {
            throw new RuntimeException("Não encontrou o ficheiro de background");
        }

        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Desenha o background no canto superior esquerdo com o tamanho original
        g2.drawImage(backGround, 0, 0, backGround.getWidth(), backGround.getHeight(), null);

        // Desenha cada GameObject
        for (GameObject go : enabledObjects) {
            ITransform t = go.transform();
            // Exemplo genérico:
            // g2.setColor(Color.RED);
            // g2.fillRect((int)t.getX(), (int)t.getY(), 20, 20);
            // Ou go.render(g2);
        }

        if (!enabledObjects.isEmpty()) {
            g2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            g2.setColor(Color.WHITE);
            // Posição x=10, y=20 para visibilidade
            g2.drawString("Score:", 10, 20);
        }
    }

    /**
     * Atualiza a lista de objetos habilitados para renderização
     * @param enabled lista dos objetos habilitados atuais
     */
    public void render(List<GameObject> enabled) {
        if (enabled != null) {
            enabledObjects.clear();
            enabledObjects.addAll(enabled);
        }
        repaint();
    }
}
