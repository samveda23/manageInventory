package manageInventory;

import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ItemRepositorySpecs {
	
	private static ItemRepositorySupport repoSupport = new ItemRepositorySupport();
	private static ItemRepository repository;
	
	@BeforeClass
	public static void createConnectionAndTable() throws ClassNotFoundException, SQLException {
		repository = repoSupport.createTableAndRepository();
	}
	
	@After
	public void clean() throws SQLException {
		repoSupport.cleanTable();
	}
	
	@AfterClass
    public static void dropTable() throws SQLException {
		repoSupport.dropTable();
	}

	@Test
	public void emptyRepositoryReturnsNothing() throws SQLException {
		// When
		List<Item> items = repository.findAll();
		// Then
		assertEquals(0, items.size());
	}

	@Test
	public void repositoryContainingOneItemReturnsOne() throws SQLException,
			ClassNotFoundException {
		// Given
		Item pen = new Item("pen", 1);
		repoSupport.insert(pen);
		// When
		List<Item> items = repository.findAll();
		// Then
		assertEquals(1, items.size());
		assertEquals(pen, items.get(0));
	}

	@Test
	public void addsUniqueItemsOnly(){
		Item pen = new Item("pen", 1);
		assertTrue(repository.addValidItem(pen));
	}

	@Test
	public void doesNotAllowItemWithSameName() throws ClassNotFoundException,
			SQLException {
		//Given
		Item pen = new Item("Pen", 1);
		repoSupport.insert(pen);
		// When - Then
		assertFalse( repository.addValidItem(pen));
	}
	
	@Test
	public void repositoryCannotWorkWithoutAConnection() {
		try {
			new JdbcRepository(null);
			fail("Repository got a connection, when not expected!");
		} catch (IllegalArgumentException e) {
		   assertEquals("Empty Connection!", e.getMessage());	
		}
	}



}
