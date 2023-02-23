package com.team4.model.classes;

import java.util.ArrayList;
import java.util.List;
import com.team4.model.abstrct.Piece;
import jakarta.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Bishop extends Piece {

	@PrimaryKeyJoinColumn
	private Long BishopID;

	@ManyToOne(targetEntity = Space.class)
	private Space currentSpace;

	public Bishop() {
		super(Color.WHITE, null);
	}

	public Bishop(Color color, Space currentSpace) {
		super(color, currentSpace);
	}

	@Override
	public List<Space> getPossibleMoves() {
		List<Space> possibleMoves = new ArrayList<>();
		// Logic to calculate moves
		return possibleMoves;
	}

	public Long getBishopID() {
		return BishopID;
	}

	public void setBishopID(Long BishopID) {
		this.BishopID = BishopID;
	}

	public Space getCurrentSpace() {
		return currentSpace;
	}

	public void setCurrentSpace(Space currentSpace) {
		this.currentSpace = currentSpace;
	}
}
