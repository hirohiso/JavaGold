package exam.gold.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsSample {

    public static void main(String[] args) {

    }

    public static class Gene1<T>{
        private T t;
        private List<T> list = new ArrayList<>();

        public Gene1(T t){
            this.t = t;
        }

        public T getPara(){
            return this.t;
        }

    }

    public static class Gen2<T extends Number>{
        public T SendReturn(T a){
            return a;
        }
    }
    public static class Gen3 <T extends Integer>extends Gen2{

    }
    public static class Gen4<T extends Integer> extends Gen2<T>{

    }

}
