package com.krishik.atm.ui.action.card;

import com.krishik.atm.ui.api.IAction;
import com.krishik.atm.ui.facade.Facade;

import java.util.Scanner;

public class CashWithdrawal implements IAction {
    @Override
    public void execute() {
        double amountOfMoney;

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter amount of money to withdraw: ");
        amountOfMoney = input.nextDouble();
        Facade.getInstance().withdrawCash(amountOfMoney);
        System.out.println("Cash issued successfully");
    }
}
