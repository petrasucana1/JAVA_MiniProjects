package company.shell;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;

public class ExportCommand implements Command {
    @Override
    public void execute(String[] args) throws CommandException {
        if (args.length != 1) {
            throw new CommandException("Invalid arguments for export command. Usage: export <repository>");
        }
        String repositoryPath = args[0];
        String outputFileName = "repository.json";

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode = objectMapper.createObjectNode();

        try {
            exportRepository(new File(repositoryPath), rootNode, objectMapper);
            objectMapper.writeValue(new File(outputFileName), rootNode);
            System.out.println("Exported repository to JSON file: " + outputFileName);
        } catch (IOException e) {
            throw new CommandException("Error exporting repository to JSON: " + e.getMessage());
        }
    }


    private void exportRepository(File directory, ObjectNode parentNode, ObjectMapper objectMapper) throws IOException {
        if (directory.isDirectory()) {
            ObjectNode directoryNode = objectMapper.createObjectNode();
            ArrayNode filesArrayNode = objectMapper.createArrayNode();
            ArrayNode directoriesArrayNode = objectMapper.createArrayNode();
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    filesArrayNode.add(file.getName());
                } else if (file.isDirectory()) {
                    exportRepository(file, directoryNode, objectMapper);
                    directoriesArrayNode.add(file.getName());
                }
            }
            directoryNode.set("files", filesArrayNode);
            directoryNode.set("directories", directoriesArrayNode);
            parentNode.set(directory.getName(), directoryNode);
        }
    }
}