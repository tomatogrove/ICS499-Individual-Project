package com.team6.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team6.model.enums.Color;
import com.team6.model.enums.Type;
import com.team6.model.pieces.Piece;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardID;

    @JsonManagedReference(value = "space-board")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Space> spaces;

    @JsonManagedReference(value = "piece-board")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Piece> pieces;

    @JsonBackReference(value = "board-chess")
    @OneToOne
    private Chess chess;

    public Board() {
        initializeSpacesAndPieces();
    }

    public Board(Chess chess) {
        this.chess = chess;
        initializeSpacesAndPieces();
    }

    private void initializeSpacesAndPieces() {
        spaces = new ArrayList<>();
        pieces = new ArrayList<>();

        Space space;
        Piece piece;

        for (int i = 8; i > 0; i--) {
            for (int j = 1; j < 9; j++) {
                space = new Space(j, i, this);
                if (i == 1 || i == 2 || i == 7 || i == 8) {
                    piece = Piece.createPiece(j, i, space, this);
                    space.setPiece(piece);
                    pieces.add(piece);
                }

                spaces.add(space);
            }
        }

    }

    private void assignPiece(Space space, Piece piece) {
        space.setPiece(piece);
        pieces.add(piece);
    }

    public Long getBoardID() {
        return boardID;
    }

    public void setBoardID(Long boardID) {
        this.boardID = boardID;
    }

    public List<Space> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<Space> spaces) {
        this.spaces = spaces;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Chess getChess() {
        return chess;
    }

    public void setChess(Chess chess) {
        this.chess = chess;
    }

    public List<Piece> findPieces(Color color, Type type) {
        List<Piece> pieces = new ArrayList<>();
        for (Piece piece : this.pieces) {
            if (piece.getType().equals(type) && piece.getColor().equals(color)) {
                pieces.add(piece);
            }
        }

        return pieces;
    }

    public List<Piece> findPiecesByColor(Color color) {
        List<Piece> pieces = new ArrayList<>();

        for (Piece piece : this.pieces) {
            if (piece.getColor().equals(color)) {
                pieces.add(piece);
            }
        }

        return pieces;
    }

    public Space findSpace(int x, int y) {
        if (x > 0 && x < 9 && y > 0 && y < 9) {
            for (Space space : spaces) {
                if (space.getX() == x && space.getY() == y) {
                    return space;
                }
            }
        }

        return null;
    }
}
