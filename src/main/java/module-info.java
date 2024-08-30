module com.app.jdlv.jdlv {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.app.jdlv.jdlv to javafx.fxml;
    exports com.app.jdlv.jdlv;
}