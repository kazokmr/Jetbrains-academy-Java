package animals.game.knowledge.dao;

import animals.Main;
import animals.game.knowledge.KnowledgeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class KnowledgeNodeDao {

    private static final KnowledgeNodeDao instance = new KnowledgeNodeDao();
    private ObjectMapper objectMapper;
    private File file;

    private KnowledgeNodeDao() {
        setFileType(Main.getFileType());
    }

    public static KnowledgeNodeDao getInstance() {
        return instance;
    }

    public void writeFile(KnowledgeNode root) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public KnowledgeNode readFile() {
        try {
            return file.exists() ? objectMapper.readValue(file, KnowledgeNode.class) : null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setFileType(String fileType) {
        String fileName = "animals";
        String localeLang = Locale.getDefault().getLanguage();
        if (!localeLang.equals("en")) {
            fileName += "_" + localeLang;
        }
        file = new File(String.format("%s.%s", fileName, fileType));
        switch (fileType) {
            case "xml":
                objectMapper = new XmlMapper();
                break;
            case "yaml":
                objectMapper = new YAMLMapper();
                break;
            default:
                objectMapper = new JsonMapper();
                file = new File(String.format("%s.%s", fileName, "json"));
                break;
        }
    }
}
