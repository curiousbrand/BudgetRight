package com.example.bpear.budgetright;

/**
 * Created by bpear on 5/3/2018.
 */

public class Debt {
    private String payee;
    private String dueDebtAmount;
    private String paybackDate;
    private String Dnotes;




    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getDueDebtAmount() {
        return dueDebtAmount;
    }

    public void setDueDebtAmount(String dueDebtAmount) {
        this.dueDebtAmount = dueDebtAmount;
    }

    public String getPaybackDate() {
        return paybackDate;
    }

    public void setPaybackDate(String paybackDate) {
        this.paybackDate = paybackDate;
    }

    public String getNotes() {
        return Dnotes;
    }

    public void setDnotes(String Dnotes) {
        this.Dnotes = Dnotes;
    }
}
