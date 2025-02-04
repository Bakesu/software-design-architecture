package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class SetFocusTool extends NullTool {
    private final DrawingEditor editor;
    private final Game game;

    public SetFocusTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        super.mouseUp(e, x, y);
            game.setTileFocus(GfxConstants.getPositionFromXY(x,y));
    }
}
