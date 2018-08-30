package shoppinglist.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;

public class ShoppingListInteractorTest {

    private ObservableList<ListEntry> list;


    private ListEntry listEntry1;
    private ListEntry listEntry2;
    private ListEntry listEntry3;

    @Before
    public void setup() {
        listEntry1 = mock(ListEntry.class);
        listEntry2 = mock(ListEntry.class);
        listEntry3 = mock(ListEntry.class);
        list = FXCollections.observableArrayList();
    }

    @Test
    public void moveEntryUp_swapsEntryWithItsPredecessor() {
        //given
        list.add(listEntry1);
        list.add(listEntry2);
        list.add(listEntry3);
        //and
        ShoppingListInteractor interactor = new ShoppingListInteractor(list);

        //when
        interactor.moveEntryUp(listEntry2);

        //then
        assertTrue(list.get(0) == listEntry2);
        assertTrue(list.get(1) == listEntry1);
    }

    @Test
    public void moveEntryUp_doesNothingIfEntryIsFirst() {
        //given
        list.add(listEntry1);
        list.add(listEntry2);
        list.add(listEntry3);
        //and
        ShoppingListInteractor interactor = new ShoppingListInteractor(list);

        //when
        interactor.moveEntryUp(listEntry1);

        //then
        assertTrue(list.get(0) == listEntry1);
    }

    @Test
    public void moveEntryDown_swapsEntryWithItsSuccessor() {
        //given
        list.add(listEntry1);
        list.add(listEntry2);
        list.add(listEntry3);
        //and
        ShoppingListInteractor interactor = new ShoppingListInteractor(list);

        //when
        interactor.moveEntryDown(listEntry1);

        //then
        assertTrue(list.get(0) == listEntry2);
        assertTrue(list.get(1) == listEntry1);
    }

    @Test
    public void moveEntryDown_doesNothingIfEntryIsLast() {
        //given
        list.add(listEntry1);
        list.add(listEntry2);
        list.add(listEntry3);
        //and
        ShoppingListInteractor interactor = new ShoppingListInteractor(list);

        //when
        interactor.moveEntryDown(listEntry3);

        //then
        assertTrue(list.get(2) == listEntry3);
    }

    @Test
    public void hasListEntryWithItemName_isTrueIfListHasEntryWithItemName() {
        //given
        String name = "Test";
        Item item = new Item(name);
        ListEntry listEntry = new ListEntry(item, 1);
        list.add(listEntry);
        //and
        ShoppingListInteractor interactor = new ShoppingListInteractor(list);

        //expect
        assertTrue(interactor.hasListEntryWithItemName(name));
    }

    @Test
    public void hasListEntryWithItemName_isFalseIfListDoesNotHaveEntryWithItemName() {
        //given
        String name = "Test";
        //and
        ShoppingListInteractor interactor = new ShoppingListInteractor(list);

        //expect
        assertFalse(interactor.hasListEntryWithItemName(name));
    }

    @Test
    public void getIndexOfEntryWithItemName_returnsCorrectIndex() {
        //given
        String name = "Test";
        Item item = new Item(name);
        ListEntry listEntry = new ListEntry(item, 1);
        list.add(listEntry);
        //and
        ShoppingListInteractor interactor = new ShoppingListInteractor(list);

        //expected
        assertEquals(interactor.getIndexOfEntryWithItemName(name), 0);
    }

    @Test
    public void getIndexOfEntryWithItemName_returnsMinusOneIfEntryDoesNotExist() {
        //given
        String name = "Test";
        //and
        ShoppingListInteractor interactor = new ShoppingListInteractor(list);

        //expected
        assertEquals(interactor.getIndexOfEntryWithItemName(name), -1);
    }

    @Test
    public void isFirstListEntry_isTrueIfEntryIsFirst() {
        //given
        list.add(listEntry1);
        list.add(listEntry2);
        list.add(listEntry3);
        //and
        ShoppingListInteractor interactor = new ShoppingListInteractor(list);

        //expect
        assertTrue(interactor.isFirstListEntry(listEntry1));
    }

    @Test
    public void isFirstListEntry_isFalseIfEntryIsNotFirst() {
        //given
        list.add(listEntry1);
        list.add(listEntry2);
        list.add(listEntry3);
        //and
        ShoppingListInteractor interactor = new ShoppingListInteractor(list);

        //expect
        assertFalse(interactor.isFirstListEntry(listEntry2));
    }

    @Test
    public void isLastListEntry_isTrueIfEntryIsLast() {
        //given
        list.add(listEntry1);
        list.add(listEntry2);
        list.add(listEntry3);
        //and
        ShoppingListInteractor interactor = new ShoppingListInteractor(list);

        //expect
        assertTrue(interactor.isLastListEntry(listEntry3));
    }

    @Test
    public void isLastListEntry_isFalseIfEntryIsNotLast() {
        //given
        list.add(listEntry1);
        list.add(listEntry2);
        list.add(listEntry3);
        //and
        ShoppingListInteractor interactor = new ShoppingListInteractor(list);

        //expect
        assertFalse(interactor.isLastListEntry(listEntry2));
    }



}
