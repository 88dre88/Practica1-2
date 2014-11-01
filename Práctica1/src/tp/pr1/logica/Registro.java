package tp.pr1.logica;

public class Registro {
	private int[] undoStack;// almacena las columnas donde han ocurrido los 10
							// ultimos cambios para la opcion deshacer siguiendo
							// el indice de 0 a N
	private int numUndo;// indica cuantas posiciones del array undoStack estan
						// ocupadas

	public Registro() {
		
		undoStack = new int[10];// en este array se guardan los 10 ultimos movimientos
		numUndo = -1;// lo inicializamos a -1 para que funciona correctamente la
						// opcion de deshacer
	}

	public void setNumUndo(int numUndo) {
		this.numUndo = numUndo;
	}
	public int getNumUndo() {
		return numUndo;
	}
	public int getUltimoMovimiento(){
		if(numUndo == -1)
			return numUndo;
		else
			return undoStack[numUndo];
	}
	private void desplazarArray(int columna){
		for(int i = 0;i < undoStack.length - 1;i ++)//si ya hay 10 movimientos registrados,se pisa el primero y se guarda el ultimo al final
			undoStack[i] = undoStack[i+1];
		undoStack[numUndo] = columna;
		
	}
	public void guardarMovimiento(int columna){
		if(numUndo < 9){//mientras no haya 10 movimientos registrados,simplemente se incrementa la variable y se guarda la columna en el array
			numUndo ++;
			undoStack[numUndo] = columna;
		}else{
			desplazarArray(columna);//si el array de registro esta lleno de desplazan todos sus valores una posicion a la izquierda y se guarda al final el valor de columna
		}
	}
}