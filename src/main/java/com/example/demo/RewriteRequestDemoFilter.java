package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import org.apache.apisix.plugin.runner.HttpRequest;
import org.apache.apisix.plugin.runner.HttpResponse;
import org.apache.apisix.plugin.runner.PostRequest;
import org.apache.apisix.plugin.runner.PostResponse;
import org.apache.apisix.plugin.runner.filter.PluginFilter;
import org.apache.apisix.plugin.runner.filter.PluginFilterChain;
import org.springframework.stereotype.Component;

@Component
public class RewriteRequestDemoFilter implements
        PluginFilter {

    @Override
    public String name() {
        /* It is recommended to keep the name of the filter the same as the class name.
         Configure the filter to be executed on apisix's routes in the following format
         curl http://127.0.0.1:9080/apisix/admin/routes/1 -H 'X-API-KEY: edd1c9f034335f136f87ad84b625c8f1' -X PUT -d '
        {
            "uri": "/*",
            "plugins": {
                "ext-plugin-pre-req": {
                    "conf": [{
                        "name": "RewriteRequestDemoFilter",
                        "value": "{\"rewrite_path\":\"\", \"conf_header_name\":\"joe\", \"conf_header_value\":\"1\", \"conf_arg_name\":\"george\", \"conf_arg_value\":\"2\"}"
                    }]
                }
            },
            "upstream": {
                "nodes": {
                    "127.0.0.1:8081": 1
                },
                "type": "roundrobin"
            }

        }'



        {
            "uri": "/*",
            "plugins": {
                "ext-plugin-pre-req": {
                    "conf": [{
                        "name": "RewriteRequestDemoFilter",
                        "value": "{\"rewrite_path\":\"bob\", \"conf_header_name\":\"joe\", \"conf_header_value\":\"1\", \"conf_arg_name\":\"george\", \"conf_arg_value\":\"2\"}"
                    }]
                }
            },
            "upstream": {
                "nodes": {
                    "127.0.0.1:8080": 1
                },
                "type": "roundrobin"
            }

        }

        {
            "uri": "/hello",
            "plugins": {
                "ext-plugin-pre-req": {
                    "conf": [{
                        "name": "RewriteRequestDemoFilter",
                        "value": "bar"
                    }]
                }
            },
            "upstream": {
                "nodes": {
                    "127.0.0.1:1980": 1
                },
                "type": "roundrobin"
            }
        }

        The value of name in the configuration corresponds to the value of return here.
         */

        return "RewriteRequestDemoFilter";
    }

    @Override
    public void postFilter(PostRequest request, PostResponse response, PluginFilterChain chain) {
        chain.postFilter(request, response);
    }

    /**
     * If you need to fetch some Nginx variables in the current plugin, you will need to declare them in this function.
     * @return a list of Nginx variables that need to be called in this plugin
     */
    @Override
    public List<String> requiredVars() {
        List<String> vars = new ArrayList<>();
        vars.add("remote_addr");
        vars.add("server_port");
        return vars;
    }

    /**
     * If you need to fetch request body in the current plugin, you will need to return true in this function.
     */
    @Override
    public Boolean requiredBody() {
        return true;
    }
}