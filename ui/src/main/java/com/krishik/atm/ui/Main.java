package com.krishik.atm.ui;

import com.krishik.atm.ui.menu.MenuController;

import java.io.IOException;
import java.util.logging.LogManager;

public class Main {

    public static void main(String[] args) throws IOException {
        LogManager.getLogManager().readConfiguration(Main.class.getClassLoader().getResourceAsStream("logging.properties"));
        MenuController.getInstance().run();
    }
}
