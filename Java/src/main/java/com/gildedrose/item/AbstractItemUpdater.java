package com.gildedrose.item;

import com.gildedrose.Item;

public abstract class AbstractItemUpdater implements ItemUpdater {

    protected Item item;

    public AbstractItemUpdater(Item item) {
        this.item = item;
    }

    protected int decrementSellIn() {
        return item.sellIn = item.sellIn - 1;
    }

    protected void changeQualityBy(int increment) {
        item.quality = item.quality + increment;
    }

}