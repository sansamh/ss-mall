package io.sansam.spi;

/**
 * <p>
 * YunDaDelivery
 * </p>
 *
 * @author houcb
 * @since 2020-07-15 10:04
 */
public class YunDaDelivery implements Delivery {
    @Override
    public boolean match(String company) {
        if ("yd".equals(company)) {
            return true;
        }
        return false;
    }

    @Override
    public void deliveryProduct(String productName) {
        System.out.println("韵达快递为您服务，您的 " + productName + " 已经送达");

    }
}
