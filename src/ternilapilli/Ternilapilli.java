package ternilapilli;

import java.util.Set;
import java.util.HashSet;

public class Ternilapilli {
	public static String canOnlySlideToAdjacentSpaces = "Solo se puede mover a casilleros adyacentes";
	public static String cannotPlayWhenGameIsOver = "Juego terminado";
	public static String X = "X";
	public static String O = "O";
	public static String statusError = "El estado no es el correcto";
	public static String positionTakenError = "La posicion ya esta ocupada";
	public static String notXsTurn = "No es el turno de x";
	public static String notOsTurn = "No es el turno de o";
	public static String cannotPutAFourthToken = "No se pude poner 4 fichas";
	public Set<Position> xs;
	public Set<Position> os;
	public String winner = "null";
	public Status status = new PutAndXStatus(this); 

	public Ternilapilli() {
		xs = new HashSet<>();
		os = new HashSet<>();
	}

	public Set<Position> getXs() {
		return xs;
	}

	public Set<Position> getOs() {
		return os;
	}

	public void putXAt(Position position) {
		status.putXAt(position);
	}

	public void putOAt(Position position) {
		status.putOAt(position);
	}

	public void slideXFrom(Position token, Position slider) {
		status.slideXFrom(token, slider);
	}

	public void slideOFrom(Position token, Position slider) {
		status.slideOFrom(token, slider);
	}

	public void isWinnerO() {
		if (hasWon(os)) {//Como es un cambio de estado dejamos el if
			winner= O;
			gameFinished(); 
		}
	}
	
	public void isWinnerX() {
		if (hasWon(xs)) {//Como es un cambio de estado dejamos el if
			winner=X;
			gameFinished(); 
		}
	}
	
	public boolean XHasWon() {
		return winner == X; 
	}

	public boolean OHasWon() {
		return winner == O;
	}

	public boolean hasWon(Set<Position> position) {
		return hasCompletedRowOrColumn(position) || hasLeftDiagonal(position) || hasRightDiagonal(position);
	}

	public void slideXfrom(int i, int j, int k, int m) {
		slideXFrom(new Position(i, j), new Position(k, m));
	}

	public void slideOfrom(int i, int j, int k, int m) {
		slideOFrom(new Position(i, j), new Position(k, m));
	}

	public void putOWhenItsOsTurn(Position position) {
		os.add(position); 	
		status = new PutAndXStatus(this);
	}
	public void putXWhenItsXsTurn(Position position) {
		xs.add(position);
		status = new PutAndOStatus(this);
	} 

	
	public void transitionFromPutOToSlideX(Position position) {
		if (xs.size() == 3) { 
			status = new SlideAndXStatus(this);;
		}
	}
	
	public void FailIfXPutsWhenItsOsTurn(Position position) {
		throw new RuntimeException(notOsTurn);
	}

	public void FailIfXslidesWhenItsStatusIsWrong(Position slider) {
		throw new RuntimeException(statusError);		
	}
	
	public void FailIfOslidesWhenItsStatusIsWrong(Position slider) {
		throw new RuntimeException(statusError);		
	}
	
	public void FailIfXPutsWhenItsStatusIsWrong(Position position) {
		throw new RuntimeException(statusError);
	}
	
	public void FailIfOPutsWhenItsStatusIsWrong(Position position) {
		throw new RuntimeException(statusError);
	}

	public void FailIfOPutsWhenItsXsTurn(Position position) {
		throw new RuntimeException(notXsTurn);
	}

	public void failWhenSpaceAlreadyTakenForX(Position position) {
		if (xs.contains(position)) {
			throw new RuntimeException(positionTakenError);
		}
	}

	public void failWhenSpaceAlreadyTakenForO(Position position) {
		if (os.contains(position)) {
			throw new RuntimeException(positionTakenError);
		}
	}

	public void failWhenSpaceIsTooFarAway(Position token, Position slider) {
		if (!moveLegalFrom(token, slider)) {
			throw new RuntimeException(canOnlySlideToAdjacentSpaces);
		}
	}

	public Status gameFinished() {
		return status = new gameOver(this);
	}

	public void slideOWhenItsOsTurn(Position token, Position slider) {
		os.remove(token);
		os.add(slider);
		status = new SlideAndXStatus(this);
	}

	public void slideXWhenItsXsTurn(Position token, Position slider) {
		xs.remove(token);
		xs.add(slider); 
		status = new SlideAndOStatus(this);
	}

	private boolean hasRightDiagonal(Set<Position> lista) {
		for (int iterador = 1; iterador <= 3; iterador++) {
			if (!lista.contains(new Position(iterador, 4 - iterador))) {
				return false;
			}
		}
		return true; 
	}

	private boolean hasLeftDiagonal(Set<Position> lista) {
		for (int iterador = 1; iterador <= 3; iterador++) {
			if (!lista.contains(new Position(iterador, iterador))) {
				return false;
			}
		}
		return true;
	}

	private boolean hasCompletedRowOrColumn(Set<Position> lista) {
		for (int iterador = 0; iterador <= 3; iterador++) {
			int filaObservable = iterador;
			int columnaObservable = iterador;
			int counterRows = (int) lista.stream().filter(p -> p.getRow() == filaObservable).count();
			int counterColumns = (int) lista.stream().filter(p -> p.getColumn() == columnaObservable).count();
			if (counterRows == 3 || counterColumns == 3) {
				return true;
			}
		}
		return false;
	}
 
	private boolean moveLegalFrom(Position token, Position slider) {
		return Math.abs(slider.getRow() - token.getRow()) < 2 && Math.abs(slider.getColumn() - token.getColumn()) < 2
				&& 0 < slider.getColumn() && slider.getColumn() < 4 && 0 < token.getColumn() && token.getColumn() < 4
				&& 0 < slider.getRow() && slider.getRow() < 4 && 0 < token.getRow() && token.getRow() < 4;
	}
}
