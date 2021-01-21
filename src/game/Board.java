package game;
import figures.CenterFrogCircle;
import figures.FrogGuard;
import figures.FrogLeader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/** @author Martin Petrov
 * Board класа, в който се съдържат съответно:
 *      1. игралните фигурки и техните позиции в ArrayList, както и
 *      размер, видимост, заглавие и действие при натискане бутона exit за самата форма, всичко поместено в конструктора на Board;
 *      2. метода paint, който извършва самата визуализация
 *
 */

public class Board extends JFrame implements MouseListener {

    //private ArrayList<Object> allFigures = new ArrayList<>();
    private Object[][] allFigures;

    public Board() {
        this.allFigures = new Object[5][5];

        this.allFigures[0][0] = (new FrogGuard(0,0));
        this.allFigures[0][1] = (new FrogGuard(0,1));
        this.allFigures[0][2] = (new FrogGuard(0,2));
        this.allFigures[0][3] = (new FrogGuard(0,3));
        this.allFigures[4][1] = (new FrogGuard(4,2));
        this.allFigures[4][2] = (new FrogGuard(4,3));
        this.allFigures[4][3] = (new FrogGuard(4,4));
        this.allFigures[4][4] = (new FrogGuard(4,4));
        this.allFigures[0][4] = (new FrogLeader(0,4));
        this.allFigures[4][0] = (new FrogLeader(4,1));


        this.setSize(500, 500);
        this.setVisible(true);
        this.setTitle("A game of war");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseListener(this);


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //проверка дали правилно се взимат координатите от дъската
        System.out.println(this.gameFigureCoordinates(e.getX()));
        System.out.println(this.gameFigureCoordinates(e.getY()));


        int row = this.gameFigureCoordinates(e.getY());
        int col = this.gameFigureCoordinates(e.getX());



        if(this.selectedFigure!=null){
            FrogGuard fg1 = (FrogGuard)this.selectedFigure;
            fg1.moveFigure(row, col);
            this.selectedFigure = null;
            this.repaint();
        }

        if(this.selectedFigure!=null){
            FrogLeader fl1 = (FrogLeader)this.selectedFigure;
            fl1.moveFigure(row, col);
            this.selectedFigure=null;
            this.repaint();
        }

        if(this.hasFigure(row,col)) {
            this.selectedFigure = this.getFigure(row, col);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

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


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                Tiles gameTiles = new Tiles(row, col);
                gameTiles.draw(g);

                RedTiles rt1 = new RedTiles(row, col);
                rt1.draw(g);

                BlackTiles bt1 = new BlackTiles(row, col);
                bt1.draw(g);

                GrayTiles gt1 = new GrayTiles(row, col);
                gt1.draw(g);

                FrogLeader fl1 = new FrogLeader(row, col);
                fl1.draw(g);

                FrogGuard fg1 = new FrogGuard(row, col);
                fg1.draw(g);

                CenterFrogCircle cfc1 = new CenterFrogCircle(row, col);
                cfc1.draw(g);
            }
        }
    }

    private Object selectedFigure;

    private boolean hasFigure(int row, int col){
        return this.getFigure(row, col) != null;
    }

    private Object getFigure(int row, int col){
        return this.allFigures[row][col];
    }

    private int gameFigureCoordinates(int coordinates) {
        return coordinates / Tiles.tileSize;
    }

}

