package com.example;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
    private final boolean color;
    private BufferedImage img;
    
    public Piece(boolean isWhite, String img_file) {
        this.color = isWhite;
         
        try {
            if (this.img == null) {
                this.img = ImageIO.read(new File(System.getProperty("user.dir")+img_file));
            }
          } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
          }
    }
    
    

    
    public boolean getColor() {
        return color;
    }
    
    public Image getImage() {
        return img;
    }
    
    public void draw(Graphics g, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();
        
        g.drawImage(this.img, x, y, null);
    }
    
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    public ArrayList<Square> getControlledSquares(Square [][]board, Square start) {
        ArrayList<Square> moves=new ArrayList<>();
        //check up-left, up-right, down-left, down-right
        int [] drow = {-1, -1, 1, 1};
        int [] dcol = {-1, 1, -1, 1};
        for(int i=0; i<drow.length; i++){
                    //in bounds and there's a piece there of the opposotie color
                    if(start.getRow()+drow[i]>=0 && start.getRow()+drow[i]<8&&
                    start.getCol()+dcol[i]>=0&&start.getCol()+dcol[i]<8){
                        moves.add(board[start.getRow()+drow[i]][start.getCol()+dcol[i]]);
                    }
                             
                        
                        
                    }
     return moves;
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    public ArrayList<Square> getLegalMoves(Board board, Square start){
        ArrayList<Square> moves=new ArrayList<>();
        for(Square [] row: board.getSquareArray()){
            for(Square s: row){
                if(s.getColor()==this.color){
                    if(!s.isOccupied()){
                        moves.add(s);
                        System.out.println("Moves size: " + moves.size());
                    }
                }
            }
        }

        int [] drow = {-1, -1, 1, 1};
        int [] dcol = {-1, 1, -1, 1};
        for(int i=0; i<drow.length; i++){
                    //in bounds and there's a piece there of the opposotie color
                    if(start.getRow()+drow[i]>=0 && start.getRow()+drow[i]<8&&
                    start.getCol()+dcol[i]>=0&&start.getCol()+dcol[i]<8 &&
                    board.getSquareArray()[start.getRow()+drow[i]][start.getCol()+dcol[i]].isOccupied() && 
                    board.getSquareArray()[start.getRow()+drow[i]][start.getCol()+dcol[i]].getOccupyingPiece().getColor()!=color){
                        moves.add(board.getSquareArray()[start.getRow()+drow[i]][start.getCol()+dcol[i]]);
                    }
                             
                        
                        
                    }
        
                
              
            
            
        
    
    	return moves;
    }
}