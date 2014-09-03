package manageInventory;

public class Item implements Comparable<Item> {

	private final String name;
	private final int quantity;

	public Item(String itemName, int quantity) {
		this.name = itemName;
		this.quantity = quantity;

	}

	public int getQuantity() {
		return quantity;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		Item given = (Item) obj;

		return given.getName().equals(this.getName())
				&& given.getQuantity() == this.getQuantity();

	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = hash * 17 + quantity;
		hash = hash * 31 + name.hashCode();
		return hash;
	}

	@Override
	public int compareTo(Item other) {

		return this.name.compareToIgnoreCase(other.name);
	}

	
}
