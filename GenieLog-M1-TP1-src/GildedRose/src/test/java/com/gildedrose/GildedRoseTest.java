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
  /* 0% INCREASING JACOCO */
  @DisplayName("Test that the quality of all item except Sulfura" +
   "is between 0-50, sulfura is always at 80")
   void testValueQualityExtr() {
    Item element1 = new Item("foo", 3, 0);
    Item element2 = new Item("Aged Brie", 4, 50);
    Item element3 = new Item("Backstage passes to a TAFKAL80ETC concert", 4, 50);

    Item element4 = new Item("Sulfuras, Hand of Ragnaros", 20, 80);

    GildedRose app1 = new GildedRose(new Item[] {element1});
    GildedRose app2 = new GildedRose(new Item[] {element2});
    GildedRose app3 = new GildedRose(new Item[] {element3});
    GildedRose app4 = new GildedRose(new Item[] {element4});

    app1.updateQuality();
    app2.updateQuality();
    app3.updateQuality();
    app4.updateQuality();

    assertEquals(0, element1.quality, "The quality is always positive");
    assertEquals(50, element2.quality, "The quality can't be higher than 50");
    assertEquals(50, element3.quality, "The quality can't be higher than 50");
    assertEquals(80, element4.quality, "Sulfuras quality is always 80");
   }
   
@DisplayName("Test the decreasing quality function")

void testDecreasingFunction() {
  Item element1 = new Item("Foo", 4, 2);
  Item element2 = new Item("Aged Brie", 4, 10);
  Item element3 = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 7);
  Item element4 = new Item("Sulfuras, Hand of Ragnaros", 20, 80);

  
    GildedRose app1 = new GildedRose(new Item[] {element1});
    GildedRose app2 = new GildedRose(new Item[] {element2});
    GildedRose app3 = new GildedRose(new Item[] {element3});
    GildedRose app4 = new GildedRose(new Item[] {element4});

    app1.updateQuality();
    app2.updateQuality();
    app3.updateQuality();
    app4.updateQuality();
  
    assertEquals(0, element1.quality, "Quality decreasing ok, foo");
    assertEquals(11, element2.quality, "Quality decreasing ok, brie");
    assertEquals(8, element3.quality, "Quality decreasing ok, backstage");
    assertEquals(80, element4.quality, "Quality decreasing ok, Sulfuras");
}


 @DisplayName("Test the quality system of Backstage Passes")

 void testPassesQuality() {
    if(element.name.equals("Backstage passes to a TAFKAL80ETC concert")){
      for(int expDate = 0; expDate <= 11; expDate++){
        Item element = new Item("Backstage passes to a TAFKAL80ETC concert", expDate, 17);
    
       GildedRose app = new GildedRose(new Item[] {element});
        app.updateQuality();

        if(expDate == 0){
          assertEquals(0, element.quality, "The concert is over");
        }
        else if(expDate <= 5){
         assertEquals(20, element.quality, "The concert is very near");
        }
        else if(expDate <=10){
         assertEquals(19, element.quality, "The concert is near");
        }
        else{
         assertEquals(18, element.quality,"the concert is far ahead");
        }
      }
    }
  } 
