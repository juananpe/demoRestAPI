package eus.ehu.demoapi;

public class Competition {
  int id;
  Area area;
  String name;
  String code;
  String emblemUrl;
  String plan;

  Season currentSeason;
  int numberOfAvailableSeasons;
  String lastUpdated;

  class Area {
    int id;
    String name;

    @Override
    public String toString() {
      return "Area{" +
          "id=" + id +
          ", name='" + name + '\'' +
          ", countryCode='" + countryCode + '\'' +
          ", ensignUrl='" + ensignUrl + '\'' +
          '}';
    }

    String countryCode;
    String ensignUrl;
  }


  @Override
  public String toString() {
    return "Competition{" +
            "id=" + id +
            ", area=" + area +
            ", name='" + name + '\'' +
            ", code='" + code + '\'' +
            ", emblemUrl='" + emblemUrl + '\'' +
            ", plan='" + plan + '\'' +
            ", currentSeason=" + currentSeason +
            ", numberOfAvailableSeasons=" + numberOfAvailableSeasons +
            ", lastUpdated='" + lastUpdated + '\'' +
            '}';
  }

  class Season {
    int id;
    String startDate;
    String endDate;
    int currentMatchday;
    Object winner;

    @Override
    public String toString() {
      return "Season{" +
          "id=" + id +
          ", startDate='" + startDate + '\'' +
          ", endDate='" + endDate + '\'' +
          ", currentMatchday=" + currentMatchday +
          ", winner=" + winner +
          '}';
    }
  }


}
