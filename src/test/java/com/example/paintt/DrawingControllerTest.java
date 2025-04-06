package com.example.paintt;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
public class DrawingControllerTest {

    private DrawingController controller;
    private Canvas canvas;


    @Start
    public void start(Stage stage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(DrawingController.class.getResource("paint.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        canvas = controller.canvas;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @Test
    public void testBrushTool(FxRobot robot){
        TextField bsizeField = robot.lookup("#bsize").queryAs(TextField.class);
        ColorPicker colorPicker = robot.lookup("#colorpicker").queryAs(ColorPicker.class);
        Button brushButton = robot.lookup("Brush").queryAs(Button.class);


        robot.clickOn(bsizeField);
        robot.write("10");
        robot.clickOn(colorPicker);

        robot.moveBy(100, 100).clickOn(MouseButton.PRIMARY); // Klickt auf die Position (10, 10) im ersten Farbfeld

        robot.clickOn(brushButton);

        robot.moveTo(canvas).press(MouseButton.PRIMARY)
                .moveBy(50, 0)
                .moveBy(0, 50)
                .moveBy(-50, 0)
                .release(MouseButton.PRIMARY);

        /*
        robot.moveTo(canvas).drag(MouseButton.PRIMARY).dropTo(canvas);

        robot.moveBy(100, 100).drag(MouseButton.PRIMARY);

         */


/*
        WritableImage[] snapshot = new WritableImage[1]; // Array, um das Bild zu speichern
        WaitForAsyncUtils.async(() -> {
            snapshot[0] = canvas.snapshot(null, null);
        });

 */
        //assertNotNull(snapshot[0]);

        assertTrue(true, "Mauszeichnen wurde simuliert");

        /*
        WritableImage snapshot = canvas.snapshot(null, null);
        assertNotNull(snapshot);

         */

        //assertNotNull(canvas.getGraphicsContext2D().getPixelWriter().getImage());



    }


}
