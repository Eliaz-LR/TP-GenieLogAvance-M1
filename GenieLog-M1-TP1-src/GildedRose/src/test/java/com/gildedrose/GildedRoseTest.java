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
    element.quality = 0;
    app.updateQuality();
    assertEquals(0, element.quality, "quality negativity test");
    

  }
  @Test
  @DisplayName("Test the quality system of Backstage Passes")

  void testPassesQuality() {
     Item element = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 17);
     if(element.name.equals("Backstage passes to a TAFKAL80ETC concert")){
       for(int expDate = 0; expDate <= 11; expDate++){
        element.quality = 17;
        element.sellIn = expDate;
        GildedRose app = new GildedRose(new Item[] {element});
 
         if(expDate == 0){
           app.updateQuality();
           assertEquals(0, element.quality, "The concert is over");
         }
         else if(expDate <= 5){
          app.updateQuality();
          assertEquals(20, element.quality, "The concert is very near");
         }
         else if(expDate <=10){
          app.updateQuality();
          assertEquals(19, element.quality, "The concert is near");
         }
         else{
          app.updateQuality();
          assertEquals(18, element.quality,"the concert is far ahead");
         }
       }
     }
    }
}