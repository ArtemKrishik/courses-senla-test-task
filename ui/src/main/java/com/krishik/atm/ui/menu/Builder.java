package com.krishik.atm.ui.menu;

import com.krishik.atm.service.DataService;
import com.krishik.atm.ui.action.card.AuthorizeUser;
import com.krishik.atm.ui.action.card.CashWithdrawal;
import com.krishik.atm.ui.action.atm.AddMoneyToAtm;
import com.krishik.atm.ui.action.card.RechargeBalance;
import com.krishik.atm.ui.facade.Facade;

public class Builder {

    private static Builder instance;
    private Menu rootMenu;

    private Builder() {
        buildMenu();
    }

    public static Builder getInstance() {
        if (instance == null) {
            instance = new Builder();
        }
        return instance;
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

    public void buildMenu() {
        rootMenu = new Menu();
        rootMenu.addMenuItem(new MenuItem("User menu", () -> { }, createUserMenu()));
        rootMenu.addMenuItem(new MenuItem("Employee menu", () -> { }, createEmployeeMenu()));
        rootMenu.addMenuItem(new MenuItem("Exit", () -> {
            DataService.getInstance().serialize();
            System.exit(0);
        }, null));

    }

    private Menu createUserMenu() {
        Menu userMenu = new Menu();
        userMenu.addMenuItem(new MenuItem("Enter credentials", new AuthorizeUser(), createUserAfterSuccessfulAuthenticationMenu()));
        userMenu.addMenuItem(new MenuItem(("Back to main menu"), () -> { }, rootMenu));
        return userMenu;
    }

    private Menu createEmployeeMenu() {
        Menu employeeMenu = new Menu();
        employeeMenu.addMenuItem(new MenuItem("Add money", new AddMoneyToAtm(), employeeMenu));
        employeeMenu.addMenuItem(new MenuItem(("Back to main menu"), () -> { }, rootMenu));
        return employeeMenu;
    }

    private Menu createUserAfterSuccessfulAuthenticationMenu() {
        Menu userAfterSuccessfulAuthenticationMenu = new Menu();
        userAfterSuccessfulAuthenticationMenu.addMenuItem(new MenuItem("Check balance",
                () -> System.out.println("Your balance: " + Facade.getInstance().checkBalance()), userAfterSuccessfulAuthenticationMenu));
        userAfterSuccessfulAuthenticationMenu.addMenuItem(new MenuItem("Сash withdrawal", new CashWithdrawal(), userAfterSuccessfulAuthenticationMenu));
        userAfterSuccessfulAuthenticationMenu.addMenuItem(new MenuItem("Recharge balance", new RechargeBalance(), userAfterSuccessfulAuthenticationMenu));
        userAfterSuccessfulAuthenticationMenu.addMenuItem(new MenuItem(("Back to main menu"), () -> { }, rootMenu));
        return userAfterSuccessfulAuthenticationMenu;
    }

    public Menu createRetryMenu(MenuItem itemToRetry) {
        Menu retryMenu = new Menu();
        retryMenu.addMenuItem(new MenuItem("Retry", itemToRetry.getAction(),  itemToRetry.getNextMenu()));
        retryMenu.addMenuItem(new MenuItem(("Cancel"), () -> { }, rootMenu));
        return retryMenu;
    }
}
