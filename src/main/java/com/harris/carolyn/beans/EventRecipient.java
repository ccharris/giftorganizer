package com.harris.carolyn.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "event_recipients")
public class EventRecipient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@OneToOne(optional=false)
	@JoinColumn(name="recipient_id")
	private Recipient recipient;
	
	@ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
	

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}




	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
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
		EventRecipient other = (EventRecipient) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	

}
