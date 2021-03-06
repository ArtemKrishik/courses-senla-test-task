package com.krishik.atm.ui.menu;

import com.krishik.atm.api.exception.OperationCancelledException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Navigator {
    private static Navigator instance;
    private Menu currentMenu;
    private Builder builder;
    private static final Logger log = Logger.getLogger(Navigator.class.getName());

    private Navigator() {
        builder = Builder.getInstance();
    }

    public static Navigator getInstance() {
        if (instance == null) {
            instance = new Navigator();
        }
        return instance;
    }

    public void printMenu() {
        System.out.println("### Please select an action ###");
        int currentMenuItemIndex = 0;
        for (MenuItem menuItem : currentMenu.getMenuItems()) {
            System.out.println(currentMenuItemIndex + ": " + menuItem.getTitle());
            currentMenuItemIndex++;
        }
    }

    public void navigate(int index) {
        if (currentMenu != null) {
            try {
                MenuItem menuItem = currentMenu.getMenuItems().get(index);
                try {
                    menuItem.doAction();
                    currentMenu = menuItem.getNextMenu();
                } catch (NumberFormatException ex) {
                    System.out.println("Incorrect input!");
                    currentMenu = builder.createRetryMenu(menuItem);
                } catch (OperationCancelledException e) {
                    log.log(Level.SEVERE, e.getMessage());
                    System.out.println(e.getMessage());
                    currentMenu = builder.createRetryMenu(menuItem);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    log.log(Level.SEVERE, e.getMessage(), e);
                    currentMenu = builder.createRetryMenu(menuItem);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Incorrect input!");
            }
        }
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
}
