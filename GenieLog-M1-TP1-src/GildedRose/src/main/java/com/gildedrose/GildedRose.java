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
            
            beforeSellInUpdate(i);
            
            if (!items[i].name.equals(sulfuras)) {
                items[i].sellIn -= 1;
            }
            
            afterSellInUpdate(i);

        }
    }

    //Traitement avant mis-à-jour de la date d'expiration
    private void beforeSellInUpdate(int i){
        

        if (items[i].name.equals(brie) || items[i].name.equals(backstage)) {

            if (items[i].quality < 50) {
                
                items[i].quality += 1;

            } if (items[i].name.equals(backstage)) {
                
                if (items[i].sellIn < 11 && items[i].quality < 50) {
                            
                    items[i].quality += 1;
                }

                if (items[i].sellIn < 6 && items[i].quality < 50) {
                            
                    items[i].quality += 1;
                }
            }

        } else if (!items[i].name.equals(sulfuras) && items[i].quality > 0) {
                
            items[i].quality -= 1;

        } 
    } 

    //Traitement après mis-à-jour de la date d'expiration
    private void afterSellInUpdate(int i){

        if (items[i].sellIn < 0) {
         
            if (items[i].name.equals(brie)) {
                if(items[i].quality < 50){
                    
                    items[i].quality += 1;
                
                }
            } else if (items[i].name.equals(backstage)) {
                  
                items[i].quality = 0;

            } else if (items[i].quality > 0 && !items[i].name.equals(sulfuras)) {
                            
                items[i].quality -= 1;
                
            } 
        }
    }
}
