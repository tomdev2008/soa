
package
======================================================
base:     com.ttfc.soa.dubbo
  - provider:  com.ttfc.soa.dubbo.provider
  - proxy:     com.ttfc.soa.dubbo.proxy

provider:
  - domain:    com.ttfc.soa.dubbo.provider.domain
  - service:   com.ttfc.soa.dubbo.provider.service
  - impl:      com.ttfc.soa.dubbo.provider.service.impl
  - mapper:    com.ttfc.soa.dubbo.provider.mapper
  
proxy: 
  - domain:    com.ttfc.soa.dubbo.proxy.domain
  - service:   com.ttfc.soa.dubbo.proxy.service
  - impl:      com.ttfc.soa.dubbo.proxy.service.impl
  
class:
=======================================================
1. 接口   ###service
2. 接口实现 ###servimpl
3. 接口代理类 ###serviceProxy
4. 接口代理雷实现  ###servimplProxy


包和class更名所影响的配置文件
========================       
   name            path  
1. mapper xml      src/main/resouces
2.                 spring-a.xml
3.                 spring-dubbo.xml
4.                 spring-mybatics.mxl
