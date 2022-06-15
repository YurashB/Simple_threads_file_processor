package mvc.bussines_logic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Statistic {

    private static volatile Statistic instance;
    private final Map<String, Integer> map = Collections.synchronizedMap(new TreeMap<>());

    private Statistic() {
    }

    public static Statistic getInstance() {
        if (instance == null) {
            instance = new Statistic();
        }
        return instance;
    }


    public Map<String, Integer> getMap() {
        return map;
    }

    public void loadDataToMap(HashMap<String, Integer> hashMap) {
        for (Map.Entry<String, Integer> mapEntry : hashMap.entrySet()) {
            if (map.containsKey(mapEntry.getKey())) {
                map.replace(mapEntry.getKey(), map.get(mapEntry.getKey()), map.get(mapEntry.getKey()) + mapEntry.getValue());
            } else {
                map.put(mapEntry.getKey(), mapEntry.getValue());
            }
        }
    }

    public void clearStatistic() {
        map.clear();
    }
}
