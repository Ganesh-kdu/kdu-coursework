package org.assessment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Team {
    private String name;
    private HashMap<String,Player> players;
    private Player wicketTaker;
    private Integer wicketsTaken;
    private Player runScorer;
    private Integer runsScored;

    public Team(){
        this.wicketsTaken = 0;
        this.runsScored = 0;
    }


    public List<String> atLeastForty(){
        ArrayList<String> bowlers = new ArrayList<>();
        for (Player p: players.values()){
            if(p.getWickets()>=40 && p.getRole().equals("BOWLER")){
                bowlers.add(p.getName());
            }
        }
        return bowlers;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer(String name){
        return players.get(name);
    }

    public void addPlayer(Player player){
        players.put(player.getName(),player);
        if(player.getWickets()>wicketsTaken){
            wicketsTaken= player.getWickets();
            wicketTaker= player;
        }

        if(player.getRuns()>runsScored){
            runsScored= player.getRuns();
            runScorer= player;
        }
    }

    public Player getWicketTaker() {
        return wicketTaker;
    }

    public Player getRunScorer() {
        return runScorer;
    }
}
