package hotciv.view.tool;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.view.GfxConstants;
import hotciv.view.figure.HotCivFigure;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;

import java.awt.*;
import java.awt.event.MouseEvent;

/** Template for the CompositionTool exercise (FRS 36.44).
 * Composition tool is basically a State Pattern, similar
 * to MiniDraw's SelectionTool. That is, upon mouse-down
 * it must determine what the user wants (from analyzing
 * what graphical elements have been clicked - unit?
 * city? tile? turn-shield? etc.) and then set its
 * internal tool state to the appropriate tool - and
 * then delegate the mouse down request to that tool.
 *
 * @author Henrik Bærbak Christensen, Aarhus University
 */
public class CompositionTool extends NullTool {
  private final DrawingEditor editor;
  private final Game game;
  private HotCivFigure figureBelowClickPoint;
  private Rectangle gameBoard;
  private Rectangle refreshButton;
  private int gameBoardDimension = GfxConstants.TILESIZE * GameConstants.WORLDSIZE;
  private int refreshButtonWidth = 45;
  private int refreshButtonHeight = 18;
  private boolean mouseIsDragged = false;

  private Tool state;

  public CompositionTool(DrawingEditor editor, Game game) {
    state = new NullTool();
    this.editor = editor;
    this.game = game;
    gameBoard = new Rectangle(GfxConstants.MAP_OFFSET_X, GfxConstants.MAP_OFFSET_Y, gameBoardDimension, gameBoardDimension);
    refreshButton = new Rectangle(GfxConstants.REFRESH_BUTTON_X, GfxConstants.REFRESH_BUTTON_Y, refreshButtonWidth, refreshButtonHeight);
  }

  @Override
  public void mouseDown(MouseEvent e, int x, int y) {
    mouseIsDragged = false;
    // Find the figure (if any) just below the mouse click position
    figureBelowClickPoint = (HotCivFigure) editor.drawing().findFigure(x, y);

    boolean isFigure = figureBelowClickPoint != null;
    // Next determine the state of tool to use

    if (isFigure) {
      boolean isTurnShield = figureBelowClickPoint.getTypeString().equals(GfxConstants.TURN_SHIELD_TYPE_STRING);
      boolean isUnit = figureBelowClickPoint.getTypeString().equals(GfxConstants.UNIT_TYPE_STRING);

      if(isTurnShield) {
        state = new EndOfTurnTool(editor, game);

      } else if(isUnit && gameBoard.contains(x,y)){
        if(e.isShiftDown()){
          state = new ActionTool(editor, game);
        } else {
          state = new MoveTool(editor, game);
        }
      }
    }

    // Finally, delegate to the selected state
    state.mouseDown(e, x, y);
  }

  @Override
  public void mouseDrag(MouseEvent e, int x, int y){
      mouseIsDragged = true;
      state.mouseDrag(e, x, y);
  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y){
    if (!mouseIsDragged && gameBoard.contains(x,y)){
      state = new SetFocusTool(editor, game);
    }
    if(!mouseIsDragged && refreshButton.contains(x,y)) {
      state = new RefreshTool(editor, game);
    }
    state.mouseUp(e, x, y);
    state = new NullTool();
  }
}