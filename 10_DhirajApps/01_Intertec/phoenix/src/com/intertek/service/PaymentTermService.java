package com.intertek.service;

import com.intertek.entity.PaymentTerm;

public interface PaymentTermService
{
  void savePaymentTerm(PaymentTerm paymentTerm);
  PaymentTerm getPaymentTermByCode(String paymentTermCode);
}

