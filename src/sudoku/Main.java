/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import sudoku.src.SudokuMap;
import sudoku.ui.TestMainFrame;

/**
 *
 * @author Kira
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SudokuMap s=new SudokuMap();
        s.getNewShuffleArray();
        TestMainFrame mainFrame=new TestMainFrame();
    }
    
}
