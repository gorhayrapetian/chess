package am.aua.chess.ui;

import am.aua.chess.core.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ChessUI class provides the graphical user interface for the chess game.
 * It extends JFrame and handles the display and interaction of the chessboard
 * and pieces to the user.
 */
public class ChessUI extends JFrame {
    private BoardSquare[][] board;
    private Chess current;
    private Position origin;

    /**
     * Constructs a new ChessUI frame and initializes the game state.
     */
    public ChessUI() {
        initializeGame();
        setupUI();
        setVisible(true);
    }

    /**
     * Initializes the game by creating a new Chess object and setting up the board.
     */
    private void initializeGame() {
        try {
            current = new Chess();
        } catch (IllegalArrangementException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Initialization Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        board = new BoardSquare[Chess.BOARD_RANKS][Chess.BOARD_FILES];
    }

    /**
     * Sets up the user interface including the chessboard panel.
     */
    private void setupUI() {
        setSize(700, 700);
        setTitle("Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = createBoardPanel();
        panel.setPreferredSize(new Dimension(700, 700));

        JPanel container = new JPanel(new GridBagLayout());
        container.add(panel, new GridBagConstraints());

        add(container, BorderLayout.CENTER);

        setResizable(true);
        updatePieces();
    }

    /**
     * Creates a panel representing the chessboard with individual squares.
     *
     * @return The JPanel containing the chessboard layout.
     */
    private JPanel createBoardPanel() {
        JPanel panel = new JPanel(new GridLayout(Chess.BOARD_RANKS, Chess.BOARD_FILES));
        panel.setPreferredSize(new Dimension(700, 700));
        boolean lightSquare = true;

        for (int i = 0; i < Chess.BOARD_RANKS; i++) {
            for (int j = 0; j < Chess.BOARD_FILES; j++) {
                board[i][j] = new BoardSquare(lightSquare, i, j);
                board[i][j].setPreferredSize(new Dimension(80, 80));
                panel.add(board[i][j]);
                lightSquare = !lightSquare;

                board[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        BoardSquare clicked = (BoardSquare) e.getSource();
                        int[] coordinates = clicked.getCoordinates();
                        boardClicked(coordinates);
                    }
                });
            }
            lightSquare = !lightSquare;
        }
        return panel;
    }

    /**
     * Handles the event when a board square is clicked.
     *
     * @param coordinates The (x,y) coordinates of the clicked square on the chessboard.
     */
    private void boardClicked(int[] coordinates) {
        if (origin == null) {
            origin = new Position(coordinates[0], coordinates[1]);
            Piece piece = current.getPieceAt(origin);
            if (piece != null && piece.getPieceColor() == current.getTurn()) {
                for (Position pos : current.reachableFrom(origin)) {
                    board[pos.getRank()][pos.getFile()].setHighlight(true);
                }
            } else {
                origin = null;
            }
        } else {
            Position destination = new Position(coordinates[0], coordinates[1]);
            if (current.performMove(new Move(origin, destination))) {
                updatePieces();
            }
            resetHighlights();
            origin = null;
        }
    }

    /**
     * Resets the highlights on the chessboard to their original state.
     */
    private void resetHighlights() {
        for (int i = 0; i < Chess.BOARD_RANKS; i++) {
            for (int j = 0; j < Chess.BOARD_FILES; j++) {
                board[i][j].setHighlight(false);
            }
        }
    }

    /**
     * Updates the board by placing the correct piece icons on the squares.
     */
    private void updatePieces() {
        for (int i = 0; i < Chess.BOARD_RANKS; i++) {
            for (int j = 0; j < Chess.BOARD_FILES; j++) {
                Piece piece = current.getPieceAt(new Position(i, j));
                board[i][j].setPiece(piece == null ? null : piece.toString());
            }
        }
    }
}
