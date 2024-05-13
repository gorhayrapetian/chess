package am.aua.chess.core;

import java.util.ArrayList;
import java.util.Arrays;

public class Queen extends Piece {
    public Queen() {
        super();
    }

    public Queen(Chess.PieceColor color) {
        super(color);
    }

    public String toString() {
        if (this.getPieceColor() == Chess.PieceColor.WHITE)
            return "Q";
        else
            return "q";
    }

    public Position[] allDestinations(Chess chess, Position p) {
        ArrayList<Position> result = new ArrayList<>();

        //result.addAll(Arrays.asList(Rook.reachablePositions(chess, p)));
        //result.addAll(Arrays.asList(Rook.reachablePositions(chess, p)));
        //Lists have not been covered, so we will iterate through and add these positions manually

        Position[] rookPositions = Rook.reachablePositions(chess, p);
        for (Position rp : rookPositions) result.add(rp);

        Position[] bishopPositions = Bishop.reachablePositions(chess, p);
        for (Position bp : bishopPositions) result.add(bp);

        return result.toArray(new Position[]{});
    }
}
