package fes.aragon.proyecto;

import fes.aragon.proyecto.utilidades.Nave;
import fes.aragon.proyecto.utilidades.Tokens;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

import javafx.animation.TranslateTransition;

public class InicioController {

    @FXML
    private Button btnCompilar;

    @FXML
    private Pane paneEscenario;

    @FXML
    private TextArea txtCode;

    private Lexico lexico;
    private Tokens token;
    private TranslateTransition transition;
    private Nave nave;

    private Queue<Runnable> colaAnimaciones = new LinkedList<>();
    private boolean isAnimacion = false;

    @FXML
    void initialize() {
        pintarEscenario();
    }

    public InicioController() {
        nave = Nave.getInstancia();
    }

    @FXML
    void getCode(ActionEvent event) throws InterruptedException, IOException {
        String code = txtCode.getText();
        String[] lineas = code.split("\\n");
        String orientacion = "arriba";

        for (String linea : lineas) {
            Reader rd = new BufferedReader(new StringReader(linea));
            lexico = new Lexico(rd);
            token = lexico.yylex();

            if (token == Tokens.ARRIBA) {
                String ruta = getClass().getResource("/imagenes/arriba.png").toExternalForm();
                Image imagen = new Image(ruta);
                orientacion = "arriba";
                addAnimacionGiroCola(imagen, orientacion);
            }
            if (token == Tokens.ABAJO) {
                String ruta = getClass().getResource("/imagenes/abajo.png").toExternalForm();
                Image imagen = new Image(ruta);
                orientacion = "abajo";
                addAnimacionGiroCola(imagen, orientacion);
            }
            if (token == Tokens.DERECHA) {
                String ruta = getClass().getResource("/imagenes/derecha.png").toExternalForm();
                Image imagen = new Image(ruta);
                orientacion = "derecha";
                addAnimacionGiroCola(imagen, orientacion);
            }
            if (token == Tokens.IZQUIERDA) {
                String ruta = getClass().getResource("/imagenes/izquierda.png").toExternalForm();
                Image imagen = new Image(ruta);
                orientacion = "izquierda";
                addAnimacionGiroCola(imagen, orientacion);
            }
            if (token == Tokens.MOVER) {
                token = lexico.yylex();
                int casillasMover = Integer.parseInt(lexico.lexema);
                addAnimacionMovimientoCola(casillasMover, orientacion);
                nave.getNaveImagen().setLayoutX(nave.getCoordenadasX());
                nave.getNaveImagen().setLayoutY(nave.getCoordenadasY());
            }
        }

        processQueue();
    }

    public void addAnimacionMovimientoCola(int casillasDesplazar, String orientacion) {
        colaAnimaciones.add(() -> moverNave(casillasDesplazar, orientacion));
    }

    public void addAnimacionGiroCola(Image imagen, String orientacion) {
        colaAnimaciones.add(() -> orientarNave(imagen, orientacion));
    }

    private void processQueue() {
        if (!isAnimacion && !colaAnimaciones.isEmpty()) {
            Runnable siguienteAnimacion = colaAnimaciones.poll();
            if (siguienteAnimacion != null) {
                isAnimacion = true;
                siguienteAnimacion.run();
            }
        }
    }

    private void moverNave(int casillasDesplazar, String naveHorientacion) {
        double distanciaDesplazar = verificarDistancia(casillasDesplazar, naveHorientacion);
        transition = new TranslateTransition(Duration.seconds(5), nave.getNaveImagen());

        switch (naveHorientacion) {
            case "arriba":
                transition.setByY(-distanciaDesplazar);
                nave.setCoordenadasY(nave.getCoordenadasY() - distanciaDesplazar);

                break;
            case "abajo":
                transition.setByY(distanciaDesplazar);
                nave.setCoordenadasY(nave.getCoordenadasY() + distanciaDesplazar);

                break;
            case "izquierda":
                transition.setByX(-distanciaDesplazar);
                nave.setCoordenadasX(nave.getCoordenadasX() - distanciaDesplazar);

                break;
            case "derecha":
                transition.setByX(distanciaDesplazar);
                nave.setCoordenadasX(nave.getCoordenadasX() + distanciaDesplazar);

                break;
        }

        //si todavia no finaliza la animacion
        transition.setOnFinished(e -> {
            System.out.println("Animación finalizada");
            isAnimacion = false;
            processQueue();
        });
        System.out.println("coordenadas x " + nave.getCoordenadasX());
        System.out.println("coordenadas y " + nave.getCoordenadasY());
        System.out.println("imagen x " + nave.getNaveImagen().getLayoutX());
        System.out.println("imagen y " + nave.getNaveImagen().getLayoutY());
        transition.play();
    }

    public double verificarDistancia(int casillasDesplazar, String naveHorientacion) {
        double distanciaDesplazar;
        double distanciaMaxima = 0;
        distanciaDesplazar = casillasDesplazar * 80;


        if (nave.getNaveImagen().getLayoutX() + distanciaDesplazar > paneEscenario.getWidth()
                || nave.getNaveImagen().getLayoutY() > paneEscenario.getHeight()) {

            switch (naveHorientacion) {
                case "arriba":
                    distanciaMaxima = paneEscenario.getHeight() - nave.getNaveImagen().getLayoutY() + 5;
                    break;
                case "abajo":
                    distanciaMaxima = paneEscenario.getHeight() - nave.getNaveImagen().getLayoutY() - 75;
                    break;
                case "izquierda":
                    distanciaMaxima = paneEscenario.getWidth() - nave.getNaveImagen().getLayoutX() + 5;
                    break;
                case "derecha":
                    distanciaMaxima = paneEscenario.getWidth() - nave.getNaveImagen().getLayoutX() - 75;
                    break;

            }
            distanciaDesplazar = distanciaMaxima;
        }
        return distanciaDesplazar;
    }

    private void orientarNave(Image imagen, String orientacion) {
        double posX = nave.getCoordenadasX();
        double posY = nave.getCoordenadasY();

        paneEscenario.getChildren().remove(nave.getNaveImagen());

        nave.setNaveImagen(new ImageView(imagen));
        nave.setOrientacion(orientacion);
        nave.setCoordenadasX(posX);
        nave.setCoordenadasY(posY);

        nave.getNaveImagen().setLayoutX(posX);
        nave.getNaveImagen().setLayoutY(posY);

        paneEscenario.getChildren().add(nave.getNaveImagen());

        isAnimacion = false;
        processQueue();
    }

    private void posicionarImagen(int casillaX, int casillaY, String orientacion) {
        // Ruta del recurso relativa a la carpeta resources
        String imagePath = "/imagenes/" + orientacion + ".png";

        // Obtener la URL del recurso
        URL resourceUrl = getClass().getResource(imagePath);
        if (resourceUrl == null) {
            throw new IllegalArgumentException("El recurso no se encuentra: " + imagePath);
        }

        // Crear la imagen
        Image imagen = new Image(resourceUrl.toExternalForm());
        nave.setNaveImagen(new ImageView(imagen));

        double centerX = casillaX * 80 + 40;
        double centerY = casillaY * 80 + 40;

        double imageX = centerX - 37.5;
        double imageY = centerY - 37.5;

        nave.setOrientacion(orientacion);
        nave.setCoordenadasX(imageX);
        nave.setCoordenadasY(imageY);

        nave.getNaveImagen().setLayoutX(imageX);
        nave.getNaveImagen().setLayoutY(imageY);
    }

    private void pintarEscenario() {
        pintarLineasVerticales();
        pintarLineasHorizontales();
        posicionarImagen(0, 0, "arriba");
        paneEscenario.getChildren().add(nave.getNaveImagen());
    }

    private void pintarLineasVerticales() {
        int startX = 80;
        int endX = 80;

        for (int i = 0; i < 8; i++) {
            Line line = new Line();
            line.setStartX(startX);
            line.setStartY(0);
            line.setEndX(endX);
            line.setEndY(640);
            paneEscenario.getChildren().add(line);
            startX += 80;
            endX += 80;
        }
    }

    private void pintarLineasHorizontales() {
        int startY = 80;
        int endY = 80;
        for (int i = 0; i < 8; i++) {
            Line line = new Line();
            line.setStartX(0);
            line.setStartY(startY);
            line.setEndX(640);
            line.setEndY(endY);
            paneEscenario.getChildren().add(line);
            startY += 80;
            endY += 80;
        }
    }
}
