package com.team4.model.classes;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Color color, Space currentSpace) {
        super(color, currentSpace);
    }

    @Override
    public List<Space> getPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();
        // We gotta add some code here
        return possibleMoves;
    }
}