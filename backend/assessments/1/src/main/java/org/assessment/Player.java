package org.assessment;

public class Player {
    private String name;
    private String team;
    private String role;
    private Integer matches;
    private Integer runs;
    private Double average;
    private Double SR;
    private Integer wickets;

    public Player(String name, String team, String role, Integer matches, Integer runs, Double average, Double SR, Integer wickets) {
        this.name = name;
        this.team = team;
        this.role = role;
        this.matches = matches;
        this.runs = runs;
        this.average = average;
        this.SR = SR;
        this.wickets = wickets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getMatches() {
        return matches;
    }

    public void setMatches(Integer matches) {
        this.matches = matches;
    }

    public Integer getRuns() {
        return runs;
    }

    public void setRuns(Integer runs) {
        this.runs = runs;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getSR() {
        return SR;
    }

    public void setSR(Double SR) {
        this.SR = SR;
    }

    public Integer getWickets() {
        return wickets;
    }

    public void setWickets(Integer wickets) {
        this.wickets = wickets;
    }
}
