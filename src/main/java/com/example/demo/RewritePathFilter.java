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

@Component
public class RewritePathFilter implements PluginFilter {

    private final Logger logger = LoggerFactory.getLogger(RewritePathFilter.class);

    @Override
    public String name() {
        return "RewritePathFilter";
    }

    @Override
    public void filter(HttpRequest request, HttpResponse response, PluginFilterChain chain) {
        logger.warn("RewritePathFilter is running");

        // Modify the request path
        String originalPath = request.getPath();
        String newPath = "/ip";
        request.setPath(newPath);
        // request.setHeader("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NzUsImtvZGUiOiI1MzU5IiwidXNlcm5hbWUiOiJ0ZGlvcGVuYXBpIiwicGFzc3dvcmQiOiIkMmEkMTAkaVNQcTZCZWRnQTMyQ1d4dUZuWnR3dW4vZTBUaDRFdnVrTk5BNThjOFhUbTdKUm42UURSZUMiLCJuYW1hIjoiQVBMSUtBU0kgT1BFTiBBUEkiLCJhbGFtYXQiOiJKbC4gUHJhdGlzdGEgUmF5YSBOby4xNyIsIm5vX2t0cCI6bnVsbCwibm9fdGxwIjpudWxsLCJmb3RvIjpudWxsLCJhb2lkIjpudWxsLCJ1c2VyX2dhdGV3YXlfbXN0cl9pZCI6MSwia29kZV9jYWJhbmciOm51bGwsImtvZGVfdXNlcl90eXBlIjoiMDA3IiwidXNlcl9nYXRld2F5X21zdHIiOnsiY3JlYXRlZF9hdCI6IjIwMjAtMTAtMTQgMTA6NDQ6MzAiLCJpZCI6IjEiLCJ1c2VyR3R3IjoiZm9zZ3ciLCJjaGFubmVsSWQiOiIyNyIsImNyZWF0ZWRfYnkiOiJzMTYyNyJ9LCJpYXQiOjE3MTU2NzAxMTMsImV4cCI6MTcxNjU3MDExM30.RYxLH3MOi42cR-wn4EPSSSgD66GEZVGvpSBQLb22jDQ");
        logger.warn("Original path: {}, New path: {}", originalPath, newPath);

        // Continue the filter chain
        chain.filter(request, response);
    }

    @Override
    public List<String> requiredVars() {
        return Collections.emptyList();
    }

    @Override
    public Boolean requiredBody() {
        return false;
    }
}
