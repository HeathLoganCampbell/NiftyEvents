# NiftyEvents
A small event management api in java, this supports event driven code :)


## Example
Lets say we are a large food chain that offers fish and chips,
We have multiple stores all over the country. So all the stores want easy
access to their stores infomation and others, so they can tell what is selling well
but some stores only want to track some things.

if we didn't use event driven code in this case, everything can quickly
get out of hand as everyone adds in there line of code

But now, any store can just add a function with ```@EventListener``` on it and their code will be 
triggered
```java
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

```

```Java
public class Main
{
    public static void main(String[] args) {
        System.out.println("Setting up...");
        EventManager eventManager = new EventManager(); //this is the hub for our events, everything passes through this
        eventManager.registerEventListeners(new ShopListener());//register a lister (only required once)
        eventManager.triggerEvents(new PurchaseEvent("Heath's Fish and Chips", "2 Chips", 1.00));
    }
}
```

```Java
//Create your own event
//Just extend Event, and boom you're done
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
```

```Java
//you can even extend your own events to build ontop of them
public class PurchaseEvent extends ShopEvent
{
    private String itemName;
    private double amount;

    public PurchaseEvent(String shopName, String itemName, double amount)
    {
        super(shopName);
        this.itemName = itemName;
        this.amount = amount;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }
}
```

### Output
```
Setting up...
PurchaseEvent] Shop Name: Heath's Fish and Chips
ShopEvent] Shop Name: Heath's Fish and Chips
```