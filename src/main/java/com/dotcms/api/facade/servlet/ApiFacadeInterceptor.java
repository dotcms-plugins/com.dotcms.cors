
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


    private final static String allowUriRegex = BundleConfigProperties.getProperty("com.dotcms.cors.uri.regex", "/api/.*");

    private final Pattern matchPattern;

    public ApiFacadeInterceptor() {
        Logger.info(this.getClass().getName(), "Starting API Facade - " + allowUriRegex);

        matchPattern = Pattern.compile(allowUriRegex);
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }


    @Override
    public Result intercept(final HttpServletRequest request, final HttpServletResponse response) throws IOException {


        if (!uriMatches(request)) {
            return Result.NEXT;
        }

        Logger.info(this.getClass().getName(),"we got an api request : " + request.getRequestURI());

        final String allowOrigin = BundleConfigProperties.getProperty("Access-Control-Allow-Origin", "*");
        final String allowHeaders = BundleConfigProperties.getProperty("Access-Control-Allow-Headers", "Authorization,Accept,Cookies,Content-Type,Content-Length");
        final String allowMethods = BundleConfigProperties.getProperty("Access-Control-Allow-Methods", "GET,HEAD,POST,PUT,DELETE,OPTIONS");

        response.addHeader("Access-Control-Allow-Origin", allowOrigin);
        response.addHeader("Access-Control-Allow-Headers", allowHeaders);
        response.addHeader("Access-Control-Allow-Methods", allowMethods);

        return new Result.Builder()
                .next()
                .wrap(new AddCorsHeaderResponse(response))
                .build();
    }

    private boolean uriMatches(HttpServletRequest request) {
        return (matchPattern.matcher(request.getRequestURI()).find());
    }

    public void destroy() {
        System.out.println("ApiFacadeInterceptor Filter Destroyed");
    }
}
