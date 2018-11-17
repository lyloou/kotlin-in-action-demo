package com.lyloou.chapter6;

public class ProcessorJavaTest implements Processor.Processor<Void> {
    public Void process() {
        System.out.println("process from java");
        return null; // can not omit
    }
}
