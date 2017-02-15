package com.utils;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;
import com.googlecode.genericdao.search.hibernate.HibernateMetadataUtil;
import com.googlecode.genericdao.search.hibernate.HibernateSearchProcessor;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Pablo Sevilla on 25/12/2016.
 */
public class BaseGenericDAOImpl<T, ID extends Serializable> implements BaseGenericDAO<T, ID> {
    Logger logger = Logger.getLogger(BaseGenericDAOImpl.class);

    @Autowired
    public SessionFactory sessionFactory;
    private Class<T> entityClass;
    private HibernateSearchProcessor searchProcessor;
    private HibernateMetadataUtil metadataUtil;

    public BaseGenericDAOImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType)this.getClass().getGenericSuperclass();
        this.entityClass = (Class)genericSuperclass.getActualTypeArguments()[0];
    }

    @PostConstruct
    public void init() {
        this.searchProcessor = HibernateSearchProcessor.getInstanceForSessionFactory(this.sessionFactory);
        this.metadataUtil = HibernateMetadataUtil.getInstanceForSessionFactory(this.sessionFactory);
    }

    public T find(Serializable id) throws EntityNotFoundException {
        Object entity = this.getSession().get(this.entityClass, id);
        if(entity == null) {
            throw new EntityNotFoundException(((Integer)id).intValue());
        } else {
            return (T)entity;
        }
    }

    public <T> T[] find(Serializable... ids) {
        Criteria c = this.getSession().createCriteria(this.entityClass);
        c.add(Restrictions.in("id", ids));
        Object[] retVal = (Object[])((Object[]) Array.newInstance(this.entityClass, ids.length));
        Iterator i$ = c.list().iterator();

        while(i$.hasNext()) {
            Object entity = i$.next();
            byte i = 0;
            if(i < ids.length) {
                retVal[i] = entity;
            }
        }

        return (T[])retVal;
    }

    public T save(Object entity) throws DAOException {
        try {
            this.toTrim(entity);
            return (T)this.getSession().save(entity);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var3) {
            this.logger.error(var3);
            throw new DAOException(var3);
        }
    }

    public void save(Object... entities) throws DAOException {
        Object[] arr$ = entities;
        int len$ = entities.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Object entity = arr$[i$];
            if(entity != null) {
                this.save(entity);
            }
        }

    }

    public T saveUpper(Object entity) throws DAOException {
        try {
            this.toUpperCaseTrim(entity);
            return (T)this.getSession().save(entity);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var3) {
            this.logger.error(var3);
            throw new DAOException(var3);
        }
    }

    public void saveUpper(Object... entities) throws DAOException {
        Object[] arr$ = entities;
        int len$ = entities.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Object entity = arr$[i$];
            if(entity != null) {
                this.saveUpper(entity);
            }
        }

    }

    public void updateUpper(Object entity) throws DAOException {
        try {
            this.toUpperCaseTrim(entity);
            this.getSession().merge(entity);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException var3) {
            this.logger.error(var3);
            throw new DAOException(var3);
        }
    }

    public void update(Object entity) throws DAOException {
        try {
            this.toTrim(entity);
            this.getSession().merge(entity);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var3) {
            this.logger.error(var3);
            throw new DAOException(var3);
        }
    }

    public boolean remove(Object entity) {
        if(entity != null) {
            this.getSession().delete(entity);
            return true;
        } else {
            return false;
        }
    }

    public void remove(Object... entities) {
        Object[] arr$ = entities;
        int len$ = entities.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Object entity = arr$[i$];
            if(entity != null) {
                this.remove(entity);
            }
        }

    }

    public boolean removeId(Serializable id) {
        if(id != null) {
            Object entity = this.getSession().get(this.entityClass, id);
            if(entity != null) {
                this.remove(entity);
                return true;
            }
        }

        return false;
    }

    public void removeId(Serializable... ids) {
        Criteria c = this.getSession().createCriteria(this.entityClass);
        c.add(Restrictions.in("id", ids));
        Iterator i$ = c.list().iterator();

        while(i$.hasNext()) {
            Object entity = i$.next();
            if(entity != null) {
                this.remove(entity);
            }
        }

    }

    public List<T> findAll() {
        return this.getSession().createCriteria(this.entityClass).list();
    }

    public boolean isAttached(Object entity) {
        return this.getSession().contains(entity);
    }

    public void refresh(Object... entities) {
        Object[] arr$ = entities;
        int len$ = entities.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Object entity = arr$[i$];
            if(entity != null) {
                this.getSession().refresh(entities);
            }
        }

    }

    public void flush() {
        this.getSession().flush();
    }

    public List search(com.googlecode.genericdao.search.ISearch search) {
        if(search == null) {
            throw new NullPointerException("Search is null.");
        } else if(search.getSearchClass() != null && !search.getSearchClass().equals(this.entityClass)) {
            throw new IllegalArgumentException("Search class does not match expected type: " + this.entityClass.getName());
        } else {
            return this.searchProcessor.search(this.getSession(), this.entityClass, search);
        }
    }

    public int count(com.googlecode.genericdao.search.ISearch search) {
        if(search == null) {
            throw new NullPointerException("Search is null.");
        } else if(search.getSearchClass() != null && !search.getSearchClass().equals(this.entityClass)) {
            throw new IllegalArgumentException("Search class does not match expected type: " + this.entityClass.getName());
        } else {
            return this.searchProcessor.count(this.getSession(), this.entityClass, search);
        }
    }

    public int count() {
        List counts = this.getSession().createQuery("select count(_it_) from " + this.metadataUtil.get(this.entityClass).getEntityName() + " _it_").list();
        int sum = 0;

        Object count;
        for(Iterator i$ = counts.iterator(); i$.hasNext(); sum += ((Long)count).intValue()) {
            count = i$.next();
        }

        return sum;
    }

    public SearchResult searchAndCount(com.googlecode.genericdao.search.ISearch search) {
        if(search == null) {
            throw new NullPointerException("Search is null.");
        } else if(search.getSearchClass() != null && !search.getSearchClass().equals(this.entityClass)) {
            throw new IllegalArgumentException("Search class does not match expected type: " + this.entityClass.getName());
        } else {
            return this.searchProcessor.searchAndCount(this.getSession(), this.entityClass, search);
        }
    }

    public SearchResult lazySearch(int first, int pageSize) {
        Object list = null;
        boolean totalCount = false;
        SearchResult searchResult = new SearchResult();
        Search search = new Search(this.entityClass);
        int totalCount1 = this.searchProcessor.count(this.getSession(), search);
        searchResult.setTotalCount(totalCount1);
        if(totalCount1 > 0) {
            search.setFirstResult(first);
            search.setMaxResults(pageSize);
            list = this.searchProcessor.search(this.getSession(), search);
        }

        if(list == null) {
            list = new ArrayList();
        }

        searchResult.setResult((List)list);
        return searchResult;
    }

    public SearchResult lazySearch(int first, int pageSize, String sortField, String sortOrder, Map<String, Object> filters) {
        Object list = null;
        boolean totalCount = false;
        SearchResult searchResult = new SearchResult();
        Search search = new Search(this.entityClass);
        if(filters != null && !filters.isEmpty()) {
            Iterator it = filters.keySet().iterator();

            while(it.hasNext()) {
                String filterProperty = (String)it.next();
                Object filterValue = filters.get(filterProperty);
                if(filterValue instanceof String) {
                    search.addFilterILike(filterProperty, "%" + filterValue.toString() + "%");
                } else if(filterValue instanceof String[]) {
                    String[] arrayFilterValue = (String[])((String[])filterValue);
                    List listFilterValue = Arrays.asList(arrayFilterValue);
                    search.addFilterIn(filterProperty, listFilterValue);
                } else {
                    search.addFilterEqual(filterProperty, filterValue);
                }
            }
        }

        int totalCount1 = this.searchProcessor.count(this.getSession(), search);
        searchResult.setTotalCount(totalCount1);
        if(totalCount1 > 0) {
            if(!StringUtils.isBlank(sortField) && !StringUtils.isBlank(sortOrder) && sortOrder.toLowerCase().contains("asc")) {
                search.addSortAsc(sortField);
            }

            search.setFirstResult(first);
            search.setMaxResults(pageSize);
            list = this.searchProcessor.search(this.getSession(), search);
        }

        if(list == null) {
            list = new ArrayList();
        }

        searchResult.setResult((List)list);
        return searchResult;
    }

    public Object searchUnique(com.googlecode.genericdao.search.ISearch search) {
        if(search == null) {
            throw new NullPointerException("Search is null.");
        } else if(search.getSearchClass() != null && !search.getSearchClass().equals(this.entityClass)) {
            throw new IllegalArgumentException("Search class does not match expected type: " + this.entityClass.getName());
        } else {
            return this.searchProcessor.searchUnique(this.getSession(), this.entityClass, search);
        }
    }

    private void toUpperCaseTrim(Object entity) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        java.lang.reflect.Field[] fields = entity.getClass().getDeclaredFields();
        String methodList = this.getMethodsNameByEntity(entity);
        if(methodList != null) {
            java.lang.reflect.Field[] arr$ = fields;
            int len$ = fields.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                java.lang.reflect.Field f = arr$[i$];
                String field = String.valueOf(f.getName().charAt(0)).toUpperCase() + f.getName().substring(1);
                if(f.getType() == String.class && methodList.contains("get" + field) && methodList.contains("set" + field)) {
                    Method getter = entity.getClass().getDeclaredMethod("get" + field, new Class[0]);
                    Method setter = entity.getClass().getDeclaredMethod("set" + field, new Class[]{String.class});
                    Object value = getter.invoke(entity, new Object[0]);
                    if(value != null) {
                        setter.invoke(entity, new Object[]{((String)value).trim().toUpperCase()});
                    }
                }
            }

        }
    }

    private void toTrim(Object entity) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        java.lang.reflect.Field[] fields = entity.getClass().getDeclaredFields();
        String methodList = this.getMethodsNameByEntity(entity);
        if(!methodList.isEmpty()) {
            java.lang.reflect.Field[] arr$ = fields;
            int len$ = fields.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                java.lang.reflect.Field f = arr$[i$];
                String field = String.valueOf(f.getName().charAt(0)).toUpperCase() + f.getName().substring(1);
                if(f.getType() == String.class && methodList.contains("get" + field) && methodList.contains("set" + field)) {
                    Method getter = entity.getClass().getDeclaredMethod("get" + field, new Class[0]);
                    Method setter = entity.getClass().getDeclaredMethod("set" + field, new Class[]{String.class});
                    Object value = getter.invoke(entity, new Object[0]);
                    if(value != null) {
                        setter.invoke(entity, new Object[]{((String)value).trim()});
                    }
                }
            }

        }
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    private String getMethodsNameByEntity(Object entity) {
        String methodList = "";
        Method[] methods = entity.getClass().getDeclaredMethods();
        if(methods != null) {
            methodList = Arrays.toString(methods);
        }

        return methodList;
    }

    @Override
    public SearchResult searchAndCount(com.utils.ISearch var1) {
        return null;
    }

    @Override
    public Object searchUnique(com.utils.ISearch var1) {
        return null;
    }
}