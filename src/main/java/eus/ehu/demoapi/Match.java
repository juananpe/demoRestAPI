package eus.ehu.demoapi;


import java.util.Date;

public class Match {

  Integer id;

  Date utcDate;
  String status;

  public void setHomeTeam(String homeTeam) {
    this.homeTeam = new Team(homeTeam);
  }

  public void setAwayTeam(String awayTeam) {
    this.awayTeam = new Team(awayTeam);
  }

  public void setScore(String score) {
    this.score = new Score(score);
  }

  Team homeTeam;
  Team awayTeam;
  Score score;

  Integer matchday;

  @Override
  public String toString() {
    return "Match{" +
            "id=" + id +
        ", utcDate=" + utcDate +
        ", status='" + status + '\'' +
        ", homeTeam=" + homeTeam +
        ", awayTeam=" + awayTeam +
        ", score=" + score +
        ", matchDay=" + matchday +
        '}';
  }

  public Date getUtcDate() {
    return utcDate;
  }

  public String getStatus() {
    return status;
  }

  public String getHomeTeam() {
    if (homeTeam.shortName==null) {
      homeTeam.shortName = Manager.get().getShortName(homeTeam.name);
    }
    return homeTeam.shortName;
  }

  public String getAwayTeam() {
    if (awayTeam.shortName==null) {
      awayTeam.shortName = Manager.get().getShortName(awayTeam.name);
    }

    return awayTeam.shortName;
  }

  public String getScore() {
    if (score.fullTime.homeTeam != null)
      return score.fullTime.homeTeam + " - " + score.fullTime.awayTeam;
    else
      return "-";
  }

  public Integer getMatchday() {
    return matchday;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  class Team {
    String name;
    String shortName;

    public Team(String name) {
      this.name = name;
      this.shortName = Manager.get().getShortName(name);
    }

    @Override
    public String toString() {
      return "Team{" +
          "name='" + name + '\'' +
          '}';
    }
  }
  class ScoreDetails {
    Integer homeTeam;
    Integer awayTeam;

    ScoreDetails(String score){
      homeTeam = Integer.parseInt(score.split("-")[0]);
      awayTeam = Integer.parseInt(score.split("-")[1]);
    }

    @Override
    public String toString() {
      return "ScoreDetails{" +
          "homeTeam=" + homeTeam +
          ", awayTeam=" + awayTeam +
          '}';
    }

  }
  class Score {
    ScoreDetails fullTime;

    Score(String score){
      fullTime = new ScoreDetails(score);
    }

    @Override
    public String toString() {
      return "Score{" +
          "fullTime=" + fullTime +
          '}';
    }
  }



}
