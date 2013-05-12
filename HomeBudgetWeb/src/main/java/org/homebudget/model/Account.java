package org.homebudget.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class Account {

	@Id
	@Column(name = "ACCOUNT_ID")
	@GeneratedValue
	private long accountId;
	
	@Column (name="ACOOUNT_NAME")
	private String accountName;

	@Column(name = "CREATION_DATE")
        @Temporal(javax.persistence.TemporalType.DATE)
	private Date dateOfCreation;

	@OneToOne
	@JoinColumn(name="USER_ID")
	private UserDetails owner;

	@Column(name = "STARTING_BALANCE")
	private float startingBalance;

	@ElementCollection
	@JoinTable(name = "TRANSACTIONS", joinColumns = @JoinColumn(name = "ACCOUNT_ID"))
	@GenericGenerator(name = "hilo-gen", strategy = "hilo")
	@CollectionId(columns = { @Column(name = "TRANSACTION_ID") }, generator = "hilo-gen", type = @Type(type = "long"))
	private Collection<Transaction> transactions = new ArrayList<Transaction>();

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public UserDetails getOwner() {
		return owner;
	}

	public void setOwner(UserDetails owner) {
		this.owner = owner;
	}

	public float getStartingBalance() {
		return startingBalance;
	}

	public void setStartingBalance(float startingBalance) {
		this.startingBalance = startingBalance;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public Collection<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Collection<Transaction> transactions) {
		this.transactions = transactions;
	}

}
