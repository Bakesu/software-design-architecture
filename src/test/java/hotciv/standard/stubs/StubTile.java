package hotciv.standard.stubs;

import hotciv.framework.Player;
import hotciv.framework.Tile;
import hotciv.framework.Unit;

class StubTile implements Tile {
    private String type;
    public StubTile(String type, int r, int c) { this.type = type; }
    public String getTypeString() { return type; }
}
