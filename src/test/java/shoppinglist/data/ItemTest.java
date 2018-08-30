package shoppinglist.data;

import org.junit.Test;

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
}
