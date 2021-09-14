package com.krishik.atm.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.krishik.atm.api.dao.IAtmDao;
import com.krishik.atm.api.dao.ICardDao;
import com.krishik.atm.api.service.IDataService;
import com.krishik.atm.dao.AtmDao;
import com.krishik.atm.dao.CardDao;
import com.krishik.atm.model.AllData;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataService implements IDataService {
    private static IDataService instance;
    private String fileName;

    private final IAtmDao atmDao;
    private final ICardDao cardDao;

    private static final Logger log = Logger.getLogger(DataService.class.getName());

    public static IDataService getInstance() {
        if (instance == null) {
            instance = new DataService();
        }
        return instance;
    }

    private DataService() {
        this.atmDao = AtmDao.getInstance();
        this.cardDao = CardDao.getInstance();

        try (InputStream input = DataService.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            this.fileName = properties.getProperty("fileNameForDataService");
        } catch (IOException e) {
            log.log(Level.SEVERE, "Property file not found");
        }
    }

    @Override
    public void serialize() {
        AllData allData = new AllData();
        allData.setAtm(atmDao.getAtm());
        allData.setCards(cardDao.getAll());

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(allData);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(json);
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    @Override
    public void deserialize() {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(fileName)) {
            AllData allData = gson.fromJson(reader, AllData.class);
            cardDao.saveAll(allData.getCards());
            atmDao.save(allData.getAtm());
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }
}
