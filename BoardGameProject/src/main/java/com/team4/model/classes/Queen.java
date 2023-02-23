package com.team4.model.classes;

import java.util.ArrayList;
import java.util.List;
import com.team4.model.abstrct.Piece;
import jakarta.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Queen extends Piece {

	@PrimaryKeyJoinColumn
	private Long QueenID;

	@ManyToOne(targetEntity = Space.class)
	private Space currentSpace;

	public Queen() {
		super(Color.WHITE, null);
	}

	public Queen(Color color, Space currentSpace) {
		super(color, currentSpace);
	}

	@Override
	public List<Space> getPossibleMoves() {
		List<Space> possibleMoves = new ArrayList<>();
		// Logic to calculate moves
		return possibleMoves;
	}

	public Long getQueenID() {
		return QueenID;
	}

	public void setQueenID(Long QueenID) {
		this.QueenID = QueenID;
	}

	public Space getCurrentSpace() {
		return currentSpace;
	}

	public void setCurrentSpace(Space currentSpace) {
		this.currentSpace = currentSpace;
	}
}
