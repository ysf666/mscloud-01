package com.neuedu.springcloud.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class AccessTokenFilter1 extends ZuulFilter {
	
	//设置需要添加参数accessToken且参数不能等于zuul
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		Object accessToken = request.getParameter("accessToken");
		System.out.println("accessToken1:" + accessToken);
		if (accessToken == null || "zuul".equals(accessToken)) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(402);
			return null;
		}
		return null;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	//执行顺序为2
	@Override
	public int filterOrder() {
		return 2;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
