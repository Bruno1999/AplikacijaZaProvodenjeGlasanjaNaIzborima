module com.example.javafxaplikacijazaprovodenjeglasanjanaizborima {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.slf4j;


    opens com.example.javafxaplikacijazaprovodenjeglasanjanaizborima to javafx.fxml;
    exports com.example.javafxaplikacijazaprovodenjeglasanjanaizborima;

}