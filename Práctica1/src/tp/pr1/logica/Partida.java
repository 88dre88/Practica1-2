package tp.pr1.logica;

/**
 * Representa la informacion de una partida. 
 *
 */
public class Partida {
	
	private static final int TAM = 7;//constante para asignar el tamaño del tablero mientras no se implementen otras opciones 
	private Tablero tablero;
	private Ficha turno;
	private boolean terminada;
	private Ficha ganador;
	private Registro registro;
	
	
	/*  Al crear una partida inicializamos todos los atributos.En principio 
	 *  el tablero sera de 7x7  pero posteriormente sera aplicable a 
	 * cualquier tamaño.
	 */
	public Partida(){
		tablero = new Tablero(TAM,TAM);
		terminada = false;
		turno = Ficha.BLANCAS;//empiezan a jugar las fichas blancas
		ganador = null;
		registro = new Registro();
	}
	
	public Tablero getTablero(){
		return tablero;
	}
	public Ficha getTurno(){
		return turno;
	}
	public void setTurno(Ficha turno){
		this.turno = turno;
	}
	public Ficha getGanador(){
		return ganador;
	}
	
	/**
	 * Coloca una ficha en el tablero. Antes de colocar la ficha comprueba que la columna seleccionada 
	 * sea valida. Tambien comprueba si el jugador ha ganado y guarda un registro del movimiento por si hay
	 * que deshacer
	 * @param color correspondiente al jugador que le toque mover
	 * @param col es la columna seleccionada donde se quiere colocar la ficha
	 * @return true si el movimiento se ha realizado satisfactoriamente y false en caso de que la columna seleccionada no sea valida
	 */
	public boolean ejecutaMovimiento(Ficha color, int col){
		boolean salir = false,mov;
		if(isTerminada() || color != turno){
			return false;
		}else{
			if(col > tablero.getAncho()){//el movimiento no se ejecutara si se elige una columna fuera de rango
				mov = false;
			}else{
				int fila = tablero.getAlto();//se comprobara de arriba a abajo hasta encontrar la primera fila vacia de la columna seleccionada
				int columna = col;
				do{
					if((tablero.existeCasilla(columna, fila)) && (tablero.getCasilla(columna,fila) == Ficha.VACIA)){//si la casilla seleccionada esta vacia (y existe) se procede a colocar la ficha
						tablero.setCasilla(columna,fila,color);//si la ficha se ha colocado correctamente devolvera true
						registro.guardarMovimiento(columna);
						mov = true;//devuelve true porque el movimiento se ha ejecutado correctamente
						salir = true;
					}else{//si la fila no estaba vacia,se comprueban el resto de filas de la columna seleccionada
						if(fila == 0)
							salir = true;
						else
							fila --;
						mov = false;
					}
				}while(!salir);
			}
			return mov;
		}
	}
	
	/**
	 * Metodo que deshace el último movimiento realizado.
	 * @return true si el movimiento se deshizo correctamente y false en caso contrario
	 */
	public boolean undo(){
		boolean deshacer = false;
		if(registro.getNumUndo() >= 0){//si numUndo es -1 quiere decir que no hay ningun movimiento almacenado y no se puede deshacer nada
			int col = registro.getUltimoMovimiento(),fila = 0;
			boolean  salir = false;
			Ficha color = Ficha.VACIA;
			do{
				if(!tablero.casillaVacia(col,fila)){//compruebo de arriba a bajo cual es la ultima fila de la columna seleccionada donde ha habido movimiento
					tablero.setCasilla(col,fila,color);
					registro.setNumUndo(registro.getNumUndo() - 1);
					deshacer = true;
					salir = true;
				}else
					if(fila < tablero.getAlto())
						fila ++;
					else
						salir = true;
			}while(!salir);
		}
		return deshacer;
	}
	/**
	 * Reinicia la partida. El tablero se vacía y se borran todos los movimientos 
	 * guardados.
	 */
	public void reset(){
		tablero.reiniciar();
		registro.setNumUndo(-1);//elimina todo movimiento guardado
	}
	
	private int compruebaFilaColumnaDiagonal(int columna,int fila,int dx,int dy,Ficha color){
		int contador = 0;
		if ((columna > 0 && columna <= tablero.getAncho()) && (fila > 0 && fila <= tablero.getAlto())){
			if(dx == 1 && dy == 0){
				columna ++;
				while(tablero.getCasilla(columna,fila) == color && columna <= tablero.getAncho()){
					contador ++;
					columna ++;
				}
			}else if(dx == 1 && dy == 1){
				columna ++;
				fila ++;
				while(tablero.getCasilla(columna,fila) == color && (columna <= tablero.getAncho() && fila <= tablero.getAlto())){
					contador ++;
					columna ++;
					fila ++;
				}
			}else if(dx == 0 && dy == 1){
				fila ++;
				while(tablero.getCasilla(columna,fila) == color && fila <= tablero.getAlto()){
					contador ++;
					fila ++;
				}
			}else if(dx == -1 && dy == 1){
				columna --;
				fila ++;
				while(tablero.getCasilla(columna,fila) == color && (columna >= 1 && fila <= tablero.getAlto())){
					contador ++;
					columna --;
					fila ++;
				}
			}else if(dx == -1 && dy == 0){
				columna --;
				while(tablero.getCasilla(columna,fila) == color && columna >= 1){
					contador ++;
					columna --;
				}
			}else if(dx == -1 && dy == -1){
				columna --;
				fila --;
				while(tablero.getCasilla(columna,fila) == color && (columna >= 1 && fila >= 1)){
					contador ++;
					columna --;
					fila --;
				}
			}else if(dx == 0 && dy == -1){
				fila --;
				while(tablero.getCasilla(columna,fila) == color && fila >= 1){
					contador ++;
					fila --;
				}
			}
			return contador;
		}else{
			return -1;
		}
	}
	private int obtenerFilaUltimoMovimiento(){
		int fila;
		if(registro.getUltimoMovimiento() == -1){
			return -1;
		}else{
			int columna = registro.getUltimoMovimiento();
			fila = 1;
			while(tablero.getCasilla(columna, fila) == Ficha.VACIA && fila <= tablero.getAlto())
				fila ++;
			return fila;
		}
	}
	public boolean isTerminada(){
		int contador = 1;
		if(registro.getUltimoMovimiento() != -1){
			contador += compruebaFilaColumnaDiagonal(registro.getUltimoMovimiento(),obtenerFilaUltimoMovimiento(),1,0,turno);
			contador += compruebaFilaColumnaDiagonal(registro.getUltimoMovimiento(),obtenerFilaUltimoMovimiento(),-1,0,turno);
			if(contador >= 4){
				terminada = true;
				ganador = turno;
			}else{
				contador = 1;
				contador += compruebaFilaColumnaDiagonal(registro.getUltimoMovimiento(),obtenerFilaUltimoMovimiento(),0,1,turno);
				contador += compruebaFilaColumnaDiagonal(registro.getUltimoMovimiento(),obtenerFilaUltimoMovimiento(),0,-1,turno);
				if(contador >= 4){
					terminada = true;
					ganador = turno;
				}else{
					contador = 1;
					contador += compruebaFilaColumnaDiagonal(registro.getUltimoMovimiento(),obtenerFilaUltimoMovimiento(),1,1,turno);
					contador += compruebaFilaColumnaDiagonal(registro.getUltimoMovimiento(),obtenerFilaUltimoMovimiento(),-1,-1,turno);
					if(contador >= 4){
						terminada =  true;
						ganador = turno;
					}else{
						contador = 1;
						contador += compruebaFilaColumnaDiagonal(registro.getUltimoMovimiento(),obtenerFilaUltimoMovimiento(),1,-1,turno);
						contador += compruebaFilaColumnaDiagonal(registro.getUltimoMovimiento(),obtenerFilaUltimoMovimiento(),-1,1,turno);
						if(contador >= 4){
							terminada =  true;
							ganador = turno;
						}else
							terminada = false;
					}
				}
				
			}
		}else
			terminada = false;
		
		return terminada;
	}
}
