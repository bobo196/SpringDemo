package ru.korshun.springdemo.component;

import java.util.Map;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class MyCustomErrorAttributes
        extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {

        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        String message = (String) errorAttributes.get("message");
        String path = (String) errorAttributes.get("path");

        errorAttributes.remove("timestamp");
        errorAttributes.remove("errors");
        errorAttributes.remove("path");

        errorAttributes.put("message", String.format("Message: %s. Path: %s", message, path));
        errorAttributes.put("status", errorAttributes.get("status"));

        return errorAttributes;
    }
}