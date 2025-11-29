package game;
import java.io.File;                 // Represents a file on your computer
import java.io.FileNotFoundException; // Handles the error if the file is missing
import java.util.Scanner;            // A tool to read text files line-by-line

public class SudokuBoard {

    // 1. The memory storage. 
    // We use a 2D array [row][col] because a Sudoku is a grid.
    // 'final' means this board size/reference won't change after we create it.
    private final int[][] grid = new int[9][9];

    // 2. The Constructor.
    // When you create 'new SudokuBoard("data.csv")', it immediately loads the data.
    public SudokuBoard(String filePath) {
        loadFromCSV(filePath);
    }

    // 3. The Logic to read the file.
    private void loadFromCSV(String filePath) {
        // We use a 'try-catch' block. File reading is risky (file might not exist).
        // If an error happens, we catch it instead of crashing safely.
        try (Scanner scanner = new Scanner(new File(filePath))) {

            // We need to fill 9 rows (index 0 to 8).
            for (int row = 0; row < 9; row++) {
                
                // Check if the file actually has a line to read.
                if (scanner.hasNextLine()) {
                    // Read the whole line. Ex: "5,3,4,6,7,8,9,1,2"
                    String line = scanner.nextLine(); 

                    // Split the text by the comma. 
                    // Now we have an array of strings: ["5", "3", "4", ...]
                    String[] numbers = line.split(",");

                    // Loop through the 9 numbers in this row
                    for (int col = 0; col < 9; col++) {
                        // The file has Strings ("5"), but we need Integers (5).
                        // Integer.parseInt() does this conversion.
                        // .trim() removes any accidental spaces (like " 5").
                        grid[row][col] = Integer.parseInt(numbers[col].trim());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // If the file isn't found, print a helpful message and stop the program.
            System.err.println("Error: The file " + filePath + " was not found.");
            System.exit(1); // 1 means "completed with error"
        }
    }

    // 4. The Getter.
    // The grid is private (Encapsulation). This method allows other classes 
    // (like the Workers) to ask "What number is at Row 0, Column 5?"
    public int getCell(int row, int col) {
        return grid[row][col];
    }
    
    // 5. A Helper to get the whole size (Good practice, though we know it's 9)
    public int getSize() {
        return 9;
    }
}