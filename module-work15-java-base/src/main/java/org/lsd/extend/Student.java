package org.lsd.extend;

/**
 * @author nhsoft.lsd
 */
public class Student extends Person1 {

    public Student() {
        super();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(final String name) {
        super.setName(name);
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

    @Override
    public void setAge(final int age) {
        super.setAge(age);
    }
}
