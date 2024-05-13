package am.aua.chess.ui;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * This class represents a single square on a chessboard. It extends JButton
 * to allow for user interaction. The squares can be either light or dark color
 * and can hold a piece represented by an ImageIcon.
 */
public class BoardSquare extends JButton {
    private static final Color LIGHT_COLOR = new Color(118, 150, 86);
    private static final Color DARK_COLOR = new Color(238, 238, 210);
    private static final Color HIGHLIGHT_COLOR = Color.RED;
    private int x;
    private int y;
    private Color squareColor;

    /**
     * Creates a new BoardSquare with specified color and position.
     *
     * @param isLight whether the square should be a light color
     * @param x the x-coordinate of this square on the board
     * @param y the y-coordinate of this square on the board
     */
    public BoardSquare(boolean isLight, int x, int y) {
        this.x = x;
        this.y = y;
        this.squareColor = isLight ? DARK_COLOR : LIGHT_COLOR;
        this.setBackground(squareColor);
    }

    /**
     * Gets the coordinates of this square on the chessboard.
     *
     * @return an array with the x and y coordinates of the square
     */
    public int[] getCoordinates() {
        return new int[]{x, y};
    }

    /**
     * Sets the chess piece icon for this square. If the piece is null, it clears the icon.
     *
     * @param piece a string representation of the piece, which maps to an image file
     */
    public void setPiece(String piece) {
        if (piece == null) {
            this.setIcon(null);
        } else {
            String filename = mapPieceToFilename(piece);
            String path = "C:\\Users\\HP\\Desktop\\OOP Chess\\" + filename;
            ImageIcon icon = new ImageIcon(path);
            if (icon.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) {
                System.out.println("Failed to load image from: " + path);
                this.setIcon(null);
            } else {
                this.setIcon(icon);
            }
        }
    }

    /**
     * Maps a string representing a chess piece to its corresponding image file name.
     *
     * @param piece a string representation of the piece
     * @return the file name of the corresponding image
     */
    public String mapPieceToFilename(String piece) {
        switch (piece) {
            case "r": return "RookB.png";    // Black rook
            case "n": return "KnightB.png";  // Black knight
            case "b": return "BishopB.png";  // Black bishop
            case "q": return "QueenB.png";   // Black queen
            case "k": return "KingB.png";    // Black king
            case "p": return "PawnB.png";    // Black pawn
            case "R": return "RookW.png";    // White rook
            case "N": return "KnightW.png";  // White knight
            case "B": return "BishopW.png";  // White bishop
            case "Q": return "QueenW.png";   // White queen
            case "K": return "KingW.png";    // White king
            case "P": return "PawnW.png";    // White pawn
            default: return null;            // Return null or some default image if the piece character is unrecognized
        }
    }

    /**
     * Sets or clears the highlight on this square.
     *
     * @param highlight true to set highlight, false to clear
     */
    public void setHighlight(boolean highlight) {
        if (highlight) {
            this.setBackground(HIGHLIGHT_COLOR);
        } else {
            this.setBackground(squareColor);
        }
    }
}
