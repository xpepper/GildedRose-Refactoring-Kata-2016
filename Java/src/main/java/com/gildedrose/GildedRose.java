package com.gildedrose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gildedrose.item.AgedBrieUpdater;
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
        HashMap<String, ItemUpdater> updaters = new HashMap<>();
        updaters.put("Sulfuras, Hand of Ragnaros", new NullUpdater(item));
        updaters.put("Backstage passes to a TAFKAL80ETC concert", new BackstagePassesUpdater(item));
        updaters.put("Aged Brie", new AgedBrieUpdater(item));
        updaters.put("Conjured", new ConjuredUpdater(item));

        ItemUpdater updater = getFrom(updaters, item.name, new GenericItemUpdater(item));
        updater.update();
    }

    private ItemUpdater getFrom(HashMap<String, ItemUpdater> itemUpdaters, String key, ItemUpdater fallbackUpdater) {
        ItemUpdater updater = itemUpdaters.get(key);
        if (updater == null) {
            updater = fallbackUpdater;
        }
        return updater;
    }

}
