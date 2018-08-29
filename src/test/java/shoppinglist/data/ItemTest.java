package shoppinglist.data;

import org.junit.Test;
import shoppinglist.Item;

import static junit.framework.TestCase.assertEquals;

public class ItemTest {

    @Test
    public void nameCanBeSetInConstructor() {
        //given
        String name = "TestItem";

        //when
        Item item = new Item(name);

        //then
        assertEquals(name, item.getName());
    }

    @Test
    public void nameCanBeSetThroughSetter() {
        //given
        String name = "TestItem";
        Item item = new Item("NameToOverride");

        //when
        item.setName(name);

        //then
        assertEquals(name, item.getName());
    }
}
