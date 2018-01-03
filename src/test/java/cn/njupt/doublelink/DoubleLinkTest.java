package cn.njupt.doublelink;

import org.junit.Test;

public class DoubleLinkTest {
    @Test
    public void testInt() {
        System.out.println("----int test----");

        DoubleLink<Integer> doubleLink = new DoubleLink<Integer>();
        doubleLink.insert(0, 10);
        doubleLink.appendLast(20);
        doubleLink.insertFirst(30);

        System.out.println("isEmpty=" + doubleLink.isEmpty());
        System.out.println("size=" + doubleLink.size());

        for (int i = 0; i < doubleLink.size(); i++) {
            System.out.println(doubleLink.get(i));
        }
    }

    @Test
    public void testString() {
        System.out.println("----String test----");

        DoubleLink<String> doubleLink = new DoubleLink<String>();
        doubleLink.insert(0, "10");
        doubleLink.appendLast("20");
        doubleLink.insertFirst("30");

        System.out.println("isEmpty=" + doubleLink.isEmpty());
        System.out.println("size=" + doubleLink.size());

        for (int i = 0; i < doubleLink.size(); i++) {
            System.out.println(doubleLink.get(i));
        }
    }

    @Test
    public void testObject() {
        Student stu1 = new Student("zhangsan");
        Student stu2 = new Student("lisi");
        Student stu3 = new Student("wangwu");

        System.out.println("----Object test----");

        DoubleLink<Student> doubleLink = new DoubleLink<Student>();
        doubleLink.insert(0, stu1);
        doubleLink.appendLast(stu2);
        doubleLink.insertFirst(stu3);

        System.out.println("isEmpty=" + doubleLink.isEmpty());
        System.out.println("size=" + doubleLink.size());

        for (int i = 0; i < doubleLink.size(); i++) {
            System.out.println(doubleLink.get(i).getName());
        }
    }
}

class Student {

    private final String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
