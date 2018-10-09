package net.ddns.pcuniverse.studentliveadmin.main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Window;

import java.util.Arrays;
import java.util.List;

public class CustomDialog extends CustomDialogSkin {

    private VBox contentPane;

    public CustomDialog(Window parent, String heading, String body, Button... buttons) {
        initOwner(parent);
        Text headingText = new Text(heading);
        headingText.getStyleClass().add("custom-dialog-heading");
        Text bodyText = new Text(body);
        bodyText.getStyleClass().add("custom-dialog-body");
        List<Button> actionButtons = Arrays.asList(buttons);
        for (Button button : actionButtons) {
            button.setOnAction(e -> {
                setCustomResult(actionButtons.indexOf(e.getSource()) + 1);
                closeAnimation();
            });
        }
        HBox buttonPane = new HBox((Button[])actionButtons.toArray());
        buttonPane.setAlignment(Pos.CENTER);
        contentPane = new VBox(headingText, bodyText, buttonPane);
        contentPane.setMinWidth(500);
        contentPane.setMaxWidth(500);
        setWidth(500);
        contentPane.setStyle("-fx-background-color: white");
        contentPane.setAlignment(Pos.CENTER);
        contentPane.setSpacing(15);
        contentPane.setPadding(new Insets(15));
        getDialogPane().setContent(contentPane);
    }


}
