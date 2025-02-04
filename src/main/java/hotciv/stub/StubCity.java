package hotciv.stub;

import hotciv.framework.City;
import hotciv.framework.Player;

public class StubCity implements City {
    private Player owner;
    private String production;
    private String workforceFocus;

    public StubCity(Player owner, String production, String workforceFocus) {
        this.owner = owner;
        this.production = production;
        this.workforceFocus = workforceFocus;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public int getTreasury() {
        return 6;
    }

    @Override
    public String getProduction() {
        return production;
    }

    @Override
    public String getWorkforceFocus() {
        return workforceFocus;
    }

    @Override
    public String getId() {
        return "cityStubId";
    }
}