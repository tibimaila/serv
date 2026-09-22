    package io.serv.repository;

    import java.util.List; 
    import java.util.Optional;

    public interface Repository<T, ID> {
        
        Optional<T> findById(ID id);

        /**
        * Returns all entities managed by the repository.
        *
        * @return a list containing all stored entities
        */
        List<T> findAll();

        /**
        * Saves an entity.
        *
        * @param entity the entity to save
        * @return the saved entity
        */
        T save(T entity);

        void deleteById(ID id);
    }
