package eus.ehu.demoapi;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.cell.CheckBoxListCell;
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
  private ComboBox<MatchDay> comboDates;

  @FXML
  private Button btnCheck;

  @FXML
  private ListView<Match> lstAreaResult;

  private ObservableList<Match> listMatches =  FXCollections.observableArrayList();

  private ObservableList<MatchDay> listDates = FXCollections.observableArrayList();

  @FXML
  void check(ActionEvent event) {
    listMatches.setAll(Manager.get().getMatches());
    lstAreaResult.setItems(listMatches);
  }

  @FXML
  void initialize() {

    HashMap<String, String> teamNames = Manager.get().loadTeamNames();

    List<MatchDay> matchDates = Manager.get().getMatchDates();
    listDates =  FXCollections.observableArrayList(matchDates);
    comboDates.setItems(listDates);

    lstAreaResult.setCellFactory(new Callback<>() {
      @Override
      public ListCell<Match> call(ListView<Match> matchListView) {
        return new ListCell<>() {
          @Override
          public void updateItem(Match match, boolean empty) {
            super.updateItem(match, empty);
            if (empty || match == null) {
              setText(null);
            } else {
              setText(teamNames.get(match.getHomeTeam()) + "-" + teamNames.get(match.getAwayTeam()));
            }
          }
        };
      }

    });

    // comboMathDays
    Callback<ListView<MatchDay>, ListCell<MatchDay>> cellFactory = new Callback<>() {
      @Override
      public ListCell<MatchDay> call(ListView<MatchDay> l) {
        return new ListCell<>() {
          @Override
          protected void updateItem(MatchDay item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null || empty) {
              setGraphic(null);
            } else {
              setText(item.getMatchDay() + "   " + item.getUtcDate().toInstant().toString().split("T")[0]);
            }
          }
        };
      }
    };

    comboDates.setButtonCell(cellFactory.call(null));
    comboDates.setCellFactory(cellFactory);

    // when selecting a matchDay, show all the matches for that matchDay
    comboDates.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
      System.out.println(newValue);
      listMatches.setAll(Manager.get().getMatches(newValue));
      lstAreaResult.setItems(listMatches);

    });
  }
}
