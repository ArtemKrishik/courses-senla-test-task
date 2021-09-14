package com.krishik.atm.service;


import com.krishik.atm.api.dao.IAtmDao;
import com.krishik.atm.api.service.IAtmService;
import com.krishik.atm.dao.AtmDao;
import com.krishik.atm.model.Atm;
import com.krishik.atm.model.Card;

public class AtmService implements IAtmService {
    private static IAtmService instance;
    private final IAtmDao atmDao;

    public static IAtmService getInstance() {
        if (instance == null) {
            instance = new AtmService();
        }
        return instance;
    }

    private AtmService() {
        this.atmDao = AtmDao.getInstance();
    }

    @Override
    public void addMoneyToAtm(double amountOfMoney) {
        getAtm().setBalance(getAtm().getBalance() + amountOfMoney);
    }

    @Override
    public void reduceMoneyFromAtm(double amountOfMoney) {
        getAtm().setBalance(getAtm().getBalance() - amountOfMoney);
    }

    @Override
    public void setActiveCard(Card card) {
        atmDao.getAtm().setActiveCard(card);
    }

    @Override
    public double getBalance() {
        return atmDao.getAtm().getBalance();
    }

    @Override
    public Card getActiveCard() {
        return atmDao.getActiveCard();
    }

    private Atm getAtm() {
        return atmDao.getAtm();
    }
}
