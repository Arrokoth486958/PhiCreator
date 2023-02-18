package dev.arrokoth.phigcreator.phi.chart.event;

import dev.arrokoth.phigcreator.phi.level.Level;

/**
 * @author Arrokoth
 * @project PhigCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public abstract class ChartEvent {
    public abstract void handle(Level level, int target);
}
