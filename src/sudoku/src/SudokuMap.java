/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.src;

import java.util.Random;
import sudoku.ui.TestMainFrame;

/**
 *
 * @author Kira
 */
public class SudokuMap {

    private static int[][][] map = {
        {{5, 9, 1}, {4, 3, 7}, {2, 8, 6}},
        {{3, 2, 7}, {6, 9, 8}, {1, 4, 5}},
        {{6, 4, 8}, {1, 5, 2}, {7, 9, 3}},
        {{1, 7, 5}, {3, 4, 9}, {6, 2, 8}},
        {{2, 3, 9}, {8, 1, 6}, {5, 7, 4}},
        {{4, 8, 6}, {2, 7, 5}, {9, 3, 1}},
        {{7, 5, 2}, {9, 6, 3}, {8, 1, 4}},
        {{9, 6, 3}, {4, 8, 1}, {7, 5, 2}},
        {{8, 1, 4}, {5, 2, 7}, {3, 6, 9}},};
    public double difficulty=0.5;

  
    public int[][][] getMap() {
        return map;
    }
    public int[] getNewShuffleArray(){
        int[] shuffleNumber={1,2,3,4,5,6,7,8,9};
        int[] shuffle=new int[10];
        
        Random random=new Random();
        
        int end=8;
        for (int i = 0; i < shuffleNumber.length; i++) {
            int num=random.nextInt(end+1);
            shuffle[i]=shuffleNumber[num];
            shuffleNumber[num]=shuffleNumber[end];
            end--;
        }
        shuffle[9]=shuffle[0];
//        for (int i = 0; i < shuffle.length; i++) {
//            System.out.println(shuffle[i]);
//        }
        return shuffle;
    }
    
    //用shuffle数组来替换所有9个小九宫格内的数字
    public void shuffleMap() {
        int[] shuffle=getNewShuffleArray();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                for (int k = 0; k < map[i].length; k++) {
                    
                    for (int shuffleCount = 0; shuffleCount < shuffle.length-1; shuffleCount++) {
                        if(map[i][j][k]==shuffle[shuffleCount]){
                            map[i][j][k]=shuffle[shuffleCount+1];
                            break;
                        }
                        
                    }
                }
            }
        }
        
    }

    //显示当前MAP 仅用于TEST
    private void showMap() {
        TestMainFrame mainFrame = new TestMainFrame();
        mainFrame.setVisible(true);
    }
}
