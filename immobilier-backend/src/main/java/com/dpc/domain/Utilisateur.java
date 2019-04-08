package com.dpc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity



public class Utilisateur implements UserDetails, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private    Long id ;
	
	
	
	@Column(name = "last_password_reset_date")
	private Date lastPasswordResetDate;
	
	@Column(name = "blocked_date")
	private Date blockedate;
	@Column(name = "deblocked_date")
	private Date deblockedate;
	@ManyToOne
	private Authority authorities;

	@Column(name = "enabled")
	private boolean enabled;
	
	@Column(name = "username", unique = true)
	private String username;
	
	@Column(unique = true, nullable = false)
	private String password;

;
	@Column(name = "profil")
	private String profil;
	 @Column(name = "created_on")
	 @Temporal(TemporalType.TIMESTAMP)
	private Date createdOn = new Date();
//	 private String createdOn;
	 
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Client client;
	 
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "artisan_id")
	private Artisant artisant; 


	

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<Authority> auth = new ArrayList<Authority>();
		auth.add(authorities);
		return auth;
	}
	
	
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}


	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}


	public Date getBlockedate() {
		return blockedate;
	}


	public void setBlockedate(Date blockedate) {
		this.blockedate = blockedate;
	}


	public Date getDeblockedate() {
		return deblockedate;
	}


	public void setDeblockedate(Date deblockedate) {
		this.deblockedate = deblockedate;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getProfil() {
		return profil;
	}


	public void setProfil(String profil) {
		this.profil = profil;
	}


	

	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public Artisant getArtisant() {
		return artisant;
	}


	public void setArtisant(Artisant artisant) {
		this.artisant = artisant;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public void setAuthorities(Authority authorities) {
		this.authorities = authorities;
	}


	public Utilisateur() {
		super();
	}


	public Utilisateur(Date lastPasswordResetDate, Authority authorities, boolean enabled, String username,
			String password, String profil, Date createdOn) {
		super();
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.authorities = authorities;
		this.enabled = enabled;
		this.username = username;
		this.password = password;
		this.profil = profil;
		this.createdOn = createdOn;
	}


	public Utilisateur(Long id, Date lastPasswordResetDate, Date blockedate, Date deblockedate, Authority authorities,
			boolean enabled, String username, String password, String profil, Date createdOn, Client client) {
		super();
		this.id = id;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.blockedate = blockedate;
		this.deblockedate = deblockedate;
		this.authorities = authorities;
		this.enabled = enabled;
		this.username = username;
		this.password = password;
		this.profil = profil;
		this.createdOn = createdOn;
		this.client = client;
	}


	public Utilisateur(Long id, Date lastPasswordResetDate, Date blockedate, Date deblockedate, Authority authorities,
			boolean enabled, String username, String password, String profil, Date createdOn, Artisant artisant) {
		super();
		this.id = id;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.blockedate = blockedate;
		this.deblockedate = deblockedate;
		this.authorities = authorities;
		this.enabled = enabled;
		this.username = username;
		this.password = password;
		this.profil = profil;
		this.createdOn = createdOn;
		this.artisant = artisant;
	}


	public Utilisateur(Long id, Date lastPasswordResetDate, Authority authorities, boolean enabled, String username,
			String password, String profil, Date createdOn, Client client) {
		super();
		this.id = id;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.authorities = authorities;
		this.enabled = enabled;
		this.username = username;
		this.password = password;
		this.profil = profil;
		this.createdOn = createdOn;
		this.client = client;
	}


	public Utilisateur(Long id, Date lastPasswordResetDate, Authority authorities, boolean enabled, String username,
			String password, String profil, Date createdOn, Artisant artisant) {
		super();
		this.id = id;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.authorities = authorities;
		this.enabled = enabled;
		this.username = username;
		this.password = password;
		this.profil = profil;
		this.createdOn = createdOn;
		this.artisant = artisant;
	}


	public Utilisateur(Date lastPasswordResetDate, Authority authorities, boolean enabled, String username,
			String password, String profil, Date createdOn, Client client) {
		super();
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.authorities = authorities;
		this.enabled = enabled;
		this.username = username;
		this.password = password;
		this.profil = profil;
		this.createdOn = createdOn;
		this.client = client;
	}


	public Utilisateur(Date lastPasswordResetDate, Authority authorities, boolean enabled, String username,
			String password, String profil, Artisant artisant) {
		super();
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.authorities = authorities;
		this.enabled = enabled;
		this.username = username;
		this.password = password;
		this.profil = profil;
		this.artisant = artisant;
	}


	public Date getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}


	public Utilisateur(Date lastPasswordResetDate, boolean enabled, String username, String password, String profil) {
		super();
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.enabled = enabled;
		this.username = username;
		this.password = password;
		this.profil = profil;
	}

	
	

}
