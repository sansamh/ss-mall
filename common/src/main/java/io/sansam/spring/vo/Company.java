package io.sansam.spring.vo;

import lombok.Data;

/**
 * <p>
 * Company
 * </p>
 *
 * @author houcb
 * @since 2020-07-10 11:05
 */
@Data
public class Company {

    private String name;

    private Employee employee;

    public void open() {
        System.out.println("company " + name + " is open");
        employee.work();
    }
}
