package com.utils;

import com.googlecode.genericdao.search.Field;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Sort;

import java.util.List;

/**
 * Created by Pablo Sevilla on 25/12/2016.
 */
public interface ISearch {
    int RESULT_AUTO = 0;
    int RESULT_ARRAY = 1;
    int RESULT_LIST = 2;
    int RESULT_MAP = 3;
    int RESULT_SINGLE = 4;

    int getFirstResult();

    int getMaxResults();

    int getPage();

    Class<?> getSearchClass();

    List<Filter> getFilters();

    boolean isDisjunction();

    List<Sort> getSorts();

    List<Field> getFields();

    boolean isDistinct();

    List<String> getFetches();

    int getResultMode();
}

