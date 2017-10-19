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
                    }
                    grid.add(cells[i][j][k]);
                }
            }
            this.add(grid);
            grid.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder()));
        }
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
            selectNum.setLocation(e.getLocationOnScreen().x,e.getLocationOnScreen().y);
            selectNum.setCell((SudokuCell) e.getSource());
            selectNum.setVisible(true);
        }else{
            selectNum.dispose();
        }
        
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
