package com.recruitment.dao;

import java.util.List;

/**
 * {@code GenericServiceProvider} interface defines basic operation contract
 * between all implementing DAOs.
 * <p>
 * 
 */

public interface GenericDAOContract<E> {

	/**
	 * Creates a new entity using the provided {@code resource}
	 * 
	 * @param resource
	 *            an object containing all associated attributes for creating
	 *            the entity
	 * @return an updated {@code Resource} object after creation
	 */
	public E add(E resource);

	/**
	 * Responsible for deleting existing resource based on unique id.
	 * 
	 * @param id
	 *            unique id of a resource
	 */
	public boolean delete(int id);

	/**
	 * Updates a resource. the resource is identified by its unique id.
	 * 
	 * @param id
	 *            unique id of a resource
	 * @param resource
	 *            new resource for update
	 * @return updated resource
	 */
	public E update(E resource);

	/**
	 * Fetches or retrieves all the resource available.
	 * 
	 * @return data in String format.
	 */
	public List<E> retrieveAll();

	/**
	 * Fetches a resource with respect to its given id.
	 * 
	 * @param id
	 *            a unique specified id.
	 * @return a resource
	 */
	public E retrieve(int id);

}
