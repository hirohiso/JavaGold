package exam.gold.resouce;

import java.util.ListResourceBundle;

public class BundleSample extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        Object[][] list = {{"KEY1","こんにちは"},{"KEY2","せかい"}};
        return list;
    }

}
