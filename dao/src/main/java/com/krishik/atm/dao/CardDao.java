package com.krishik.atm.dao;

import com.krishik.atm.api.dao.ICardDao;
import com.krishik.atm.model.Card;

import java.util.ArrayList;
import java.util.List;

public class CardDao implements ICardDao {
    private static ICardDao instance;
    private List<Card> repository = new ArrayList<>();

    private CardDao() {
    }

    public static ICardDao getInstance() {
        if (instance == null) {
            instance = new CardDao();
        }
        return instance;
    }

    @Override
    public Card getCardByCardNumber(String cardNumber) {
        return repository.stream()
                .filter(card -> card.getCardNumber().equals(cardNumber)).findFirst().get();
    }

    @Override
    public List<Card> getAll() {
        return repository;
    }

    @Override
    public void saveAll(List<Card> cards) {
        repository.clear();
        repository.addAll(cards);
    }
}
