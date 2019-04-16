package com.example.bpear.budgetright;

/**
 * Created by bpear on 5/1/2018.
 */

public class Bills {
    String id;
    public String billName;
    public String dueAmount;
    public String dueDate;
    public String notes;

   /* public Bills(String id, String billName, String dueAmount, String dueDate, String notes) {
        this.id = id;
        this.billName = billName;
        this.dueDate = dueDate;
        this.dueAmount = dueAmount;
        this.notes = notes;
    }*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(String dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

