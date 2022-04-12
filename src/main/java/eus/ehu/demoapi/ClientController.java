package eus.ehu.demoapi;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.util.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClientController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private ComboBox<String> comboDates;

  @FXML
  private Button btnCheck;

  @FXML
  private ListView<Match> lstAreaResult;

  private List<Match> matches;
  private ObservableList<Match> listMatches;

  private ObservableList<String> listDates;

  @FXML
  void check(ActionEvent event) {
    matches = Manager.get().getMatches();
    listMatches = FXCollections.observableArrayList(matches);
    lstAreaResult.setItems(listMatches);
  }

  @FXML
  void initialize() {

    HashMap<String, String> teamNames = Manager.get().loadTeamNames();

    List<String> matchDates = Manager.get().getMatchDates();
    listDates =  FXCollections.observableArrayList(matchDates);
    comboDates.setItems(listDates);

    lstAreaResult.setCellFactory(new Callback<ListView<Match>, ListCell<Match>>() {
      @Override
      public ListCell<Match> call(ListView<Match> matchListView) {
        return new ListCell<>(){
          @Override
          public void updateItem(Match match, boolean empty) {
            super.updateItem(match, empty);
            if (empty || match == null) {
              setText(null);
            } else {
              setText(teamNames.get( match.getHomeTeam() ) + "-" + teamNames.get(match.getAwayTeam()));
            }
          }
        };
      }

    });


  }
}
