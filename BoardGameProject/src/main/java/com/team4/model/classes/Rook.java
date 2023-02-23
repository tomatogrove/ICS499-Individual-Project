package com.team4.model.classes;

import java.util.ArrayList;
import java.util.List;
import com.team4.model.abstrct.Piece;
import jakarta.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Rook extends Piece {

	@PrimaryKeyJoinColumn
	private Long RookID;

	@ManyToOne(targetEntity = Space.class)
	private Space currentSpace;

	public Rook() {
		super(Color.WHITE, null);
	}

	public Rook(Color color, Space currentSpace) {
		super(color, currentSpace);
	}

	@Override
	public List<Space> getPossibleMoves() {
		List<Space> possibleMoves = new ArrayList<>();
		// Logic to calculate moves
		return possibleMoves;
	}

	public Long getRookID() {
		return RookID;
	}

	public void setRookID(Long RookID) {
		this.RookID = RookID;
	}

	public Space getCurrentSpace() {
		return currentSpace;
	}

	public void setCurrentSpace(Space currentSpace) {
		this.currentSpace = currentSpace;
	}
}
