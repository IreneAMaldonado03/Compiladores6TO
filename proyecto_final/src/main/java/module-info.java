module fes.aragon.proyecto.proyecto_final {
    requires javafx.controls;
    requires javafx.fxml;
    requires jlayer;


    opens fes.aragon.proyecto to javafx.fxml;
    exports fes.aragon.proyecto;
}