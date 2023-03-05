package dev.arrokoth.phicreator.player;

import dev.arrokoth.phicreator.chart.phi.chart.Chart;
import dev.arrokoth.phicreator.chart.phi.chart.objects.JudgeLine;
import dev.arrokoth.phicreator.chart.phi.chart.objects.LineDisappearEvent;
import dev.arrokoth.phicreator.player.objects.JudgeLineObject;
import dev.arrokoth.phicreator.player.objects.NoteObject;
import dev.arrokoth.phicreator.player.objects.RenderObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class PlayerClient extends JPanel {
    protected final JFrame frame;
    protected final Chart chart;
    protected final Image illustration;
    protected boolean running = false;
    protected int fps = 0;
    protected final List<RenderObject> renderObjects;

    public PlayerClient(Chart chart, Dimension size) {
        try {
            this.chart = chart;
            this.renderObjects = new ArrayList<>();

            this.illustration = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);

            BufferedImage inImage = ImageIO.read(chart.getIllustration());
//            BufferedImage outImage = new BufferedImage(inImage.getWidth(), inImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
//
//            int blurRadius = 8;
//            for (int x = 0; x < outImage.getWidth(); x++) {
//                for (int y = 0; y < outImage.getHeight(); y++) {
//                    int baseColor = inImage.getRGB(x, y);
//                    int alpha = (baseColor >> 24) & 0xFF;
//                    int red = (baseColor >> 16) & 0xFF;
//                    int green = (baseColor >> 8) & 0xFF;
//                    int blue = baseColor & 0xFF;
//
//                    int argb = (alpha << 24) + (red << 16) + (green << 8) + blue;
//                    outImage.setRGB(x, y, argb);
//                }
//            }

            Graphics2D graphics = ((BufferedImage) this.illustration).createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.drawImage(/*outImage*/inImage.getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH), 0, 0, null);
            graphics.dispose();

            this.frame = new JFrame();
            this.frame.setTitle("Simulator");
            this.frame.setSize(size);
            this.frame.setResizable(false);
            this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    running = false;
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void play() {
        if (running) {
            return;
        }

        running = true;
        frame.setVisible(true);

        new Thread(() -> {
            int maxFps = 60;
            long fpsTimer = 0L;
            int fpsBuf = 0;

            float frameTime = 1000f / maxFps;
            long lastFrameTime = 0L;
            frame.setContentPane(this);
            long timeStart = System.currentTimeMillis() - 3000;

            while (running) {
                while (System.currentTimeMillis() - lastFrameTime < frameTime);

                renderObjects.clear();
                for (JudgeLine line : chart.getLines()) {
                    for (double key : line.moveEvents.keySet()) {
//                        System.out.println(key);
                        if (key < System.currentTimeMillis() - timeStart) {
                            continue;
                        } else if (key > System.currentTimeMillis() - timeStart) {
                            break;
                        }
                        this.renderObjects.add(new JudgeLineObject(line.moveEvents.get(key).start * 512, line.moveEvents.get(key).start2 * 512, 0));
                    }
                }
                System.out.println(this.renderObjects);

                repaint();

                lastFrameTime = System.currentTimeMillis();
                fpsBuf++;

                if (System.currentTimeMillis() > fpsTimer + 1000) {
                    fps = fpsBuf;
                    fpsBuf = 0;
                    fpsTimer = System.currentTimeMillis();

                    for (int i = 0; i < maxFps - fps; i++) {
                        repaint();
                        fps++;
                    }
                }
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        graphics.drawImage(illustration, 0, 0, getWidth(), getHeight(), new Color(0f, 0f, 0f, 0f), null);
        graphics.setColor(new Color(0f, 0f, 0f, 0.75f));
        graphics.fillRect(0, 0, getWidth(), getHeight());

        graphics.setFont(new Font(graphics.getFont().getFamily(), Font.BOLD, 14));
        graphics.setColor(Color.WHITE);
        graphics.drawString(String.format("| FPS: %d", fps), 0, graphics.getFont().getSize());

        List<RenderObject> renderObjectsCopy = new ArrayList<>(renderObjects);
        for (Object object : renderObjectsCopy) {
            if (object instanceof JudgeLineObject) {
                graphics.setColor(Color.WHITE);
//                graphics.setStroke(new BasicStroke(4));
//                System.out.println(((JudgeLineObject) object).x);
//                graphics.drawImage(
//                        Textures.TAP.getScaledInstance(160, 32, Image.SCALE_SMOOTH),
//                        projectionX(((JudgeLineObject) object).x) - 80,
//                        projectionY(((JudgeLineObject) object).y) - 16,
//                        new Color(0f, 0f, 0f, 0f),
//                        null);
//                graphics.drawLine(Math.tan(((JudgeLineObject) object).rot));
                graphics.drawLine(
                        projectionX(((JudgeLineObject) object).x),
                        projectionY(((JudgeLineObject) object).y),
                        0, 0);
            }
        }

//        graphics.drawRect(projectionX(0),projectionY(0), projectionX(2), projectionY(2));
//        graphics.setColor(Color.WHITE);
//        graphics.setStroke(new BasicStroke(4));
//        graphics.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

        graphics.dispose();
    }

    private int projectionX(double x) {
        return (int) (((x + 512) / 1024d) * frame.getWidth());
    }

    private int projectionY(double y) {
        return (int) (((y + 512) / 1024d) * frame.getHeight());
    }
}
