/**
 * ResponseWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService;

public interface ResponseWS extends java.rmi.Remote {
    public java.lang.String[] getAllResByPid(java.lang.String userName, java.lang.String password, int pId) throws java.rmi.RemoteException;
    public java.lang.String deleteRes(java.lang.String userName, java.lang.String password, int rId) throws java.rmi.RemoteException;
    public java.lang.String[] createRes(java.lang.String userName, java.lang.String password, java.lang.String comment, int pId) throws java.rmi.RemoteException;
}
