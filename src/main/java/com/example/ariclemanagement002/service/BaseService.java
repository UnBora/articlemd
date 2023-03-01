package com.example.ariclemanagement002.service;


public interface BaseService<T, ID, RES>{

    void save(T obj);
    RES getById(ID id);
    RES deleteById(ID id, T obj);
    RES disableById(ID id, T obj);
    RES enableById(ID id, T obj);

}
