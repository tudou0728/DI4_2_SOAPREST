/**
 * UserWSService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService;

public interface UserWSService extends javax.xml.rpc.Service {
    public java.lang.String getUserWSAddress();

    public webService.UserWS getUserWS() throws javax.xml.rpc.ServiceException;

    public webService.UserWS getUserWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
