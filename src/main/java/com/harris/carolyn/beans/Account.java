package com.harris.carolyn.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Size;

import com.harris.carolyn.repository.AccountRepository;
import com.stormpath.sdk.servlet.account.AccountResolver;

@Entity
@Table(name = "accounts")
public class Account {

	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Size(max = 90, min = 2)
	private String firstName;

	private String lastName;
	
	private String email;
	
	private boolean active;
	
	private String role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Recipient> recipients;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Event> events;
	
	public Account setAccount(HttpServletRequest req, Account account){
		if(AccountResolver.INSTANCE.getAccount(req) != null){
			account.setFirstName(AccountResolver.INSTANCE.getAccount(req).getGivenName());
			account.setLastName(AccountResolver.INSTANCE.getAccount(req).getSurname());
			account.setEmail(AccountResolver.INSTANCE.getAccount(req).getEmail());
			account.setRole("USER");
			account.setActive(true);
		}
		return account;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Recipient> getRecipients() {
		return recipients;
	}

	public void setRecipients(Set<Recipient> recipients) {
		this.recipients = recipients;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	}
