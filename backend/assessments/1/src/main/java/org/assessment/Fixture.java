package org.assessment;

public class Fixture {
    private String date;
    private String time;
    private String homeTeam;
    private String awayTeam;
    private Integer matchNumber;
    private String ground;

    public Fixture(String date, String homeTeam, String awayTeam, Integer matchNumber) {
        this.date = date;
        if (matchNumber%2==0){
            this.time = "6:30";
        }
        else{
            this.time = "9:30";
        }
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchNumber = matchNumber;
        this.ground = homeTeam+"_home";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Integer getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(Integer matchNumber) {
        this.matchNumber = matchNumber;
    }

    public String getGround() {
        return ground;
    }

    public void setGround(String ground) {
        this.ground = ground;
    }
}
