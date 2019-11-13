package ru.nsu.fit.g16203.galios;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class FileSearch {

    FileSearch(File file, File folder) {
        String name = file.getName();

        List<String> results = myFunc(name, folder);
        results.forEach(System.out::println);
    }

    private List<String> myFunc(String fileName, File folder) {
        List<String> results = new LinkedList<>();

        Stack<File> dirs = new Stack<>();
        dirs.push(folder);


        while (!dirs.isEmpty()) {
            File[] files = dirs.pop().listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isDirectory())
                        dirs.push(f);
                    else {
                        if (f.getName().contains(fileName)) {
                            results.add(f.getAbsolutePath());
                        }
                    }
                }
            }
        }
        return results;
    }
}
