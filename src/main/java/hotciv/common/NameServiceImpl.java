package hotciv.common;

import hotciv.framework.City;
import hotciv.framework.Unit;

import java.util.HashMap;
import java.util.Map;

public class NameServiceImpl implements NameService {

    private Map<String, City> cityMap;
    private Map<String, Unit> unitMap;

    public NameServiceImpl() {
        cityMap = new HashMap<>();
        unitMap = new HashMap<>();
    }


    @Override
    public void putCity(String objectId, City city) {
        cityMap.put(objectId, city);
    }

    @Override
    public City getCity(String objectId) {
        return cityMap.get(objectId);
    }

    @Override
    public void putUnit(String objectId, Unit unit) {
        unitMap.put(objectId, unit);
    }

    @Override
    public Unit getUnit(String objectId) {
        return unitMap.get(objectId);
    }
}
