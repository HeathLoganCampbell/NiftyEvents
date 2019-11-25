package games.bevs.niftyevents.demo.event;

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
