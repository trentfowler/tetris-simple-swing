package tetris;

import java.awt.*;

public class Board {

    private Color[][] squares;

    public Board() {
        squares = new Color[20][10];
        init();
    }

    public Color[][] get() { return squares; }

    private void init() {
        for (int row = 0; row < 20; row++)
            for (int col = 0; col < 10; col++)
                // Gray represents an empty square.
                squares[row][col] = Color.DARK_GRAY;
    }

    private boolean is_occupied(Point point) {
        return squares[point.y][point.x] == Color.DARK_GRAY ? false: true;
    }

    public boolean can_move(Piece piece, Tetris.Move direction) {
        if (direction == Tetris.Move.LEFT)
            for (Point xy_index: piece.occupies())
                if (xy_index.x == 0 || this.is_occupied(
                        new Point(xy_index.x - 1, xy_index.y)
                ))
                    return false;

        if (direction == Tetris.Move.RIGHT)
            for (Point xy_index: piece.occupies())
                if (xy_index.x == 9 || this.is_occupied(
                        new Point(xy_index.x + 1, xy_index.y)
                ))
                    return false;

        if (direction == Tetris.Move.DOWN)
            for (Point xy_index: piece.occupies())
                if (xy_index.y == 19 || this.is_occupied(
                        new Point(xy_index.x, xy_index.y + 1)
                ))
                    return false;

        if (direction == Tetris.Move.ROTATE) { }
            //TODO: Implement check for rotate.

        return true;
    }

    public void add(Piece piece) {
        for (Point xy_index: piece.occupies())
            if (xy_index.x >= 0 && xy_index.x < 10
                    && xy_index.y >= 0 && xy_index.y < 20)
                squares[xy_index.y][xy_index.x] = piece.color();
    }

    public boolean did_hit_bottom(Piece piece) {
        for (Point xy_index: piece.occupies())
            if (xy_index.y == 19 || this.is_occupied(
                    new Point(xy_index.x, xy_index.y + 1)
            ))
                return true;

        return false;
    }

    public void clear_lines() {
        for (int row = 0; row < 20; row++) {
            boolean can_clear = true;
            for (int col = 0; col < 10; col++)
                if (squares[row][col] == Color.DARK_GRAY)
                    can_clear = false;

            if (can_clear)
                for (int i = 19; i >= 0; i--)
                    for (int j = 0; j < 10; j++) {
                        if (i == 0)
                            squares[0][j] = Color.DARK_GRAY;
                        else if (i < row)
                            squares[i + 1][j] = squares[i][j];
                    }
        }

    }

    /**
     * Draws the game board showing the position
     * of the pieces.
     *
     * @return String representation of board.
     */
    @Override public String toString() {
        StringBuilder s = new StringBuilder();
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 10; col++) {
                s = squares[row][col] == Color.DARK_GRAY ?
                        s.append('-'): s.append('X');
            }
            s.append("\n");
        }
        return s.toString();
    }
}
