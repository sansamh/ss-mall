package io.sansam.spi;

/**
 * <p>
 * Delivery
 * </p>
 *
 * @author houcb
 * @since 2020-07-15 10:03
 */
public interface Delivery {

    boolean match(String company);

    void deliveryProduct(String productName);
}
