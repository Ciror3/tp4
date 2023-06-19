package ternilapilli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.Test;

public class TernilapilliTest {
	Ternilapilli game;

	@BeforeEach
	public void setUp() {
		game = new Ternilapilli();
	}

	@Test
	void test00CreateANewEmptyTernilapilli() {
		assertTrue(game.getXs().isEmpty());
		assertTrue(game.getOs().isEmpty());
	}

	@Test
	void test01PutFirstX() {
		game.putXAt(new Position(1, 1));

		assertEquals(1, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getOs().isEmpty());
	}

	@Test
	void test02PutFirstO() {
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));

		assertEquals(1, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertEquals(1, game.getOs().size());
		assertTrue(game.getOs().contains(new Position(2, 2)));
	}

	@Test
	void test03XCannotPlayTwiceInARow() {
		game.putXAt(new Position(1, 1));

		assertThrowsLike(() -> game.putXAt(new Position(3, 3)), Ternilapilli.notXsTurn);
		assertEquals(1, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getOs().isEmpty());

	}

	@Test
	void test04OCannotPlayTwiceInARow() {
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
		game.putXAt(new Position(1, 1));
		assertThrowsLike(() -> game.putOAt(new Position(1, 1)), Ternilapilli.positionTakenError);
		assertEquals(1, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getOs().isEmpty());

	}

	@Test
	void test08CannotPutAnXInAnAlreadyOccupiedPositionByO() {
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
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(3, 1));
		game.putOAt(new Position(2, 3));

		assertFalse(game.XHasWon());
		assertFalse(game.OHasWon());
	}

	@Test
	void test10CheckIfXsWinsByRows() {
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
		game.putXAt(new Position(1, 3));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(2, 2));
		game.putOAt(new Position(3, 2));
		game.putXAt(new Position(3, 1));

		assertThrowsLike(() -> game.putOAt(new Position(3, 3)), Ternilapilli.cannotPlayWhenGameIsOver);

	}

	@Test
	void test20XCannotPutIfItIsNotInTheProperStatus() {
		game.putXAt(new Position(1, 3));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(2, 3));
		game.putOAt(new Position(3, 2));
		game.putXAt(new Position(3, 1));
		game.putOAt(new Position(1, 1));

		assertThrowsLike(() -> game.putXAt(new Position(3, 3)), Ternilapilli.statusError);
		assertEquals(3, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 3)));
		assertTrue(game.getXs().contains(new Position(2, 3)));
		assertTrue(game.getXs().contains(new Position(3, 1)));
	}

	@Test
	void test21OCannotPutIfItIsNotInTheProperStatus() {
		game.putXAt(new Position(1, 3));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(2, 3));
		game.putOAt(new Position(3, 2));
		game.putXAt(new Position(3, 1));
		game.putOAt(new Position(1, 1));

		assertThrowsLike(() -> game.putXAt(new Position(3, 3)), Ternilapilli.statusError);
		assertEquals(3, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 3)));
		assertTrue(game.getXs().contains(new Position(2, 3)));
		assertTrue(game.getXs().contains(new Position(3, 1)));
	}

	@Test
	void test22XCanSlideAToken() {
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
	void test23PlayerCanSlideAToken() {
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
	void test24XCanWinIfItSlidesATokenInARow() {
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 2));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(2, 3));
		game.putOAt(new Position(3, 2));

		game.slideXfrom(2, 3, 1, 3);

		assertTrue(game.XHasWon());
		assertFalse(game.OHasWon());

	}

	@Test
	void test25OCanWinIfItSlidesATokenInARow() {
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 2));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(3, 3));
		game.putOAt(new Position(3, 2));

		game.slideXfrom(1, 2, 1, 3);
		game.slideOfrom(3, 2, 2, 3);

		assertTrue(game.OHasWon());
		assertFalse(game.XHasWon());

	}

	@Test
	void test26XCanWinIfItSlidesATokenInAColumn() {
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(2, 3));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(1, 3));

		game.slideXfrom(3, 2, 3, 1);

		assertTrue(game.XHasWon());
		assertFalse(game.OHasWon());

	}

	@Test
	void test27OCanWinIfItSlidesATokenInAColumn() {
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(2, 3));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(1, 3));

		game.slideXfrom(1, 1, 1, 2);
		game.slideOfrom(2, 2, 3, 3);

		assertFalse(game.XHasWon());
		assertTrue(game.OHasWon());

	}

	@Test
	void test28XCanWinIfItSlidesATokenInADiagonal() {
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 1));
		game.putXAt(new Position(2, 2));
		game.putOAt(new Position(2, 3));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(1, 3));

		game.slideXfrom(2, 3, 3, 3);

		assertTrue(game.XHasWon());
		assertFalse(game.OHasWon());

	}

	@Test
	void test29OCanWinIfItSlidesATokenInADiagonal() {
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(3, 1));
		game.putXAt(new Position(3, 2));
		game.putOAt(new Position(2, 3));

		game.slideXfrom(3, 2, 3, 3);
		game.slideOfrom(2, 3, 1, 3);

		assertTrue(game.OHasWon());
		assertFalse(game.XHasWon());

	}

	@Test
	void test30CannotSlideAnXInAnAlreadyOccupiedPositionByX() {
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
	void test31CannotSlideAnOInAnAlreadyOccupiedPositionByO() {
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
	void test32CannotSlideAnOInAnAlreadyOccupiedPositionByX() {
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
	void test33CannotSlideAnXInAnAlreadyOccupiedPositionByO() {
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
	void test34CannotSlideAXIntoAnIllegalPosition() {
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
	void test35CannotSlideAnOIntoAnIllegalPosition() {
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
	void test36XCannotSlideWhenGameIsOver() {
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
	void test37OCannotSlideWhenGameIsOver() {
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

	@Test
	void test37XCannotSlideWhenPutStatus() {
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));
		game.putOAt(new Position(1, 2));

		assertThrowsLike(() -> game.slideXfrom(2, 1, 3, 1), Ternilapilli.statusError);
		assertEquals(2, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getXs().contains(new Position(2, 1)));
	}
 
	@Test
	void test38OCannotSlideWhenPutStatus() {
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(2, 1));

		assertThrowsLike(() -> game.slideOfrom(2, 2, 1, 2), Ternilapilli.statusError);
		assertEquals(1, game.getOs().size());
		assertTrue(game.getOs().contains(new Position(2, 2)));
	}

	private void assertThrowsLike(Executable lambda, String message) {
		assertEquals(message, assertThrows(RuntimeException.class, lambda).getMessage());
	}
}