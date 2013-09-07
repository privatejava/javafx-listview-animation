package np.com.ngopal;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import np.com.ngopal.animation.AnimationType;
import np.com.ngopal.control.cell.AnimatedListCell;

/**
 *
 * @author Narayan G. Maharjan <me@ngopal.com.np>
 */
public class ListViewAnimation extends Application {
    ObservableList list = FXCollections.observableArrayList();

    ListView<String> listView;

    ComboBox<AnimationType> box;

    HBox hbox;

    AnchorPane root;

    Button btn;

    /**
     * For initializing Containers
     */
    public void initContainers() {
        root = new AnchorPane();
        hbox = new HBox(10);

        AnchorPane.setBottomAnchor(listView, 50d);
        AnchorPane.setTopAnchor(listView, 10d);
        AnchorPane.setLeftAnchor(listView, 10d);
        AnchorPane.setRightAnchor(listView, 10d);
        AnchorPane.setBottomAnchor(hbox, 10d);
        AnchorPane.setLeftAnchor(hbox, 10d);
    }

    /**
     * For initializing controls
     */
    public void initControls() {
        listView = new ListView<>();
        listView.setCellFactory(AnimatedListCell.forListView(AnimationType.ROTATE_RIGHT, AnimationType.FADE_OUT));


        box = new ComboBox<>();
        box.valueProperty().addListener(new ChangeListener<AnimationType>() {
            @Override
            public void changed(
                    ObservableValue<? extends AnimationType> ov, AnimationType t, AnimationType t1) {
                if (!t1.equals(t)) {
                    listView.setCellFactory(AnimatedListCell.forListView(t1));
                }
            }
        });

        btn = new Button("Add New");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                list.add("Added New");
            }
        });


    }

    @Override
    public void start(Stage stage) throws Exception {
        //Loading custom fonts
        Font.loadFont(getClass().getResource("fonts/segoesc.ttf").toExternalForm(), 12);

        //adding values to list
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }

        //Initializing Controls
        initControls();
        initContainers();

        //Adding Values
        listView.setItems(list);
        box.getItems().addAll(AnimationType.values());

        //Adding controls to container
        hbox.getChildren().addAll(new Label("Animation Type:"), box, btn);
        root.getChildren().addAll(listView, hbox);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        scene.setCamera(new PerspectiveCamera());
        stage.setTitle("List Animation!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
