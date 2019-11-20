package ru.nsu.fit.g16203.galios;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        if (args.length > 0) {
            if (args[0].equals("--bytes")) {
                if (args.length == 2) {
                    long longToBytes = Long.parseLong(args[1]);
                    System.out.println(LongBytes.getBits(longToBytes) + " '1' bits in " + longToBytes + " number");
                }
            }
            else if (args[0].equals("--array")) {
                long[] longArray = new long[args.length - 1];
                for (int i = 1; i < args.length; i++) {
                    longArray[i - 1] = Long.parseLong(args[i]);
                }
                byte[] byteArray = LongBytes.toBytes(longArray);

                System.out.println("Byte array: " + Arrays.toString(byteArray));
                System.out.println("Long array from byte array: " + Arrays.toString(LongBytes.backToLongBytes(byteArray)));
            }
        }
        printHelp();
    }

    private static void printHelp() {

    }
}
