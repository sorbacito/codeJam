package com.sorbac.leetcode.medium._36;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/valid-sudoku/">https://leetcode.com/problems/valid-sudoku/</a>
 */
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            if (!isValidRow(i, board)
                || !isValidColumn(i, board)) {
                return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!isValidSubbox(i, j , board)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidSubbox(int i, int j, char[][] board) {
        Set<Character> unique = new HashSet<>();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                char boardValue = board[3 * i + row][3 * j + col];
                if (boardValue != '.' && unique.contains(boardValue)) {
                    return false;
                }
                unique.add(boardValue);
            }
        }
        return true;
    }

    private boolean isValidRow(int row, char[][] board) {
        Set<Character> unique = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            char value = board[row][i];
            if (value != '.' && unique.contains(value)) {
                return false;
            }
            unique.add(value);
        }
        return true;
    }

    private boolean isValidColumn(int row, char[][] board) {
        Set<Character> unique = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            char boardValue = board[i][row];
            if (boardValue != '.' && unique.contains(boardValue)) {
                return false;
            }
            unique.add(boardValue);
        }
        return true;
    }
}
