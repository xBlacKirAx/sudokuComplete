/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Stack;
import javax.swing.BorderFactory;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import sudoku.src.SudokuMap;
/**
 *
 * @author Kira
 */
public class TestMainFrame extends JFrame implements MouseListener{
    SudokuCell[][][] cells;
//    int[][][] map=new int[9][3][3];
    private SelectNumFrame selectNum;
    private LinkedList<SudokuCell> blankCellList=new LinkedList<SudokuCell>();
    
    public TestMainFrame(){
        init();
        addCanvers();
    }
    private void addCanvers(){
        SudokuMap m=new SudokuMap();
        m.shuffleMap();
        cells=new SudokuCell[9][3][3];
        
            this.setLayout(new GridLayout(3,3));
        for (int i = 0; i < cells.length; i++) {
            JPanel grid=new JPanel();
            grid.setLayout(new GridLayout(3,3));
            for (int j = 0; j < cells[i].length; j++) {
                for (int k = 0; k < cells[i].length; k++) {
                    cells[i][j][k]=new SudokuCell();
                    grid.add(cells[i][j][k]);
                    
                    if(Math.random()*10>m.difficulty){
                        cells[i][j][k].setText(""+m.getMap()[i][j][k]);
                        cells[i][j][k].setEnabled(false);
                        cells[i][j][k].setForeground(Color.gray);
                    }else{
                        cells[i][j][k].addMouseListener(this);
                        cells[i][j][k].setBackground(Color.orange);
                        cells[i][j][k].recordLocation(i, j, k);
                        blankCellList.add(cells[i][j][k]);
                    }
                    grid.add(cells[i][j][k]);
                }
            }
            this.add(grid);
            grid.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
            
        }
        System.out.println(checkClear());
    }
    
    
    
    
    
    //检查是否通关
    private boolean checkClear(){
        boolean clear=true;
        for (int index = 0; index < blankCellList.size(); index++) {
            SudokuCell current=blankCellList.get(index);//用current来表示当前正在检查的node
            
            //第一部分先检查原先的空格是否全填满了。没填满回FALSE
            if(current.getText().isEmpty()){
                clear=false;
            }
            
            //第二部分检查current所在的小九宫格是否有重复，有重复回FALSE
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if(current.getText().trim().equals(cells[current.i][row][col].getText().trim()) && !(current.j==row&&current.k==col)){
                    clear= false;
                    }
                }
            }
            
            
            //第三部分检查current所在的行是否有重复，有重复回FALSE
            int start;
            if(current.i<3){
                start=0;
            }else if(current.i>=3 && current.i<6){
                start=3;
            }else{
                start=6;
            }
            for (int grid = start; grid < start+3; grid++) {
                for (int col = 0; col < 3; col++) {
                    if(current.getText().trim().equals(cells[grid][current.j][col].getText().trim()) && !(current.i==grid&&current.k==col)){
                            clear= false;
                    }
                }
            }
            
            
            //第四部分检查current所在的列是否有重复，有重复回FALSE
            if(current.i%3==0){
                start=0;
            }else if(current.i%3==1){
                start=1;
            }else if(current.i%3==2){
                start=2;
            }
            for (int grid = start; grid < 9; grid+=3) {
                for (int row = 0; row < 3; row++) {
                    if(current.getText().trim().equals(cells[grid][row][current.k].getText().trim()) && !(current.i==grid&&current.j==row)){
                        System.out.println(cells[grid][row][current.k].getText().trim());
                            clear= false;
                    }
                }
            }
            
        }
        return clear;
    }
    
    
    
    
    //界面基础设定
    private void init(){
        this.setSize(600,600);
        this.setLocation(500,50);
        this.setTitle("Sudoku");
        this.setResizable(false);
        this.setLayout(new GridLayout(3,3));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    
    
    
    
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            if(selectNum!=null){
            selectNum.dispose();
        }
            selectNum=new SelectNumFrame();
            selectNum.setModal(true);
            selectNum.setLocation(e.getLocationOnScreen().x,e.getLocationOnScreen().y);
            selectNum.setCell((SudokuCell) e.getSource());
            selectNum.setVisible(true);
        }else{
            selectNum.dispose();
        }
        System.out.println(checkClear());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
