package com.javaplenomarcosabreu.dao;

public interface Dao<T> {

	Integer find(Integer id);

	void persist(T t) throws Exception;

	void merge(T t) throws Exception;

	void remove(T t) throws Exception;

	boolean searchByAttribute(String attributeName, String attributeValue) throws Exception;
}

