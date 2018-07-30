/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.idempiere.org/license.html           *
 *****************************************************************************/
package org.compiere.impl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.I_M_Movement;
import org.compiere.model.I_M_MovementLine;
import org.compiere.model.I_M_MovementLineMA;
import org.compiere.orm.Query;
import org.idempiere.common.util.CLogger;
import org.idempiere.common.util.DB;
import org.idempiere.common.util.Env;
import org.idempiere.common.util.Util;


/**
 *	Movement Material Allocation
 *	
 *  @author Jorg Janke
 *  @version $Id: MMovementLineMA.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class MMovementLineMA extends X_M_MovementLineMA
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -155379485409000271L;


	/**
	 * 	Get Material Allocations for Line
	 *	@param ctx context
	 *	@param M_MovementLine_ID line
	 *	@param trxName trx
	 *	@return allocations
	 */
	public static MMovementLineMA[] get (Properties ctx, int M_MovementLine_ID, String trxName)
	{
		ArrayList<MMovementLineMA> list = new ArrayList<MMovementLineMA>();
		String sql = "SELECT * FROM M_MovementLineMA WHERE M_MovementLine_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, M_MovementLine_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				list.add (new MMovementLineMA (ctx, rs, trxName));
			}
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		MMovementLineMA[] retValue = new MMovementLineMA[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	get
	
	/**
	 * 	Delete all Material Allocation for Movement
	 *	@param M_Movement_ID movement
	 *	@param trxName transaction
	 *	@return number of rows deleted or -1 for error
	 */
	public static int deleteMovementMA (int M_Movement_ID, String trxName)
	{
		String sql = "DELETE FROM M_MovementLineMA ma WHERE EXISTS "
			+ "(SELECT * FROM M_MovementLine l WHERE l.M_MovementLine_ID=ma.M_MovementLine_ID"
			+ " AND M_Movement_ID=" + M_Movement_ID + ")";
		return DB.executeUpdate(sql, trxName);
	}	//	deleteInOutMA
	
	/**
	 * 	Delete all Material Allocation for Movement Line
	 *	@param M_MovementLine_ID movement line
	 *	@param trxName transaction
	 *	@return number of rows deleted or -1 for error
	 */
	public static int deleteMovementLineMA (int M_MovementLine_ID, String trxName)
	{
		String sql = "DELETE FROM M_MovementLineMA WHERE M_MovementLine_ID=? AND IsAutoGenerated='Y' ";
		return DB.executeUpdate(sql,M_MovementLine_ID ,trxName);
	}	//	deleteMovementLineMA
	
	/**	Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MMovementLineMA.class);

	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_MovementLineMA_ID ignored
	 *	@param trxName trx
	 */
	public MMovementLineMA (Properties ctx, int M_MovementLineMA_ID,
		String trxName)
	{
		super (ctx, M_MovementLineMA_ID, trxName);
		if (M_MovementLineMA_ID != 0)
			throw new IllegalArgumentException("Multi-Key");
	}	//	MMovementLineMA

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result ser
	 *	@param trxName trx
	 */
	public MMovementLineMA (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MMovementLineMA
	
	/**
	 * 	Parent Constructor
	 *	@param parent parent
	 *	@param M_AttributeSetInstance_ID asi
	 *	@param MovementQty qty
	 *  @param DateMaterialPolicy
	 */
	public MMovementLineMA (MMovementLine parent, int M_AttributeSetInstance_ID, BigDecimal MovementQty,Timestamp DateMaterialPolicy)
	{
		this(parent,M_AttributeSetInstance_ID,MovementQty,DateMaterialPolicy,true);
	}
	
	/**
	 * @param parent
	 * @param M_AttributeSetInstance_ID
	 * @param MovementQty
	 * @param DateMaterialPolicy
	 * @param isAutoGenerated
	 */
	public MMovementLineMA (MMovementLine parent, int M_AttributeSetInstance_ID, BigDecimal MovementQty,Timestamp DateMaterialPolicy,boolean isAutoGenerated)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setM_MovementLine_ID(parent.getM_MovementLine_ID());
		//
		setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
		setMovementQty(MovementQty);
		if (DateMaterialPolicy == null)
		{
			if (M_AttributeSetInstance_ID > 0)
			{
				DateMaterialPolicy = MStorageOnHand.getDateMaterialPolicy(parent.getM_Product_ID(), M_AttributeSetInstance_ID, parent.get_TrxName());
			}
			if (DateMaterialPolicy == null)
			{
				DateMaterialPolicy = parent.getParent().getMovementDate();
			}
		}
		setDateMaterialPolicy(DateMaterialPolicy);
		setIsAutoGenerated(isAutoGenerated);
	}	//	MMovementLineMA

	@Override
	public void setDateMaterialPolicy(Timestamp DateMaterialPolicy) {
		if (DateMaterialPolicy != null)
			DateMaterialPolicy = Util.removeTime(DateMaterialPolicy);
		super.setDateMaterialPolicy(DateMaterialPolicy);
	}
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuilder sb = new StringBuilder ("MMovementLineMA[");
		sb.append("M_MovementLine_ID=").append(getM_MovementLine_ID())
			.append(",M_AttributeSetInstance_ID=").append(getM_AttributeSetInstance_ID())
			.append(", Qty=").append(getMovementQty())
			.append ("]");
		return sb.toString ();
	}	//	toString


	public static MMovementLineMA addOrCreate(MMovementLine line, int M_AttributeSetInstance_ID, BigDecimal MovementQty, Timestamp DateMaterialPolicy)
	{
		return addOrCreate(line,M_AttributeSetInstance_ID,MovementQty,DateMaterialPolicy,true);
	}
	
	public static MMovementLineMA addOrCreate(MMovementLine line, int M_AttributeSetInstance_ID, BigDecimal MovementQty, Timestamp DateMaterialPolicy,boolean isAutoGenerated)
	{
		Query query = new Query(Env.getCtx(), I_M_MovementLineMA.Table_Name, "M_MovementLine_ID=? AND M_AttributeSetInstance_ID=? AND DateMaterialPolicy=trunc(cast(? as date))",
					line.get_TrxName());
		MMovementLineMA po = query.setParameters(line.getM_MovementLine_ID(), M_AttributeSetInstance_ID, DateMaterialPolicy).first();
		if (po == null)
			po = new MMovementLineMA(line, M_AttributeSetInstance_ID, MovementQty, DateMaterialPolicy,isAutoGenerated);
		else
			po.setMovementQty(po.getMovementQty().add(MovementQty));
		return po;
	}
	
	/**
	 * 
	 * @param M_MovementLine_ID
	 * @param trxName
	 * @return
	 */
	public static BigDecimal getManualQty (int M_MovementLine_ID, String trxName)
	{
		String sql = "SELECT SUM(movementqty) FROM M_MovementLineMA ma WHERE ma.M_MovementLine_ID=?  AND ma.IsAutoGenerated='N'";
		BigDecimal totalQty = DB.getSQLValueBD(trxName, sql, M_MovementLine_ID);
		return totalQty==null?Env.ZERO:totalQty;
	} //totalLineQty
	
	
	
	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return save
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		//Set DateMaterialPolicy
		if(!newRecord && is_ValueChanged(COLUMNNAME_M_AttributeSetInstance_ID)){
			I_M_MovementLine line = getM_MovementLine();
			
			Timestamp dateMPolicy = null;
			if(getM_AttributeSetInstance_ID()>0)
			{
				dateMPolicy = MStorageOnHand.getDateMaterialPolicy(line.getM_Product_ID(), getM_AttributeSetInstance_ID(), get_TrxName());
			}
			
			if(dateMPolicy == null)
			{
				I_M_Movement movement = line.getM_Movement();
				dateMPolicy = movement.getMovementDate();
			}
			
			setDateMaterialPolicy(dateMPolicy);
		}
		
		return true;
	} //beforeSave
	
}	//	MMovementLineMA
