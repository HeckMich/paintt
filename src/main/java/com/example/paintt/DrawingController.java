package com.example.paintt;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DrawingController implements Initializable {


    @FXML
    private ColorPicker colorpicker;

    @FXML
    private TextField bsize;

    @FXML
    public Canvas canvas;


    boolean toolSelected = false;

    GraphicsContext brushTool;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        brushTool = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged( e -> {
            double size = Double.parseDouble(bsize.getText()); // gets Textsize and stores it in variable "size"
            //get coordinates of mouse
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;

            if(toolSelected && !bsize.getText().isEmpty()){
                brushTool.setFill(colorpicker.getValue());
                brushTool.fillRoundRect(x, y, size, size, size, size );
            }
        });
    }

    @FXML
    public void newcanvas(ActionEvent e){

        TextField getCanvasWidth = new TextField();
        getCanvasWidth.setPromptText("Width");
        getCanvasWidth.setPrefWidth(150);
        getCanvasWidth.setAlignment(Pos.CENTER);

        TextField getCanvasHeight = new TextField();
        getCanvasHeight.setPromptText("Height");
        getCanvasHeight.setPrefWidth(150);
        getCanvasHeight.setAlignment(Pos.CENTER);

        Button createButton = new Button();
        createButton.setText("Create Canvas");

        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(getCanvasWidth, getCanvasHeight, createButton);

        Stage createStage = new Stage();
        AnchorPane root = new AnchorPane();
        root.setPrefWidth(200);
        root.setPrefHeight(200);
        root.getChildren().add(vBox);   //adds vBox to Stage

        Scene canvasScene = new Scene(root);
        createStage.setTitle("Create Canvas");
        createStage.setScene(canvasScene);
        createStage.show();

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                double canvasWidthReceived = Double.parseDouble(getCanvasWidth.getText());
                double canvasHeightReceived = Double.parseDouble(getCanvasHeight.getText());
                canvas = new Canvas();          //alter Canvas wird wiederverwendet
                canvas.setWidth(canvasWidthReceived);
                canvas.setHeight(canvasHeightReceived);
                vBox.getChildren().add(canvas);
                createStage.close();
            }
        });

    }

    @FXML
    public void toolselected(ActionEvent e) {
        toolSelected = true;
    }


}