package com.gildedrose;

class GildedRose {
    Item[] items;

    static public String sulfuras = "Sulfuras, Hand of Ragnaros";
    static public String backstage = "Backstage passes to a TAFKAL80ETC concert";
    static public String brie = "Aged Brie";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {

            //Traitement avant mis-à-jour du sellIn
            beforeSellInUpdate(i);

            //Diminue la date d'expiration si l'item n'est pas Sulfuras
            if (!items[i].name.equals(sulfuras)) {
                items[i].sellIn -= 1;
            }

            //Traitement après mis-à-jour du sellIn
            afterSellInUpdate(i);

        }
    }

    private void beforeSellInUpdate(int i){
        //Traitement avant mis-à-jour du sellIn
        if (!items[i].name.equals(brie) && !items[i].name.equals(backstage)) {
            if (items[i].quality > 0) {
                if (!items[i].name.equals(sulfuras)) {
                    items[i].quality -= 1;
                        
                }
            }
        } else {
            if (items[i].quality < 50) {
                
                items[i].quality += 1;

                if (items[i].name.equals(backstage)) {
                    if (items[i].sellIn < 11 && items[i].quality < 50) {
                            
                        items[i].quality += 1;
                    }

                    if (items[i].sellIn < 6 && items[i].quality < 50) {
                            
                        items[i].quality += 1;
                    }
                }
            }
        }
    } 

    private void afterSellInUpdate(int i){

        //Traitement après mis-à-jour du sellIn
        if (items[i].sellIn < 0) {
            if (!items[i].name.equals(brie)) {
                if (!items[i].name.equals(backstage)) {
                    if (items[i].quality > 0) {
                        if (!items[i].name.equals(sulfuras)) {
                            items[i].quality -= 1;
                        }
                    }
                } else {
                    items[i].quality = 0;
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality += 1;
                }
            }
        }

    }

}