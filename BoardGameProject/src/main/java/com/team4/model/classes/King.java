package com.team4.model.classes;

import java.util.ArrayList;
import java.util.List;

import com.team4.model.abstrct.Piece;

public class King extends Piece {
    public King(Color color, Space currentSpace) {
        super(color, currentSpace);
    }

    @Override
    public List<Space> getPossibleMoves() {
        List<Space> possibleMoves = new ArrayList<>();
        // We gotta add some code here
        return possibleMoves;
    }
}