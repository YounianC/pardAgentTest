package test;

import java.util.ArrayList;
import java.util.List;

public class ClassMatchTest1 {

    public void test11() {
        String path = this.getClass().getResource("").getFile();

        //System.out.println("====" + path);
        //System.out.println("ClassMatchTest11");
    }
    public static void test12() {
        //System.out.println("ClassMatchTest12");
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");


        System.out.println(list.subList(3,10 > list.size() ? list.size() :10));
    }
}
