package com.company;

import com.company.models.ExpenseType;
import com.company.services.ExpenseService;
import com.company.services.UserService;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        UserService userService = new UserService();
        ExpenseService expenseService = new ExpenseService();

        userService.addUser("a","a.com","a9");//0
        userService.addUser("b","a.com","a9");//1
        userService.addUser("c","a.com","a9");//2
        userService.addUser("d","a.com","a9");//3

        System.out.println(userService.getUsers());

        expenseService.addExpense(userService.getUser(0),  Arrays.asList(userService.getUser(1),userService.getUser(2),userService.getUser(3)),
                3,750,1, ExpenseType.EQUAL,Arrays.asList(250,250,250));

        expenseService.addExpense(userService.getUser(0),  Arrays.asList(userService.getUser(1),userService.getUser(2)),
                2,1250,2, ExpenseType.EXACT,Arrays.asList(370,880));

        expenseService.showExpense();
        System.out.println("======");
        expenseService.showExpense(3);
        System.out.println("======");


        expenseService.addExpense(userService.getUser(1),  Arrays.asList(userService.getUser(0)),
                1,250,3, ExpenseType.EQUAL,Arrays.asList(250));

        expenseService.showExpense();
        System.out.println("======");

        expenseService.addExpense(userService.getUser(1),  Arrays.asList(userService.getUser(0)),
                1,250,4, ExpenseType.EQUAL,Arrays.asList(370));
        expenseService.showExpense();


    }
}
