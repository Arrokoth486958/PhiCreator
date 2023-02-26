package dev.arrokoth.phicreator.phi.chart.event;

import dev.arrokoth.phicreator.phi.level.Level;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class NoteMoveEvent extends ChartEvent {
    public final int target;
    public final int deltaX;
    public final int deltaY;

    public NoteMoveEvent(int target, int deltaX, int deltaY) {
        this.target = target;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public void handle(Level level) {
    }
}
