package dev.arrokoth.phicreator.player.objects;

import dev.arrokoth.phicreator.player.Textures;

import java.awt.*;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class NoteObject implements RenderObject {
    public double x;
    public double y;
    private final int type;
    private final boolean highlight;

    public NoteObject(int type, boolean highlight) {
        this.type = type;
        this.highlight = highlight;
    }

    @Override
    public Image getTexture() {
        switch (type) {
            case 1:
                return highlight ? Textures.TAP_HIGHLIGHT : Textures.TAP;
            case 2:
                return highlight ? Textures.DRAG_HIGHLIGHT : Textures.DRAG;
            case 3:
                return highlight ? Textures.HOLD_HIGHLIGHT : Textures.HOLD;
            case 4:
                return highlight ? Textures.FLICK_HIGHLIGHT : Textures.FLICK;
            default:
                return null;
        }
    }
}
