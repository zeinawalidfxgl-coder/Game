/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Dell
 */
public class ValidationResult {
 public record Duplicate(String type, int row, int col, int value) {}
   //type dah el hwa row col box kda  
    final List<Duplicate> duplicates = new ArrayList<>();

    public void addDuplicate(String type, int r, int c, int val) {
        duplicates.add(new Duplicate(type, r, c, val));
    }

    public boolean isValid() {
        return duplicates.isEmpty();
    }  
   
    public void printDuplicates() {
        if (isValid()) {
            System.out.println("VALID");
            return;
        }
        
        System.out.println("INVALID");
    
        for (Duplicate d : duplicates) {
            System.out.printf("%s, R:%d, C:%d, Value:%d%n", d.type, d.row + 1, d.col + 1, d.value);
        }
    }
}
