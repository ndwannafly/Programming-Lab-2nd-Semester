package Commands;

import Core.CollectionManager;
import Core.InputChecker;

public class RemoveByIdCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final InputChecker inputChecker;
    public RemoveByIdCommand(CollectionManager C, InputChecker checker){
        this.collectionManager = C;
        this.inputChecker = checker;
    }
    @Override
    public boolean execute(String argument) {
        if (inputChecker.longValidCheck(argument, (long) 0, Long.MAX_VALUE)) {
            Long id = Long.parseLong(argument);
            if (collectionManager.removeById(id)) {
                System.out.println("Id doesn't exist. Please insert the existing Id!");
                return false;
            }
            return true;
        }
        System.out.println("The inserting ID is not in valid range! Please insert Id greater than 0!");
        return false;
    }
}
