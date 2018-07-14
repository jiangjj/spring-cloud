package com.jiangjj.licensingservice.utils;

import org.springframework.http.HttpHeaders;

public class UserContext {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN = "Authorization";
    public static final String USER_ID = "tmx-user-id";
    public static final String ORG_ID = "tmx-org-id";

    private static final ThreadLocal<String> correlationId = new ThreadLocal<>();
    private static final ThreadLocal<String> authToken = new ThreadLocal<>();
    private static final ThreadLocal<String> userId = new ThreadLocal<>();
    private static final ThreadLocal<String> orgId = new ThreadLocal<>();

    public static String getCorrelationId() {
        return correlationId.get();
    }
    public static void setCorrelationId(String cid) {
        correlationId.set(cid);
    }
    public static String getAuthToken() {
        return authToken.get();
    }
    public static void setAuthToken(String token) {
        authToken.set(token);
    }
    public static String getUserId() {
        return userId.get();
    }
    public static void setUser(String id) {
        userId.set(id);
    }
    public static String getOrgId() {
        return orgId.get();
    }
    public static void setOrgId(String id) {
        orgId.set(id);
    }

    public static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CORRELATION_ID, getCorrelationId());
        return headers;
    }
}
