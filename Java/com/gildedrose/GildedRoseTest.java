package com.gildedrose;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest {

    private GildedRose app;
    private List<Item> items;

    @Before
    public void setup() {
        items = new ArrayList<>();
        app = new GildedRose(items);
    }

    @Test
    public void generic_items_have_quality_decreasing_by_one_for_each_inventory_update() {
        items.add(new Item("a generic item", 1, 2));

        assertEquals(1, app.items.get(0).sellIn);
        assertEquals(2, app.items.get(0).quality);

        app.updateInventory();

        assertEquals(1-1, app.items.get(0).sellIn);
        assertEquals(2-1, app.items.get(0).quality);
    }

    @Test
    public void the_quality_of_an_item_is_never_negative() {
        items.add(new Item("a generic item", 1, 0));

        app.updateInventory();

        assertEquals(0, app.items.get(0).quality);
    }



}
