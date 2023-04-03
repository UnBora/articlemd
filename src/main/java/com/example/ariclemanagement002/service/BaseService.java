package com.example.ariclemanagement002.service;


public interface BaseService<T, ID>{

    void save(T obj);
    T getById(ID id);
    T deleteById(ID id, T obj);
    T disableById(ID id, T obj);
    T enableById(ID id, T obj);

}
