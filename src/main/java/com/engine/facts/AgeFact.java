package com.engine.facts;

/**
 * @author Krzysztof Ziomek
 * @since 23/05/2017.
 */
public class AgeFact implements Fact {

    private Integer age;

    public AgeFact(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }
}
