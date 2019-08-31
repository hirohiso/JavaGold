package exam.gold.resouce;

import java.util.ListResourceBundle;

public class BundleSample_en_US extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        Object[][] list = {{"KEY1","Hello..."},{"KEY2","World....!!"}};
        return list;
    }

}
