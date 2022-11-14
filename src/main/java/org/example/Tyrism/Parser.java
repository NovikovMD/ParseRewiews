package org.example.Tyrism;

import org.openqa.selenium.WebDriver;

public interface Parser <T> {
    T Parse(WebDriver document);
}
