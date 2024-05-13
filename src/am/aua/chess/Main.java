package am.aua.chess;

import am.aua.chess.cli.ChessConsole;
import am.aua.chess.ui.ChessUI;

import javax.swing.*;

/**
 * This class contains the main entry point of the chess program.
 * It determines whether to launch the graphical user interface (GUI)
 * or the console-based user interface (CLI) based on the command line arguments provided.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new ChessUI();
                }
            });
        } else if (args.length == 1 && args[0].equals("-console")) {
            ChessConsole console = new ChessConsole();
            console.run();
        } else {
            System.out.println("Invalid command line arguments. Use -console for console UI or no arguments for GUI.");
        }
    }
}
