package com.team4.model.util;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.team4.model.Chess;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class UserAccount {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long userAccountID;
	
	@Column(unique = true)
	private String username;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	@JsonBackReference(value="useraccount-session")
	@OneToOne()
	private Session session;

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "chessID")
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "ChessUserAccount",
			joinColumns = @JoinColumn(name = "userAccountID"),
			inverseJoinColumns = @JoinColumn(name = "chessID"))
	private List<Chess> chessList;
	
	public UserAccount() {
		chessList = new ArrayList<>();
	}
	
	public UserAccount(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
		chessList = new ArrayList<>();
	}
	
	public Long getUserAccountID() { return userAccountID; }
	public void setUserAccountID(Long userAccountID) { this.userAccountID = userAccountID; }
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	public Session getSession() { return session; }

	public void setSession(Session session) { this.session = session; }

	public List<Chess> getChessList() {
		return chessList;
	}

	public void setChessList(List<Chess> chessList) {
		this.chessList = chessList;
	}

	public List<Chess> findChessListByStatus(Chess.Status status) {
		List<Chess> chessWithStatus = new ArrayList<>();

		for (Chess chess : chessList) {
			if (chess.getStatus().equals(status)) {
				chessWithStatus.add(chess);
			}
		}

		return chessWithStatus;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserAccount that = (UserAccount) o;
		return Objects.equals(userAccountID, that.userAccountID);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userAccountID);
	}

	public List<Chess> addToChessList(Chess game) {
		chessList.add(game);
		return chessList;
	}
}
