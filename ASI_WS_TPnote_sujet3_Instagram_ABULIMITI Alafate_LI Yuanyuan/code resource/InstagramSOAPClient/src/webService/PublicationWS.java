/**
 * PublicationWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService;

public interface PublicationWS extends java.rmi.Remote {
    public java.lang.String[] getOtherAllPubs(java.lang.String userName, java.lang.String password, int follew_uId) throws java.rmi.RemoteException;
    public java.lang.String[] getOwnAllPubs(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException;
    public java.lang.String createPub(java.lang.String userName, java.lang.String password, java.lang.String imagePath, java.lang.String comment) throws java.rmi.RemoteException;
    public java.lang.String getPub(java.lang.String userName, java.lang.String password, int pId) throws java.rmi.RemoteException;
    public java.lang.String deletePub(java.lang.String userName, java.lang.String password, int pId) throws java.rmi.RemoteException;
}
