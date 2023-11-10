package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_INTEGER_ARGUMENT;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventID;
import seedu.address.model.event.EventInformation;
import seedu.address.model.event.EventLocation;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.model.note.NoteContent;
import seedu.address.model.note.NoteID;
import seedu.address.model.note.NoteTitle;
import seedu.address.model.person.Address;
import seedu.address.model.person.ContactID;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();

        try {
            long longValue = Long.parseLong(trimmedIndex);
            if (longValue > Integer.MAX_VALUE) {
                return Index.fromOneBased(Integer.MAX_VALUE);
            }
        } catch (Exception e) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }

        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String eventName} into a {@code EventName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventName} is invalid.
     */
    public static ContactID parseContactID(String contactID) throws ParseException {
        requireNonNull(contactID);
        String trimmedContactID = contactID.trim();
        if (trimmedContactID.isEmpty()) {
            throw new ParseException(ContactID.MESSAGE_NON_EMPTY);
        }
        ContactID result = null;
        try {
            result = ContactID.fromString(trimmedContactID);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_INTEGER_ARGUMENT, e.getMessage()));
        }
        return result;
    }

    /**
     * Parses a {@code String noteTitle} into a {@code NoteTitle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code noteTitle} is invalid.
     */
    public static NoteTitle parseNoteTitle(String noteTitle) throws ParseException {
        requireNonNull(noteTitle);
        String trimmedNoteTitle = noteTitle.trim();
        if (!NoteTitle.isValidNoteTitle(trimmedNoteTitle)) {
            throw new ParseException(NoteTitle.MESSAGE_CONSTRAINTS);
        }
        return NoteTitle.fromString(trimmedNoteTitle);
    }

    /**
     * Parses a {@code String noteContent} into a {@code NoteContent}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code noteContent} is invalid.
     */
    public static NoteContent parseNoteContent(String noteContent) throws ParseException {
        requireNonNull(noteContent);
        String trimmedNoteContent = noteContent.trim();
        if (!NoteContent.isValidNoteContent(trimmedNoteContent)) {
            throw new ParseException(NoteContent.MESSAGE_CONSTRAINTS);
        }
        return NoteContent.fromString(trimmedNoteContent);
    }

    /**
     * Parses a {@code String noteID} into a {@code NoteID}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code noteID} is invalid.
     */
    public static NoteID parseNoteID(String noteID) throws ParseException {
        requireNonNull(noteID);
        String trimmedNoteID = noteID.trim();
        if (trimmedNoteID.isEmpty()) {
            throw new ParseException(NoteID.MESSAGE_NON_EMPTY);
        }
        NoteID result = null;
        try {
            result = NoteID.fromString(trimmedNoteID);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_INTEGER_ARGUMENT, e.getMessage()));
        }
        return result;
    }

    /**
     * Parses a {@code String eventName} into a {@code EventName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventName} is invalid.
     */
    public static EventName parseEventName(String eventName) throws ParseException {
        requireNonNull(eventName);
        String trimmedEventName = eventName.trim();
        if (!EventName.isValidEventName(trimmedEventName)) {
            throw new ParseException(EventName.MESSAGE_CONSTRAINTS);
        }
        return EventName.fromString(trimmedEventName);
    }

    /**
     * Parses a {@code String eventTime} into a {@code EventTime} with the specified date-time format.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventTime} is invalid.
     */
    public static EventTime parseEventTime(String eventTime) throws ParseException {
        String trimmedEventTime = "";
        if (eventTime != null) {
            trimmedEventTime = eventTime.trim();
            if (trimmedEventTime.isEmpty()) {
                throw new ParseException(EventTime.MESSAGE_NON_EMPTY);
            }
        }
        EventTime result = null;
        try {
            result = EventTime.fromString(trimmedEventTime);
        } catch (DateTimeParseException e) {
            throw new ParseException(EventTime.MESSAGE_INVALID_DATETIME_FORMAT + e.getMessage());
        }
        return result;
    }

    /**
     * Parses a {@code String eventLocation} into a {@code EventLocation}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventLocation} is invalid.
     */
    public static EventLocation parseEventLocation(String eventLocation) throws ParseException {
        if (eventLocation == null) {
            return EventLocation.fromString("");
        }
        String trimmedEventLocation = eventLocation.trim();
        if (!EventLocation.isValidEventLocation(trimmedEventLocation)) {
            throw new ParseException(EventLocation.MESSAGE_CONSTRAINTS);
        }
        return EventLocation.fromString(trimmedEventLocation);
    }

    /**
     * Parses a {@code String eventInformation} into a {@code EventInformation}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventInformation} is invalid.
     */
    public static EventInformation parseEventInformation(String eventInformation) throws ParseException {
        if (eventInformation == null) {
            return EventInformation.fromString("");
        }
        String trimmedEventInformation = eventInformation.trim();
        if (!EventInformation.isValidEventInformation(trimmedEventInformation)) {
            throw new ParseException(EventInformation.MESSAGE_CONSTRAINTS);
        }
        return EventInformation.fromString(trimmedEventInformation);
    }

    /**
     * Parses a {@code String eventID} into a {@code EventID}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code eventID} is invalid.
     */
    public static EventID parseEventID(String eventID) throws ParseException {
        requireNonNull(eventID);
        String trimmedEventID = eventID.trim();
        if (trimmedEventID.isEmpty()) {
            throw new ParseException(EventID.MESSAGE_NON_EMPTY);
        }
        EventID result = null;
        try {
            result = EventID.fromString(trimmedEventID);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_INTEGER_ARGUMENT, e.getMessage()));
        }
        return result;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Calculate the similarity score of objects where 0.0 implies absolutely no
     * similarity
     * and 1.0 implies absolute similarity
     *
     * @author rrice-reused
     * @param first the first string to compare
     * @param second the second string to compare
     * @return a number between 0.0 and 1.0
     */
    public static double score(String first, String second) {
        // Create two sets of character bigrams, one for each string
        Set<String> s1 = splitIntoBigrams(first);
        Set<String> s2 = splitIntoBigrams(second);

        // Get the number of elements in each set
        int n1 = s1.size();
        int n2 = s2.size();

        // Find the intersection, and get the number of elements in that set
        s1.retainAll(s2);
        int nt = s1.size();

        return (2.0 * (double) nt) / ((double) (n1 + n2));
    }

    //@author rrice-reused
    private static Set<String> splitIntoBigrams(String s) {
        ArrayList<String> bigrams = new ArrayList<String>();

        if (s.length() < 2) {
            bigrams.add(s);
        } else {
            for (int i = 1; i < s.length(); i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(s.charAt(i - 1));
                sb.append(s.charAt(i));
                bigrams.add(sb.toString());
            }
        }
        return new TreeSet<String>(bigrams);
    }
}
