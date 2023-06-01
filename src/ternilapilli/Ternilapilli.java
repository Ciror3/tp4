package ternilapilli;

import java.util.Set;
import java.util.HashSet;

public class Ternilapilli {
	public static String postionTakenError = "La posicion ya esta ocupada";
	public static String notXsTurn = "No es el turno de x";
	public static String notOsTurn = "No es el turno de o";
	public Set<Position> xs;
	public Set<Position> os;
	public String turn;
	
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
		if(turn == "O") {
			throw new RuntimeException(notXsTurn);
		} 
		if(xs.contains(position)) {
			throw new RuntimeException(postionTakenError);
		}
		if(os.contains(position)) { 
			throw new RuntimeException(postionTakenError);
		}
		xs.add(position);
		turn = "O"; 
	}
 
	public void putOAt(Position position) {
		if(turn == "X") {
			throw new RuntimeException(notOsTurn);
		}
		if(os.contains(position)) { 
			throw new RuntimeException(postionTakenError);
		}
		if(xs.contains(position)) {
			throw new RuntimeException(postionTakenError);
		}
		os.add(position);
		turn = "X";
	}
}
