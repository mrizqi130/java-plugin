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
public class RewriteSignIn implements PluginFilter {

    private final Logger logger = LoggerFactory.getLogger(RewriteSignIn.class);
    private final Gson gson = new Gson();

    @Override
    public String name() {
        return "RewriteSignIn";
    }

    @Override
    public void filter(HttpRequest request, HttpResponse response, PluginFilterChain chain) {
        logger.warn("RewriteSignIn is running");

        // Modify the request path
        String originalPath = request.getPath();
        String newPath = "/api/developer/auth/sign-in";
        request.setPath(newPath);
        logger.warn("Original path: {}, New path: {}", originalPath, newPath);

        // Modify the request body
        // JsonObject originalBody = gson.fromJson(request.getBody(), JsonObject.class);
        // JsonObject jsonBody = new JsonObject();
        response.setHeader("grant_type", "0");
        response.setHeader("additionalInfo", "{}");
        // Modify or add new JSON fields
        // jsonBody.addProperty("username", originalBody.get("nama pengguna").getAsString());
        // jsonBody.addProperty("password", originalBody.get("kata sandi").getAsString());
        // String newBody = gson.toJson(jsonBody);
        // request.setBody(newBody);
        // logger.warn("Original body: {}, New body: {}", originalBody, jsonBody);
        // Continue the filter chain
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
