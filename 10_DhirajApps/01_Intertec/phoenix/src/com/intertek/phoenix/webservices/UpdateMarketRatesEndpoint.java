package com.intertek.phoenix.webservices;

import com.intertek.entity.CurrencyRate;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.externalEntity.MarketRateX;
import com.intertek.phoenix.externalEntity.response.AcknowledgementX;

/*
 * Subscriber
 * @author Eric.Nguyen
 */
public class UpdateMarketRatesEndpoint extends BaseJDomPayloadEndpoint<MarketRateX, AcknowledgementX> {
    @Override
    public AcknowledgementX process(MarketRateX obj) throws PhoenixException {
        System.out.println("Got UpdateMarketRatesEndpoint");

        CurrencyRate currencyRate = obj.convert();

        getEsbService().saveOrUpdate(CurrencyRate.class, currencyRate);

        AcknowledgementX result = new AcknowledgementX();
        result.setStatusCode("success");
        return result;
    }

    @Override
    public String getId(MarketRateX obj) {
        return "(" + obj.getRateIndex() + ", " + obj.getTerm() + ", " + obj.getFromCurrencyCode() + ", " + obj.getToCurrencyCode() + ", " + obj.getRateType()
               + ")";
    }
}
