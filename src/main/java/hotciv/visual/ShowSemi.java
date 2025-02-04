package hotciv.visual;

import hotciv.standard.GameImpl;
import hotciv.standard.variants.factories.SemiCivFactory;
import hotciv.view.tool.CompositionTool;
import hotciv.view.tool.MoveTool;
import minidraw.standard.*;
import minidraw.framework.*;

import hotciv.framework.*;
import hotciv.stub.*;

public class ShowSemi {

    public static void main(String[] args) {
        Game game = new GameImpl(new SemiCivFactory());

        DrawingEditor editor =
                new MiniDrawApplication( "Move any unit using the mouse",
                        new HotCivFactory4(game) );
        editor.open();
        editor.showStatus("Move units to see Game's moveUnit method being called.");

        editor.setTool( new CompositionTool(editor, game) );
    }
}
