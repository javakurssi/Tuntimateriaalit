package tunti2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Tämä esimerkki havainnollistaa samankokoisesta datamäärästä Hashmap
 * tietorakenteen ja ArrayListin eroja.
 */
public class IsoDataMaara {

    public static void main(String[] args) {
        int dataSize = 10000000;
        Map<String, Integer> hashMap = createHashMap(dataSize);
        List<String> list = createArrayList(dataSize);

        // Measure time for HashMap lookup
        long startTimeHashMap = System.currentTimeMillis();
        boolean containsKeyHashMap = hashMap.containsKey("5000000");
        long endTimeHashMap = System.currentTimeMillis();
        System.out.println("HashMap containsKey: " + containsKeyHashMap);
        System.out.println("HashMap search time: " + (endTimeHashMap - startTimeHashMap) + " milliseconds");

        // Measure time for ArrayList contains
        long startTimeList = System.currentTimeMillis();
        boolean containsList = list.contains("5000000");
        long endTimeList = System.currentTimeMillis();
        System.out.println("ArrayList contains: " + containsList);
        System.out.println("ArrayList search time: " + (endTimeList - startTimeList) + " milliseconds");
    }

    private static Map<String, Integer> createHashMap(int dataSize) {
        Map<String, Integer> hashMap = new HashMap<>();
        Random random = new Random();
        for (int i = 0; i < dataSize; i++) {
            String key = String.valueOf(random.nextInt(dataSize));
            hashMap.put(key, i);
        }
        return hashMap;
    }

    private static List<String> createArrayList(int dataSize) {
        List<String> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < dataSize; i++) {
            String key = String.valueOf(random.nextInt(dataSize));
            list.add(key);
        }
        return list;
    }
}
