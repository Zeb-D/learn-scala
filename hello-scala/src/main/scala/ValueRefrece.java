import java.util.ArrayList;
import java.util.List;

/**
 * @author created by Zeb灬D on 2021-08-13 10:34
 */
public class ValueRefrece {
    public static void f1(String s) {
        s = "f1";
    }

    public static void f2(Integer s) {
        s = Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        String s = "f";
        f1(s);
        System.out.println(s);
        Integer i = new Integer(1);
        f2(i);
        System.out.println(i);

        p p1 = new c();
        p1.sayHello();

        String uid = "123";
        List<String> list = new ArrayList<>();
        list.add(uid);
        changeList(uid, list);
        System.out.println(uid);
        System.out.println(list.get(0));

    }

    static class p {
        protected String name = "p";

        public p() {
            System.out.println(name);
        }

        public void sayHello() {
            System.out.println("p");
        }
    }

    static class c extends p {
        public c() {
            name = "c";
            System.out.println(name);
        }

        public void sayHello() {
            System.out.println("c");
        }
    }

    public static void changeList(String uid, List<String> list) {
        uid = "456";
        list.clear();
        list.add(uid);
    }
}
