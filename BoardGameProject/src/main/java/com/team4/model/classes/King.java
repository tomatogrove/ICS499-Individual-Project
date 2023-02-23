package com.team4.model.classes;

import java.util.ArrayList;
import java.util.List;
import com.team4.model.abstrct.Piece;
import jakarta.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class King extends Piece {

	@PrimaryKeyJoinColumn
	private Long kingID;

	@ManyToOne(targetEntity = Space.class)
	private Space currentSpace;

	public King() {
		super(Color.WHITE, null);
	}

	public King(Color color, Space currentSpace) {
		super(color, currentSpace);
	}

	@Override
	public List<Space> getPossibleMoves() {
		List<Space> possibleMoves = new ArrayList<>();
		// Logic to calculate moves
		return possibleMoves;
	}

	public Long getKingID() {
		return kingID;
	}

	public void setKingID(Long kingID) {
		this.kingID = kingID;
	}

	public Space getCurrentSpace() {
		return currentSpace;
	}

	public void setCurrentSpace(Space currentSpace) {
		this.currentSpace = currentSpace;
	}
}
