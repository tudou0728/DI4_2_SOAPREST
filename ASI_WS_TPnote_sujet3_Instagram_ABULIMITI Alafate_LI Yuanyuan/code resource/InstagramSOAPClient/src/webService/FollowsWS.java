/**
 * FollowsWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService;

public interface FollowsWS extends java.rmi.Remote {
    public java.lang.String deleteFollow(java.lang.String userName, java.lang.String password, int followId) throws java.rmi.RemoteException;
    public java.lang.String deleteFollower(java.lang.String userName, java.lang.String password, int followerId) throws java.rmi.RemoteException;
    public java.lang.String[] createFollow(java.lang.String userName, java.lang.String password, int followId) throws java.rmi.RemoteException;
    public java.lang.String[] getFollowsOfUser(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException;
    public java.lang.String[] getFollowersOfUser(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException;
}
