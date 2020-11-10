package rubik;

import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class Board {

    private Field[][] board;
    private final int boardSize;
    private ArrayList<Color> colors;
    private Random random = new Random();


    public Board(int boardSize) {
        this.boardSize = boardSize;
        board = new Field[this.boardSize][this.boardSize];
        for (int i = 0; i < this.boardSize; ++i) {
            for (int j = 0; j < this.boardSize; ++j) {
                board[i][j] = new Field();
            }
        }
        // Setting colors
        colors = new ArrayList<>();
        for (int i = 0; i < this.boardSize; ++i) {
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            colors.add(color);
        }
        Collections.shuffle(colors);

        for (int i = 0; i < this.boardSize; ++i) {
            Color color = colors.remove(colors.size() - 1);
            for (int j=0; j<this.boardSize;){
                int x = random.nextInt(this.boardSize);
                int y = random.nextInt(this.boardSize);
                if (board[x][y].getColor() == null) {
                    board[x][y].setColor(color);
                    j++;
                }
            }
        }

    }
    
    public boolean isOver() {
        boolean rows = true;
        boolean colums = true;
        for (int i = 0; i < boardSize; i++) {
            Color color = board[i][0].getColor();
            for (int j = 1; j < boardSize; j++) {
                if (board[i][j].getColor() != color) {
                    colums =  false;
                }
            }
        }

        for (int i = 0; i < boardSize; i++) {
            Color color = board[0][i].getColor();
            for (int j = 1; j < boardSize; j++) {
                if (board[j][i].getColor() != color) {
                    rows = false;
                }
            }
        }
        return rows || colums;
    }
    
    public Field get(int x, int y) {
        return board[x][y];
    }

    public int getBoardSize() {
        return boardSize;
    }

}
