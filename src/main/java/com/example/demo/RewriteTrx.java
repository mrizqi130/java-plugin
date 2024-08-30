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
public class RewriteTrx implements PluginFilter {

    private final Logger logger = LoggerFactory.getLogger(RewriteTrx.class);
    private final Gson gson = new Gson();

    @Override
    public String name() {
        return "RewriteTrx";
    }

    @Override
    public void filter(HttpRequest request, HttpResponse response, PluginFilterChain chain) {
        logger.warn("RewriteTrx is running");

        // Modify the request path
        String originalPath = request.getPath();
        String newPath = "/api/developer/core/trx-v2";
        request.setPath(newPath);
        logger.warn("Original path: {}, New path: {}", originalPath, newPath);

        // Modify the request body
        // JsonObject originalBody = gson.fromJson(request.getBody(), JsonObject.class);
        // JsonObject jsonBody = new JsonObject();
        // request.setHeader("grant_type", "0");
        // request.setHeader("additionalInfo", "{}");
        // Modify or add new JSON fields
        // jsonBody.addProperty("partnerReferenceNo", "2020102900000000000001");
        // jsonBody.addProperty("amount", "");
        // jsonBody.addProperty("value", "12345678.00");
        // jsonBody.addProperty("currency", "IDR");
        // jsonBody.addProperty("beneficiarAccountNo", "888801000003301");
        // jsonBody.addProperty("beneficiaryEmail", "yories.yolanda@work.co.id");
        // jsonBody.addProperty("currency", "IDR");
        // jsonBody.addProperty("customerReference", "10052019");
        // jsonBody.addProperty("feeType", "1. OUR Fee is charged to the sender (default) 2. BEN Fee is charged to the recipient 3. SHA|1000 Fee is shared between senderand recipient, with sender is charged Rp1.000,00 and the recipient will be charged the rest");
        // jsonBody.addProperty("remark", "remark test");
        // jsonBody.addProperty("sourceAccount No", "888801000157508");
        // jsonBody.addProperty("transaction Date", "2019-07-03T12:08:56-07:00");
        // JsonObject additionalInfo = new JsonObject();
        // additionalInfo.addProperty("deviceId", "12345679237");
        // additionalInfo.addProperty("channel", "mobilephone");
        // jsonBody.addProperty("additionalInfo", additionalInfo.getAsString());
        // jsonBody.addProperty("password", originalBody.get("kata sandi").getAsString());
        // String jsonBody = gson.toJson(jsonBody);
        // request.setBody(jsonBody.getAsString());
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
