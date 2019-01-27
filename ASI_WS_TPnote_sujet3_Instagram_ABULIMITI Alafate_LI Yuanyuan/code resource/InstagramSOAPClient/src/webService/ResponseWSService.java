/**
 * ResponseWSService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService;

public interface ResponseWSService extends javax.xml.rpc.Service {
    public java.lang.String getResponseWSAddress();

    public webService.ResponseWS getResponseWS() throws javax.xml.rpc.ServiceException;

    public webService.ResponseWS getResponseWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
