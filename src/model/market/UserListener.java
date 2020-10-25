package model.market;

public interface UserListener {

  public void reactToMoneyChange(int amount);

  public void reactToResourceChange(String resourceType, int amount);
}
