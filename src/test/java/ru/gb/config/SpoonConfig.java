package ru.gb.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources({"file:src/test/resources/spoonconfig.properties"})
public interface SpoonConfig extends Config {
    SpoonConfig spoonConfig = ConfigFactory.create(SpoonConfig.class);

    String baseURI();
    String apiKey();
}
