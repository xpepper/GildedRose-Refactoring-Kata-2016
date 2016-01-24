package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void generic_items_have_quality_decreasing_by_one_for_each_inventory_update() {
        Item[] items = new Item[] { new Item("foo", 1, 2) };

        GildedRose app = new GildedRose(items);

        assertEquals(1, app.items[0].sellIn);
        assertEquals(2, app.items[0].quality);

        app.updateInventory();

        assertEquals(1-1, app.items[0].sellIn);
        assertEquals(2-1, app.items[0].quality);
    }

}
