package com.mediaocean.retailstore.services;

import com.mediaocean.retailstore.vo.CartVo;
import com.mediaocean.retailstore.vo.ItemizedBillVo;

public interface BillingService {

    ItemizedBillVo generateItemizedBill(CartVo cartVo);
}
