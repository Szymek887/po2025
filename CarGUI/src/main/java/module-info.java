module com.szymczak.cargui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.szymczak.cargui to javafx.fxml;
    exports com.szymczak.cargui;
}