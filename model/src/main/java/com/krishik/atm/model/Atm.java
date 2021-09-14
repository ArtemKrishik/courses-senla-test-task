package com.krishik.atm.model;

import com.krishik.atm.model.generic.AEntity;

import java.util.Objects;

public class Atm extends AEntity {
    private double balance;
    private Card activeCard;

    public Atm(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Card getActiveCard() {
        return activeCard;
    }

    public void setActiveCard(Card activeCard) {
        this.activeCard = activeCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Atm atm)) return false;
        return getId().equals(atm.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Atm #" + getId() + "; Balance: " + getBalance();
    }
}
