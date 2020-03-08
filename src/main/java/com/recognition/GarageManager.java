package com.recognition;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class GarageManager {
    private final static Set<String> garageRepository = new HashSet<String>();
    private static final String nextLine = "\r\n";
    static String path = System.getProperty("user.dir") + "\\src\\main\\resources\\garage.txt";
    File file = new File(path);
    private static final GarageManager garageManager = new GarageManager();

    private GarageManager() {
    }

    public static Set<String> getGarageRepository() {
        return garageRepository;
    }

    public static GarageManager getGarageManager() {
        return garageManager;
    }


    void inputGarage(String number) throws IOException {
        readInGarage();
        garageRepository.add(number);
        writeInGarage();
    }

    void outputGarage(String number) throws IOException {
        readInGarage();
        garageRepository.remove(number);
        writeInGarage();
    }

    Set<String> listGarage() throws IOException {
        readInGarage();
        return garageRepository;
    }

    private void readInGarage() throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            garageRepository.add(line);
        }
    }

    private void writeInGarage() throws IOException {
        FileOutputStream writeGarage = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(writeGarage);
        for (String num : garageRepository) {
            bufferedOutputStream.write((num + nextLine).getBytes());
        }
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }
}
