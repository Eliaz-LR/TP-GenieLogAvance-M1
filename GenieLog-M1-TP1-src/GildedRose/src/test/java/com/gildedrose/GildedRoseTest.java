package com.gildedrose;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

  @Test
  @DisplayName("Test that the name is unchanged")
  void testName() {
    Item element = new Item("foo", 0, 0);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertEquals("foo", element.name, "the name changed");
  }
  @Test
  @DisplayName("On gère d'abord les comportements des items normaux")
  /*
   * Propriétés à vérifier pour les cartes normales:
   * Si SellIn < 0 alors un objet se dégrade 2x plus vite.
   * La qualité est comprise entre 0 et 50 
   */
  void testBasicItem(){
    Item element = new Item("foo", 1, 8);
    GildedRose app = new GildedRose(new Item[] {element});

    app.updateQuality();
    assertEquals(0, element.sellIn, "sellIn decreasing test");
    assertEquals(7, element.quality, "quality decreasing test");

    app.updateQuality();
    assertEquals(-1, element.sellIn, "negative sellIn normal test");
    assertEquals(5, element.quality, "negative sellIn / decreasing quality double");
  
    element.quality = 1;
    app.updateQuality();
    assertEquals(0, element.quality, "quality negativity test");

    element.quality = 0;
    app.updateQuality();
    assertEquals(0, element.quality, "quality negativity test");
    

  }
  @Test
  @DisplayName("Test the quality system of Backstage Passes")

  void testPassesQuality() {
     Item element = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 17);
     Item element48 = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 48);
     Item element49 = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 49);
     Item elementMax = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 50);

     for(int expDate = 0; expDate <= 11; expDate++){
      element.quality = 17;
      element.sellIn = expDate;
      element48.quality = 48;
      element48.sellIn = expDate;
      element49.quality = 49;
      element49.sellIn = expDate;
 
      elementMax.quality = 50;
      elementMax.sellIn = expDate;

      GildedRose app = new GildedRose(new Item[] {element});
      GildedRose app48 = new GildedRose(new Item[] {element48});
      GildedRose app49 = new GildedRose(new Item[] {element49});
      GildedRose appMax = new GildedRose(new Item[] {elementMax});

        if(expDate == 0){
          app.updateQuality();
          assertEquals(0, element.quality, "The concert is over");

          app48.updateQuality();
          assertEquals(0, element48.quality, "The concert is over");

          app49.updateQuality();
          assertEquals(0, element49.quality, "The concert is over");

          appMax.updateQuality();
          assertEquals(0, elementMax.quality, "the concert is over max qual");


        }
        else if(expDate <= 5){
        app.updateQuality();
        assertEquals(20, element.quality, "The concert is very near"); 

        app48.updateQuality();
        assertEquals(50, element48.quality, "The concert is over");
          
        app49.updateQuality();
        assertEquals(50, element49.quality, "The concert is over");

        appMax.updateQuality();
        assertEquals(50, elementMax.quality, "the concert is over max qual");

        }
        else if(expDate <=10){
        app.updateQuality();
        assertEquals(19, element.quality, "The concert is near");

        app48.updateQuality();
        assertEquals(50, element48.quality, "The concert is over");

        app49.updateQuality();
          assertEquals(50, element49.quality, "The concert is over");

        appMax.updateQuality();
        assertEquals(50, elementMax.quality, "the concert is over max qual");
        }
        else{
        app.updateQuality();
        assertEquals(18, element.quality,"the concert is far ahead");

        app48.updateQuality();
        assertEquals(49, element48.quality, "The concert is over");

        app49.updateQuality();
          assertEquals(50, element49.quality, "The concert is over");

        appMax.updateQuality();
        assertEquals(50, elementMax.quality, "the concert is over max qual");

        }
      }

    }

  @Test
  @DisplayName("Test the property of Sulfuras")
  void testSulfurasProperty() {
    
    Item element = new Item("Sulfuras, Hand of Ragnaros", 10, 80);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertEquals(80, element.quality, "constant value sulfuras test");
    assertEquals(10, element.sellIn, "fixed sellIn test");

    element.sellIn = -10;
    app.updateQuality();
    assertEquals(80, element.quality, "contraint value sulfuras (neg) test");
    assertEquals(-10, element.sellIn, "fixed sellIn test");

  }

  @Test
  @DisplayName("Test the property of Aged Brie")
  void testAgedBrie(){
    Item element = new Item("Aged Brie", 10, 40);
    GildedRose app = new GildedRose(new Item[] {element});
    app.updateQuality();
    assertEquals(41, element.quality, "quality increasing brie test");

    element.quality = 49;
    app.updateQuality();
    assertEquals(50, element.quality, "max quality brie test");

    element.quality = 50;
    app.updateQuality();
    assertEquals(50, element.quality, "max quality brie test");

    element.sellIn = -5;
    element.quality = 8;
    app.updateQuality();
    assertEquals(10, element.quality, "negative increasing brie Test");

    element.quality = 50;
    app.updateQuality();
    assertEquals(50, element.quality, "negative increasing brie Test");

  }

}
