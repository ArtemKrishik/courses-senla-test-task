package com.krishik.atm.api.service;

import com.krishik.atm.model.Card;

public interface IAtmService {

    void addMoneyToAtm(double amountOfMoney);

    void reduceMoneyFromAtm(double amountOfMoney);

    void setActiveCard(Card card);

    double getBalance();

    Card getActiveCard();
}
