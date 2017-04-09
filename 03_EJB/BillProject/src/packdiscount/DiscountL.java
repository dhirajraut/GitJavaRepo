package packdiscount;

import javax.ejb.EJBLocalObject;

public interface DiscountL extends EJBLocalObject, DiscountInterface {
	float getDiscount(float amt, float percent);
}
