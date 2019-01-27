/**
 * UserWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService;

public interface UserWS extends java.rmi.Remote {
    public java.lang.String[] createUser(java.lang.String userName, java.lang.String password, boolean privacy) throws java.rmi.RemoteException;
    public java.lang.String deleteUser(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException;
    public java.lang.String updateName(java.lang.String userName, java.lang.String password, java.lang.String newUserName) throws java.rmi.RemoteException;
    public java.lang.String updatePrivacy(java.lang.String userName, java.lang.String password, boolean newPrivacy) throws java.rmi.RemoteException;
    public java.lang.String updatePassword(java.lang.String userName, java.lang.String password, java.lang.String newPassword) throws java.rmi.RemoteException;
    public java.lang.String[] getUserByNameAndPassword(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException;
}
