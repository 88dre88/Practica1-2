package tp.pr1.logica;

public class Partida {

	private static final int TAM = 7;
	
	
	private Tablero tablero;
	private Ficha turno;
	private Ficha ganador;
	private boolean terminada;
	
	//Atributos para almacenar los movimientos
	private Registro registro;
	
	
	//Constructor
	/**
	 * Construye una partida (inicializa todos los atributos de la clase)
	 */
	public Partida(){
		
		tablero = new Tablero(TAM, TAM);//Instanciamos el tablero(Se crea el tablero)
		turno = Ficha.BLANCA; //Empiezan las blancas por defecto
		ganador = null; //Color indeterminador
		terminada = false;
		
		registro = new Registro();//Instanciamos variable para almacenar los movimientos
		
	}
	/**
	 * Reinicia la partida en curso(todos los atributos)
	 */
	public void reset(){
		
		reset();//Inicializa el tablero en la clase Tablero
		
		//Inicializo los movimientos
		registro.setNumUndo(-1);
		
		//El turno vuelve al color por defecto(blanco)
		turno = Ficha.BLANCA;
		
	}
	/**
	 * Ejecuta el movimiento indicado por el jugador	
	 * @param color-Del jugador que tiene turno.
	 * @param col-Colunma donde se quiere colocar
	 * @return- True si se moviento ejecutado. False en caso contrario
	 */
	public boolean ejecutaMovimiento(Ficha color, int col){
		boolean movOk = false;
		int fila = 0;
		
		//Si partida terminada no se ejecuta movimiento
		if(isTerminada()){
			System.err.println("La partida terminada ");
			return movOk;
		}
		
		if( color != getTurno()){
			System.err.println("No es el turno de "+color);
			return movOk;
		}
		
		
		if( col > tablero.getAncho() || col <= 0){
			
			System.err.println("Columna no valida");
			return movOk;
		}
		
		//Devuelve la casilla VACIA donde se puede mover la ficha
		fila = casillaVacia(col);
		
		if (fila == -1){
			//Si una columna esta llena 
			System.err.println("La columna "+col +" esta llena");
			return movOk;
		}else{
			//Coloco la casilla
			tablero.setCasilla(col, fila, color);
			
			
			//Guardo movimiento en undo
			registro.saveMov(col);	
			
			movOk = true;
		}
		
		
		return movOk;
	}

	
	/**
	 * Comprueba la casilla vacia en una columna, recorriendo las filas
	 * de abajo hacia arriba
	 * @param columna desde donde se hace la comprobacion
	 * @return posicion de la fila VACIA usando(columna)
	 */
	private int casillaVacia(int columna){
	
		boolean encontrado = false;	
		//Numero de filas en el tablero
		int fila = tablero.getAlto(); 
		//Posicion donde la casilla estara VACIA
		int pos = -1;
		
		//Compruebo si la casilla existe
		/**
		 * PUEDO USAR MI CASILLA OK EN LUGAR DE LOS GET NO?
		 */
		
		
		//Recorro las filas de abajo hacia arriba hasta encontrar una casilla VACIA
		while( !encontrado && fila > 0){
		
			if(tablero.getCasilla(columna, fila) == Ficha.VACIA )
			{	
				encontrado = true;
				pos = fila;//Devuelvo la fila donde la casilla esta VACIA
				
			}else{
				//Comprobamos la fila superior
				fila--;
			}
		}
		return pos;
	}
	
	
	/**
	 * Compruebo la ultima casilla que ha tenido movimiendo(distinta a VACIA),
	 * recorriendo las fillas de arriba hacia abajo.
	 * @param columna desde donde se hace la comprobacion
	 * @return posicion de la fila no VACIA(ULTIMO MOVIMIENTO) usando(columna)
	 */
	
	private int casillaLlena(int columna){
		
		boolean encontrado = false;	
		//Numero de filas en el tablero, empieza en 0
		int fila = 0; 
		//Si la casilla esta vacia y no habido movimiento informo...
		int pos = -1;
		
		//Compruebo si la casilla existe
		/**
		 * PUEDO USAR MI CASILLA OK EN LUGAR DE LOS GET NO?
		 */
		
		
		//Recorro las filas de arriba hacia abajo hasta encontrar una casilla distinta de vacia
		while( !encontrado && fila < tablero.getAlto() ){//No encontrado y no me salgo del tablero
		
			if(tablero.getCasilla(columna, fila) != Ficha.VACIA )
			{	
				encontrado = true;
				pos = fila;//Devuelvo la fila donde la casilla esta VACIA
				
			}else{
				//Comprobamos la fila superior
				fila++;
			}
		}
		return pos;
	}
	
	public boolean undo(){
		
		boolean undoOk = false;
		int columna=0;
		int fila = 0;
		Ficha color = Ficha.VACIA;
		
		//Compruebo que hay movimientos para deshacer
		if(registro.getNumUndo() >=0){
			//Obtengo el ultimo movimiento
			columna = registro.getUltimoMov();
			
			//Obtengo la fila donde se produjo el ultimo movimiento
			/**
			 * ojo este metodo devuelve -1 si
			 * columna esta vacia.. ? hace falta implementar?
			 */
			fila = casillaLlena(columna);
			
			//Deshago el movimiento y meto una ficha VACIA
			tablero.setCasilla(columna, fila, color);
			
			//Decremento contador que almacena los movimientos
			/**
			 * HACE FALTA COMRPOBAR SI CONTADOR ES >0????
			 */
			registro.setNumUndo(registro.getNumUndo()-1);
			
			undoOk= true;//Movimiento desecho!
		}
		
		return undoOk;
	}
	
	
	
	/**
	 * Devuelve el color del jugador que tiene el turno
	 * @return Color del jugador.(VACIA si la partida termina)
	 */
	public Ficha getTurno(){
		
		if(isTerminada()){
			//Si partida terminada = ficha vacia
			turno = Ficha.VACIA;
		}
		
		return turno;
	}
	
	
	
	public boolean isTerminada(){
		boolean fin = false;
		
		
		return fin;
	}
	
	
	
}
