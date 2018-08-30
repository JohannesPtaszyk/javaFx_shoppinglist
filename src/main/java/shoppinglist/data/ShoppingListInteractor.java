package shoppinglist.data;

import javafx.collections.ObservableList;

public class ShoppingListInteractor {

    private static final String DIRECTION_UP = "UP";
    private static final String DIRECTION_DOWN = "DOWN";

    private final ObservableList<ListEntry> list;

    public ShoppingListInteractor(ObservableList<ListEntry> list) {
        this.list = list;
    }

    public void moveEntryUp(ListEntry listEntry) {
        if(isActionInvalid(listEntry, DIRECTION_UP)) {
            return;
        }

        int newIndex = list.indexOf(listEntry) - 1;
        swapEntries(listEntry, newIndex);
    }

    public void moveEntryDown(ListEntry listEntry) {
        if(isActionInvalid(listEntry, DIRECTION_DOWN)) {
            return;
        }

        int newIndex = list.indexOf(listEntry) + 1;
        swapEntries(listEntry, newIndex);
    }

    public int getIndexOfEntryWithItemName(String itemName) {
        for (ListEntry el : list) {
            if (el.getItem().getName().equals(itemName)) {
                return list.indexOf(el);
            }
        }
        return -1;
    }

    public boolean hasListEntryWithItemName(String itemName) {
        for (ListEntry el : list) {
            if (el.getItem().getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    private void swapEntries(ListEntry listEntry, int newIndex) {
        ListEntry entryToReplace = list.get(newIndex);
        list.set(list.indexOf(listEntry), entryToReplace);
        list.set(newIndex, listEntry);
    }

    private boolean isActionInvalid(ListEntry listEntry, String direction) {
        int entryIndex = list.indexOf(listEntry);
        return isEntryNotInList(listEntry) || isMoveInvalid(entryIndex, direction);
    }

    private boolean isEntryNotInList(ListEntry listEntry) {
        return !list.contains(listEntry);
    }

    private boolean isMoveInvalid(int entryIndex, String direction) {
    return (isFirstListEntry(entryIndex) && isDirectionUp(direction))
            || (isLastListEntry(entryIndex) && isDirectionDown(direction))
            || isListSizeOne();
    }

    private boolean isDirectionUp(String direction) {
        return direction.equals(DIRECTION_UP);
    }

    private boolean isDirectionDown(String direction) {
        return direction.equals(DIRECTION_DOWN);
    }

    private boolean isFirstListEntry(int entryIndex) {
        return entryIndex <= 0;
    }

    public boolean isFirstListEntry(ListEntry listEntry) {
        int entryIndex = list.indexOf(listEntry);
        return isFirstListEntry(entryIndex);
    }

    private boolean isLastListEntry(int entryIndex) {
        return entryIndex >= list.size() - 1;
    }

    public boolean isLastListEntry(ListEntry listEntry) {
        int entryIndex = list.indexOf(listEntry);
        return isLastListEntry(entryIndex);
    }

    private boolean isListSizeOne() {
        return list.size() == 1;
    }
}
