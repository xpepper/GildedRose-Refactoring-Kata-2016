package com.gildedrose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gildedrose.item.AgeBrieUpdater;
import com.gildedrose.item.BackstagePassesUpdater;
import com.gildedrose.item.ConjuredUpdater;
import com.gildedrose.item.GenericItemUpdater;
import com.gildedrose.item.ItemUpdater;
import com.gildedrose.item.NullUpdater;

class GildedRose {
    private List<Item> items;

    public GildedRose() {
        this(new ArrayList<Item>());
    }

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateInventory() {
        for (Item eachItem : items) {
            update(eachItem);
        }
    }

    public Item item(int index) {
        return items.get(index);
    }

    public void add(Item item) {
        items.add(item);
    }

    private void update(Item item) {
        HashMap<String, ItemUpdater> itemUpdaters = new HashMap<>();
        itemUpdaters.put("Sulfuras, Hand of Ragnaros", new NullUpdater(item));
        itemUpdaters.put("Backstage passes to a TAFKAL80ETC concert", new BackstagePassesUpdater(item));
        itemUpdaters.put("Aged Brie", new AgeBrieUpdater(item));
        itemUpdaters.put("Conjured", new ConjuredUpdater(item));

        ItemUpdater updater = itemUpdaters.get(item.name);
        if (updater != null) {
            updater.update();
        } else {
            new GenericItemUpdater(item).update();
        }

    }

}
