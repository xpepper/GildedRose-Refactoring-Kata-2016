package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest {

    private GildedRose app;
    private Item[] items;

    @Before
    public void setup() {
        items = new Item[1];
        app = new GildedRose(items);
    }

    @Test
    public void generic_items_have_quality_decreasing_by_one_for_each_inventory_update() {
        items[0] = new Item("a generic item", 1, 2);

        assertEquals(1, app.items[0].sellIn);
        assertEquals(2, app.items[0].quality);

        app.updateInventory();

        assertEquals(1-1, app.items[0].sellIn);
        assertEquals(2-1, app.items[0].quality);
    }

    @Test
    public void the_quality_of_an_item_is_never_negative() {
        items[0] = new Item("a generic item", 1, 0);

        app.updateInventory();

        assertEquals(0, app.items[0].quality);
    }



}
