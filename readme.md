spring cloud oauth2 jwt token
一、认证授权服务uaa
1.  @EnableAuthorizationServer 配置认证服务器
访问客户端 ClientDetailsServiceConfigurer 配置在内存中，(可以配置在数据库中)
指定TokenStore为jwt
设置加密key
```
accessTokenConverter.setSigningKey("jwt_sign_key");
```


2. 用户信息 UserDetailsService 获取用户数据的服务
UserDetailsServiceImpl 实现类，在内存中构造了用户，实际要从数据库中查找
3. @EnableResourceServer 为获取用户信息 /user

二、资源服务 product-server
指定jwt签名key  自动配置了jwt TokenStore
```yaml
security:
  oauth2:
    resource:
      jwt:
        key-value: jwt_sign_key
```

资源服务
```
// 声明资源服务
@EnableResourceServer
@EnableWebSecurity
// security 注解 权限 未使用
@EnableGlobalMethodSecurity(prePostEnabled = true)
```


三、网关gateway
@EnableOAuth2Sso 开启客户端配置


# 路由配置
```yaml
zuul:
  routes:
    uaa:
      # 敏感信息 必需
      sensitiveHeaders:
      serviceId: oauth-server
    pms:
      sensitiveHeaders:
      serviceId: product-server
  add-proxy-headers: true

security:
  oauth2:
    client:
      # 授权地址
      access-token-uri: http://localhost:9000/uaa/oauth/token
      user-authorization-uri: http://localhost:9000/uaa/oauth/authorize
      # 访问客户端 在auth-server中指定的
      client-id: client_app
    resource:
      # 资源服务获取用户的地址
      user-info-uri: http://localhost:9000/uaa/user
      prefer-token-info: false
```



最后网关访问

![post-man-oauth2](.\oauth2-server\images\post-man-oauth2.png)



###  选择token  访问资源请求
![choose-token](.\oauth2-server\images\choose-token.png)
带token 访问ok
http://localhost:8000/uaa/user

http://localhost:8000/pms/product

http://localhost:8000/pms/product/user
