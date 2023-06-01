package ternilapilli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TernilapilliTest {
	@Test
	void test00() {
		Ternilapilli game = new Ternilapilli();

		assertTrue(game.getXs().isEmpty());
		assertTrue(game.getOs().isEmpty());
	}

	@Test
	void test01() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));

		assertEquals(1, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertTrue(game.getOs().isEmpty());
	}

	@Test
	void test02() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));

		assertEquals(1, game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1, 1)));
		assertEquals(1, game.getOs().size());
		assertTrue(game.getOs().contains(new Position(2, 2)));
	}

	@Test
	void test03() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		try {
			game.putXAt(new Position(2, 2));
			fail("Exception Error");
		} catch (RuntimeException anError) {
			assertEquals(Ternilapilli.notXsTurn, anError.getMessage());
			assertEquals(1, game.getXs().size());
			assertTrue(game.getXs().contains(new Position(1, 1)));
			assertTrue(game.getOs().isEmpty());
		}
	}

	@Test
	void test04() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));

		try {
			game.putOAt(new Position(3, 3));
			fail("Exception Error");
		} catch (RuntimeException anError) {
			assertEquals(Ternilapilli.notOsTurn, anError.getMessage());
			assertEquals(1, game.getXs().size());
			assertTrue(game.getXs().contains(new Position(1, 1)));
			assertEquals(1, game.getOs().size());
			assertTrue(game.getOs().contains(new Position(2, 2)));
		}
	}

	@Test
	void test05() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));

		try {
			game.putXAt(new Position(1, 1));
			fail("Exception Error");
		} catch (RuntimeException anError) {
			assertEquals(Ternilapilli.postionTakenError, anError.getMessage());
			assertEquals(1, game.getXs().size());
			assertTrue(game.getXs().contains(new Position(1, 1)));
			assertEquals(1, game.getOs().size());
			assertTrue(game.getOs().contains(new Position(2, 2)));
		}
	}

	@Test
	void test06() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		game.putXAt(new Position(1, 2));
		try {
			game.putOAt(new Position(2, 2));
			fail("Exception Error");
		} catch (RuntimeException anError) {
			assertEquals(Ternilapilli.postionTakenError, anError.getMessage());
			assertEquals(2, game.getXs().size());
			assertTrue(game.getXs().contains(new Position(1, 1)));
			assertTrue(game.getXs().contains(new Position(1, 2)));
			assertEquals(1, game.getOs().size());
			assertTrue(game.getOs().contains(new Position(2, 2)));
		}
	}

	@Test
	void test07() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		try {
			game.putOAt(new Position(1, 1));
			fail("Exception Error");
		} catch (RuntimeException anError) {
			assertEquals(Ternilapilli.postionTakenError, anError.getMessage());
			assertEquals(1, game.getXs().size());
			assertTrue(game.getXs().contains(new Position(1, 1)));
			assertTrue(game.getOs().isEmpty());
		}
	}

	@Test
	void test08() {
		Ternilapilli game = new Ternilapilli();
		game.putXAt(new Position(1, 1));
		game.putOAt(new Position(2, 2));
		try {
			game.putXAt(new Position(2, 2));
			fail("Exception Error"); 
		} catch (RuntimeException anError) {
			assertEquals(Ternilapilli.postionTakenError, anError.getMessage());
			assertEquals(1, game.getXs().size());
			assertTrue(game.getXs().contains(new Position(1, 1)));
			assertEquals(1, game.getOs().size());
			assertTrue(game.getOs().contains(new Position(2, 2)));
		}
	}

}
