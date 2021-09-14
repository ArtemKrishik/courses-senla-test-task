package com.krishik.atm.ui.facade;

import com.krishik.atm.service.AtmService;
import com.krishik.atm.service.CardService;
import com.krishik.atm.api.service.IAtmService;
import com.krishik.atm.api.service.ICardService;

public class Facade {

    private static ICardService cardService;
    private static IAtmService atmService;
    private static Facade instance;

    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }

    private Facade() {
        cardService = CardService.getInstance();
        atmService = AtmService.getInstance();
    }

    public double checkBalance() {
        return cardService.checkBalance();
    }

    public void addMoneyToAtm(double amountOfMoney) {
        atmService.addMoneyToAtm(amountOfMoney);
    }

    public void authorizeUser(String cardNumber, String password) {
        cardService.authorizeUser(cardNumber, password);
    }

    public void withdrawCash(double amountOfMoney) {
        cardService.withdrawCash(amountOfMoney);
    }

    public void rechargeBalance(double amountOfMoney) {
        cardService.rechargeBalance(amountOfMoney);
    }
}
