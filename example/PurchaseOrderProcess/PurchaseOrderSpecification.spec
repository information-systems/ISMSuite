

transaction Offer.products( product: universe ) {
  register product;
  insert (product) into product;
}

transaction Offer.suppliers( supplier: universe) {
	register supplier;
	insert (supplier) into supplier;
}

transaction Offer.provides( supplier: universe, product: universe ) {
  register product;
  register supplier;
  insert (product) into product;
  insert (supplier) into supplier;
  insert (supplier, product) into supplies;
}

transaction Offer.CreatePO( order: universe, product: universe) {
  register order;
  insert (order) into order;
  insert (order, product) into contains;
}

transaction Offer.SendBid( supplier: universe, order: universe ) {
  insert (supplier, order) into bids;
}

transaction Offer.TimeOut( supplier: universe, order: universe ) {
}

transaction Offer.SelectOffer( supplier: universe, order: universe ) {
  insert (supplier, order) into receives;
}

transaction Offer.RequestBid( supplier: universe, order: universe ) {
}

transaction Offer.Deliver( supplier: universe, order: universe ) {
  insert (supplier, order) into delivers;
}


