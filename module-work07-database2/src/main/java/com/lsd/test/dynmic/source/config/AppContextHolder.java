package com.lsd.test.dynmic.source.config;

public class AppContextHolder {

    private static final ThreadLocal<String> sourceId = new InheritableThreadLocal<>();

    private static final ThreadLocal<Boolean> readOnly = new InheritableThreadLocal<>();

    public static void setSourceKey(String tenantId) {
        sourceId.set(tenantId);
    }

    public static String getSourceKey() {
        return sourceId.get();
    }

    public static void setReadOnly(Boolean isReadOnly) {
        readOnly.set(isReadOnly);
    }

    public static Boolean getReadOnly() {
        return readOnly.get();
    }


    public static void clearSourceKey() {
        sourceId.remove();
    }
}
