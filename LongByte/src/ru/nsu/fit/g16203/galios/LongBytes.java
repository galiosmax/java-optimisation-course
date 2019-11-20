package ru.nsu.fit.g16203.galios;

class LongBytes {
    static int getBits(long number) {
        int count = 0;

        while (number != 0) {
            if ((number & 1) == 1) {
                count++;
            }
            number >>>= 1;
        }
        return count;
    }

    static byte[] toBytes(long[] array) {

        int length = array.length;
        byte[] byteArray = new byte[length * Long.BYTES];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < Long.BYTES; j++) {
                byteArray[i * Long.BYTES + (Long.BYTES - 1 - j)] = (byte)(array[i] >>> j * Byte.SIZE);
            }
        }
        return byteArray;
    }

    static long[] backToLongBytes(byte[] array) {
        int length = array.length / Long.BYTES;
        long[] longArray = new long[length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < Long.BYTES; j++) {
                longArray[i] <<= Byte.SIZE;
                longArray[i] |= (array[j] & 0xFF);
            }
        }
        return longArray;
    }
}
