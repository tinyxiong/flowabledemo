package com.tinyxiong.flowabledemo.base;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseLoggingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseLoggingExtension.class);
    private static final String EMPTY_LINE = "\n";

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        LOGGER.debug(EMPTY_LINE);
        LOGGER.debug("#### START {}.{} ###########################################################", context.getRequiredTestClass().getSimpleName(),
            context.getRequiredTestMethod().getName());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        context.getExecutionException().ifPresent(BaseLoggingExtension::logExecutionException);

        LOGGER.debug("#### END {}.{} ###########################################################", context.getRequiredTestClass().getSimpleName(),
            context.getRequiredTestMethod().getName());
        LOGGER.debug(EMPTY_LINE);
    }

    private static void logExecutionException(Throwable ex) {
        if (ex instanceof AssertionError) {
            LOGGER.error(EMPTY_LINE);
            LOGGER.error("ASSERTION FAILED: {}", ex, ex);
        } else {
            LOGGER.error(EMPTY_LINE);
            LOGGER.error("EXCEPTION: {}", ex, ex);
        }
    }
}
