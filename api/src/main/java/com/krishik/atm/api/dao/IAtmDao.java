package com.krishik.atm.api.dao;

import com.krishik.atm.model.Atm;
import com.krishik.atm.model.Card;

public interface IAtmDao {
    Card getActiveCard();

    Atm getAtm();

    void save(Atm atm);
}
