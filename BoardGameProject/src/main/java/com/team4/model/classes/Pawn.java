package com.team4.model.classes;

import java.util.ArrayList;
import java.util.List;
import com.team4.model.abstrct.Piece;
import jakarta.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Pawn extends Piece {

	@PrimaryKeyJoinColumn
	private Long PawnID;

	@ManyToOne(targetEntity = Space.class)
	private Space currentSpace;

	public Pawn() {
		super(Color.WHITE, null);
	}

	public Pawn(Color color, Space currentSpace) {
		super(color, currentSpace);
	}

	@Override
	public List<Space> getPossibleMoves() {
		List<Space> possibleMoves = new ArrayList<>();
		// Logic to calculate moves
		return possibleMoves;
	}

	public Long getPawnID() {
		return PawnID;
	}

	public void setPawnID(Long PawnID) {
		this.PawnID = PawnID;
	}

	public Space getCurrentSpace() {
		return currentSpace;
	}

	public void setCurrentSpace(Space currentSpace) {
		this.currentSpace = currentSpace;
	}
}
