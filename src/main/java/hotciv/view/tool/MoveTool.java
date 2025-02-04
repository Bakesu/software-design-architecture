package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;
import minidraw.standard.SelectionTool;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MoveTool extends SelectionTool {

    private final DrawingEditor editor;
    private final Game game;
    private Rectangle gameBoard;
    private int gameBoardDimension = GfxConstants.TILESIZE * GameConstants.WORLDSIZE;
    private Position from;
    private Position to;

    public MoveTool(DrawingEditor editor, Game game) {
        super(editor);
        this.editor = editor;
        this.game = game;
        gameBoard = new Rectangle(GfxConstants.MAP_OFFSET_X, GfxConstants.MAP_OFFSET_Y, gameBoardDimension, gameBoardDimension);
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        from = GfxConstants.getPositionFromXY(x, y);

        Drawing model = editor().drawing();

        model.lock();

        draggedFigure = model.findFigure(e.getX(), e.getY());

        if (draggedFigure != null) {
            fChild = createDragTracker(draggedFigure);
        }
        fChild.mouseDown(e, x, y);
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        to = GfxConstants.getPositionFromXY(x, y);

        if (from != null && gameBoard.contains(x, y)) {
            game.moveUnit(from, to);
        }
        to = null;
        from = null;

        editor().drawing().unlock();
        fChild.mouseUp(e, x, y);
        fChild = cachedNullTool;
        draggedFigure = null;
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        super.mouseDrag(e, x, y);
    }
}
