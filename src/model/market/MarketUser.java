package model.market;

public interface MarketUser {
  public void giveResources(String resourceType, int amount);

  public void giveMoney(int amount);

  public void addListener(UserListener listener);
}
