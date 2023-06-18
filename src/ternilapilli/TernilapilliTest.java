package ternilapilli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.Test;

public class TernilapilliTest {
	@Test
	void test00CreateANewEmptyTernilapilli() { 
		Ternilapilli game = new Ternilapilli();

		assertTrue(game.getXs().isEmpty());
		assertTrue(game.getOs().isEmpty());
	}

	@Test
	void test01PutFirstX() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));

		assertEquals(1, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getOs().isEmpty());
	}

	@Test
	void test02PutFirstO() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));

		assertEquals(1, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertEquals(1, game.getOs().size());
		assertTrue(game.getOs().contains(new Position(2, 2)));
	}

	@Test
	void test03XCannotPlayTwiceInARow() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		
		assertThrowsLike(() -> game.putXAt(new Position(3, 3)), Ternilapilli.notXsTurn);
		assertEquals(1, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getOs().isEmpty());
		
	}
 
	@Test
	void test04OCannotPlayTwiceInARow() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));

		assertThrowsLike(() -> game.putOAt(new Position(3, 3)), Ternilapilli.notOsTurn);
		assertEquals(1, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertEquals(1, game.getOs().size());
		assertTrue(game.getOs().contains(new Position(2, 2)));
	}

	@Test
	void test05CannotPutAnXInAnAlreadyOccupiedPositionByX() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));

		assertThrowsLike(() -> game.putXAt(new Position(1, 1)), Ternilapilli.positionTakenError);
		assertEquals(1, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertEquals(1, game.getOs().size());
		assertTrue(game.getOs().contains(new Position(2, 2)));
	} 

	@Test
	void test06CannotPutAnOInAnAlreadyOccupiedPositionByO() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 2));
		
		assertThrowsLike(() -> game.putOAt(new Position(1, 1)), Ternilapilli.positionTakenError);
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getXs().contains(new Position(1, 2)));
		assertEquals(1, game.getOs().size());
		assertTrue(game.getOs().contains(new Position(2, 2)));
	}

	@Test
	void test07CannotPutAnOInAnAlreadyOccupiedPositionByX() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		assertThrowsLike(() -> game.putOAt(new Position(1, 1)), Ternilapilli.positionTakenError);
		assertEquals(1, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getOs().isEmpty());
		
	}

	@Test
	void test08CannotPutAnXInAnAlreadyOccupiedPositionByO() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(3, 1)); 
		assertThrowsLike(() -> game.putOAt(new Position(2, 2)), Ternilapilli.positionTakenError);
		assertEquals(2, game.getXs().size()); 
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertEquals(1, game.getOs().size());
		assertTrue(game.getOs().contains(new Position(2, 2)));
	}

	@Test
	void test09NoOneWins() { 
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(3, 1)); 
		game.putOAt(new Position(2, 3));
		

		assertFalse(game.XHasWon());
		assertFalse(game.OHasWon());
	} 

	@Test
	void test10CheckIfXsWinsByRows() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 3));
		game.putOAt(new Position(3, 2));
		game.putXAt(new Position(1, 2));

		assertTrue(game.XHasWon());
		assertFalse(game.OHasWon());
	}

	@Test
	void test11CheckIfOsWinsByRows() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(3, 3));
		game.putOAt(new Position(1, 1));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(1, 3));
		game.putXAt(new Position(3, 1));
		game.putOAt(new Position(1, 2));

		assertFalse(game.XHasWon());
		assertTrue(game.OHasWon());
	}

	@Test
	void test12CheckIfXsWinsByColumns() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(3, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(3, 2));
		game.putXAt(new Position(1, 1));

		assertTrue(game.XHasWon());
		assertFalse(game.OHasWon());
	}

	@Test
	void test13CheckIfOsWinsByColumns() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(3, 3));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(3, 2));
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(1, 2));

		assertFalse(game.XHasWon());
		assertTrue(game.OHasWon());
	}

	@Test
	void test14CheckHasWon() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(3, 3));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(3, 2));
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(1, 2));

		assertFalse(game.XHasWon());
		assertTrue(game.OHasWon());
	}

	@Test
	void test15CheckXWinsInTheLeftDiagonal() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(3, 3));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(2, 2));
		game.putOAt(new Position(3, 2));
		game.putXAt(new Position(1, 1));

		assertTrue(game.XHasWon());
		assertFalse(game.OHasWon());
	}

	@Test
	void test16CheckOWinsInTheLeftDiagonal() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(3, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(3, 3));
		game.putXAt(new Position(1, 2));
		game.putOAt(new Position(1, 1));

		assertFalse(game.XHasWon());
		assertTrue(game.OHasWon());
	}

	@Test
	void test17CheckXWinsInTheRightDiagonal() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 3));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(2, 2));
		game.putOAt(new Position(3, 2));
		game.putXAt(new Position(3, 1));

		assertTrue(game.XHasWon());
		assertFalse(game.OHasWon());
	}

	@Test
	void test18CheckOWinsInTheLeftDiagonal() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(1, 3));
		game.putXAt(new Position(1, 2));
		game.putOAt(new Position(3, 1));

		assertFalse(game.XHasWon());
		assertTrue(game.OHasWon());
	}

	@Test 
	void test19OCannotPlayWhenGameIsOver() {
		Ternilapilli game = new Ternilapilli(); 
		game.putXAt(new Position(1, 3));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(2, 2));
		game.putOAt(new Position(3, 2));
		game.putXAt(new Position(3, 1));

		assertThrowsLike(() -> game.putOAt(new Position(3, 3)), Ternilapilli.cannotPlayWhenGameIsOver);
	
	}

	@Test
	void test20XCannotPutAFourthToken() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 3));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(2, 3));
		game.putOAt(new Position(3, 2));
		game.putXAt(new Position(3, 1));
		game.putOAt(new Position(1, 1));

		assertThrowsLike(() -> game.putXAt(new Position(3, 3)), Ternilapilli.cannotPutAFourthToken);
		assertEquals(3, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 3)));
		assertTrue(game.getXs().contains(new Position(2, 3)));
		assertTrue(game.getXs().contains(new Position(3, 1)));
	}

	@Test
	void test21XCanSlideAToken() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(1, 3));
		game.putXAt(new Position(1, 2));
		game.putOAt(new Position(3, 2));

		game.slideXfrom(2, 1, 3, 1);
		assertEquals(3, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(3, 1)));
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getXs().contains(new Position(1, 2)));
	}

	@Test
	void test22PlayerCanSlideAToken() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(1, 3));
		game.putXAt(new Position(1, 2));
		game.putOAt(new Position(3, 2));

		game.slideXfrom(2, 1, 3, 1);
		game.slideOfrom(3, 2, 3, 3);
		assertEquals(3, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(3, 1)));
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getXs().contains(new Position(1, 2)));
		assertEquals(3, game.getOs().size());
		assertTrue(game.getOs().contains(new Position(3, 3)));
		assertTrue(game.getOs().contains(new Position(1, 3)));
		assertTrue(game.getOs().contains(new Position(2, 2)));
	}

	@Test
	void test23XCanWinIfItSlidesATokenInARow() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 2));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(2, 3));
		game.putOAt(new Position(3, 2));

		game.slideXfrom(2,3,1,3);

		assertTrue(game.isWinnerX());
		assertFalse(game.isWinnerO());

	}

	@Test
	void test24OCanWinIfItSlidesATokenInARow() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 2));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(3, 3));
		game.putOAt(new Position(3, 2));

		game.slideXfrom(1, 2, 1, 3);
		game.slideOfrom(3, 2, 2, 3);
 
		assertTrue(game.isWinnerO());
		assertFalse(game.isWinnerX());

	}

	@Test
	void test25XCanWinIfItSlidesATokenInAColumn() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(2, 3));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(1, 3));

		game.slideXfrom(3, 2, 3, 1);

		assertTrue(game.isWinnerX());
		assertFalse(game.isWinnerO());

	}

	@Test
	void test26OCanWinIfItSlidesATokenInAColumn() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(2, 3));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(1, 3));

		game.slideXfrom(1, 1, 1, 2);
		game.slideOfrom(2, 2, 3, 3);
		
		assertFalse(game.isWinnerX());
		assertTrue(game.isWinnerO());

	}

	@Test
	void test27XCanWinIfItSlidesATokenInADiagonal() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(2, 2));
		game.putOAt(new Position(2, 3));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(1, 3));

		game.slideXfrom(2, 3, 3, 3);

		assertTrue(game.isWinnerX());
		assertFalse(game.isWinnerO());
 
	}

	@Test
	void test28OCanWinIfItSlidesATokenInADiagonal() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(3, 1));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(2, 3));

		game.slideXfrom(3, 2, 3, 3);
		game.slideOfrom(2, 3, 1, 3);

		assertTrue(game.isWinnerO());
		assertFalse(game.isWinnerX());

	}

	@Test
	void test29CannotSlideAnXInAnAlreadyOccupiedPositionByX() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(3, 1));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(2, 3));

		assertThrowsLike(() -> game.slideXfrom(2, 1, 1, 1), Ternilapilli.positionTakenError);
		assertEquals(3, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getXs().contains(new Position(2, 1)));
		assertTrue(game.getXs().contains(new Position(3, 2)));
	}

	@Test
	void test30CannotSlideAnOInAnAlreadyOccupiedPositionByO() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(3, 1));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(2, 3));
		game.slideXfrom(2, 1, 1, 2);
		assertThrowsLike(() -> game.slideOfrom(3, 1, 2, 2), Ternilapilli.positionTakenError);
		assertEquals(3, game.getOs().size());
		assertTrue(game.getOs().contains(new Position(2, 2)));
		assertTrue(game.getOs().contains(new Position(2, 3)));
		assertTrue(game.getOs().contains(new Position(3, 1)));
	}

	@Test
	void test31CannotSlideAnOInAnAlreadyOccupiedPositionByX() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(3, 1));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(2, 3));
		game.slideXfrom(2, 1, 1, 2);
		assertThrowsLike(() -> game.slideOfrom(3, 1, 3, 2), Ternilapilli.positionTakenError);
		assertEquals(3, game.getOs().size());
		assertTrue(game.getOs().contains(new Position(2, 2)));
		assertTrue(game.getOs().contains(new Position(2, 3)));
		assertTrue(game.getOs().contains(new Position(3, 1)));
	}

	@Test
	void test32CannotSlideAnXInAnAlreadyOccupiedPositionByO() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(3, 1));
		game.putXAt(new Position(3, 2)); 
		game.putOAt(new Position(2, 3));

		assertThrowsLike(() -> game.slideXfrom(2, 1, 2, 2), Ternilapilli.positionTakenError);
		assertEquals(3, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getXs().contains(new Position(2, 1)));
		assertTrue(game.getXs().contains(new Position(3, 2)));
	}

	
	@Test
	void test33CannotSlideAXIntoAnIllegalPosition() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(3, 1)); 
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(2, 3));

		assertThrowsLike(() -> game.slideXfrom(2, 1, 1, 3), Ternilapilli.canOnlySlideToAdjacentSpaces);
		assertEquals(3, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getXs().contains(new Position(2, 1)));
		assertTrue(game.getXs().contains(new Position(3, 2)));
	}
	
	@Test
	void test34CannotSlideAnOIntoAnIllegalPosition() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(3, 1));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(3, 3));
		
		game.slideXfrom(2, 1, 1, 2);
		assertThrowsLike(() -> game.slideOfrom(3, 3, 1, 3), Ternilapilli.canOnlySlideToAdjacentSpaces);
		assertEquals(3, game.getOs().size());
		assertTrue(game.getOs().contains(new Position(2, 2)));
		assertTrue(game.getOs().contains(new Position(3, 1)));
		assertTrue(game.getOs().contains(new Position(3, 3)));
	}
	
	@Test 
	void test35XCannotSlideWhenGameIsOver() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(3, 1)); 
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(1, 3));
		
		assertThrowsLike(() -> game.slideXfrom(2, 1, 1, 2), Ternilapilli.cannotPlayWhenGameIsOver);
		assertEquals(3, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getXs().contains(new Position(2, 1)));
		assertTrue(game.getXs().contains(new Position(3, 2)));
	}
	
	@Test 
	void test36OCannotSlideWhenGameIsOver() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(1, 2)); 
		game.putXAt(new Position(3, 2)); 
		game.putOAt(new Position(2, 3)); 
		
		game.slideXfrom(3, 2, 3, 1); 
		assertThrowsLike(() -> game.slideOfrom(2, 2, 3, 3), Ternilapilli.cannotPlayWhenGameIsOver);
		assertEquals(3, game.getOs().size());
		assertTrue(game.getOs().contains(new Position(1, 2)));
		assertTrue(game.getOs().contains(new Position(2, 2)));
		assertTrue(game.getOs().contains(new Position(2, 3)));
	}
	
	
	
	private void assertThrowsLike(Executable lambda, String message) {
		assertEquals(message, assertThrows(RuntimeException.class, lambda).getMessage());
	}
}