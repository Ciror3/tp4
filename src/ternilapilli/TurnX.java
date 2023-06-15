package ternilapilli;

import java.util.Optional;

public class TurnX extends Status{
	public Ternilapilli ternilapilli;
	public String status;
	 public TurnX(Ternilapilli ternilapilli) {
	        this.ternilapilli = ternilapilli;
	        status = "X";
	 }
	
	public void putXAt(Position position) {
		if(ternilapilli.allTokensOnBoard()) {
			throw new RuntimeException(ternilapilli.cannotPutAFourthToken);
		}
		ternilapilli.putXWhenItsXsTurn(position);
		xWon();
		ternilapilli.status = new TurnO(ternilapilli);
	}

	public void putOAt(Position position) {
		ternilapilli.FailIfXPutsWhenItsOsTurn(position);
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
