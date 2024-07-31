package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.apisix.plugin.runner.HttpRequest;
import org.apache.apisix.plugin.runner.HttpResponse;
import org.apache.apisix.plugin.runner.filter.PluginFilter;
import org.apache.apisix.plugin.runner.filter.PluginFilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Test implements
        PluginFilter {

    private final Logger logger = LoggerFactory.getLogger(Test.class);

    @Override
    public String name() {
        return "Test";
    }

    @Override
    public void filter(HttpRequest request, HttpResponse response, PluginFilterChain chain) {
        logger.warn("Test is running wwww");
        String http_x_test = request.getVars("http_x_test");
        logger.warn("http_x_test: {}", http_x_test);
        chain.filter(request, response);
    }

    /**
     * If you need to fetch some Nginx variables in the current plugin, you will
     * need to declare them in this function.
     *
     * @return a list of Nginx variables that need to be called in this plugin
     */
    @Override
    public List<String> requiredVars() {
        List<String> vars = new ArrayList<>();
        vars.add("remote_addr");
        vars.add("server_port");
        vars.add("http_x_test");
        return vars;
    }

    /**
     * If you need to fetch request body in the current plugin, you will need to
     * return true in this function.
     */
    @Override
    public Boolean requiredBody() {
        return true;
    }
}
