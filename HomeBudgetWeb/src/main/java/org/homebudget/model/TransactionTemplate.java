package org.homebudget.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class TransactionTemplate {

	@Id
	@GeneratedValue
	@Column(name = "TRANSACTION_TEMPLATE_ID")
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CATEGORY")
	protected Category category;

	@Column(name = "AMOUNT")
	protected float amount;

	@Enumerated(EnumType.STRING)
	@Column(name = "TRANSACTION_TYPE")
	protected Transaction.TransactionType type;

	@Column(name = "COMMENT")
	protected String comment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OWNER_METADATA")
	private UserMetadata ownerMetadata;

	public TransactionTemplate(Transaction transaction) {
		this.amount = transaction.getAmount();
		this.category = transaction.getCategory();
		this.comment = transaction.getComment();
		this.type = transaction.getType();
		this.ownerMetadata = transaction.getUserMetadata();
	}

	public TransactionTemplate() {

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

	public UserMetadata getOwnerMetadata() {
		return ownerMetadata;
	}

	public void setOwnerMetadata(UserMetadata ownerMetadata) {
		this.ownerMetadata = ownerMetadata;
	}

}