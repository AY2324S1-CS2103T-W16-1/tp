package seedu.address.ui;

import java.util.Comparator;
import java.util.Set;
import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;
import seedu.address.model.note.Note;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML TableView<Person> table;
    @FXML TableColumn<Person, Void> indexCol;
    @FXML TableColumn<Person, String> nameCol;
    @FXML TableColumn<Person, String> emailCol;
    @FXML TableColumn<Person, String> phoneCol;
    @FXML TableColumn<Person, String> addressCol;
    @FXML TableColumn<Person, Void> tagsCol;
    @FXML TableColumn<Person, Void> notesCol;
    @FXML TableColumn<Person, Void> eventsCol;


    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList) {
        super(FXML);

        indexCol.setCellFactory(col -> new TableIndexCell());
        indexCol.setPrefWidth(50);

        nameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName().fullName));
        emailCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail().value));

        phoneCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhone().value));
        phoneCol.setPrefWidth(100);

        addressCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress().value));
        tagsCol.setCellFactory(col -> new TableTagsCell(personList));
        notesCol.setCellFactory(col -> new TableNotesCell(personList));
        eventsCol.setCellFactory(col -> new TableEventsCell(personList));

        table.setItems(personList);
    }

    class TableTagsCell extends TableCell<Person, Void> {
        ObservableList<Person> personList;

        public TableTagsCell(ObservableList<Person> personList) {
            this.personList = personList;
        }

        @Override
        public void updateIndex(int index) {
            super.updateIndex(index);
            if (isEmpty() || index < 0) {
                setGraphic(null);
            } else {
                FlowPane tagFlowPane = new FlowPane();
                tagFlowPane.setVgap(8);
                tagFlowPane.setHgap(10);
                tagFlowPane.setPrefWrapLength(200);
                personList.get(index).getTags().stream()
                    .forEach(tag -> tagFlowPane.getChildren().add(new Label(tag.tagName)));
                setGraphic(tagFlowPane);
            }
        }
    }

    class TableNotesCell extends TableCell<Person, Void> {
        ObservableList<Person> personList;

        public TableNotesCell(ObservableList<Person> personList) {
            this.personList = personList;
        }

        @Override
        public void updateIndex(int index) {
            super.updateIndex(index);
            if (isEmpty() || index < 0) {
                setGraphic(null);
            } else {
                ListView<Note> notes = new ListView<>();
                notes.setItems(personList.get(index).getNotes());
                notes.setCellFactory(cell -> new NoteCell());
                notes.setPrefHeight(120);
                notes.setPrefWidth(300);
                setGraphic(notes);
            }
        }
    }

    class TableEventsCell extends TableCell<Person, Void> {
        ObservableList<Person> personList;

        public TableEventsCell(ObservableList<Person> personList) {
            this.personList = personList;
        }

        @Override
        public void updateIndex(int index) {
            super.updateIndex(index);
            if (isEmpty() || index < 0) {
                setGraphic(null);
            } else {
                ListView<Event> events = new ListView<>();
                events.setItems(personList.get(index).getEvents());
                events.setCellFactory(cell -> new EventCell());
                events.setPrefHeight(120);
                setGraphic(events);
            }
        }
    }

    class TableIndexCell extends TableCell<Person, Void> {
        @Override
        public void updateIndex(int index) {
            super.updateIndex(index);
            if (isEmpty() || index < 0) {
                setText(null);
            } else {
                setText(Integer.toString(index + 1));
            }
        }
    }

    private class NoteCell extends ListCell<Note> {
        @Override
        protected void updateItem(Note note, boolean empty) {
            super.updateItem(note, empty);
            if (empty || note == null) {
                setGraphic(null);
                setText(null);
            } else {
                setText((getIndex() + 1) + ". " + note.getUiText());
                setTextFill(Color.WHITE);
            }
        }
    }

    private class EventCell extends ListCell<Event> {
        @Override
        protected void updateItem(Event event, boolean empty) {
            super.updateItem(event, empty);
            if (empty || event == null) {
                setGraphic(null);
                setText(null);
            } else {
                setText((getIndex() + 1) + ". " + event.getUiText());
                setTextFill(Color.WHITE);
            }
        }
    }

}
