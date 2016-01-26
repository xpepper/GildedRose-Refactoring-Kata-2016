package com.gildedrose.item;

import com.gildedrose.Item;

public class GenericItemUpdater implements ItemUpdater {

    private Item item;

    public GenericItemUpdater(Item item) {
        this.item = item;
    }

    @Override
    public void update() {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
        if (item.sellIn <= 0) {
            if (item.quality > 0) {
                item.quality = item.quality - 1;
            }
        }
        item.sellIn = item.sellIn - 1;
    }

}
