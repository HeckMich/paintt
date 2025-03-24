module com.example.paintt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.paintt to javafx.fxml;
    exports com.example.paintt;
}