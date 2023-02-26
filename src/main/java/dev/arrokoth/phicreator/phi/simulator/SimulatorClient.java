package dev.arrokoth.phicreator.phi.simulator;

import dev.arrokoth.phicreator.phi.chart.Note;
import dev.arrokoth.phicreator.phi.level.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class SimulatorClient extends JPanel {
    public static final int MAX_FPS = 60;
    public static final float FRAME_TIME = 1000f / MAX_FPS;

    private final Thread thread;
    private final Level level;
    private final int width;
    private final int height;
    private boolean running;

    public SimulatorClient(Level level, int width, int height, JFrame frame) {
        this.thread = new Thread(this::start);
        this.level = level;
        this.width = width;
        this.height = height;
        this.running = false;

        frame.setContentPane(this);
    }

    private void start() {
        while (running) {
            repaint();
        }
    }

    private void draw(Graphics g) {
//        running = true;
//        long startTime = System.currentTimeMillis();
//        long lastFrameTime = 0;
//
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D graphics = (Graphics2D) image.getGraphics();
//
//        while (running) {
//            graphics.setColor(Color.BLACK);
//            graphics.fillRect(0, 0, width, height);
//            graphics.drawImage(level.getIllustration(), 0, 0, width, height, null);
////              running = true;
////              this.level = level;
//
//            for (Note x : level.getNoteOnTime(System.currentTimeMillis() - startTime)) {
//                graphics.setColor(Color.RED);
//                graphics.draw(new Rectangle(0, (int) (Math.sin(System.currentTimeMillis() - startTime) * 32), 64, 64));
//            }
//
////            surface.setColor(Color.BLACK);
////            surface.fillRect(0, 0, width, height);
//            this.getGraphics().drawImage(image, 0, 0, width, height, null);
//
//            while (System.currentTimeMillis() - lastFrameTime < FRAME_TIME);
//
//            lastFrameTime = System.currentTimeMillis();
//            image.flush();
//            graphics.dispose();
//        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
        paintComponent(g);
    }

    @Override
    public void repaint(Rectangle r) {
        super.repaint(r);
    }

    public void run() {
        thread.start();
    }

    public boolean isInterrupted() {
        return thread.isInterrupted();
    }

    public void interrupt() {
        thread.interrupt();
        running = false;
    }
}
