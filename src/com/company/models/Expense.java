package com.company.models;

import java.util.List;

public  class Expense {
    //u1 1000 4 u1 u2 u3 u4 EQUAL
    //u1 1250 2 u2 u3 EXACT 370 880
    private User paidBy;
    private List<User> paidTo;

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public List<User> getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(List<User> paidTo) {
        this.paidTo = paidTo;
    }

    public int getPaidToCount() {
        return paidToCount;
    }

    public void setPaidToCount(int paidToCount) {
        this.paidToCount = paidToCount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public List<Integer> getSplitAmounts() {
        return splitAmounts;
    }

    public void setSplitAmounts(List<Integer> splitAmounts) {
        this.splitAmounts = splitAmounts;
    }

    private int paidToCount;
    private int amount;
    private int id;
    private ExpenseType expenseType;
    private List<Integer> splitAmounts;

    public Expense(User paidBy, List<User> paidTo, int paidToCount, int amount, int id, ExpenseType expenseType, List<Integer> splitAmounts) {
        this.paidBy = paidBy;
        this.paidTo = paidTo;
        this.paidToCount = paidToCount;
        this.amount = amount;
        this.id = id;
        this.expenseType = expenseType;
        this.splitAmounts = splitAmounts;
    }
}
