package eus.ehu.demoapi;


public class Match {


  String utcDate;
  String status;

  Team homeTeam;
  Team awayTeam;
  Score score;

  @Override
  public String toString() {
    return "Match{" +
        "utcDate='" + utcDate + '\'' +
        ", status='" + status + '\'' +
        ", homeTeam=" + homeTeam +
        ", awayTeam=" + awayTeam +
        ", score=" + score +
        '}';
  }

  public String getUtcDate() {
    return utcDate;
  }

  public String getStatus() {
    return status;
  }

  public String getHomeTeam() {
    return homeTeam.name;
  }

  public String getAwayTeam() {
    return awayTeam.name;
  }

  public String getScore() {
    return score.fullTime.homeTeam + ":" + score.fullTime.awayTeam;
  }

  class Team {
    String name;

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

    @Override
    public String toString() {
      return "Score{" +
          "fullTime=" + fullTime +
          '}';
    }
  }



}
