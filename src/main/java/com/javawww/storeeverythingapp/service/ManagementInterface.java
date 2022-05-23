package com.javawww.storeeverythingapp.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface ManagementInterface<T> {
    T add(T entity) ;

    void delete(Long id) ;

    T getById(Long id) ;

    List<T> findAll();
}
