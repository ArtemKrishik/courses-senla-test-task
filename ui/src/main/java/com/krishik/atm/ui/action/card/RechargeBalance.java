package com.krishik.atm.ui.action.card;

import com.krishik.atm.api.exception.OperationCancelledException;
import com.krishik.atm.ui.api.IAction;
import com.krishik.atm.ui.facade.Facade;

import java.util.Scanner;

public class RechargeBalance implements IAction {
    @Override
    public void execute() {
        double amountOfMoney;

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter amount of money to add: ");
        amountOfMoney = input.nextDouble();
        if (amountOfMoney > 1000000) {
            throw new OperationCancelledException("The recharge amount must not exceed a 1 000 000");
        }
        Facade.getInstance().rechargeBalance(amountOfMoney);
        System.out.println("Balance was recharged successfully");
    }
}
