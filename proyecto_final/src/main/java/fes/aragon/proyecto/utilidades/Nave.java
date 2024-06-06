package fes.aragon.proyecto.utilidades;

import javafx.scene.image.ImageView;

public class Nave {
	private static Nave nave;
	ImageView naveImagen;
	String orientacion;
	double coordenadasX;
	double coordenadasY;
	
	private Nave() {
	}
	
	public static Nave getInstancia() {
		if(nave == null) {
			nave = new Nave();
		}
		return nave;
	}

	public ImageView getNaveImagen() {
		return naveImagen;
	}

	public void setNaveImagen(ImageView naveImagen) {
		this.naveImagen = naveImagen;
	}

	public String getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}

	public double getCoordenadasX() {
		return coordenadasX;
	}

	public void setCoordenadasX(double coordenadasX) {
		this.coordenadasX = coordenadasX;
	}

	public double getCoordenadasY() {
		return coordenadasY;
	}

	public void setCoordenadasY(double coordenadasY) {
		this.coordenadasY = coordenadasY;
	}
	
	
}
