package com.ttfc.soa.dubbo.filter;

import java.util.List;

import com.alibaba.dubbo.common.logger.*;
import com.alibaba.dubbo.rpc.*;


public class AuthorityFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityFilter.class);

    private IpWhiteList ipWhiteList;

    //dubbo通过setter方式自动注入
    public void setIpWhiteList(IpWhiteList ipWhiteList) {
        this.ipWhiteList = ipWhiteList;
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException  {
        if (!ipWhiteList.isEnabled()) {
            System.out.println("白名单禁用");
            return invoker.invoke(invocation);
        }

        String clientIp = RpcContext.getContext().getRemoteHost();
        System.out.println("访问ip为{}"+ clientIp);
        List<String> allowedIps = ipWhiteList.getAllowedIps();
        if (allowedIps.contains(clientIp)) {
            return invoker.invoke(invocation);
        } else {
            return new RpcResult();
        }
    }
}