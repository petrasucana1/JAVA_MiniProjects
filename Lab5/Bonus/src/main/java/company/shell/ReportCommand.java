package company.shell;

import company.repository.InvalidRepositoryException;
import company.repository.Repository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Command to create and open a HTML report representing the content of the repository
 */
public class ReportCommand implements Command{

    @Override
    public void execute(String[] args) throws CommandException {
        if(args.length != 1){
            throw new CommandException("Invalid arguments for report command. Usage: report <repository>");
        }

        String repository= args[0];
        String reportOutput="report.html";

        Configuration configuration= new Configuration(Configuration.VERSION_2_3_31);
        configuration.setClassForTemplateLoading(ReportCommand.class, "/templates");

        try {
            Repository repository1= new Repository(repository);
            List<File> subdirectories= repository1.getAllSubdirectories();

            Template template = configuration.getTemplate("report_template.ftl");

            Map<String, Object> data = new HashMap<>();
            data.put("repository", subdirectories);

            FileWriter writer = new FileWriter(reportOutput);
            template.process(data, writer);
            writer.close();

            Desktop.getDesktop().open(new File(reportOutput));

        } catch (IOException  | InvalidRepositoryException e) {
                throw new CommandException("Error generating or opening HTML report: " + e.getMessage());
       } catch (TemplateException e) {
                throw new CommandException("Error processing FreeMarker template: " + e.getMessage());
        }
    }
}
