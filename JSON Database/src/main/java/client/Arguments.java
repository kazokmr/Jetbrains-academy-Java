package client;

import com.beust.jcommander.Parameter;

public class Arguments {

    @Parameter(names = {"-t", "--type"}, description = "Type of Command")
    private String type;

    @Parameter(names = {"-k", "--key"})
    private String key;

    @Parameter(names = {"-v", "--value"})
    private String value;

    @Parameter(names = {"-in", "--import"})
    private String filename;

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getFilename() {
        return filename;
    }
}
