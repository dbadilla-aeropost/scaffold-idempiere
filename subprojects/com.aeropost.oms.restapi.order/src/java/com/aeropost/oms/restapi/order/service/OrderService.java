package com.aeropost.oms.restapi.order.service;

import java.math.BigDecimal;

/*import org.compiere.order.MOrder;
import org.idempiere.common.util.Env;
/*
import org.compiere.order.MOrderLine;
import org.compiere.product.MProduct;
*/
import org.osgi.service.component.annotations.Component;

@Component
public class OrderService {
	
	/*public MOrder createOrder() {
        //zero creates a new order
        MOrder ord = new MOrder(Env.getCtx(), 0, "marketplacePOC");
        ord.saveEx("marketplacePOC");
        return ord;
    }
    /*
    public MOrder createOrderWithProduct(int prodId, int qty) {
        MOrder ord = createOrder();
        MOrderLine prodLine = new MOrderLine(ord);
        MProduct prod = MProduct.get(Env.getCtx(), prodId);
        prodLine.setProduct(prod);
        prodLine.setQty(BigDecimal.valueOf(qty));
        ord.saveEx("marketplacePOC");
        prodLine.save("marketplacePOC");
        return ord;
    
    }*/

}
