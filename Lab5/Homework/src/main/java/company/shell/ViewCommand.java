package company.shell;

import java.io.IOException;
import java.io.File;
import java.awt.Desktop;

/**
 * Command to view a document
 */
public class ViewCommand implements Command{
    @Override
    public void execute(String[] args) throws CommandException {
        if(args.length != 1) {
            throw new CommandException("Invalid arguments for view command. Usage: view <document>");
        }

        String document= args[0];
        try{
            Desktop.getDesktop().open(new File(document));
        } catch (IOException e){
            throw new CommandException("Error opening document: "+ e.getMessage());
        }
    }
}
