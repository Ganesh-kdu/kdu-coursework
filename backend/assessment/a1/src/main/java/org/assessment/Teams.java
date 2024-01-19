package org.assessment;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Teams {
    private HashMap<String, Team> teams;
    private ArrayList<String> teamNames;
    private ArrayList<Player> players;

    public void addPlayer(String team, Player player){
        players.add(player);
        teams.computeIfAbsent(team, k -> new Team());
        teams.get(team).addPlayer(player);
    }

    public List<String> getBowlersOverForty(String team){
        return teams.get(team).atLeastForty();
    }

    public List<Player> getBestOfTeam(String team){
        return new Player[]{teams.get(team).getRunScorer(), teams.get(team).getWicketTaker()}
    }

    
    public List<Player> top3Wicket(){

    }

    public List<Player> top3Runs(){

    }
    public List<String> getTeamNames() {
        return teamNames;
    }

    public Teams(String path) {
        FileReader fileReader = null;
        teams = new HashMap<>();
        HashSet<String> teamNamesSet = new HashSet<>();
        try {
            fileReader = new FileReader(new File(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();
        String[] nextRecord;
        try {
            while  ((nextRecord = csvReader.readNext())!=null){
                Player player = new Player(nextRecord[0],nextRecord[1],nextRecord[2],Integer.parseInt(nextRecord[3]),Integer.parseInt(nextRecord[4]),Double.parseDouble(nextRecord[5]),Double.parseDouble(nextRecord[6]),Integer.parseInt(nextRecord[7]));
                teamNamesSet.add(nextRecord[1]);
                addPlayer(nextRecord[1], player);
            }}catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.teamNames = new ArrayList<>(teamNames) ;
    }
}
