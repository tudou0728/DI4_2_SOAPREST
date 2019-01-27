package webService;

public class UserWSProxy implements webService.UserWS {
  private String _endpoint = null;
  private webService.UserWS userWS = null;
  
  public UserWSProxy() {
    _initUserWSProxy();
  }
  
  public UserWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initUserWSProxy();
  }
  
  private void _initUserWSProxy() {
    try {
      userWS = (new webService.UserWSServiceLocator()).getUserWS();
      if (userWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)userWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)userWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (userWS != null)
      ((javax.xml.rpc.Stub)userWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public webService.UserWS getUserWS() {
    if (userWS == null)
      _initUserWSProxy();
    return userWS;
  }
  
  public java.lang.String[] createUser(java.lang.String userName, java.lang.String password, boolean privacy) throws java.rmi.RemoteException{
    if (userWS == null)
      _initUserWSProxy();
    return userWS.createUser(userName, password, privacy);
  }
  
  public java.lang.String deleteUser(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException{
    if (userWS == null)
      _initUserWSProxy();
    return userWS.deleteUser(userName, password);
  }
  
  public java.lang.String updateName(java.lang.String userName, java.lang.String password, java.lang.String newUserName) throws java.rmi.RemoteException{
    if (userWS == null)
      _initUserWSProxy();
    return userWS.updateName(userName, password, newUserName);
  }
  
  public java.lang.String updatePrivacy(java.lang.String userName, java.lang.String password, boolean newPrivacy) throws java.rmi.RemoteException{
    if (userWS == null)
      _initUserWSProxy();
    return userWS.updatePrivacy(userName, password, newPrivacy);
  }
  
  public java.lang.String updatePassword(java.lang.String userName, java.lang.String password, java.lang.String newPassword) throws java.rmi.RemoteException{
    if (userWS == null)
      _initUserWSProxy();
    return userWS.updatePassword(userName, password, newPassword);
  }
  
  public java.lang.String[] getUserByNameAndPassword(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException{
    if (userWS == null)
      _initUserWSProxy();
    return userWS.getUserByNameAndPassword(userName, password);
  }
  
  
}