package com.krishik.atm.ui.action.card;

import com.krishik.atm.api.exception.OperationCancelledException;
import com.krishik.atm.ui.api.IAction;
import com.krishik.atm.ui.facade.Facade;

import java.util.Scanner;

public class AuthorizeUser implements IAction {
    @Override
    public void execute() {
        String cardNumber;
        String password;

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter card number: ");
        cardNumber = input.nextLine();
        if (!cardNumber.matches("[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}")) {
            throw new OperationCancelledException("Invalid card number!");
        }
        System.out.print("Please enter password: ");
        password = input.nextLine();
        Facade.getInstance().authorizeUser(cardNumber, password);
        System.out.println("Authenticated successfully");
    }
}
