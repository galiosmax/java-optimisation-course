package ru.nsu.fit.g16203.galios;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class DataSearch {

    private final int ARRAY_SIZE = 1 << 20;

    DataSearch(String substring, File file) {

        if (file.isDirectory()) {
            searchDir(substring, file);
        } else {
            search(substring, file);
        }

    }

    private void searchDir(String substring, File folder) {
        Stack<File> dirs = new Stack<>();
        dirs.push(folder);

        while (!dirs.isEmpty()) {
            File[] files = dirs.pop().listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isDirectory())
                        dirs.push(f);
                    else {
                        search(substring, f);
                    }
                }
            }
        }
    }

    private void search(String substring, File file) {

        TwoWaySearch algorithm = new TwoWaySearch();
        List<Long> positions = new LinkedList<>();

        int length = substring.length();
        byte[] needed = substring.getBytes();
        long fileSize = file.length();
        long currentPos = 0;

        BufferedInputStream reader = null;
        try {
            reader = new BufferedInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        byte[] array = new byte[ARRAY_SIZE];

        try {
            while (currentPos < fileSize) {

                int size = 0;
                if (reader != null) {
                    reader.mark(ARRAY_SIZE);
                    size = reader.read(array, 0, ARRAY_SIZE);
                    reader.reset();
                    reader.skip(size - length);
                }
                List<Long> positionList = algorithm.indexOf(needed, array, size);
                for (Long val : positionList) {
                    positions.add(val + currentPos);
                }
                currentPos += size;
                if (currentPos < fileSize - 1) {
                    currentPos -= length;
                }
            }
            positions.forEach(pos -> System.out.println(file.getName() + ": " + pos));
        } catch (IOException e) {
            System.out.println("Can't read " + file.getName());
            System.out.println(e.getMessage());
        }
    }

}
