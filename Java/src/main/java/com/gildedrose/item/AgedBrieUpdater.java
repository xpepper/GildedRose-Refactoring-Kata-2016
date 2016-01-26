package com.gildedrose.item;

import com.gildedrose.Item;

public class AgedBrieUpdater extends AbstractItemUpdater  {

    public AgedBrieUpdater(Item item) {
        super(item);
    }

    @Override
    public void update() {
        if (item.quality < 50) {
            changeQualityBy(+1);
        }

        if (item.sellIn <= 0) {
            if (item.quality < 50) {
                changeQualityBy(+1);
            }
        }

        decrementSellIn();
    }

}
