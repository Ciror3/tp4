package ternilapilli;

import java.util.Optional;

public class TurnO extends Status{
	public Ternilapilli ternilapilli;
	public String status;
	public TurnO(Ternilapilli ternilapilli) {
		this.ternilapilli = ternilapilli;
        status = "O";
	}
	 
	public void putXAt(Position position) {
		throw new RuntimeException(ternilapilli.notXsTurn);
	}

	public void putOAt(Position position) {
		if(ternilapilli.allTokensOnBoard()) { 
			throw new RuntimeException(ternilapilli.cannotPutAFourthToken);
		}
		if(ternilapilli.status instanceof TurnX) { 
			throw new RuntimeException(ternilapilli.notXsTurn);
		} 
		ternilapilli.os.add(position); 
		oWon();
		ternilapilli.status = new TurnX(ternilapilli); 
	}


	public void checkIfOsContainsIt(Position position) {
		 Optional.of(position) 
         		 .filter(p -> !ternilapilli.os.contains(p))
         		 .orElseThrow(() -> new RuntimeException(ternilapilli.postionTakenError));
	}

	public void checkIfXsContainsIt(Position position) {
		Optional.of(position)
		 .filter(p -> !ternilapilli.xs.contains(p))
		 .orElseThrow(() -> new RuntimeException(ternilapilli.postionTakenError));
	}
	
	private void oWon() {
		Optional.of(ternilapilli)
	    .filter(t -> t.hasWon(t.os))
	    .ifPresent(t -> t.winner = t.O);
	}
}