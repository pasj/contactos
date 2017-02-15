package com.utils;

import com.googlecode.genericdao.search.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Pablo Sevilla on 25/12/2016.
 */
public interface BaseGenericDAO<T, ID extends Serializable> {
    T find(ID var1) throws EntityNotFoundException;

    <T> T[] find(Serializable... var1);

    T save(Object var1) throws DAOException;

    void save(Object... var1) throws DAOException;

    T saveUpper(Object var1) throws DAOException;

    void saveUpper(Object... var1) throws DAOException;

    void updateUpper(Object var1) throws DAOException;

    void update(Object var1) throws DAOException;

    boolean remove(Object var1);

    void remove(Object... var1);

    boolean removeId(Serializable var1);

    void removeId(Serializable... var1);

    List<T> findAll();

    boolean isAttached(Object var1);

    void refresh(Object... var1);

    void flush();

    List search(com.googlecode.genericdao.search.ISearch var1);

    int count(com.googlecode.genericdao.search.ISearch var1);

    int count();

    SearchResult searchAndCount(ISearch var1);

    SearchResult lazySearch(int var1, int var2);

    SearchResult lazySearch(int var1, int var2, String var3, String var4, Map<String, Object> var5);

    Object searchUnique(ISearch var1);
}
