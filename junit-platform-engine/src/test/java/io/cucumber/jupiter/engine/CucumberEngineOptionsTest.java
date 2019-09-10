package io.cucumber.jupiter.engine;

import io.cucumber.core.snippets.SnippetType;
import org.junit.jupiter.api.Test;

import static io.cucumber.jupiter.engine.Constants.ANSI_COLORS_DISABLED_PROPERTY_NAME;
import static io.cucumber.jupiter.engine.Constants.DRY_RUN_ENABLED_PROPERTY_NAME;
import static io.cucumber.jupiter.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.jupiter.engine.Constants.SNIPPET_TYPE_PROPERTY_NAME;
import static io.cucumber.jupiter.engine.Constants.STRICT_ENABLED_PROPERTY_NAME;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CucumberEngineOptionsTest {

    @Test
    void getPluginNames() {
        assertEquals(
            asList("html:path/to/report.html"),
            new CucumberEngineOptions(
                new MapConfigurationParameters(PLUGIN_PROPERTY_NAME, "html:path/to/report.html")
            ).plugins()
        );
        assertEquals(
            asList("html:path/with spaces/to/report.html", "json:path/with spaces/to/report.json"),
            new CucumberEngineOptions(
                new MapConfigurationParameters(PLUGIN_PROPERTY_NAME, "\"html:path/with spaces/to/report.html\" \"json:path/with spaces/to/report.json\"")
            ).plugins()
        );
    }

    @Test
    void isStrict() {
        assertTrue(new CucumberEngineOptions(new MapConfigurationParameters(STRICT_ENABLED_PROPERTY_NAME, "true")).isStrict());
        assertFalse(new CucumberEngineOptions(new MapConfigurationParameters(STRICT_ENABLED_PROPERTY_NAME, "false")).isStrict());
    }

    @Test
    void isMonochrome() {
        assertTrue(new CucumberEngineOptions(new MapConfigurationParameters(ANSI_COLORS_DISABLED_PROPERTY_NAME, "true")).isMonochrome());
        assertFalse(new CucumberEngineOptions(new MapConfigurationParameters(ANSI_COLORS_DISABLED_PROPERTY_NAME, "false")).isMonochrome());
    }

    @Test
    void getGlue() {

    }

    @Test
    void isDryRun() {
        assertTrue(new CucumberEngineOptions(new MapConfigurationParameters(DRY_RUN_ENABLED_PROPERTY_NAME, "true")).isDryRun());
        assertFalse(new CucumberEngineOptions(new MapConfigurationParameters(DRY_RUN_ENABLED_PROPERTY_NAME, "false")).isDryRun());
    }

    @Test
    void getSnippetType() {
        assertEquals(SnippetType.UNDERSCORE, new CucumberEngineOptions(new MapConfigurationParameters(SNIPPET_TYPE_PROPERTY_NAME, "underscore")).getSnippetType());
        assertEquals(SnippetType.CAMELCASE, new CucumberEngineOptions(new MapConfigurationParameters(SNIPPET_TYPE_PROPERTY_NAME, "camelcase")).getSnippetType());
    }
}