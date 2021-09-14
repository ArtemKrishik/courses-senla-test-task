package com.krishik.atm.model;

import java.util.List;

public class AllData {
    private Atm atm;
    private List<Card> cards;

    public Atm getAtm() {
        return atm;
    }

    public void setAtm(Atm atm) {
        this.atm = atm;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
