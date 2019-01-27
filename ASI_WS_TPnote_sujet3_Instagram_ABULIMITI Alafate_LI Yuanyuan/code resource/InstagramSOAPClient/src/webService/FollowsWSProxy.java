package webService;

public class FollowsWSProxy implements webService.FollowsWS {
  private String _endpoint = null;
  private webService.FollowsWS followsWS = null;
  
  public FollowsWSProxy() {
    _initFollowsWSProxy();
  }
  
  public FollowsWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initFollowsWSProxy();
  }
  
  private void _initFollowsWSProxy() {
    try {
      followsWS = (new webService.FollowsWSServiceLocator()).getFollowsWS();
      if (followsWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)followsWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)followsWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (followsWS != null)
      ((javax.xml.rpc.Stub)followsWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public webService.FollowsWS getFollowsWS() {
    if (followsWS == null)
      _initFollowsWSProxy();
    return followsWS;
  }
  
  public java.lang.String deleteFollow(java.lang.String userName, java.lang.String password, int followId) throws java.rmi.RemoteException{
    if (followsWS == null)
      _initFollowsWSProxy();
    return followsWS.deleteFollow(userName, password, followId);
  }
  
  public java.lang.String deleteFollower(java.lang.String userName, java.lang.String password, int followerId) throws java.rmi.RemoteException{
    if (followsWS == null)
      _initFollowsWSProxy();
    return followsWS.deleteFollower(userName, password, followerId);
  }
  
  public java.lang.String[] createFollow(java.lang.String userName, java.lang.String password, int followId) throws java.rmi.RemoteException{
    if (followsWS == null)
      _initFollowsWSProxy();
    return followsWS.createFollow(userName, password, followId);
  }
  
  public java.lang.String[] getFollowsOfUser(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException{
    if (followsWS == null)
      _initFollowsWSProxy();
    return followsWS.getFollowsOfUser(userName, password);
  }
  
  public java.lang.String[] getFollowersOfUser(java.lang.String userName, java.lang.String password) throws java.rmi.RemoteException{
    if (followsWS == null)
      _initFollowsWSProxy();
    return followsWS.getFollowersOfUser(userName, password);
  }
  
  
}