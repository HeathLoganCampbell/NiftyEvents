package games.bevs.niftyevents.demo.event;

import games.bevs.niftyevents.Event;

public class ShopEvent extends Event
{
    private String shopName;

    public ShopEvent(String shopName)
    {
        this.shopName = shopName;
    }

    public String getShopName()
    {
        return shopName;
    }

    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }
}
