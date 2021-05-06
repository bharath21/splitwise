package com.company.services;

import com.company.models.Expense;
import com.company.models.ExpenseType;
import com.company.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseService {
    private final List<Expense> expenses;
    private final Map<Integer, Map<Integer, Integer>> owedBy;

    private final Map<Integer, Map<Integer, Integer>> owedTo;

    public ExpenseService() {
        owedBy = new HashMap<>();
        owedTo = new HashMap<>();
        expenses = new ArrayList<>();
    }

    public boolean addExpense(User paidBy, List<User> paidTo, int paidToCount, int amount, int id, ExpenseType expenseType, List<Integer> splitAmounts) {
        if (!isValidExpense(paidBy, paidTo, paidToCount, amount, id, expenseType, splitAmounts)) {
            return false;
        }
        Expense expense = new Expense(paidBy, paidTo, paidToCount, amount, id, expenseType, splitAmounts);
        expenses.add(expense);
        adjustExpenses(expense);
        return true;
    }

    private void adjustExpenses(Expense expense) {
        User paidBy = expense.getPaidBy();
        List<User> paidTo = expense.getPaidTo();
        List<Integer> splitAmounts = expense.getSplitAmounts();
        int counter = 0;
        for (var paidToUser : paidTo) {
            int splitAmount = splitAmounts.get(counter);
            if (!owedBy.containsKey(paidBy.getId())) {
                owedBy.put(paidBy.getId(), new HashMap<>());
            }

            if (!owedTo.containsKey(paidToUser.getId())) {
                owedTo.put(paidToUser.getId(), new HashMap<>());
            }

            if (!owedBy.containsKey(paidToUser.getId())) {
                owedBy.put(paidToUser.getId(), new HashMap<>());
            }

            if (!owedTo.containsKey(paidBy.getId())) {
                owedTo.put(paidBy.getId(), new HashMap<>());
            }

            if (owedBy.get(paidToUser.getId()).containsKey(paidBy.getId())) {
                int previousBalance = owedBy.get(paidToUser.getId()).get(paidBy.getId());
                if (splitAmounts.get(counter) < previousBalance) {
                    owedBy.get(paidToUser.getId()).replace(paidBy.getId(), previousBalance - splitAmount);
                    owedTo.get(paidBy.getId()).replace(paidToUser.getId(), previousBalance - splitAmount);
                } else if (splitAmounts.get(counter) == previousBalance) {
                    owedBy.get(paidToUser.getId()).remove(paidBy.getId());
                    owedTo.get(paidBy.getId()).remove(paidToUser.getId());
                } else {
                    owedBy.get(paidToUser.getId()).remove(paidBy.getId());
                    owedTo.get(paidBy.getId()).remove(paidToUser.getId());
                    owedBy.get(paidBy.getId()).put(paidToUser.getId(), splitAmount - previousBalance);
                    owedTo.get(paidToUser.getId()).put(paidBy.getId(), splitAmount - previousBalance);
                }
            } else {
                int previousBalance = 0;
                if (owedBy.containsKey(paidBy.getId()) && owedBy.get(paidBy.getId()).containsKey(paidToUser.getId())) {
                    previousBalance = owedBy.get(paidBy.getId()).get(paidToUser.getId());
                }

                owedBy.get(paidBy.getId()).put(paidToUser.getId(), previousBalance + splitAmount);

                if (owedTo.containsKey(paidToUser.getId()) && owedTo.get(paidToUser.getId()).containsKey(paidBy.getId())) {
                    previousBalance = owedTo.get(paidToUser.getId()).get(paidBy.getId());
                }

                owedTo.get(paidToUser.getId()).put(paidBy.getId(), previousBalance + splitAmount);
            }
            counter++;
        }
    }

    private boolean isValidExpense(User paidBy, List<User> paidTo, int paidToCount, int amount, int id, ExpenseType expenseType, List<Integer> splitAmounts) {
        if (paidTo.contains(paidBy)) {
            System.out.println("user cannot pay to himself/herself");
            return false;
        }

        if (splitAmounts.size() != paidToCount) {
            System.out.println("not exact number of split amounts entered");
        }

        int totalAmount = 0;
        for (var splitAmount : splitAmounts) {
            totalAmount += splitAmount;
        }
        if (totalAmount != amount) {
            System.out.println("sum not added up");
            return false;
        }
        return true;
    }

    public void showExpense() {
        System.out.println(owedBy);
        System.out.println(owedTo);
    }

    public void showExpense(int id) {
        var map = owedBy.get(id);
        for (var owedByUser : map.keySet()) {
            System.out.println(owedByUser + " owes " + id + " " + map.get(owedByUser));
        }
        System.out.println(owedTo);
    }

}
