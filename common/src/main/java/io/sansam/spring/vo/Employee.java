package io.sansam.spring.vo;

import lombok.Data;

/**
 * <p>
 * Employee
 * </p>
 *
 * @author houcb
 * @since 2020-07-10 11:06
 */
@Data
public class Employee {

    private String name;

    public void work() {
        System.out.println("worker " + name + " is working");
    }
}
