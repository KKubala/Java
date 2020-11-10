package rubik;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BoardGUI {

    private JPanel[][] blocks;
    private Board board;
    private JPanel boardPanel;
    private JLabel movesLabel;

    private Random random = new Random();
    private int clickNum = 0;

    public BoardGUI(int boardSize) {
        board = new Board(boardSize);
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(board.getBoardSize()+2, board.getBoardSize()+2));
        
        // Fake button
        placeholder();

        //Up Buttons
        for (int i=0; i<board.getBoardSize(); i++){
            addButton('u', "^", i);
        }
        // Fake button
        placeholder();
        
        // Color Fields
        blocks = new JPanel[board.getBoardSize()][board.getBoardSize()];
        for (int i = 0; i < board.getBoardSize(); ++i) {

            //left buttons
            addButton('l', "<", i);

            for (int j = 0; j < board.getBoardSize(); ++j) {
                JPanel rect = new JPanel();
                rect.setPreferredSize(new Dimension(80, 40));
                blocks[i][j] = rect;
                boardPanel.add(rect);
            }

            //right buttons
            addButton('r', ">", i);
        }

        // Fake button
        placeholder();

        //Up Buttons
        for (int i=0; i<board.getBoardSize(); i++){
            addButton('d', "v", i);
        }

        // Fake button
        placeholder();

        movesLabel = new JLabel(" ");
        movesLabel.setHorizontalAlignment(JLabel.RIGHT);
        refreshAll();
    }

    private void placeholder(){
        Color black = new Color(0,0,0);
        JPanel block = new JPanel();
        block.setPreferredSize(new Dimension(80, 40));
        boardPanel.add(block);
        block.setBackground(black);
    }

    private void addButton(char direct, String text, int c)
    {
        JButton button = new JButton();
        button.addActionListener(new ButtonListener(direct, c));
        button.setPreferredSize(new Dimension(80, 40));
        boardPanel.add(button);
        button.setText(text);

    }

    private void refreshAll() {
        for(int x=0; x<board.getBoardSize(); x++){
            for (int y=0; y<board.getBoardSize(); y++){
                JPanel button = blocks[x][y];
                Field field = board.get(x, y);
                button.setBackground(field.getColor());
            }
        }
        movesLabel.setText(clickNum + " moves");
    }

    private void restartGame(){
        board = new Board(board.getBoardSize());
        clickNum = 0;
        refreshAll();
    }

    public JPanel getBoardPanel() {
        return boardPanel;
    }

    class ButtonListener implements ActionListener {

        private char direct;
        private int s;

        public ButtonListener(char direct, int s) {
            this.direct = direct;
            this.s = s;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            clickNum++;
            movesLabel.setText(clickNum + " moves");

            if(direct=='u'){
                Color firstColor = board.get(0,s).getColor();
                for (int i=0; i<board.getBoardSize(); i++){
                    Color color = board.get((i+1)%board.getBoardSize(),s).getColor();
                    board.get(i,s).setColor(color);
                }
                board.get(board.getBoardSize()-1,s).setColor(firstColor);
            }

            if(direct=='l'){
                Color firstColor = board.get(s,0).getColor();
                for (int i=0; i<board.getBoardSize(); i++){
                    Color color = board.get(s, (i+1)%board.getBoardSize()).getColor();
                    board.get(s,i).setColor(color);
                }
                board.get(s, board.getBoardSize()-1).setColor(firstColor);
            }

            if(direct=='r'){
                Color lastColor = board.get(s,board.getBoardSize()-1).getColor();
                for (int i=board.getBoardSize()-1; i>-1; i--){
                    Color color = board.get(s, (i-1+board.getBoardSize())%board.getBoardSize()).getColor();
                    board.get(s,i).setColor(color);
                }
                board.get(s, 0).setColor(lastColor);
            }

            if(direct=='d'){
                Color lastColor = board.get(board.getBoardSize()-1,s).getColor();
                for (int i=board.getBoardSize()-1; i>-1; i--){
                    Color color = board.get((i-1+board.getBoardSize())%board.getBoardSize(),s).getColor();
                    board.get(i,s).setColor(color);
                }
                board.get(0,s).setColor(lastColor);
            }
            refreshAll();
            if (board.isOver()) {
                JOptionPane.showMessageDialog(boardPanel, "You have won in " + clickNum + " moves. ", "Congrats!",
                        JOptionPane.PLAIN_MESSAGE);
                        restartGame();                        
            }
        }
    }

    public JLabel getMovesLabel() {
        return movesLabel;
    }
}