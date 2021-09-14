package com.krishik.atm.model;

import com.krishik.atm.model.generic.AEntity;

import java.util.Objects;

public class Card extends AEntity {
    private String cardNumber;
    private String password;
    private double balance;
    private int numberOfUnsuccessfulAttempts;
    private boolean isBlocked;

    public Card(String cardNumber, String password, double balance, int numberOfUnsuccessfulAttempts, boolean isBlocked) {
        this.cardNumber = cardNumber;
        this.password = password;
        this.balance = balance;
        this.numberOfUnsuccessfulAttempts = numberOfUnsuccessfulAttempts;
        this.isBlocked = isBlocked;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getNumberOfUnsuccessfulAttempts() {
        return numberOfUnsuccessfulAttempts;
    }

    public void setNumberOfUnsuccessfulAttempts(int numberOfUnsuccessfulAttempts) {
        this.numberOfUnsuccessfulAttempts = numberOfUnsuccessfulAttempts;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card card)) return false;
        return getCardNumber().equals(card.getCardNumber()) &&
                getId().equals(card.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardNumber(), getId());
    }

    @Override
    public String toString() {
        return "Card #" + getId() + "; Card number: " + getCardNumber();
    }
}

