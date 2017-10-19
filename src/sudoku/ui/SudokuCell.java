/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

/**
 *
 * @author Kira
 */
public class SudokuCell extends JButton {
    public int i;
    public int j;
    public int k;
    	public SudokuCell(){
		this.setSize(60,60);
		Font font = new Font("",2,24);
		this.setFont(font);
		this.setBackground(new Color(255,255,255));
		this.setForeground(Color.BLUE);
        }
    public void recordLocation(int i,int j, int k){
        this.i=i;
        this.j=j;
        this.k=k;
    }
}
