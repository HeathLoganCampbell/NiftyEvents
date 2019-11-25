package games.bevs.niftyevents.demo;

import games.bevs.niftyevents.EventManager;
import games.bevs.niftyevents.demo.event.PurchaseEvent;
import games.bevs.niftyevents.demo.listeners.ShopListener;

public class Main
{
    public static void main(String[] args) {
        System.out.println("Setting up...");
        EventManager eventManager = new EventManager();
        eventManager.registerEventListeners(new ShopListener());
        eventManager.triggerEvents(new PurchaseEvent("Heath's Fish and Chips", "2 Chips", 1.00)); //will make all registered events run
    }
}
