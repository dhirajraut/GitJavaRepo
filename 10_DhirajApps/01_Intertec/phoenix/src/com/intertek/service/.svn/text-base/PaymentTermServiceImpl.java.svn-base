package com.intertek.service;

import java.util.List;

import com.intertek.entity.PaymentTerm;

public class PaymentTermServiceImpl extends BaseService implements PaymentTermService
{
  public void savePaymentTerm(PaymentTerm paymentTerm)
  {
     getDao().save(paymentTerm);
  }

  public PaymentTerm getPaymentTermByCode(String paymentTermCode)
  {
    List pts = getDao().find(
      "from PaymentTerm pt where pt.paymentTermsCode = ?",
      new Object[] { paymentTermCode }
    );

    if(pts.size() > 0) return (PaymentTerm)pts.get(0);

    return null;
  }
}

