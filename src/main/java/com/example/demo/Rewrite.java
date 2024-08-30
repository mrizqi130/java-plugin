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
import com.google.gson.JsonObject;

@Component
public class Rewrite implements PluginFilter {

    private final Logger logger = LoggerFactory.getLogger(Rewrite.class);
    private final Gson gson = new Gson();

    @Override
    public String name() {
        return "Rewrite";
    }

    @Override
    public void filter(HttpRequest request, HttpResponse response, PluginFilterChain chain) {
        logger.warn("Rewrite is running");

        // Modify the request path
        String originalPath = request.getPath();
        String newPath = "/api/developer/auth/sign-in";
        request.setPath(newPath);
        logger.warn("Original path: {}, New path: {}", originalPath, newPath);

        // Modify the request body
        String originalBody = request.getBody();
        JsonObject jsonBody = gson.fromJson(originalBody, JsonObject.class);

        // Modify or add new JSON fields
        jsonBody.addProperty("username", "tdiopenapi");
        jsonBody.addProperty("password", "TD10p3N4pI");

        String newBody = gson.toJson(jsonBody);
        request.setBody(newBody);
        logger.warn("Original body: {}, New body: {}", originalBody, newBody);

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
