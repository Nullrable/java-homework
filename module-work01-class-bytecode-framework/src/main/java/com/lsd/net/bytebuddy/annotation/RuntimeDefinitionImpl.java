package com.lsd.net.bytebuddy.annotation;

import java.lang.annotation.Annotation;

/**
 * @Author: nhsoft.lsd
 */
public class RuntimeDefinitionImpl implements RuntimeDefinition {
    @Override
    public Class<? extends Annotation> annotationType() {
        return RuntimeDefinition.class;
    }
}
