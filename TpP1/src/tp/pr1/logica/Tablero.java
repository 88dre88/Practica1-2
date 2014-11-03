package tp.pr1.logica;

public class Tablero {

	//Atributos
	private Ficha[][] tablero;
	private int ancho;
	private int alto;
	
	//Constructora
	/**
	 * Construye un tablero vacio
	 * @param tx- Tamaño de columnas
	 * @param ty- Tamaño de filas
	 */
	public Tablero(int tx,int ty){
		
		ancho = tx;//Columna
		alto = ty;//Fila
		
		if( tx <= 0 || ty <= 0){
			ancho = 1;
			alto = 1;
		}
		
		//Creamos el array bidimensional
		tablero = new Ficha[ancho][alto];
		
		//Inicializamos las fichas
		reset();

	}
	
	
	//Metodos de consulta
	/**
	 * Metodo para obtener el alto del tablero
	 * @return Numero de filas del tablero
	 */
	public int getAlto(){
		return alto;
	}
	
	/**
	 * Metodo para obtener el alto del tablero
	 * @return Numero de columnas del tablero
	 */
	public int getAncho(){
		return ancho;
	}
	/**
	 * Metodo para acceder a la informacion de una casita
	 * @param x-Numero de columnas(ancho=
	 * @param y-Numero de fila(alto)
	 * @return Infomacion de la casilla. Si la casilla no valida, devuelve
	 * una Ficha VACIA
	 */
	public Ficha getCasilla(int x, int y){
		
		Ficha casilla = Ficha.VACIA;
		//Compruebo que la casilla es valida
		if(casillaOk(x-1,y-1)){
			//Si casilla valida devuelvo
			casilla = tablero[x-1][y-1];
		}
		return casilla;
		
	}
	/**
	 * Metodo privado, que se utiliza para comprobar si una
	 * coordenada esta fuera del rango del tablero
	 * @param x-Numero de columnas(ancho)
	 * @param y-Numero de fila(alto))
	 * @return Si esta en el rango True, en caso contrario False
	 */
	private boolean casillaOk(int x, int y){
		/*
		 * Si elegimos una casilla fuera del rango
		 * devolveremos false
		 * */

		//return ( (x<=ancho && x >= 0) || (y <= alto && y >= 0));
		
		return !((x > ancho || x < 0) || (y > alto || y < 0));
	
	}
	
	/**
	 * Permite especificar el nuevo contenido de una casilla.
	 * Tambien puede utilizarse para quitar una ficha
	 * @param x- Numero de colomunas(ancho)
	 * @param y-Numero de filas(alto)
	 * @param color-Color de casilla.Si es VACIA, celda sin ficha
	 */
	public void setCasilla(int x, int y, Ficha color){
		
		//Compruebo si la casilla es valida
		if(casillaOk(x-1,y-1)){
			
			if(color == Ficha.VACIA){
				tablero[x-1][y-1] = Ficha.VACIA;
			}
			else{
				tablero[x-1][y-1] = color;
			}
		}
		
	}
	/**
	 * Inicializa las casillas del tablero
	 * con fichas vacias
	 */
	public void reset(){
		//Cada casilla estara Vacia
		for(int x = 0; x < ancho; x++){
			for(int y = 0;y < alto; y++){
				tablero[x][y] = Ficha.VACIA;
			}
		}
	
	}
	
	/**
	 * Metodo que dibuja el tablero con su contenido
	 */
	public String toString(){
		
		String table = "";
		Ficha ficha = null;
		
		//Pintamos las filas desde arriba(alto)
		for( int x = 0; x < alto; x++){
			table+= "|"; //Concatenamos
			//Pintamos las columnas desde arriba(ancho)
			for(int y = 0; y < ancho; y++){
				//Comprobaos si existen fichas
				ficha = tablero[y][x];
				//Elegimos que pintar
				table +=contenidoCasilla(ficha);
			}
			
			table +="|\n";
		}
		//Pintamos la parte de abajo
		table += "+";//El primer + de la izquierda	
		
		for(int colum = 0; colum < ancho; colum++){
			table += "-";
			
		}
		table += "+\n";//El segundo + de la izquierda
		
		table += " ";
		for(int pos = 0; pos <ancho; pos++){
			table += pos+1;//Empieza desde el 1
		}
		table += " \n";
		
		return table;
	
}
	
	/**
	 * Funcion que me devuelve que pintar en cada casilla dependiendo del contenido
	 * de la casilla del tablero
	 */
	private String contenidoCasilla(Ficha ficha){
		
		String casilla = null;//Inicializo
		
		switch (ficha){
		case VACIA:
			casilla =" ";
		break;
		case BLANCA:
			casilla ="X";
		break;
		case NEGRA:
			casilla ="0";
		break;
		
		}
		
		return casilla;
	}
	
	//Pruebas
	public static void main(String[] args){
		
		Tablero pras = new Tablero(-5,7);
		
		System.out.println(pras);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
