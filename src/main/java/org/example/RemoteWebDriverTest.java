package org.example;
import java.net.URL;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.HasCasting;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;


public class RemoteWebDriverTest extends BaseTest {
    URL gridUrl;

    @BeforeEach
    public void startGrid() {
        gridUrl = startStandaloneGrid();
    }

    @Test
    public void runRemote() {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(gridUrl, options);
    }

    @Test
    public void uploads() {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(gridUrl, options);
        driver.get("https://github.com");
        String titulo = driver.getTitle();
        System.out.println(titulo);
    }
    @Test
    public void augment() {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(gridUrl, options);

        driver = new Augmenter().augment(driver);

        Assertions.assertTrue(driver instanceof HasCasting);
    }

    @Test
    public void remoteWebDriverBuilder() {
        driver =
                RemoteWebDriver.builder()
                        .address(gridUrl)
                        .oneOf(new ChromeOptions())
                        .setCapability("ext:options", Map.of("key", "value"))
                        .config(ClientConfig.defaultConfig())
                        .build();

        Assertions.assertTrue(driver instanceof HasCasting);
    }

}