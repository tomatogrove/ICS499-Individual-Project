package com.team6.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.team6.model.pieces.Piece;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long spaceID;

    private int x;
    private int y;

    @JsonManagedReference(value = "piece-space")
    @OneToOne(cascade = CascadeType.ALL)
    private Piece piece;

    @JsonBackReference(value = "space-board")
    @ManyToOne(cascade = CascadeType.ALL)
    private Board board;

    public Space() {

    }

    public Space(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Space(int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public long getSpaceID() {
        return spaceID;
    }

    public void setSpaceID(long spaceID) {
        this.spaceID = spaceID;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Transient
    public boolean isOccupied() {
        return this.piece != null;
    }


}
