package tp.pr1.control;

import java.util.Scanner;

import tp.pr1.logica.Ficha;
import tp.pr1.logica.Partida;

public class Controlador {
	
	private Partida partida;
	private Scanner in;

	
	public Controlador(){
		partida = new Partida();
		in = new Scanner(System.in);
	}
	/**
	 * Metodo que realiza la simulacion del juego. Mientras el usuario no seleccione la opcion de 
	 * salida, el metodo seguira mostrando el menu y accediendo a las diferentes opciones.
	 */
	public void run(){
		
		boolean salir = false;
		do{
			Ficha turno = partida.getTurno();
			System.out.print(partida.getTablero().toString());
			System.out.println("Juegan " + turno.toString() + '.');
			System.out.println("¿Qué quieres hacer?");
			String opcion = in.next().toLowerCase();//convierto todo lo escrito por el usuario a minusculas para no hacer distincion
			switch(opcion){
				case "poner":{
					System.out.print("Introduce la columna:");
					int columna = in.nextInt();
					boolean aux = partida.ejecutaMovimiento(turno, columna);
					if(!aux)
						System.err.println("Movimiento incorrecto");
					else {
					if(!partida.isTerminada()){
						cambiarTurno(turno);
					}else{
						System.out.print(partida.getTablero().toString());
						System.out.println("se termino");
						salir = true;
					}
					}
					break;
				}
				case "deshacer":{
					boolean aux = partida.undo();
					if(!aux)
						System.out.println("Imposible deshacer.");
					else
						cambiarTurno(turno);
					break;
				}
				case "reiniciar":{
					partida.reset();
					System.out.println("Partida reiniciada.");
					break;
				}
				case "salir":{
					System.out.println("Fin del juego.");
					salir = true;
					break;
				}
				default:{
					System.out.println("Opcion no valida.");
					break;
				}
			}
		}while(!salir);


		
	}
	private void cambiarTurno(Ficha turno){
		if(turno == Ficha.BLANCAS)
			partida.setTurno(Ficha.NEGRAS);
		else
			partida.setTurno(Ficha.BLANCAS);
	}
	
}

