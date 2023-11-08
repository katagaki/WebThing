package com.kaminari.WebThing;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {
	@Id @Column(name = "Username") private String username;
	@Column(name = "PasswordHash") private String passwordHash;
	@Column(name = "Name") private String name;
	@Column(name = "LastSignIn") private Date lastSignIn;

	public User() {
		this.username = "";
		this.passwordHash = "Z-@";
		this.name = "";
	}

	public User(String username, String password, String name) {
		this.username = username;
		this.passwordHash = User.passwordHash(password);
		this.name = name;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public String getName() {
		return this.name;
	}

	public Date getLastSignIn() {
		return this.lastSignIn;
	}

	public void setPassword(String password) {
		this.passwordHash = User.passwordHash(password);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastSignIn(Date lastSignIn) {
		this.lastSignIn = lastSignIn;
	}

	public static String passwordHash(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashedPasswordBytes = md.digest(password.getBytes("UTF-8"));
			return String.format("%032X", new BigInteger(1, hashedPasswordBytes));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}  catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "Z-@";
	}
}
