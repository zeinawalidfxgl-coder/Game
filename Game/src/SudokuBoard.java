/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class SudokuBoard {
   private final int[][] board;

    public SudokuBoard(int[][] board) {
        this.board = board;
    }
    public int getCell(int r, int c) {
        return board[r][c];
    }
}
