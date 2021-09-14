package com.krishik.atm.dao;

import com.krishik.atm.api.dao.IAtmDao;
import com.krishik.atm.model.Atm;
import com.krishik.atm.model.Card;

public class AtmDao implements IAtmDao {
    private static IAtmDao instance;
    private Atm atm;

    private AtmDao() {
    }

    public static IAtmDao getInstance() {
        if (instance == null) {
            instance = new AtmDao();
        }
        return instance;
    }

    @Override
    public Card getActiveCard() {
        return atm.getActiveCard();
    }

    @Override
    public Atm getAtm() {
        return atm;
    }

    @Override
    public void save(Atm atm) {
        this.atm = atm;
    }
}
