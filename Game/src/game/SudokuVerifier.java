package game;

import java.util.ArrayList;
import java.util.List;

public class SudokuVerifier {
    
    private final SudokuBoard board;

    public SudokuVerifier(SudokuBoard board) {
        this.board = board;
    }

    public ValidationResult verify(int mode) {
        ValidationResult result = new ValidationResult();
        
        switch (mode) {
            case 0:
                runSequential(result);
                break;
            case 3:
                runMode3(result);
                break;
            case 27:
                runMode27(result);
                break;
            default:
                break;
        }
        
        return result;
    }

    private void runSequential(ValidationResult result) {
        // Mode 0: Run directly in Main thread
        new RowChecker(board, result, 0, 9).run();
        new ColChecker(board, result, 0, 9).run();
        new BoxChecker(board, result, 0, 9).run();
    }

    private void runMode3(ValidationResult result) {
        // Mode 3: 3 Threads + Main
        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(new RowChecker(board, result, 0, 9)));
        threads.add(new Thread(new ColChecker(board, result, 0, 9)));
        threads.add(new Thread(new BoxChecker(board, result, 0, 9)));
        executeThreads(threads);
    }

    private void runMode27(ValidationResult result) {
        // Mode 27: 27 Threads
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            threads.add(new Thread(new RowChecker(board, result, i, i + 1)));
            threads.add(new Thread(new ColChecker(board, result, i, i + 1)));
            threads.add(new Thread(new BoxChecker(board, result, i, i + 1)));
        }
        executeThreads(threads);
    }

    private void executeThreads(List<Thread> threads) {
        for (Thread t : threads) t.start();
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}