package com.krishik.atm.api.service;

public interface ICardService {

    void authorizeUser(String cardNumber, String password);

    void withdrawCash(double amountOfMoney);

    double checkBalance();

    void rechargeBalance(double amountOfMoney);
}
