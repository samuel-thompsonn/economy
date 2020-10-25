package model.market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManualUser implements MarketUser {

  private Map<String,Integer> myResources;
  private int myMoney;
  private List<UserListener> myListeners;

  public ManualUser() {
    myResources = new HashMap<>();
    myMoney = 0;
    myListeners = new ArrayList<>();
  }

  @Override
  public void giveResources(String resourceType, int amount) {
    myResources.putIfAbsent(resourceType,0);
    myResources.put(resourceType,myResources.get(resourceType) + 1);
    myListeners.forEach(userListener -> userListener.reactToResourceChange(resourceType,amount));
  }

  @Override
  public void giveMoney(int amount) {
    myMoney += amount;
    myListeners.forEach(userListener -> userListener.reactToMoneyChange(amount));
  }

  @Override
  public void addListener(UserListener listener) {
    myListeners.add(listener);
  }
}
