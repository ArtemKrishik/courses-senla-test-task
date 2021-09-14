package com.krishik.atm.ui.menu;

import com.krishik.atm.service.DataService;

import java.util.Scanner;

public class MenuController {
    private static MenuController instance;
    private Builder builder;
    private Navigator navigator;

    private MenuController() {
        builder = Builder.getInstance();
        navigator = Navigator.getInstance();
    }

    public static MenuController getInstance() {
        if (instance == null) {
            instance = new MenuController();
        }
        return instance;
    }

    public void run() {
        DataService.getInstance().deserialize();
        Scanner input = new Scanner(System.in);
        navigator.setCurrentMenu(builder.getRootMenu());
        navigator.printMenu();
        Integer index = -2;

        while (!index.equals(-1)) {
            index = input.nextInt();
            navigator.navigate(index);
            navigator.printMenu();
        }
    }
}
