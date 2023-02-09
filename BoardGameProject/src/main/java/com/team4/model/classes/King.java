package com.boardgame.model;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(Color color, Square currentSquare) {
        super(color, currentSquare);
    }

    @Override
    public List<Square> getPossibleMoves() {
        List<Square> possibleMoves = new ArrayList<>();
        // We gotta add some code here
        return possibleMoves;
    }
}