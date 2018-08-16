/**
 * 
 */
package com.aeropost.oms.restapi.order.impl;

import java.math.BigDecimal;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.compiere.order.MOrder;
import org.idempiere.common.util.Env;
import org.compiere.order.MOrderLine;
import org.compiere.product.MProduct;

//import com.aeropost.oms.restapi.order.service.OrderService;
//import com.aeropost.oms.restapi.order.service.OrderServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.idempiere.common.db.Database;
import pg.org.compiere.db.DB_PostgreSQL;
import org.idempiere.common.util.DB;
import org.idempiere.common.db.CConnection;

/**
 * @author dbadilla
 *
 */

@Path("/order")
public class OrderController {

	public class OrderDTO {
		private int id;
		public int getId() { return id; }

		private OrderDTO(MOrder ord) {
			id = ord.get_ID();
		}
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public String getOrder() {
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		try {
			new Database().setDatabase(new DB_PostgreSQL());
			DB.setDBTarget(CConnection.get(null));
			MOrder ord = new MOrder(Env.getCtx(), 100, "marketplacePOC");			
			OrderDTO dto = new OrderDTO(ord);
			result = mapper.writeValueAsString(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.toString();
		}
		return result;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createOrder(
			@FormParam("productId") int prodId,
			@FormParam("qty") int qty) {
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		try {
			//MOrder ord = orderService.createOrderWithProduct(prodId, qty);
			MOrder ord = new MOrder(Env.getCtx(), 0, "marketplacePOC");
			MOrderLine prodLine = new MOrderLine(ord);
	        MProduct prod = MProduct.get(Env.getCtx(), prodId);
	        prodLine.setProduct(prod);
	        prodLine.setQty(BigDecimal.valueOf(qty));
	        ord.saveEx("marketplacePOC");
	        prodLine.save("marketplacePOC");
			result = mapper.writeValueAsString(ord);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
