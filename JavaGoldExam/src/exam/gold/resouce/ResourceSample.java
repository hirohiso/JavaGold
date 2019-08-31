package exam.gold.resouce;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceSample {

    public static void main(String[] args) {
        //リソースファイルの読みこみ
        {
            ResourceBundle rb = ResourceBundle.getBundle("prop");
            System.out.println(rb.getString("KEY1") +" "+ rb.getString("KEY2"));



        }
        {
            ResourceBundle rb = ResourceBundle.getBundle("exam.gold.resouce.BundleSample");
            System.out.println(rb.getString("KEY1") +" "+ rb.getString("KEY2"));
        }
        {
            Locale us = Locale.US;
            ResourceBundle rb = ResourceBundle.getBundle("exam.gold.resouce.BundleSample",us);
            System.out.println(rb.getString("KEY1") +" "+ rb.getString("KEY2"));
        }

        //キー取得（存在）
        //キー取得（存在しない）
        //キー取得（代替）

        //リソースバンドルの読みこみ
        //キー取得（存在）
        //キー取得（存在しない）
        //キー取得（代替）

    }

}
