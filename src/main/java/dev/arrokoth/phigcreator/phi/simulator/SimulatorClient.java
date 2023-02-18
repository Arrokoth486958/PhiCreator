package dev.arrokoth.phigcreator.phi.simulator;

import dev.arrokoth.phigcreator.phi.chart.Note;
import dev.arrokoth.phigcreator.phi.level.Level;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Arrokoth
 * @project PhigCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class SimulatorClient extends Thread {
    private final Level level;
    private final int width;
    private final int height;
    private final Graphics2D surface;
    private boolean running;

    public SimulatorClient(Level level, int width, int height, Graphics2D surface) {
        this.level = level;
        this.width = width;
        this.height = height;
        this.surface = surface;
        this.running = false;
    }

    @Override
    public void run() {
        running = true;
        long startTime = System.currentTimeMillis();

        while (running) {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = (Graphics2D) image.getGraphics();

            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 0, width, height);
            graphics.drawImage(level.getIllustration(), 0, 0, width, height, null);
//              running = true;
//              this.level = level;

            for (Note x : level.getNoteOnTime(System.currentTimeMillis() - startTime)) {
                graphics.setColor(Color.RED);
                graphics.draw(new Rectangle(0, 0, 512, 512));
            }

            surface.setColor(Color.BLACK);
            surface.fillRect(0, 0, width, height);
            surface.drawImage(image, 0, 0, width, height, null);
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        running = false;
    }
}
