package io.sansam.config.controller.graph;

/**
 * <p>
 * BitMap
 * </p>
 *
 * @author houcb
 * @since 2020-04-27 17:55
 */
public class BitMap {
    // char = 2byte = 16bit
    private char[] bytes;
    private int bites;

    public BitMap(int bites) {
        this.bites = bites;
        this.bytes = new char[bites / 16 + 1];
    }

    public int set(int k) {
        if (k > bites) return -1;
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        bytes[byteIndex] |= (1 << bitIndex);
        return k;
    }

    public boolean get(int k) {
        if (k > bites) return false;
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        return (bytes[byteIndex] & (1 << bitIndex)) != 0;
    }
}
