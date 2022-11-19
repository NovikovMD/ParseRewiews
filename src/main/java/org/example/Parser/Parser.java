package org.example.Parser;

import org.openqa.selenium.WebDriver;

public interface Parser <T> {
    T Parse(WebDriver document);
}
