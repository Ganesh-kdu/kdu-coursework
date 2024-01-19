package org.assessment;

import java.util.HashMap;

public class Team {
    private String name;
    private HashMap<String,Player> players;

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
    }
}
