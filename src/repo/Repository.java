package repo;

import java.util.Optional;

import model.BaseEntity;

public interface Repository<ID, T extends BaseEntity<ID>> {

	    /**
	     * Find the entity with the given id
	     * @param id-must not be null
	     * @return the entity with the given id
	     * @throws RentalException if the given id is null
	     */
	    Optional<T> findOne(String title);

	    /**
	     *
	     * @return all entities
	     */
	    Iterable<T> findAll();

	    /**
	     * Save the entity to the repository
	     * @param entity-must not be null
	     * @return null if the entity is saved, the entity otherwise
	     * @throws ValidatorException if the entity is not valid
	     */
	    Optional<T> save(T entity);

	    /**
	     * Updates a given entity
	     * @param entity-must not be null
	     * @return null if the entity was updated, the entity otherwise
	     * @throws ValidatorException if the entity is not valid
	     */
	    Optional<T> update(T entity);
}


