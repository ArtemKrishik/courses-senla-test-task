package com.krishik.atm.api.dao;

import com.krishik.atm.model.Card;

import java.util.List;

public interface ICardDao {
    Card getCardByCardNumber(String cardNumber);

    List<Card> getAll();

    void saveAll(List<Card> cards);
}
