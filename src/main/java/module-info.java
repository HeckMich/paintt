module com.example.paintt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires JTransforms;


    opens com.example.paintt to javafx.fxml;
    exports com.example.paintt;
}