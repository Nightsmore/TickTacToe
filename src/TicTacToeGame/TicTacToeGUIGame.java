package TicTacToeGame;

import games.board.*;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import static javax.swing.JFrame.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class TicTacToeGUIGame extends JFrame  {
    
    private final Board gb;
    private int turn;
    
  
    
    private void takeTurn(Cell c) { 
        Outcome gR;
        //gR for gameResult
        
        
    
        Mark curMark = (turn++ % 2 == 0 )?Mark.NOUGHT
                : Mark.CROSS;
        gb.setCell(curMark, c.getRow(), c.getColumn());
        
        try {
           gR = getOutcome();  
            switch (gR) {
                case PLAYER1_WIN:
                    JOptionPane.showMessageDialog (this, "Player 1 wins!");
                    break;
                case PLAYER2_WIN:
                    JOptionPane.showMessageDialog(this, "Player 2 wins!");
                    break;
                case CONTINUE:
                    System.out.println("Doing nothing");
                    break;
                case TIE:
                    JOptionPane.showMessageDialog(this, "Player tie.");
                    break;
                default:
                    break;
            }
            
        } catch (Exception exc) {
            System.err.println(exc);
        }  
        
    }
    
    private TicTacToeGUIGame() 
    {
        gb = new Board(3,3, (ActionEvent ae) -> {
            Cell c = (Cell) ae.getSource();
            takeTurn(c);
        }); 
    
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setTitle("TIC-TAC-TOE");
    this.setSize(300, 300);
    this.add(gb);
    this.setVisible(true);
    
    
    }
    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {new TicTacToeGUIGame(); }
        });
    }
    
    public Outcome getOutcome(){
        
        int boardLength = 3;
        int noughtWin = 0; 
        int crossWin = 0;
        
        
        //Checking for a win in rows
        for (int r = 0; r < 3; r++) {
           
            for (int c = 0; c < 3; c++) {
                  switch (gb.getCell(r, c).getContent()) {
                      case NOUGHT: 
                          System.out.println("Nought on row " + r + ", column " + c );
                          noughtWin++;
                          if (noughtWin >= boardLength)
                              return(Outcome.PLAYER1_WIN);
                          break;
                          
                      case CROSS: 
                          System.out.println("Cross on row " + r + ", column " + c );
                          crossWin++;
                          if (crossWin >= boardLength)
                              return(Outcome.PLAYER2_WIN);
                          break;
                      default: 
                          break;

                  }
                  
            }
            noughtWin = 0; 
            crossWin = 0;          
        }
        
        for (int c = 0; c < 3; c++) {
            for (int r = 0; r < 3; r++) {
            
            switch(gb.getCell(r,c).getContent()) {
                
                case NOUGHT: 
                    noughtWin++; 
                    if (noughtWin == boardLength)
                        return(Outcome.PLAYER1_WIN);
                    break;
                
                case CROSS: 
                    crossWin++; 
                    if (crossWin == boardLength) 
                        return(Outcome.PLAYER2_WIN);
                    break;
                
                default:
                    break;
            }
        }
        crossWin = 0;
        noughtWin = 0;
        }
        
        //checking across the board
        for (int i = 0; i < 3; i++) {
            switch (gb.getCell(i,i).getContent()) {
                case NOUGHT: 
                    noughtWin++;
                    if (noughtWin >= boardLength)
                        return(Outcome.PLAYER1_WIN);
                    break;
                case CROSS:
                    crossWin++;
                    if (crossWin >= boardLength) 
                        return(Outcome.PLAYER2_WIN);
                    break;
                default: 
                    break;
            }
        } noughtWin = 0; 
        crossWin = 0;
        
        int j = 0; 
        int k = 2;
        for (int i = 0 ; i < 3; i++) {
            switch(gb.getCell(k,j).getContent()) {
                    case NOUGHT: 
                    noughtWin++;
                    if (noughtWin >= boardLength)
                        return(Outcome.PLAYER1_WIN);
                    break;
                case CROSS:
                    crossWin++;
                    if (crossWin >= boardLength) 
                        return(Outcome.PLAYER2_WIN);
                    break;
                default: 
                    break;
            }
            j++;
            k--;
        }
                
        if(containsEmpty()) 
            return Outcome.CONTINUE;
       
       return Outcome.TIE;
}
    
    Boolean containsEmpty() {
        Boolean empty = false;
       
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++ ){
                switch(gb.getCell(r, c).getContent()) {
                    case EMPTY: 
                        empty = true;
                        break;
                    default: 
                }
            }
        
        }
        
    
   
    return(empty);
    }
    

    
}
