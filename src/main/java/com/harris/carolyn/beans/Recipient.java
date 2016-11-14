package com.harris.carolyn.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "recipients")
public class Recipient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String birthday;
	
	private String notes;
	
	private String groupTag;
	
	
	
	public String getGroupTag() {
		return groupTag;
	}


	public void setGroupTag(String groupTag) {
		this.groupTag = groupTag;
	}

	@OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    private Set<Gift> gifts;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	

	
	@ManyToMany(mappedBy = "recipients")
	private Set<Event> events;
	

	

	public Set<Event> getEvents() {
		return events;
	}


	public void setEvents(Set<Event> events) {
		this.events = events;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}



	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	public Set<Gift> getGifts() {
		return gifts;
	}

	public void setGifts(Set<Gift> gifts) {
		this.gifts = gifts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
		Recipient other = (Recipient) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
