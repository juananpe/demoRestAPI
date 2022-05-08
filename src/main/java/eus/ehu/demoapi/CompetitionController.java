package eus.ehu.demoapi;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.embed.swing.SwingFXUtils;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CompetitionController {

    private List<Competition> competitions = new ArrayList<Competition>();
    int current = 0;
    int max;

    @FXML
    private TextField acountrycode;

    @FXML
    private TextField aid;

    @FXML
    private TextField aname;

    @FXML
    private TextField cid;

    @FXML
    private TextField cname;

    @FXML
    private ImageView emblem;

    @FXML
    private Button next;

    @FXML
    private Button previous;


    @FXML
    void clickNext(ActionEvent event) {
        if (current < max) current++;
        show();
    }

    @FXML
    void clickPrevious(ActionEvent event) {
        if (current > 0) current--;
        show();
    }

    @FXML
    void initialize() {

        String body = Utils.request("competitions");
        Gson gson = new Gson();
        JsonObject jsonObject;

        jsonObject = gson.fromJson(body, JsonObject.class);
        Type competitionListType = new TypeToken<ArrayList<Competition>>(){}.getType();
        competitions = gson.fromJson((jsonObject.get("competitions")), competitionListType);
        max = competitions.size();

        show();

    }

    private void show(){
        aid.setText(String.valueOf(competitions.get(current).area.id));
        aname.setText(competitions.get(current).area.name);
        acountrycode.setText(String.valueOf(competitions.get(current).area.countryCode));
        cid.setText(String.valueOf(competitions.get(current).id));
        cname.setText(competitions.get(current).name);

        // System.out.println(competitions.get(current));

        String emblemUrl = competitions.get(current).emblemUrl;
        System.out.println(emblemUrl);
        if (emblemUrl != null)  System.out.println(emblemUrl.substring(emblemUrl.length() - 3 ));

        if (emblemUrl != null && emblemUrl.substring(emblemUrl.length() - 3 ).equals("svg")) {
            BufferedImageTranscoder transcoder = new BufferedImageTranscoder();
            try (InputStream file = new URL(emblemUrl).openStream();) {
                TranscoderInput transIn = new TranscoderInput(file);
                try {
                    transcoder.transcode(transIn, null);
                    Image img = SwingFXUtils.toFXImage(transcoder.getBufferedImage(), null);
                    if (img.isError()) {
                        System.out.println("Error loading image from " + emblemUrl);
                        // if you need more details
                        // img.getException().printStackTrace();
                    }
                    emblem.setImage(img);
                } catch (TranscoderException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (emblemUrl != null && emblemUrl.substring(emblemUrl.length() - 3 ).equals("png")) {
            Image image = new Image(emblemUrl);
            emblem.setImage(image);
        } else {
            emblem.setImage(null);
        }
    }
}
