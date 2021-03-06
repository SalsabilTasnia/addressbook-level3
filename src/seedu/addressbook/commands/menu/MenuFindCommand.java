package seedu.addressbook.commands.menu;

import seedu.addressbook.commands.Command;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.menu.ReadOnlyMenus;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.*;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class MenuFindCommand extends Command {

    public static final String COMMAND_WORD = "findmenu";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all menu items whose names contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers.\n\t"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n\t"
            + "Example: " + COMMAND_WORD + " coke cheese burger";

    private final Set<String> keywords;

    public MenuFindCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyMenus> menusFound = getMenuItemsWithNameContainingAnyKeyword(keywords);
        return new MenuCommandResult(getMessageForMenuListShownSummary(menusFound), menusFound);
    }

    /**
     * Retrieve all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyMenus> getMenuItemsWithNameContainingAnyKeyword(Set<String> keywords) {
        final List<ReadOnlyMenus> matchedMenuItems = new ArrayList<>();
        for (ReadOnlyMenus menu : rms.getAllMenus()){
            final Set<String> wordsInName = new HashSet<>(menu.getName().getWordsInName());
            if (!Collections.disjoint(wordsInName, keywords)) {
                matchedMenuItems.add(menu);
            }
        }
        return matchedMenuItems;
    }

}
