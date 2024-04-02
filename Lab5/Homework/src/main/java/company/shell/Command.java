package company.shell;

public interface Command {
    void execute(String[] args) throws CommandException;

}
