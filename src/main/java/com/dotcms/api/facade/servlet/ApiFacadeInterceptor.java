
package com.dotcms.api.facade.servlet;


import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dotcms.api.facade.util.BundleConfigProperties;
import com.dotcms.filters.interceptor.Result;
import com.dotcms.filters.interceptor.WebInterceptor;
import com.dotmarketing.util.Logger;



public class ApiFacadeInterceptor implements WebInterceptor {


    private final static String[] filters = {"/api/"};
    private final static String allowUriRegex =  BundleConfigProperties.getProperty("com.dotcms.api.allowed.uri.regex", "/api/.*");

    private final Pattern matchPattern;
    private final char[] token;
    public ApiFacadeInterceptor() {
        Logger.info(this.getClass(), "Starting API Facade - "  + allowUriRegex );
        
        matchPattern=Pattern.compile(allowUriRegex);
        this.token =  BundleConfigProperties.getProperty("com.dotcms.api.token", "").toCharArray();

    }


    @Override
    public final String[] getFilters() {
        return filters;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }


    @Override
    public Result intercept(final HttpServletRequest request, final HttpServletResponse response) throws IOException {


        
        if(!uriMatches(request) || noAuthorization(request)) {
            return Result.NEXT;
            
        }
        
        System.out.println("we got an api request : " + request.getRequestURI());

        
        return new Result.Builder()
                        .next()
                        .wrap(new AddTokenHttpRequestWrapper(request, token))
                        .wrap(new AddCorsHeaderResponse(response))
                        .build();

    }


    private boolean uriMatches(HttpServletRequest request) {
        return (matchPattern.matcher(request.getRequestURI()).find());
    }
    private boolean noAuthorization(HttpServletRequest request) {
        return request.getHeader("Authorization")!=null;
    }
    
    public void destroy() {
        System.out.println("ApiFacadeInterceptor Filter Destroyed");
    }



}
