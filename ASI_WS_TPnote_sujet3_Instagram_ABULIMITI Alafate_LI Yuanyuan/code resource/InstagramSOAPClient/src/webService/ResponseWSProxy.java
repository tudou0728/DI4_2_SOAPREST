package webService;

public class ResponseWSProxy implements webService.ResponseWS {
  private String _endpoint = null;
  private webService.ResponseWS responseWS = null;
  
  public ResponseWSProxy() {
    _initResponseWSProxy();
  }
  
  public ResponseWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initResponseWSProxy();
  }
  
  private void _initResponseWSProxy() {
    try {
      responseWS = (new webService.ResponseWSServiceLocator()).getResponseWS();
      if (responseWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)responseWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)responseWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (responseWS != null)
      ((javax.xml.rpc.Stub)responseWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public webService.ResponseWS getResponseWS() {
    if (responseWS == null)
      _initResponseWSProxy();
    return responseWS;
  }
  
  public java.lang.String[] getAllResByPid(java.lang.String userName, java.lang.String password, int pId) throws java.rmi.RemoteException{
    if (responseWS == null)
      _initResponseWSProxy();
    return responseWS.getAllResByPid(userName, password, pId);
  }
  
  public java.lang.String deleteRes(java.lang.String userName, java.lang.String password, int rId) throws java.rmi.RemoteException{
    if (responseWS == null)
      _initResponseWSProxy();
    return responseWS.deleteRes(userName, password, rId);
  }
  
  public java.lang.String[] createRes(java.lang.String userName, java.lang.String password, java.lang.String comment, int pId) throws java.rmi.RemoteException{
    if (responseWS == null)
      _initResponseWSProxy();
    return responseWS.createRes(userName, password, comment, pId);
  }
  
  
}