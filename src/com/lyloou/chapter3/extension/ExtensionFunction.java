package com.lyloou.chapter3.extension;

import com.lyloou.chapter3.override.Button;
import com.lyloou.chapter3.override.View;
import com.lyloou.chapter3.override.ViewKt;

import java.util.Arrays;

import static com.lyloou.chapter3.extension.ExtensionKt.returnSplit;

public class ExtensionFunction {
    public static void main(String[] args) {
        System.out.println(ExtensionFunctionKt.lastChar("demoX"));

        View view = new View();
        Button btn = new Button();
        View btn2 = new Button();
        ViewKt.showOff(btn);
        ViewKt.showOff(btn, 1);
        ViewKt.showOff(btn2); // you can click Ctrl + B to jump the declared position
        ViewKt.showOff(view);


        System.out.println(Arrays.toString("12.345-6.A".split("\\.")));
        System.out.println(returnSplit("demo1:demo2", ":"));
    }
}
