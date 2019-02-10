package games.board;

import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;

public class Board extends JPanel {
    
    private Cell[][] cells; 
    
    public Board(int rows, int columns, ActionListener ah) {
        cells = new Cell[rows][columns];
        this.setLayout(new GridLayout(rows, columns));
        for ( int r = 0; r < cells.length; r++ ) {
            
            for ( int c = 0; c < cells[r].length; c++ ) {
                cells[r][c] = new Cell(r,c); 
                this.add(cells[r][c]);
                cells[r][c].addActionListener(ah);
            }   
        
        }
        
    }
    
    public void setCell( Mark mark, int row, int column ) 
            throws IllegalArgumentException {
        
        if ( cells[row][column].getContent() == Mark.EMPTY ) {
            cells[row][column].setContent(mark);
        } else 
            throw new IllegalArgumentException
            ("Player already there!"); 
    
    } 
    
    public Cell getCell(int row, int column) {
        return cells[row][column];
    }
    
    public String toString() {
        
        StringBuilder str = new StringBuilder();
        
        for ( int r = 0; r < cells.length; r++ ){
            str.append("|");
            
            for ( int c = 0; c < cells[r].length; c++ ) {
               
                switch(cells[r][c].getContent()) {
                    case NOUGHT:
                        str.append("O");
                        break;
                    case CROSS:
                        str.append("X");
                        break;
                    case YELLOW:
                        str.append("Y");
                        break;
                    case RED:
                        str.append("R");
                        break;
                    case BLUE:
                        str.append("B");
                        break;
                    case GREEN:
                        str.append("G");
                        break;
                    case MAGENTA:
                        str.append("M");
                        break;
                    case ORANGE:
                        str.append("C"); //Set it to C for "cutie" as in the little sort-of-orange. 
                        break;           //( I really just think realistically it'd get confusing using "M" twice.) 
                    default: 
                        str.append(" ");
                }
                str.append("|");
            }
            str.append("\n");
        }
        return str.toString();
    }
}


