package game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import java.util.*;

public class ValidationResult {

    private final Map<String, Map<Integer, List<Integer>>> duplicates = new HashMap<>();

    // synchronized method → thread-safe
    public synchronized void addDuplicate(String type, int id, int value, List<Integer> locations) {
        // اتأكد إن outer map موجودة
        if (!duplicates.containsKey(type)) {
            duplicates.put(type, new HashMap<>());
        }

        Map<Integer, List<Integer>> innerMap = duplicates.get(type);

        // اتأكد إن inner map موجودة
        if (!innerMap.containsKey(value)) {
            innerMap.put(value, new ArrayList<>());
        }

        // أضف كل المواقع للقائمة
        innerMap.get(value).addAll(locations);
    }

    // synchronized print method (اختياري لو هيتنادى عليها من أكثر من thread)
    public synchronized void printDuplicates() {
        if (duplicates.isEmpty()) {
            System.out.println("No duplicates found. Sudoku is valid!");
            return;
        }

        for (String type : duplicates.keySet()) {
            Map<Integer, List<Integer>> map = duplicates.get(type);
            for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
                System.out.println(type + " - Value " + entry.getKey() + " duplicated at positions: " + entry.getValue());
            }
        }
    }

    public synchronized boolean isValid() {
        return duplicates.isEmpty();
    }
}
