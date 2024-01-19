package org.assessment;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class Tournament {
    private static HashMap<String, Team> teams;
    static {
        parseCSV();
    }

    public static void parseCSV() throws IOException {
        Path path = new Path("src/main/resources/IPL_2021-data.csv");
        ArrayList<String[]> coinList = new ArrayList<>();
        FileReader fileReader = new FileReader(path.toFile());
        CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
        String[] nextRecord;
        while ((nextRecord = csvReader.readNext()) != null) {
            coinList.add(nextRecord);
        }
        return coinList;
    }
    public static void main(String[] args) {
    }
}
