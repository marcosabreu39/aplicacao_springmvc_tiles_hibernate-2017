package com.appspringmvc.dao;

public interface Dao<T> {

	void persist(T t) throws Exception;

	void merge(T t) throws Exception;

	void delete(T t) throws Exception;

	boolean searchByAttribute(String attributeName, String attributeValue) throws Exception;
}

