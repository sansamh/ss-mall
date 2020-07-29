package io.sansam.spi;

/**
 * <p>
 * ShunFengDelivery
 * </p>
 *
 * @author houcb
 * @since 2020-07-15 10:03
 */
public class ShunFengDelivery implements Delivery {
    @Override
    public boolean match(String company) {
        if ("sf".equals(company)) {
            return true;
        }
        return false;
    }

    @Override
    public void deliveryProduct(String productName) {
        System.out.println("顺丰快递为您服务，您的 " + productName + " 已经送达");
    }
}
