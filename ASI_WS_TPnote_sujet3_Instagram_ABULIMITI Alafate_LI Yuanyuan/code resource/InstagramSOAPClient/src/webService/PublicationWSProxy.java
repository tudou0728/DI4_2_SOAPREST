package webService;

public class PublicationWSProxy implements webService.PublicationWS {
  private String _endpoint = null;
  private webService.PublicationWS publicationWS = null;
  
  public PublicationWSProxy() {
    _initPublicationWSProxy();
  }
  
  public PublicationWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initPublicationWSProxy();
  }
  
  private void _initPublicationWSProxy() {
    try {
      publicationWS = (new webService.PublicationWSServiceLocator()).getPublicationWS();
      if (publicationWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)publicationWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)publicationWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (publicationWS != null)
      ((javax.xml.rpc.Stub)publicationWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public webService.PublicationWS getPublicationWS() {
    if (publicationWS == null)
      _initPublicationWSProxy();
    return publicationWS;
  }
  
  public java.lang.String[] getOtherAllPubs(java.lang.String userName, java.lang.String password, int follew_uId) throws java.rmi.RemoteException{
    if (publicationWS == null)
      _initPublicationWSProxy();
    return publicationWS.getOtherAllPubs(userName, password, follew_uId);
  }
  
  public java.lang.String[] getOwnAllPubs(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException{
    if (publicationWS == null)
      _initPublicationWSProxy();
    return publicationWS.getOwnAllPubs(userName, password);
  }
  
  public java.lang.String createPub(java.lang.String userName, java.lang.String password, java.lang.String imagePath, java.lang.String comment) throws java.rmi.RemoteException{
    if (publicationWS == null)
      _initPublicationWSProxy();
    return publicationWS.createPub(userName, password, imagePath, comment);
  }
  
  public java.lang.String getPub(java.lang.String userName, java.lang.String password, int pId) throws java.rmi.RemoteException{
    if (publicationWS == null)
      _initPublicationWSProxy();
    return publicationWS.getPub(userName, password, pId);
  }
  
  public java.lang.String deletePub(java.lang.String userName, java.lang.String password, int pId) throws java.rmi.RemoteException{
    if (publicationWS == null)
      _initPublicationWSProxy();
    return publicationWS.deletePub(userName, password, pId);
  }
  
  
}