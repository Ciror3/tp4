package ternilapilli;

import java.util.Optional;

public class TurnX extends Status{
	public Ternilapilli ternilapilli;
	public String status;
	 public TurnX(Ternilapilli ternilapilli) {
	        this.ternilapilli = ternilapilli;
	        status = "X";
	 }
	
	public void notOsTurn() {
		throw new RuntimeException(Ternilapilli.notOsTurn);
	}
	 
	public void putXAt(Position position) {
		if(ternilapilli.allTokensOnBoard()) {
			throw new RuntimeException(ternilapilli.cannotPutAFourthToken);
		}
		if(ternilapilli.status instanceof TurnO) { 
			throw new RuntimeException(ternilapilli.notXsTurn);
		}  
		ternilapilli.xs.add(position);
		xWon();
		ternilapilli.status = new TurnO(ternilapilli);
	}

	public void putOAt(Position position) {
		throw new RuntimeException(ternilapilli.notOsTurn);
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
	
	public void xWon() {
		Optional.of(ternilapilli)
	    .filter(t -> t.hasWon(t.xs))
	    .ifPresent(t -> t.winner = t.X);
	}
}
