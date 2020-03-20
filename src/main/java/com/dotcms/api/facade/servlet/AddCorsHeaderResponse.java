package com.dotcms.api.facade.servlet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import com.dotcms.api.facade.util.BundleConfigProperties;
import com.dotcms.mock.response.MockResponse;


/**
 * Proxy for HTTPServletResponse
 *
 */
public class AddCorsHeaderResponse extends HttpServletResponseWrapper implements MockResponse {

    private final static String corsHeader = BundleConfigProperties.getProperty("com.dotcms.api.cors.allow.origin", "*");
    private final static String CORS_ORIGIN = "Access-Control-Allow-Origin";

    public AddCorsHeaderResponse(HttpServletResponse response) {
        super(response);


    }



    @Override
    public String getHeader(String name) {
        if (CORS_ORIGIN.equalsIgnoreCase(name)) {
            return corsHeader;
        }
        return super.getHeader(name);
    }



    @Override
    public void addHeader(String name, String value) {
        if (CORS_ORIGIN.equalsIgnoreCase(name)) {
            super.addHeader(name, corsHeader);
        }
        else {
            super.addHeader(name, value);
        }

    }



    @Override
    public boolean containsHeader(String name) {
        if (CORS_ORIGIN.equalsIgnoreCase(name)) {
            return true;
        }
        return super.containsHeader(name);
    }



    @Override
    public Collection<String> getHeaderNames() {

        Set<String> headers= new HashSet<>(super.getHeaderNames());

        headers.add(CORS_ORIGIN);
        return headers;
    }



    @Override
    public Collection<String> getHeaders(String name) {
        if (CORS_ORIGIN.equalsIgnoreCase(name)) {
            Set<String> headers= new HashSet<>();
            headers.add(CORS_ORIGIN);
            return headers;
        }
        return super.getHeaders(name);
    }



    @Override
    public void setHeader(String name, String value) {
        if (CORS_ORIGIN.equalsIgnoreCase(name)) {
            super.setHeader(name, corsHeader);
        }else {
            super.setHeader(name, value);
        }
    }



    @Override
    public HttpServletResponse response() {
        return this;
    }



}
