package org.example.Tyrism;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.sql.Driver;
import java.util.ArrayList;

public class ParserWorker<T> {
    Parser<T> parser;
    ParserSettings parserSettings;
    HTMLLoader loader;
    boolean isActive;

    public ArrayList<OnNewDataHandler> onNewDataList = new ArrayList<>();
    public ArrayList<OnCompleted> onCompletedList = new ArrayList<>();

    public Parser<T> getParser() {
        return parser;
    }

    public ParserSettings getParserSettings() {
        return parserSettings;
    }

    public void setParser(Parser<T> parser) {
        this.parser = parser;
    }

    public void setParserSettings(ParserSettings parserSettings) {
        this.parserSettings = parserSettings;
        loader=new HTMLLoader(parserSettings);
    }

    public void Start()throws IOException{
        isActive=true;
        Worker();
    }

    public void Abort(){
        isActive=false;
    }

    public ParserWorker(Parser<T> parser) {
        this.parser = parser;
    }

    private void Worker()throws IOException{
            if(!isActive){
                onCompletedList.get(0).OnCompleted(this);
                return;
            }
            WebDriver driver = loader.GetSourceByPageId();
            T result = parser.Parse(driver);
            driver.quit();
            onNewDataList.get(0).OnNewData(this,result);

        onCompletedList.get(0).OnCompleted(this);
        isActive = false;
    }
}
