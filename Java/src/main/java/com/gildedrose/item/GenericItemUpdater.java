package com.gildedrose.item;

import com.gildedrose.Item;

public class GenericItemUpdater extends AbstractItemUpdater {

    public GenericItemUpdater(Item item) {
        super(item);
    }

    @Override
    public void update() {
        if (item.quality > 0) {
            changeQualityBy(-1);
        }

        if (item.sellIn <= 0) {
            if (item.quality > 0) {
                changeQualityBy(-1);
            }
        }

        decrementSellIn();
    }

}
