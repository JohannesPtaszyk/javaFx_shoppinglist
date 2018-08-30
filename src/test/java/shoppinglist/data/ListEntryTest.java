package shoppinglist.data;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;

public class ListEntryTest {

    @Test
    public void listEntryTakesItemAndAmountAndSetsIt() {
        //given
        Item item = mock(Item.class);
        int amount = 2;

        //when
        ListEntry listEntry = new ListEntry(item, amount);

        //then
        assertTrue(listEntry.getItem() == item);
        assertTrue(listEntry.getAmount() == amount);
    }

    @Test
    public void constructorThrowsIllegalArgumentExceptionIfItemIsNull() throws IllegalArgumentException{
        //given
        Item item = null;
        int amount = 0;

        try {
            //when
            ListEntry listEntry = new ListEntry(item, amount);

        } catch (IllegalArgumentException e) {
            //then
            assertTrue(true);
        }

    }

}
