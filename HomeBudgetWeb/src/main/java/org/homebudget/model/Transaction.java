package org.homebudget.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Transaction {

	@Id
	@GeneratedValue
	@Column(name = "TRANSACTION_ID")
	private Long id;

	@Column(name = "EXECUTION_DATE")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date executionDate;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY")
	protected Category category;

	@Column(name = "AMOUNT")
	protected float amount;

	@Enumerated(EnumType.STRING)
	@Column(name = "TRANSACTION_TYPE")
	protected TransactionType type;

	@Column(name = "COMMENT")
	protected String comment;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "ATTACHMENT")
	private BinaryResource attachment;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ACCOUNT_ID")
	private Account account;

	public Date getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}

	public BinaryResource getAttachment() {

		return attachment;
	}

	public void setAttachment(BinaryResource attachment) {

		this.attachment = attachment;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Transaction.TransactionType getType() {
		return type;
	}

	public void setType(Transaction.TransactionType type) {

		this.type = type;
	}

	public float getAmount() {

		return amount;
	}

	public void setAmount(float amount) {

		this.amount = amount;
	}

	public Category getCategory() {

		return category;
	}

	public void setCategory(Category category) {

		this.category = category;
	}

	public String getComment() {

		return comment;
	}

	public void setComment(String comment) {

		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public UserMetadata getUserMetadata(){
		UserMetadata result = null;
		if(account != null){
			result = account.getOwnerMetadata();
		}
		return result;
	}

	public static enum TransactionType {

		INCOME, OUTCOME

	};

}
