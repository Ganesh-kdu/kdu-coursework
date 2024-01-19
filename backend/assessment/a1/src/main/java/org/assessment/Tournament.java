package org.assessment;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Tournament {
    private static HashMap<String, Team> teams;
    static {
        parseCSV();
    }

    public static void parseCSV() throws IOException {
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
