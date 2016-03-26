package com.diandian.ycdyus.moneymanager.model;

/**
 * Created by cretin on 16/3/26.
 */
public class BudgetManagerModel {
    private boolean budgetStatus;
    private boolean budgetPeriod;
    private float budgetCount;

    public boolean isBudgetStatus() {
        return budgetStatus;
    }

    public void setBudgetStatus(boolean budgetStatus) {
        this.budgetStatus = budgetStatus;
    }

    public boolean isBudgetPeriod() {
        return budgetPeriod;
    }

    public void setBudgetPeriod(boolean budgetPeriod) {
        this.budgetPeriod = budgetPeriod;
    }

    public float getBudgetCount() {
        return budgetCount;
    }

    public void setBudgetCount(float budgetCount) {
        this.budgetCount = budgetCount;
    }
}
