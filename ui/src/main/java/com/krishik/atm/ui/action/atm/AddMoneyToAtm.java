package com.krishik.atm.ui.action.atm;

import com.krishik.atm.ui.api.IAction;
import com.krishik.atm.ui.facade.Facade;

import java.util.Scanner;

public class AddMoneyToAtm implements IAction {
    @Override
    public void execute() {
        double amountOfMoney;

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter amount of money to add: ");
        amountOfMoney = input.nextDouble();
        Facade.getInstance().addMoneyToAtm(amountOfMoney);
        System.out.println("Money was added successfully");
    }
}
