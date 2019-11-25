package games.bevs.niftyevents.demo.listeners;

import games.bevs.niftyevents.EventListener;
import games.bevs.niftyevents.demo.event.PurchaseEvent;
import games.bevs.niftyevents.demo.event.ShopEvent;

public class ShopListener
{
    @EventListener
    public void onPurchase(PurchaseEvent event)
    {
        System.out.println("PurchaseEvent] Shop Name: " + event.getShopName());
    }

    @EventListener
    public void onShop(ShopEvent event)
    {
        System.out.println("ShopEvent] Shop Name: " + event.getShopName());
    }
}
