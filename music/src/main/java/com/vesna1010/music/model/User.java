package com.vesna1010.music.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.vesna1010.music.enums.Role;

@Entity
@Table(name = "USERS")
public class User {

	private Long id;
	private String name;
	private String email;
	private String password;
	private String confirmPassword;
	private boolean enabled = true;
	private Role role = Role.GUEST;

	public User() {
	}

	public User(String name, String email, String password, boolean enabled, Role role) {
		this(null, name, email, password, enabled, role);
	}

	public User(Long id, String name, String email, String password, boolean enabled, Role role) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "EMAIL", nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PASSWORD", nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Transient
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Column(name = "ENABLED")
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE", nullable = false)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
