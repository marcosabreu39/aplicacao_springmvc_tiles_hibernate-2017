public interface Dao<T> {

	Object find(Object id);

	void persist(T t);

	void merge(T t);

	void remove(T t);

	boolean searchByAttribute(String attributeName, String attributeValue);
}

