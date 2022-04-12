package eus.ehu.demoapi;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class Test {

  // get a file from the resources folder
  // works everywhere, IDEA, unit test and JAR file.
  public InputStream getFileFromResourceAsStream(String fileName) {

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

  public static void main(String[] args) throws IOException {
    Test test = new Test();
    test.go5();


  }

  private void go4() throws IOException {
    Gson gson = new Gson();
    Reader reader = new InputStreamReader(getFileFromResourceAsStream("matches.json"));

    JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

   // JsonObject jo = (JsonObject) map.get("matches");

    for (JsonElement je: jsonObject.get("matches").getAsJsonArray()) {
      System.out.println( je.getAsJsonObject().get("utcDate").getAsString() +
                          je.getAsJsonObject().get("status") +
                          je.getAsJsonObject().get("homeTeam").getAsJsonObject().get("name") +
                          je.getAsJsonObject().get("awayTeam").getAsJsonObject().get("name") +
                          je.getAsJsonObject().get("score").getAsJsonObject().get("fullTime").getAsJsonObject().get("homeTeam")+":"+
                          je.getAsJsonObject().get("score").getAsJsonObject().get("fullTime").getAsJsonObject().get("awayTeam")
                );
    }


//    // print map entries
//    for (Map.Entry<?, ?> entry : map.entrySet()) {
//      // System.out.println(entry.getKey() + "=" + entry.getValue());
//      System.out.println(entry.getKey());
//    }

    // close reader
    reader.close();
   // '.matches[]| .utcDate, " " , .homeTeam.name, " - ", .awayTeam.name," ", .score.fullTime.homeTeam,":", .score.fullTime.awayTeam, "\n"'

  }

  private void go5() {
    Gson gson = new Gson();
    Reader reader = new InputStreamReader(getFileFromResourceAsStream("matches.json"));
    JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
    Type matchListType = new TypeToken<ArrayList<Match>>() {}.getType();
    ArrayList<Match> matches = gson.fromJson((jsonObject.get("matches")), matchListType);
    for (Match m : matches) {
        System.out.println(m);
    }
  }

  private void go2() {
    Gson gson = new Gson();
    Reader reader = new InputStreamReader(getFileFromResourceAsStream("test.json"));
    JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
    Type competitionListType = new TypeToken<ArrayList<Competition>>() {}.getType();
    ArrayList<Competition> competitions = gson.fromJson((jsonObject.get("competitions")), competitionListType);
    for (Competition c : competitions) {
      if (c.area.countryCode.equals("ESP")){
        System.out.println(c);
      }
    }
  }

  private void go3() {
    Gson gson = new Gson();
    Reader reader = new InputStreamReader(getFileFromResourceAsStream("test.json"));
    JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

    for (JsonElement je: jsonObject.get("competitions").getAsJsonArray()) {
      System.out.println(je.getAsJsonObject() ); // .get("area")); // .getAsJsonObject().get("countryCode"));
    }

  //  gson.fromJson(jsonObject.get("competitions").getAsJsonArray().get(0));
//    for (Competition c : competitions) {
//      if (c.area.countryCode.equals("ESP")){
//        System.out.println(c);
//      }
//    }
  }

  private void go1() throws IOException {
    Gson gson = new Gson();
    Reader reader = new InputStreamReader(getFileFromResourceAsStream("test.json"));

    // convert JSON file to map
    Map<?, ?> map = gson.fromJson(reader, Map.class);
    // print map entries
    for (Map.Entry<?, ?> entry : map.entrySet()) {
      System.out.println(entry.getKey() + "=" + entry.getValue());
    }

    // close reader
    reader.close();
    // Reader reader = Files.newBufferedReader(Paths.get("test.json"));
  }
}
