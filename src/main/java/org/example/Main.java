package org.example;

import org.example.Tyrism.*;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        ParserWorker<ArrayList<String>> parser = new ParserWorker<>(new MainParser());
        parser.setParserSettings(new MainSettings());
        parser.onCompletedList.add(new Completed());
        parser.onNewDataList.add(new NewData());

        parser.Start();
        Thread.sleep(10000);
        parser.Abort();


    }
    static class NewData implements OnNewDataHandler<ArrayList<String>> {

        @Override
        public void OnNewData(Object sender, ArrayList<String> args) {
            for (String s : args) {
                System.out.println(s);
            }
        }
    }
    static class Completed implements OnCompleted {

        @Override
        public void OnCompleted(Object sender) {
            System.out.println("Загрузка закончена");
        }
    }

}