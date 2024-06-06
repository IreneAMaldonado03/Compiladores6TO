package fes.aragon.proyecto;

import fes.aragon.proyecto.musica.MusicaCiclica;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Inicio extends Application {

    private Thread hiloFondo;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Inicio.fxml"));
        MusicaCiclica entrada = new MusicaCiclica("musica_entrada");
        hiloFondo = new Thread(entrada);
        hiloFondo.start();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Proyecto final");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        hiloFondo.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}