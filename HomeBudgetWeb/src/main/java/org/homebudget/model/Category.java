package org.homebudget.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "CATEGORY")
public class Category {

    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue
    private int    categoryId;

    @Column(name = "CATEGORY_NAME")
    private String category;

    public int getCategoryId() {

        return categoryId;
    }

    public void setCategoryId(int categoryId) {

        this.categoryId = categoryId;
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {

        this.category = category;
    }

}
