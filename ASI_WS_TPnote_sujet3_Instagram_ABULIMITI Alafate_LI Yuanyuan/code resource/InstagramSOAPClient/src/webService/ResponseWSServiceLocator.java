/**
 * ResponseWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService;

public class ResponseWSServiceLocator extends org.apache.axis.client.Service implements webService.ResponseWSService {

    public ResponseWSServiceLocator() {
    }


    public ResponseWSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ResponseWSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ResponseWS
    private java.lang.String ResponseWS_address = "http://localhost:8080/InstagramSOAP/services/ResponseWS";

    public java.lang.String getResponseWSAddress() {
        return ResponseWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ResponseWSWSDDServiceName = "ResponseWS";

    public java.lang.String getResponseWSWSDDServiceName() {
        return ResponseWSWSDDServiceName;
    }

    public void setResponseWSWSDDServiceName(java.lang.String name) {
        ResponseWSWSDDServiceName = name;
    }

    public webService.ResponseWS getResponseWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ResponseWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getResponseWS(endpoint);
    }

    public webService.ResponseWS getResponseWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            webService.ResponseWSSoapBindingStub _stub = new webService.ResponseWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getResponseWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setResponseWSEndpointAddress(java.lang.String address) {
        ResponseWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (webService.ResponseWS.class.isAssignableFrom(serviceEndpointInterface)) {
                webService.ResponseWSSoapBindingStub _stub = new webService.ResponseWSSoapBindingStub(new java.net.URL(ResponseWS_address), this);
                _stub.setPortName(getResponseWSWSDDServiceName());
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
        if ("ResponseWS".equals(inputPortName)) {
            return getResponseWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webService", "ResponseWSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webService", "ResponseWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ResponseWS".equals(portName)) {
            setResponseWSEndpointAddress(address);
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
