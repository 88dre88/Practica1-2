package tp.pr1.logica;

public class Registro {

	
	private static final int MAXMOV = 10;
	//Atributos para almacenar los movimientos
	private int[] undoStack;
	private int numUndo;
	
	
	//Constructura
	
	public Registro(){
		
		//Instanciamos el array para almacenar movimientos
		
		undoStack = new int[MAXMOV];
		numUndo= -1;
	}
	/**Permite especificar el contenido de los movimientos desechos
	 * 
	 * @param numUndo- numero de movimientos en el registro
	 */
	public void setNumUndo(int numUndo){
		
		this.numUndo = numUndo;
	}
	

	/**Permite almacenar la columna donde se realizo el movimiento
	 * en el array de movimientos
	 * @param columna
	 */
	public void saveMov(int columna){
		
		/*
		 * PARA COMPROBAR COLUMNA VALIDA? TABLERO.GET.. 
		 * NECESITO UN OBJETO PARA ACCEDER AL METODO...
		 * 
		 */
		
		//Si hay espacio en el array almaceno
		if(numUndo < MAXMOV){
			
			numUndo++;
			//Almaceno en la posicion
			undoStack[MAXMOV] = columna ; 
			
		}else{
			//Sino desplazo los movimientos
			desplazaIz();
			undoStack[MAXMOV] = columna;
		}
		
		/**
		 * undostarMAZMOV.. LO HACE EN LAS DOS RAMAS
		 * LUEGO SE PUEDE SACAR FUERA NO ???8888888888
		 */
	}
	
	/**
	 * Desplaza el contenido del array de movimientos a la izquierda
	 * para almacenar en la ultima posicion del array el ultimo movimiento
	 */
	private void desplazaIz(){
		
		for(int i = 0; i< undoStack.length-1; i++){
			undoStack[i]= undoStack[i+1];
		}
	}
	
	/**
	 * Permite saber en que columna se ha realizado el ultimo movimiento
	 * @return columna que ha sido almacenada la ultima
	 */
	public int getUltimoMov()
	{	
		return undoStack[MAXMOV];
	}
	
	
	public int getNumUndo(){
		return numUndo;
	}

	
	
}
