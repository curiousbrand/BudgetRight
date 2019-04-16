package com.example.bpear.budgetright;

/**
 * Created by bpear on 5/3/2018.
 */


public class Goal {
    private String goalName;
    private String targetMoney;
    private String savedMoney;
    private String desiredDate;

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(String targetMoney) {
        this.targetMoney = targetMoney;
    }

    public String getSavedMoney() {
        return savedMoney;
    }

    public void setSavedMoney(String savedMoney) {
        this.savedMoney = savedMoney;
    }

    public String getDesiredDate() {
        return desiredDate;
    }

    public void setDesiredDate(String desiredDate) {
        this.desiredDate = desiredDate;
    }
}

