package com.harris.carolyn.beans;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "gifts")
public class Gift {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Size(max = 200)
	private String name;
	
	@Size(max = 500)
	private String linkOne;
	
	@Size(max = 500)
	private String linkTwo;
	
	private Boolean bought;
	
	private BigDecimal price;
	
	
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@ManyToOne
    @JoinColumn(name = "recipient_id")
    private Recipient recipient;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLinkOne() {
		return linkOne;
	}

	public void setLinkOne(String linkOne) {
		this.linkOne = linkOne;
	}

	public String getLinkTwo() {
		return linkTwo;
	}

	public void setLinkTwo(String linkTwo) {
		this.linkTwo = linkTwo;
	}

	public Boolean getBought() {
		return bought;
	}

	public void setBought(Boolean bought) {
		this.bought = bought;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
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
		Gift other = (Gift) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
