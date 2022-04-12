package eus.ehu.demoapi;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Manager {

  public static Manager manager;

  private List<Match> matches;

  public static Manager get() {
    if (manager == null) {
      manager = new Manager();
    }

    return manager;
  }

  private Manager() {

  }

  private InputStream getFileFromResourceAsStream(String fileName) {

    // The class loader that loaded the class
    ClassLoader classLoader = getClass().getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(fileName);

    // the stream holding the file content
    if (inputStream == null) {
      throw new IllegalArgumentException("file not found! " + fileName);
    } else {
      return inputStream;
    }

  }

  private String request(String endpoint) {
    // Interesting endpoints
    // .url("https://api.football-data.org/v2/competitions/2014/standings")
    // .url("https://api.football-data.org/v2/competitions/2014/teams")
    // .url("https://api.football-data.org/v2/competitions/2014")
    // .url("https://api.football-data.org/v2/competitions")

    String result = "";

    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
        .url("https://api.football-data.org/v2/" + endpoint)
        .get()
        .addHeader("X-Auth-Token", System.getenv("TOKEN"))
        .build();

    try {
      Response response = client.newCall(request).execute();
      if (response.code() == 200) {
        result = response.body().string();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return result;
  }


  public HashMap<String, String> loadTeamNames() {
    Gson gson = new Gson();

    JsonObject jsonObject;
    // if file exists
    try {
      System.out.println("From cache");
      Reader reader = new InputStreamReader(getFileFromResourceAsStream("teams.json"));
      jsonObject = gson.fromJson(reader, JsonObject.class);
    } catch (Exception e) {
      System.out.println("From HTTPS");
      jsonObject = gson.fromJson(request("competitions/2014/teams"), JsonObject.class);
    }

    Type matchListType = new TypeToken<ArrayList<Team>>() {}.getType();
    ArrayList<Team> teams = gson.fromJson((jsonObject.get("teams")), matchListType);

    HashMap<String, String> mapTeams = new HashMap<>();
    for (Team team :
        teams) {
        mapTeams.put( team.getName(), team.getShortName());
    }

    return mapTeams;

  }

  public List<Match> getMatches() {
    if (matches == null) {
      Gson gson = new Gson();

      JsonObject jsonObject;
      // if file exists
      try {
        System.out.println("From cache");
        Reader reader = new InputStreamReader(getFileFromResourceAsStream("matches.json"));
        jsonObject = gson.fromJson(reader, JsonObject.class);
      } catch (Exception e) {
        System.out.println("From HTTPS");
        jsonObject = gson.fromJson(request("competitions/2014/matches"), JsonObject.class);
      }


      Type matchListType = new TypeToken<ArrayList<Match>>() {
      }.getType();
      matches = gson.fromJson((jsonObject.get("matches")), matchListType);
    }

    return matches;
  }

  public List<String> getMatchDates() {
    List<Match> listMatches = getMatches();
    return listMatches.stream().map(match -> match.getUtcDate().split("T")[0]).distinct().collect(Collectors.toList());
  }
}
