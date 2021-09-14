package com.krishik.atm.service;

import com.krishik.atm.api.dao.ICardDao;
import com.krishik.atm.api.exception.OperationCancelledException;
import com.krishik.atm.api.service.IAtmService;
import com.krishik.atm.api.service.ICardService;
import com.krishik.atm.dao.CardDao;
import com.krishik.atm.model.Card;

public class CardService implements ICardService {
    private static ICardService instance;
    private final ICardDao cardDao;
    private final IAtmService atmService;

    public static ICardService getInstance() {
        if (instance == null) {
            instance = new CardService();
        }
        return instance;
    }

    private CardService() {
        this.cardDao = CardDao.getInstance();
        this.atmService = AtmService.getInstance();
    }

    @Override
    public void authorizeUser(String cardNumber, String password) {
        if (!isCardExist(cardNumber)) {
            throw new OperationCancelledException("Incorrect card number!");

        }
        if (isPasswordIncorrect(cardNumber, password)) {
            incorrectPasswordAction(cardNumber);
            throw new OperationCancelledException("Incorrect password!");
        }
        if (isCardBlocked(cardNumber)) {
            throw new OperationCancelledException("Your card is blocked!");
        }
        atmService.setActiveCard(cardDao.getCardByCardNumber(cardNumber));
    }

    @Override
    public void withdrawCash(double amountOfMoney) {
        Card card = getActiveCard();
        if (card.getBalance() < amountOfMoney) {
            throw new OperationCancelledException("You don't have enough money!");
        } else if (atmService.getBalance() < amountOfMoney) {
            throw new OperationCancelledException("Unfortunately, atm don't have enough money");
        }
        atmService.reduceMoneyFromAtm(amountOfMoney);
        card.setBalance(card.getBalance() - amountOfMoney);
    }

    @Override
    public double checkBalance() {
        return atmService.getActiveCard().getBalance();
    }

    @Override
    public void rechargeBalance(double amountOfMoney) {
        Card card = getActiveCard();
        card.setBalance(card.getBalance() + amountOfMoney);
    }

    private boolean isCardExist(String cardNumber) {
        return cardDao.getAll().stream()
                .anyMatch(card -> cardNumber.equals(card.getCardNumber()));
    }

    private boolean isPasswordIncorrect(String cardNumber, String password) {
        return cardDao.getAll().stream()
                .anyMatch(card -> cardNumber.equals(card.getCardNumber()) && !password.equals(card.getPassword()));
    }

    private boolean isCardBlocked(String cardNumber) {
        return cardDao.getCardByCardNumber(cardNumber).isBlocked();
    }

    private void incorrectPasswordAction(String cardNumber) {
        Card card = cardDao.getCardByCardNumber(cardNumber);
        int numberOfUnsuccessfulAttempts = cardDao.getCardByCardNumber(cardNumber).getNumberOfUnsuccessfulAttempts();
        if (numberOfUnsuccessfulAttempts == 2) {
            card.setNumberOfUnsuccessfulAttempts(0);
            card.setBlocked(true);
            throw new OperationCancelledException("Card has been blocked!");
        } else {
            card.setNumberOfUnsuccessfulAttempts(numberOfUnsuccessfulAttempts + 1);
        }
    }

    private Card getActiveCard() {
        return atmService.getActiveCard();
    }
}