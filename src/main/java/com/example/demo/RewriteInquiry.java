package com.example.demo;

import java.util.Collections;
import java.util.List;

import org.apache.apisix.plugin.runner.HttpRequest;
import org.apache.apisix.plugin.runner.HttpResponse;
import org.apache.apisix.plugin.runner.filter.PluginFilter;
import org.apache.apisix.plugin.runner.filter.PluginFilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class RewriteInquiry implements PluginFilter {

    private final Logger logger = LoggerFactory.getLogger(RewriteInquiry.class);
    private final Gson gson = new Gson();

    @Override
    public String name() {
        return "RewriteInquiry";
    }

    @Override
    public void filter(HttpRequest request, HttpResponse response, PluginFilterChain chain) {
        logger.warn("RewriteInquiry is running");

        // Modify the request path
        String originalPath = request.getPath();
        String newPath = "/api/v1.0/account-saving/0080210002622";
        request.setPath(newPath);
        request.setArg("userId", "s1627");
        request.setArg("financing", "false");
        logger.warn("Original path: {}, New path: {}", originalPath, newPath);
        chain.filter(request, response);
    }

    @Override
    public List<String> requiredVars() {
        return Collections.emptyList();
    }

    @Override
    public Boolean requiredBody() {
        return true;
    }
}
