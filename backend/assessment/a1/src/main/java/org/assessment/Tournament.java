package org.assessment;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tournament {
    private static Teams teams;
    private static ArrayList<Fixture> fixtures;
    private static List<String> teamNames;
    static {
        teams= new Teams("src/main/resources/IPL_2021-data.csv");
        teamNames = teams.getTeamNames();
        fixtures = new ArrayList<>();
    }

    public static void main(String[] args) {
//        int matchNumber = 0;
//
//        for(int i=0; i<teamNames.size(); i++){
//            for(int j=0; j<teamNames.size(); j++){
//                fixtures.add(new Fixture());
//            }
//        }
    }
}
