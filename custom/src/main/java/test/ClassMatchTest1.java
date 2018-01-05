package test;

public class ClassMatchTest1 {

    public void test11() {
        String path = this.getClass().getResource("").getFile();

        //System.out.println("====" + path);
        //System.out.println("ClassMatchTest11");
    }
    public static void test12() {
        //System.out.println("ClassMatchTest12");
    }
}
