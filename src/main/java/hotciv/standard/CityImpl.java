package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

import java.util.HashMap;
import java.util.UUID;

public class CityImpl implements City {
private int treasury = 0;
private int population = 1;
private String production;
private String workFocus;
private Player owner;
private final String id;

private static final HashMap<String, Integer> unitCosts = new HashMap(){{
    put(GameConstants.ARCHER, GameConstants.ARCHER_COST);
    put(GameConstants.LEGION, GameConstants.LEGION_COST);
    put(GameConstants.SETTLER, GameConstants.SETTLER_COST);
    put(GameConstants.CARAVAN, GameConstants.CARAVAN_COST);
    }};

    public CityImpl(Player owner) {
        this.owner = owner;
        production = GameConstants.ARCHER;
        workFocus = GameConstants.foodFocus;
        id = UUID.randomUUID().toString();
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getSize() {
        return population;
    }

    @Override
    public int getTreasury() {
        return treasury;
    }

    @Override
    public String getProduction() {
        return production;
    }

    @Override
    public String getWorkforceFocus() {
        return workFocus;
    }

    @Override
    public String getId(){
        return id;
    }

    public void addToTreasury(int amount) {
        treasury += amount;
    }

    public void reduceCostOfProductionFromTreasury() {
            treasury -= unitCosts.get(production);
        }

    public void setProduction(String production){
        this.production = production;
    }

    public void setWorkFocus(String workFocus) {
        this.workFocus = workFocus;
    }

    public void setOwner(Player player){
        owner = player;
    }

    public boolean hasEnoughTreasury(){
        return treasury >= unitCosts.get(production);
    }

    public void increasePopulation(){
        population++;
    }
}
