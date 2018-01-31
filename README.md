sso-oauth2
=====
基于`OAuth2.0`中的`授权码模式`和`JavaEE`中的`SSM框架`进行开发的`单点登陆系统`<br>
The system of `Single sign-on` based on `OAuth2.0` and `SSM`.

## 目录
* [OAuth2.0授权码模式](#OAuth2.0授权码模式)
    * [术语解释](#术语解释)
    * [参数解释](#参数解释)
    * [协议流程](#协议流程)
* [系统实现](#系统实现)
    * [SSO服务器](#SSO服务器)
    * [SSO客户端](#SSO客户端)
* [第三方系统接入和使用单点登陆服务](#第三方系统接入和使用单点登陆服务)
    * [申请接入](#申请接入)
    * [审核并同意接入](#审核并同意接入)
    * [SSO客户端使用](#SSO客户端使用)
    * [第三方工程使用](#第三方工程使用)
      * [实现登录的servlet](#实现登录的servlet)
      * [实现退出的servlet](#实现退出的servlet)
* [测试](#测试)

OAuth2.0授权码模式
-----
### 术语解释
|序号|术语|详情|
|---|----|-----|
|1|`Resource Server(RS)`|资源服务器，理解为SSO的客户端|
|2|`Authenticate Server(AS)`|授权服务器，理解为SSO的服务器|
|3|`code`|授权码|
|4|`Access Token`|访问令牌|
|5|`Refresh  Token`|刷新令牌|

### 参数解释
|序号|参数名|参数解释|
|---|----|-----|
|1|`SSO_SERVER_IP`|SSO服务器应用所部署服务器的ip地址|
|2|`SSO_SERVER_PORT`|SSO服务器应用所占用的端口|
|3|`SSO_CLIENT_IP`|SSO客户端应用所部署服务器的ip地址（与所在第三方应用一致）|
|4|`SSO_CLIENT_PORT`|SSO客户端应用所占用的端口（与所在第三方应用一致）|
|5|`CLIENT$ID`|SSO服务器应用所占用的端口|
|6|`CLIENT$SECRET`|SSO服务器应用所占用的端口|
|7|`SSO_CLIENT_URI`|重定向地址，即redirectURI（SSO服务器重定向回SSO客户端所在的第三方系统）|
|7|`returnURI`|目标地址returnURI（用户要访问的资源）|


### 协议流程
![](/img/sso-oauth2-flow.png "协议流程")


系统实现
-----
### SSO服务器
   Spring+SpringMVC+Mybatis、MySQL数据库
   第三方系统接入界面为基于Bootstrap的Hui前端框架

### SSO客户端
   Servlet、HttpClient

第三方系统接入和使用单点登陆服务
-----
### 申请接入
|描述|向SSO服务器申请接入第三方应用|
|----|-----|
|URL|http://IP地址:端口号/sso-server/common/client/switch-on-sso.jsp|
|示例|http://127.0.0.1:8080/sso-server/common/client/switch-on-sso.jsp|
|URL|客户端ID|
   界面如下：
![](/img/第三方接入申请pic.png "第三方接入申请界面")

### 审核并同意接入
|描述|SSO服务器管理人员审核并同意第三方系统接入|
|----|-----|
|URL|http://IP地址:端口号/sso-server/common/login.jsp|
|示例|http:// 127.0.0.1:8080/sso-server/common/login.jsp|
|参数|username=admin password=admin|
   界面如下：
![](/img/审核并同意接入pic.png "审核并同意接入界面")

### 第三方工程使用
   步骤：
      1.将sso-client打成jar包，并导入到第三方系统中
      2.编写servlet继承并重写OAuthServlet中的回调方法
      3.配置filter和servlet
#### 实现登录的servlet
   public class ClientOauthServlet extends `OAuthServlet` {
      @Override
      public void loginSuccess(HttpServletRequest request, HttpServletResponse response,
            AccessTokenModel accessTokenModel) {
         `User user = new User("username", "password");
         request.getSession().setAttribute("user", user);`
         System.out.println("SSO登陆验证成功后的操作...");
      }

      @Override
      public void loginError(HttpServletRequest request, HttpServletResponse response) {
         System.out.println("SSO登陆验证失败后的操作...");
      }
   }
#### 实现退出的servlet
   public class ClientLogoutServlet extends LogoutServlet {
      @Override
      public void logoutError(HttpServletRequest request, HttpServletResponse response) {
         `request.getSession().setAttribute("user", null);`
         System.out.println("退出失败后的操作...");
      }

      @Override
      public void logoutSuccess(HttpServletRequest request, HttpServletResponse response) {
         System.out.println("退出成功后的操作...");
      }
   }

测试
-----
