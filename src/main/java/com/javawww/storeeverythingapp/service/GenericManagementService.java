package com.javawww.storeeverythingapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.text.MessageFormat;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
public class GenericManagementService <T, U extends JpaRepository<T, Long>> implements ManagementInterface<T> {
    protected U repository;

    public GenericManagementService(U repository) {
        this.repository = repository;
    }

    @Override
    public T add (T entity) {
        log.info(MessageFormat.format("Added entity {0} ", entity.getClass()));
        return repository.save(entity);
    }

    @Override
    public void delete (Long id) {
        log.info(MessageFormat.format("Deleted entity with id = {0}", id));
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T getById (Long id) {
        log.info(MessageFormat.format("Select entity with id = {0}", id));
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No entity found for such id!"));
    }


    @Override
    public List<T> findAll () {
        return repository.findAll();
    }
}
