package com.gildedrose.item;

import com.gildedrose.Item;

public class ConjuredUpdater extends AbstractItemUpdater {

    public ConjuredUpdater(Item item) {
        super(item);
    }

    @Override
    public void update() {
        if (item.quality > 0) {
            changeQualityBy(-2);
        }

        if (item.sellIn <= 0) {
            if (item.quality > 0) {
                changeQualityBy(-2);
            }
        }

        decrementSellIn();
    }

}
