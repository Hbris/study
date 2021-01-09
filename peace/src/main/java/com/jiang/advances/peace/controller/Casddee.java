package com.jiang.advances.peace.controller;

import jakarta.validation.Configuration;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.spi.BootstrapState;
import jakarta.validation.spi.ConfigurationState;
import jakarta.validation.spi.ValidationProvider;

/**
 * Company: waiqin365
 * Description:
 * author:jjw
 * create 2021-01-08 16:29
 */
public class Casddee implements ValidationProvider {
    @Override
    public Configuration createSpecializedConfiguration(BootstrapState state) {
        return null;
    }

    @Override
    public Configuration<?> createGenericConfiguration(BootstrapState state) {
        return null;
    }

    @Override
    public ValidatorFactory buildValidatorFactory(ConfigurationState configurationState) {
        return null;
    }
}
