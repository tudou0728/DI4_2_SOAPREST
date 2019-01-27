/**
 * FollowsWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService;

public class FollowsWSServiceLocator extends org.apache.axis.client.Service implements webService.FollowsWSService {

    public FollowsWSServiceLocator() {
    }


    public FollowsWSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FollowsWSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FollowsWS
    private java.lang.String FollowsWS_address = "http://localhost:8080/InstagramSOAP/services/FollowsWS";

    public java.lang.String getFollowsWSAddress() {
        return FollowsWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FollowsWSWSDDServiceName = "FollowsWS";

    public java.lang.String getFollowsWSWSDDServiceName() {
        return FollowsWSWSDDServiceName;
    }

    public void setFollowsWSWSDDServiceName(java.lang.String name) {
        FollowsWSWSDDServiceName = name;
    }

    public webService.FollowsWS getFollowsWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FollowsWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFollowsWS(endpoint);
    }

    public webService.FollowsWS getFollowsWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            webService.FollowsWSSoapBindingStub _stub = new webService.FollowsWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getFollowsWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFollowsWSEndpointAddress(java.lang.String address) {
        FollowsWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (webService.FollowsWS.class.isAssignableFrom(serviceEndpointInterface)) {
                webService.FollowsWSSoapBindingStub _stub = new webService.FollowsWSSoapBindingStub(new java.net.URL(FollowsWS_address), this);
                _stub.setPortName(getFollowsWSWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("FollowsWS".equals(inputPortName)) {
            return getFollowsWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webService", "FollowsWSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webService", "FollowsWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FollowsWS".equals(portName)) {
            setFollowsWSEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
