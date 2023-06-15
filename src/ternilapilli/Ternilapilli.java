package ternilapilli;

import java.util.Set;
import java.util.HashSet;

public class Ternilapilli {
	public static String canOnlySlideToAdjacentGaps = "Solo se puede mover a casilleros adyacentes";
	public static String cannotPlayWhenGameIsOver = "Juego terminado";
	public static String X = "X";
	public static String O = "O";
	public static String postionTakenError = "La posicion ya esta ocupada";
	public static String notXsTurn = "No es el turno de x";
	public static String notOsTurn = "No es el turno de o";
	public static String cannotPutAFourthToken = "No se pude poner 4 fichas";
	public Set<Position> xs;
	public Set<Position> os;
	public String winner;
	public Status status = new TurnX(this);
	 
	public Ternilapilli() {
		xs = new HashSet<>();
		os = new HashSet<>();
	}
	//HAcer metodos muy especificos y chequeamos
	public Set<Position> getXs() {
		return xs;
	}

	public Set<Position> getOs() {
		return os;
	}

	public void putXAt(Position position) {
//		if(allTokensOnBoard()) {
//			throw new RuntimeException(cannotPutAFourthToken);
//		}
//		if(turn == O) {
//			throw new RuntimeException(notXsTurn);
//		} 
//		if(xs.contains(position)) {
//			throw new RuntimeException(postionTakenError);
//		}
//		if(os.contains(position)) { 
//			throw new RuntimeException(postionTakenError);
//		}
//		xs.add(position);
//		if(hasWon(xs)) {
//			winner = X;
//		}
//		turn = O; 
		status.putXAt(position);
	}

	public void putOAt(Position position) {
//		if(allTokensOnBoard()) {
//			throw new RuntimeException(cannotPutAFourthToken);
//		} 
//		if(isGameOver()) {
//			throw new RuntimeException(cannotPlayWhenGameIsOver);
//		}
//		if(turn == X) {
//			throw new RuntimeException(notOsTurn);
//		}
//		if(os.contains(position)) { 
//			throw new RuntimeException(postionTakenError);
//		}
//		if(xs.contains(position)) {
//			throw new RuntimeException(postionTakenError);
//		}
//		os.add(position);
//		if(hasWon(os)) {
//			winner = O;
//		}
//		turn = X;
		status.putOAt(position);
	}

	private boolean isGameOver() {
		return winner == X || winner == O;
	}

	public boolean isWinnerO() {
		return  hasWon(os);
	}

	public boolean isWinnerX() {
		return  hasWon(xs);
	}
	 
	public boolean XHasWon() {
		return winner == X;
	}
	
	public boolean OHasWon() {
		return winner == O;
	}
	
	public boolean hasWon(Set<Position> position) {
		return  hasCompletedRowOrColumn(position)||hasLeftDiagonal(position)|
				hasRightDiagonal(position);
	}
	 
	private boolean hasRightDiagonal(Set<Position>lista) {
		for(int iterador = 1; iterador <= 3;iterador++) {
			if(!lista.contains(new Position(iterador,4 - iterador))) {
				return false;
			}
		}
		return true; 
	}
	
	private boolean hasLeftDiagonal(Set<Position>lista) {
		for(int iterador = 1; iterador <= 3;iterador++) {
			if(!lista.contains(new Position(iterador,iterador))) {
				return false;
			}
		}
		return true;
	}
	
	private boolean hasCompletedRowOrColumn(Set<Position>lista) {
		for(int iterador = 0; iterador <= 3;iterador++) {
			int filaObservable = iterador;
			int columnaObservable = iterador;
			int counterRows =  (int) lista.stream().filter(p -> p.getRow() == filaObservable).count();
			int counterColumns = (int) lista.stream().filter(p -> p.getColumn()==columnaObservable).count();
			if(counterRows == 3 || counterColumns == 3) {
				return true; 
			}  
		}
		return false;
	}

//	public void slideXFrom(Position token, Position slider) {
//		if(allTokensOnBoard()) {
//			throw new RuntimeException(cannotPutAFourthToken);
//		}
//		if(turn == O) {
//			throw new RuntimeException(notXsTurn);
//		} 
//		if(xs.contains(slider)) {
//			throw new RuntimeException(postionTakenError);
//		}
//		if(os.contains(slider)) { 
//			throw new RuntimeException(postionTakenError);
//		}
//		if(!moveLegalFrom(token,slider)) {
//			throw new RuntimeException(canOnlySlideToAdjacentGaps);
//		}
//		xs.remove(token);
//		xs.add(slider);
//		if(hasWon(xs)) {
//			winner = X;
//		}
//		turn = O; 
//	} 
//
//	public void slideOFrom(Position token, Position slider) {
//		if(allTokensOnBoard()) {
//			throw new RuntimeException(cannotPutAFourthToken);
//		}
//		if(turn == X) {
//			throw new RuntimeException(notXsTurn);
//		} 
//		if(xs.contains(slider)) {
//			throw new RuntimeException(postionTakenError);
//		}
//		if(os.contains(slider)) { 
//			throw new RuntimeException(postionTakenError);
//		} 
//		if(!moveLegalFrom(token,slider)) {
//			throw new RuntimeException(canOnlySlideToAdjacentGaps);
//		}
//		os.remove(token);
//		os.add(slider); 
//		if(hasWon(os)) {
//			winner = O;
//		}
//		turn = X;
//	} 
	
	private boolean moveLegalFrom(Position token, Position slider) {
		return Math. abs(slider.getRow()-token.getRow()) < 2 
			&& Math. abs(slider.getColumn()-token.getColumn()) < 2
			&&  0 < slider.getColumn() && slider.getColumn() < 4
			&&  0 < token.getColumn() && token.getColumn() < 4
			&&  0 < slider.getRow() && slider.getRow() < 4
			&&  0 < token.getRow() && token.getRow() < 4;
	} 
	
//	public void slideXfrom(int i, int j, int k, int m) {
//		slideXFrom(new Position(i,j), new Position(k,m));
//	}
	
//	public void slideOfrom(int i, int j, int k, int m) {
//		slideOFrom(new Position(i,j), new Position(k,m));
//	}
	 
	public boolean allTokensOnBoard() {
		return xs.size() > 3 || os.size() > 3;
	}
	
	public void putXWhenItsXsTurn(Position position) {
		xs.add(position);
		status = new TurnO(this);

	}
	
	public void FailIfXPutsWhenItsOsTurn(Position position) {
		throw new RuntimeException(notOsTurn);
	}
	
	
	
	public void putOWhenItsOsTurn(Position position) {
		os.add(position);
		status = new TurnX(this);

	}
	 
	public void FailIfOPutsWhenItsXsTurn(Position position) {
		throw new RuntimeException(notXsTurn);
	}
}
