package uj.jwzp.w4.tools;

import org.apache.commons.cli.*;

import java.util.Arrays;

public class CommandLineDefaultParser {

    private static final String FILE_NAME = "fileName";

    private final Options options;

    public CommandLineDefaultParser() {
        options = createOptions();
    }

    private Options createOptions() {
        Options options = new Options();
        options.addOption(FILE_NAME, true, "File Name");
        return options;
    }

    public String parse(String[] args) {
        try {
            CommandLineParser parser = new BasicParser();
            CommandLine line = parser.parse(options, args);
            return line.getOptionValue(FILE_NAME);
        } catch (ParseException e) {
            throw new RuntimeException("Exception occurred during parsing command line args:" + Arrays.asList(args));
        }
    }
}
