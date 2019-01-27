/**
 * PublicationWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webService;

public class PublicationWSServiceLocator extends org.apache.axis.client.Service implements webService.PublicationWSService {

    public PublicationWSServiceLocator() {
    }


    public PublicationWSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PublicationWSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PublicationWS
    private java.lang.String PublicationWS_address = "http://localhost:8080/InstagramSOAP/services/PublicationWS";

    public java.lang.String getPublicationWSAddress() {
        return PublicationWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PublicationWSWSDDServiceName = "PublicationWS";

    public java.lang.String getPublicationWSWSDDServiceName() {
        return PublicationWSWSDDServiceName;
    }

    public void setPublicationWSWSDDServiceName(java.lang.String name) {
        PublicationWSWSDDServiceName = name;
    }

    public webService.PublicationWS getPublicationWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PublicationWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPublicationWS(endpoint);
    }

    public webService.PublicationWS getPublicationWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            webService.PublicationWSSoapBindingStub _stub = new webService.PublicationWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getPublicationWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPublicationWSEndpointAddress(java.lang.String address) {
        PublicationWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (webService.PublicationWS.class.isAssignableFrom(serviceEndpointInterface)) {
                webService.PublicationWSSoapBindingStub _stub = new webService.PublicationWSSoapBindingStub(new java.net.URL(PublicationWS_address), this);
                _stub.setPortName(getPublicationWSWSDDServiceName());
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
        if ("PublicationWS".equals(inputPortName)) {
            return getPublicationWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webService", "PublicationWSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webService", "PublicationWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PublicationWS".equals(portName)) {
            setPublicationWSEndpointAddress(address);
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
