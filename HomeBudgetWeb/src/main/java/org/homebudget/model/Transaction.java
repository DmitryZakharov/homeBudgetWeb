package org.homebudget.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class Transaction {

	public static enum TransactionType {
		INCOME, OUTCOME
	};

	@Column(name="DATE_OF_TRANSACTION")
	private Date dateOFTransaction;

	@OneToOne
	@JoinColumn(name="CATEGORY_ID")
	private Category category;

	@Column(name="AMOUNT")
	private float amount;

	@Enumerated(EnumType.STRING)
	@Column (name="TRANSACTION_TYPE")
	private TransactionType transactionType;

	public Date getDateOFTransaction() {
		return dateOFTransaction;
	}

	public void setDateOFTransaction(Date dateOFTransaction) {
		this.dateOFTransaction = dateOFTransaction;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
