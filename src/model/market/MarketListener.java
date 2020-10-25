package model.market;

import model.market.offer.ViewableOffer;

public interface MarketListener {
  public void reactToNewOffer(ViewableOffer offer);
}
