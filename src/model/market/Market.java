package model.market;

import model.market.offer.ViewableOffer;

import java.util.List;

public interface Market {
  public void makeOffer(String type, String resourceType, int price, MarketUser owner);

  public List<ViewableOffer> fetchOffers(MarketUser owner);

  public void subscribe(MarketListener listener);

}
