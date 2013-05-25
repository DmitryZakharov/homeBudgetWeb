package org.homebudget.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Transaction {

    public static enum TransactionType {

        INCOME, OUTCOME

    };

    @Column(name = "DATE_OF_TRANSACTION")
    @Temporal(TemporalType.DATE)
    private Date            dateOFTransaction;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
    private Category        category;

    @Column(name = "AMOUNT")
    private float           amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_TYPE")
    private TransactionType transactionType;

    @Column(name = "COMMENT")
    private String          comment;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "RESOURCE_ID")
    private BinaryResource  transactionImage;

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

    public String getComment() {

        return comment;
    }

    public void setComment(String comment) {

        this.comment = comment;
    }

    public BinaryResource getTransactionImage() {

        return transactionImage;
    }

    public void setTransactionImage(BinaryResource transactionImage) {

        this.transactionImage = transactionImage;
    }

}
