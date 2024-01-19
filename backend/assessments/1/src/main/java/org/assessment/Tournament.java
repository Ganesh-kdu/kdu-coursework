package org.assessment;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

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
        int matchNumber = 0;
        for(int i=0; i<teamNames.size(); i++){
            for(int j=0; j<teamNames.size(); j++){
                if (i!=j)
                    if (matchNumber%2==0) {
                        fixtures.add(new Fixture( new Date(2024,1,(int)(matchNumber/2)%31),teamNames.get(i), teamNames.get(j), matchNumber));
                    }
                    else {
                        fixtures.add(new Fixture(new Date(2024,1,(int)(matchNumber/2)%31), teamNames.get(j), teamNames.get(i), matchNumber));
                    }
            }
            matchNumber+=1;
        }
        int option = 1;
        Scanner sc = new Scanner(System.in);
        while (true) {
            option = sc.nextInt();
            if (option==1) {
                String team = sc.next();
                teams.getBowlersOverForty(team);
            } else if (option==2) {
                String team = sc.next();
                teams.getBestOfTeam(team);
            } else if (option==3) {
                teams.top3Runs();
                teams.top3Wicket();
            }
            else{
                break;
            }
        }
    }
}
