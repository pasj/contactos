package com.utils;

import javax.faces.FactoryFinder;
import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
/**
 * Created by Pablo Sevilla on 23/1/2017.
 */

public class FacesUtils {
    private FacesUtils() {
    }

    public static ServletContext getServletContext() {
        return (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
    }

    public static Object getManagedBean(String beanName) {
        return null;
    }

    public static void resetManagedBean(String beanName) {
    }

    public static void setManagedBeanInSession(String beanName, Object managedBean) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(beanName, managedBean);
    }

    public static String getRequestParameter(String name) {
        return (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }

    public static void addInfoMessage(String msg) {
        addInfoMessage((String)null, msg);
    }

    public static void addInfoMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
    }

    public static void addErrorMessage(String msg) {
        addErrorMessage((String)null, msg);
    }

    public static void addErrorMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
    }

    private static Application getApplication() {
        ApplicationFactory appFactory = (ApplicationFactory)FactoryFinder.getFactory("javax.faces.application.ApplicationFactory");
        return appFactory.getApplication();
    }

    public static HttpServletRequest getServletRequest() {
        return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    private static String getJsfEl(String value) {
        return "#{" + value + "}";
    }
}
