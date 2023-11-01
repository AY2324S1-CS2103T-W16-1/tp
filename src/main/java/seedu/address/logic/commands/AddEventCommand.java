package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.ContactID;
import seedu.address.model.person.Person;

/**
 * The command handler for {@code add event} command
 */
public class AddEventCommand extends AddCommand {

    public static final String SECONDARY_COMMAND_WORD = "event";
    public static final String MESSAGE_SUCCESS = "New event added: ";
    public static final String MESSAGE_CONTACT_NOT_FOUND = "Can not find the target contact with ID: ";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SECONDARY_COMMAND_WORD
            + ": Adds an event to a contact.\n"
            + "Usage:  add event -id CONTACT_ID -en EVENT_NAME -st "
            + "START_TIME [-et END_TIME] [-loc LOCATION] [-i INFORMATION]";

    private final Event toAdd;
    private final ContactID contactId;

    /**
     * Creates an AddEventCommand to add the specified {@code Event}
     */
    public AddEventCommand(ContactID contactId, Event event) {
        requireNonNull(event);
        this.contactId = contactId;
        this.toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person person = model.findPersonByUserFriendlyId(this.contactId);
        if (person == null) {
            throw new CommandException(MESSAGE_CONTACT_NOT_FOUND + this.contactId.getId());
        }
        person.addEvent(this.toAdd);

        return new CommandResult(MESSAGE_SUCCESS + toAdd.getName());
    }
}
