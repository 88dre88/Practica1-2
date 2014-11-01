package tp.pr1.logica;

/**
 * Almacena la informacion del tablero de juego.
 */

public class Tablero {

	private Ficha[][] tablero;
	private int ancho;
	private int alto;

	// Construye un tablero vacio de las dimensiones 
	public Tablero(int ancho, int alto) {
		if(ancho <= 0 || alto <= 0){
			this.ancho = 1;
			this.alto = 1;
		}else{
			this.ancho = ancho;
			this.alto = alto;
		}
		tablero = new Ficha[ancho][alto];//creamos el array bidimensional de fichas e inicializamos todas sus casillas a Ficha.Vacia
		reiniciar();//
	}

	/**
	 * Acceso al atributo alto de la clase
	 * 
	 * @return Numero de filas del tablero
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * Acceso al atributo ancho de la clase
	 * 
	 * @return Numero de columnas del tablero
	 */
	public int getAncho() {
		return ancho;
	}
	
	public Ficha[][] getTablero(){
		return tablero;
	}
	
	/**
	 * Comprueba si existe una casilla.
	 * @return true si existe la casilla seleccionada y false en caso contrario.
	 * 
	 */
	public boolean existeCasilla(int x, int y) {/*
		 * si elegimos una coordenada que este
		 * fuera del rango de nuestro tablero,
		 * devolvera false. Esto lo utilizamos
		 * despues para colocar una ficha o para
		 * obtener la informacion de una casilla. Metodo 
		 * privado porque solo se usa internamente en la 
		 * clase.
		 */
		return !((x > ancho|| x <= 0) || (y > alto || y <= 0));
	}
	/**
	 * Obtenemos informacion de una casilla concreta.
	 * 
	 * @param x
	 *            -Numero de la columna[1..ancho]
	 * @param y
	 *            -Numero de fila[1...alto]
	 * @return Ficha donde se almacena la informacion de la casilla seleccionada
	 * 
	 */
	public Ficha getCasilla(int x, int y) {
		// devuelve enumerado con la informacion de la casilla seleccionada
		Ficha color = Ficha.VACIA;
		// comprobamos que las coordenadas son correctas respecto al tamaño del tablero
		if (existeCasilla(x,y)) {
			color = tablero[x-1][y-1];// hay que restar uno ya que los indices de un array van de 0 a n-1
		}
		return color; // Devuelve el color de la ficha(vacía si no hay ficha) existente en la casilla o null si no existe la casilla
	}
	
	/**
	 * Permite colocar una ficha en una casilla
	 * 
	 * @param x-Numero de la columna[1..ancho]        
	 * @param y-Numero de fila[1...alto]   
	 */
	public void setCasilla(int x, int y, Ficha color) {
		if (existeCasilla(x,y) && getCasilla(x,y) == Ficha.VACIA) {
			tablero[x-1][y-1] = color;
		} 
	}
	
	/**
	 * Comprueba si una casilla esta vacia.
	 * @return true si esta vacia y false en caso contrario
	 */
	public boolean casillaVacia(int x,int y){
		return (existeCasilla(x,y) && tablero[x][y] == Ficha.VACIA);
	}

	/** Resetea el tablero a su estado inicial
	 * 
	 */
	public void reiniciar(){//recorremos el array bidimensional de fichas y cambiamos el valor de cada casilla a Ficha.Vacia
		for(int i = 0;i < ancho;i ++){
			for(int j = 0;j < alto;j ++){
				tablero[i][j] = Ficha.VACIA;
			}
		}
	}
	
	/**
	 * Muestra una representacion del estado del tablero. Las casillas vacias se representan con un espacio en blanco, las 
	 * fichas negras con una O y las fichas blancas con una X;
	 */
	public String toString() {
		
		String resultado = "";
		for(int i = 0;i < alto;i ++){
			resultado += " |";
			for(int j = 0;j < ancho;j ++){
				Ficha ficha = tablero[j][i];
				switch(ficha){
					case VACIA:
						resultado += ' ';
						break;
					case BLANCAS:
						resultado += 'X';
						break;
					case NEGRAS:
						resultado += 'O';
						break;
				}
			}
			resultado += "|\n";
		}
		for(int i = 0;i < 2;i ++){
			if(i == 0){
				resultado += " +";
				for(int j = 0;j < ancho;j ++){
					resultado += '-';
				}
				resultado += "+\n";
			}else{
				resultado += "  ";
				for(int j = 0;j < ancho;j ++){
					resultado += j + 1;
				}
				resultado += " \n";
			}
		}
		return resultado;
	}
	
}