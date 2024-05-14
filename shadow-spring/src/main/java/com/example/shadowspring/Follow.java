package com.example.shadowspring;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "follower")
public class Follow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String followby;
	private String followto;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFollowby() {
		return followby;
	}

	public void setFollowby(String followby) {
		this.followby = followby;
	}

	public String getFollowto() {
		return followto;
	}

	public void setFollowto(String followto) {
		this.followto = followto;
	}
}
