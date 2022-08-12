package org.lsd.extend;

/**
 * @author nhsoft.lsd
 */
public class Person1 {

    private String name;

    private int age;

    final void fly() {
        System.out.println("i am flying");
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }
}
