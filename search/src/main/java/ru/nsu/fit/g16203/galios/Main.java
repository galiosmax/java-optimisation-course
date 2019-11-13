package ru.nsu.fit.g16203.galios;

import org.docopt.Docopt;

import java.io.File;
import java.util.Map;

public class Main {

    private static final String doc =
            "Search.\n"
                    + "\n"
                    + "Usage:\n"
                    + "  search (-d | --data) <text> <folder|file>\n"
                    + "  search (-n | --name) <file> <folder>\n"
                    + "  search (-h | --help)\n"
                    + "  search (-v | --version)\n"
                    + "\n"
                    + "Options:\n"
                    + "  -h --help      Show this screen.\n"
                    + "  -v --version   Show version.\n"
                    + "  -d --data      Search by data.\n"
                    + "  -n --name      Search by name.\n"
                    + "\n";

    private static final String TEXT = "<text>";
    private static final String FOLDERFILE = "<folder | file>";
    private static final String FOLDER = "<folder>";
    private static final String FILE = "<file>";
    private static final String DATA = "--data";
    private static final String NAME = "--name";

    public static void main(String[] args) {
        Map<String, Object> opts =
                new Docopt(doc).withVersion("Search 1.0").parse(args);

        if ((Boolean) opts.get(DATA)) {
            File file = new File((String) opts.get(FOLDERFILE));
            String substring = (String) opts.get(TEXT);

            new DataSearch(substring, file);
        } else if ((Boolean) opts.get(NAME)) {

            File file = new File((String) opts.get(FILE));
            File folder = new File((String) opts.get(FOLDER));

            new FileSearch(file, folder);
        } else {
            System.out.println(opts);
        }
    }

}
