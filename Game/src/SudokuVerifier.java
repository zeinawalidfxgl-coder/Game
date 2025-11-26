/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class SudokuVerifier {
   private final SudokuBoard sudokuBoard;

    public SudokuVerifier(SudokuBoard board) {
        this.sudokuBoard = board;
    }

    /**
     * Executes the sequential validation (Mode 0)
     */
    //
    public void runSequentialVerification() {
        ValidationResult rowResults = validateAllRows();
        ValidationResult colResults = validateAllCols();
        ValidationResult boxResults = validateAllBoxes();

        // ntshof kol el error w bt merge 
        ValidationResult finalResult = mergeResults(rowResults, colResults, boxResults);
        
        finalResult.printDuplicates();
    }

    // --- Core Check Functions ---

    private ValidationResult validateAllRows() {
        ValidationResult result = new ValidationResult();

        for (int r = 0; r < 9; r++) {
            boolean[] seen = new boolean[10]; // Indices 1-9 for Sudoku digits
            for (int c = 0; c < 9; c++) {
                int digit = sudokuBoard.getCell(r, c);
                
                // Assuming input is sanitized (1-9), otherwise add a check for 0
                if (digit < 1 || digit > 9) continue; 
                
                if (seen[digit]) {
                    result.addDuplicate("ROW", r, c, digit);
                } else {
                    seen[digit] = true;
                }
            }
        }
        return result;
    }

    private ValidationResult validateAllCols() {
        ValidationResult result = new ValidationResult();
        
        for (int c = 0; c < 9; c++) { // Iterate through each column
            boolean[] seen = new boolean[10];
            for (int r = 0; r < 9; r++) { // Iterate through the rows in this column
                int digit = sudokuBoard.getCell(r, c);
                
                if (digit < 1 || digit > 9) continue; 
                
                if (seen[digit]) {
                    result.addDuplicate("COL", r, c, digit);
                } else {
                    seen[digit] = true;
                }
            }
        }
        return result;
    }

    private ValidationResult validateAllBoxes() {
        ValidationResult result = new ValidationResult();
        
        // Loops iterate over the starting cell (0,0), (0,3), (0,6), (3,0), etc.
        for (int startR = 0; startR < 9; startR += 3) {
            for (int startC = 0; startC < 9; startC += 3) {
                boolean[] seen = new boolean[10];

                // Inner loops iterate over the 9 cells *within* the current box
                for (int r = startR; r < startR + 3; r++) {
                    for (int c = startC; c < startC + 3; c++) {
                        int digit = sudokuBoard.getCell(r, c);
                        
                        if (digit < 1 || digit > 9) continue; 

                        if (seen[digit]) {
                            result.addDuplicate("BOX", r, c, digit);
                        } else {
                            seen[digit] = true;
                        }
                    }
                }
            }
        }
        return result;
    }
    
  
    
    // This function combines the duplicate lists from all checks
    private ValidationResult mergeResults(ValidationResult... results) {
        ValidationResult finalResult = new ValidationResult();
        for (ValidationResult res : results) {
            // This is a simplified merge. In a real application, you might use 
            // a custom method to append the duplicates list from 'res' to 'finalResult'.
            // For now, we'll assume the ValidationResult class has a way to expose or append its list.
            for (ValidationResult.Duplicate d : res.duplicates) {
                 finalResult.addDuplicate(d.type(), d.row(), d.col(), d.value());
            }
        }
        return finalResult;
    }
} 
    

