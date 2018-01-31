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
* [第三方系统接入单点登陆服务](#第三方系统接入单点登陆服务)
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

第三方系统接入单点登陆服务
-----


测试
-----
