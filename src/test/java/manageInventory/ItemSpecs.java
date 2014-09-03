package manageInventory;

import static org.junit.Assert.*;

import org.junit.Test;

public class ItemSpecs {

	@Test
	public void twoItemsAreNotEqual() {
		Item pen = new Item("pen", 1);
		assertFalse(pen.equals(new Object()));

	}

	@Test
	public void twoItemsAreEqual() {
		Item pen1 = new Item("pen", 1);
		Item pen2 = new Item("pen", 1);
		assertTrue(pen1.equals(pen2));
		assertTrue(pen2.equals(pen1));

	}

	@Test
	public void itemIsNotEqualToNull() {
		Item pen = new Item("pen", 1);
		assertFalse(pen.equals(null));
	}

	@Test
	public void itemIsEqualToItself() {
		Item pen = new Item("pen", 1);
		assertTrue(pen.equals(pen));
	}

	// TODO Transitive test and Hashcode test
}
