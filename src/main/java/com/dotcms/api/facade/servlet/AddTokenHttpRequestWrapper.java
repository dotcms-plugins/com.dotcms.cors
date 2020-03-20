package com.dotcms.api.facade.servlet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class AddTokenHttpRequestWrapper extends HttpServletRequestWrapper {

    private final char[] token;

    public AddTokenHttpRequestWrapper(HttpServletRequest request, char[] token) {
        super(request);
        this.token = token;
    }

    @Override
    public String getHeader(String name) {
        if ("Authorization".equalsIgnoreCase(name)) {
            return "Bearer " + new String(token);

        }
        return super.getHeader(name);
    }



    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> headers = new ArrayList<>();
        if ("Authorization".equalsIgnoreCase(name)) {
            headers.add("Bearer " + new String(token));
            return Collections.enumeration(headers);
        } else {
            return super.getHeaders(name);
        }

    }

}
