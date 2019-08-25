package exam.gold.localFormat;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class LocalFormatSample {

    public static void main(String[] args) {
        {
            NumberFormat nf1 = NumberFormat.getInstance();
            NumberFormat nf2 = NumberFormat.getInstance(Locale.US);
            System.out.println(nf1.format(1234567890));
            System.out.println(nf2.format(1234567890));
        }
        {
            NumberFormat nf1 = NumberFormat.getCurrencyInstance();
            NumberFormat nf2 = NumberFormat.getCurrencyInstance(Locale.US);
            System.out.println(nf1.format(1234567890));
            System.out.println(nf2.format(1234567890));
        }
        {
            customFormat("",1234567890);
            customFormat("\u00a500,0,0,0,000,000.000",1234567890);

        }
    }

    private static void customFormat(String format ,double value){
        DecimalFormat df = new DecimalFormat(format);
        System.out.println(df.format(value));
    }

}
