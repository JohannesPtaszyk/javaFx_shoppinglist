package shoppinglist.data;

import com.sun.istack.internal.NotNull;

public class ListEntry {

    private Item item;
    private int amount;

    public ListEntry(@NotNull Item item, int amount) throws IllegalArgumentException {
        if(item == null) {
            throw new IllegalArgumentException("item can not be null!");
        }

        this.item = item;
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }
}
