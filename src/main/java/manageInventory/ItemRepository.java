package manageInventory;
import java.util.List;

public interface ItemRepository {
	public abstract boolean addValidItem(Item item);

	public abstract List<Item> findAll();
}