package dev.arrokoth.phicreator.player.objects;

import dev.arrokoth.phicreator.chart.phi.chart.objects.Note;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class JudgeLineObject implements RenderObject {
    public double x;
    public double y;
    public double rot;

    public JudgeLineObject(double x, double y, double rot) {
        this.x = x;
        this.y = y;
        this.rot = rot;
    }

    @Override
    public Image getTexture() {
        return null;
    }

    @Override
    public String toString() {
        return "JudgeLineObject{" +
                "x=" + x +
                ", y=" + y +
                ", rot=" + rot +
                '}';
    }
}
