package cegepst.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderingEngine {

    private JFrame frame;
    private JPanel panel;
    private BufferedImage bufferedImage;

    public RenderingEngine() {
        initializeFrame();
        initializePanel();
    }

    public Graphics2D getRenderingBuffer() {
        bufferedImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics;
        graphics = bufferedImage.createGraphics();
        graphics.setRenderingHints(getOptimalRenderingHints());
        return graphics;
    }

    public void renderBufferOnScreen() {
        Graphics2D graphics = (Graphics2D) panel.getGraphics();
        graphics.drawImage(bufferedImage, 0, 0, panel);
        Toolkit.getDefaultToolkit().sync();
        graphics.dispose();
    }

    public void start() {
        frame.setVisible(true);
    }

    public void stop() {
        frame.setVisible(false);
        frame.dispose();
    }

    private void initializeFrame() {
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null); // Center frame on screen
        frame.setResizable(false);
        frame.setTitle("Bouncing Balls");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setState(JFrame.NORMAL);
        // setUndecorated(true); // Supprimer la barre de l'application
    }

    private void initializePanel() {
        panel = new JPanel();
        panel.setBackground(Color.blue);
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
        frame.add(panel); // Ajouter le panneau dans le JFrame
    }

    private RenderingHints getOptimalRenderingHints() {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        return rh;
    }
}