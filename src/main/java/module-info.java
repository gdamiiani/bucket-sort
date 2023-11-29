module org.gustavo.bucketsort {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.gustavo.bucketsort to javafx.fxml;
    exports org.gustavo.bucketsort;
    exports org.gustavo.bucketsort.enums;
}