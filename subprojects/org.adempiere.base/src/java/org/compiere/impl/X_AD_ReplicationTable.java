/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.idempiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.impl;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.I_AD_ReplicationTable;
import org.compiere.orm.MTable;
import org.idempiere.orm.I_Persistent;
import org.idempiere.common.util.KeyNamePair;
 
 

/** Generated Model for AD_ReplicationTable
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_AD_ReplicationTable extends PO implements I_AD_ReplicationTable, I_Persistent
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171031L;

    /** Standard Constructor */
    public X_AD_ReplicationTable (Properties ctx, int AD_ReplicationTable_ID, String trxName)
    {
      super (ctx, AD_ReplicationTable_ID, trxName);
      /** if (AD_ReplicationTable_ID == 0)
        {
			setAD_ReplicationStrategy_ID (0);
			setAD_ReplicationTable_ID (0);
			setAD_Table_ID (0);
			setEntityType (null);
// @SQL=select get_sysconfig('DEFAULT_ENTITYTYPE','U',0,0) from dual
			setReplicationType (null);
        } */
    }

    /** Load Constructor */
    public X_AD_ReplicationTable (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_AD_ReplicationTable[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_ReplicationStrategy getAD_ReplicationStrategy() throws RuntimeException
    {
		return (org.compiere.model.I_AD_ReplicationStrategy)MTable.get(getCtx(), org.compiere.model.I_AD_ReplicationStrategy.Table_Name)
			.getPO(getAD_ReplicationStrategy_ID(), get_TrxName());	}

	/** Set Replication Strategy.
		@param AD_ReplicationStrategy_ID 
		Data Replication Strategy
	  */
	public void setAD_ReplicationStrategy_ID (int AD_ReplicationStrategy_ID)
	{
		if (AD_ReplicationStrategy_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ReplicationStrategy_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ReplicationStrategy_ID, Integer.valueOf(AD_ReplicationStrategy_ID));
	}

	/** Get Replication Strategy.
		@return Data Replication Strategy
	  */
	public int getAD_ReplicationStrategy_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ReplicationStrategy_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getAD_ReplicationStrategy_ID()));
    }

	/** Set Replication Table.
		@param AD_ReplicationTable_ID 
		Data Replication Strategy Table Info
	  */
	public void setAD_ReplicationTable_ID (int AD_ReplicationTable_ID)
	{
		if (AD_ReplicationTable_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_ReplicationTable_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_ReplicationTable_ID, Integer.valueOf(AD_ReplicationTable_ID));
	}

	/** Get Replication Table.
		@return Data Replication Strategy Table Info
	  */
	public int getAD_ReplicationTable_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_ReplicationTable_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set AD_ReplicationTable_UU.
		@param AD_ReplicationTable_UU AD_ReplicationTable_UU	  */
	public void setAD_ReplicationTable_UU (String AD_ReplicationTable_UU)
	{
		set_Value (COLUMNNAME_AD_ReplicationTable_UU, AD_ReplicationTable_UU);
	}

	/** Get AD_ReplicationTable_UU.
		@return AD_ReplicationTable_UU	  */
	public String getAD_ReplicationTable_UU () 
	{
		return (String)get_Value(COLUMNNAME_AD_ReplicationTable_UU);
	}

	public org.compiere.model.I_AD_Table getAD_Table() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Table)MTable.get(getCtx(), org.compiere.model.I_AD_Table.Table_Name)
			.getPO(getAD_Table_ID(), get_TrxName());	}

	/** Set Table.
		@param AD_Table_ID 
		Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID)
	{
		if (AD_Table_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Table_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Table_ID, Integer.valueOf(AD_Table_ID));
	}

	/** Get Table.
		@return Database Table information
	  */
	public int getAD_Table_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Table_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** EntityType AD_Reference_ID=389 */
	public static final int ENTITYTYPE_AD_Reference_ID=389;
	/** Set Entity Type.
		@param EntityType 
		Dictionary Entity Type; Determines ownership and synchronization
	  */
	public void setEntityType (String EntityType)
	{

		set_Value (COLUMNNAME_EntityType, EntityType);
	}

	/** Get Entity Type.
		@return Dictionary Entity Type; Determines ownership and synchronization
	  */
	public String getEntityType () 
	{
		return (String)get_Value(COLUMNNAME_EntityType);
	}

	/** ReplicationType AD_Reference_ID=126 */
	public static final int REPLICATIONTYPE_AD_Reference_ID=126;
	/** Local = L */
	public static final String REPLICATIONTYPE_Local = "L";
	/** Merge = M */
	public static final String REPLICATIONTYPE_Merge = "M";
	/** Reference = R */
	public static final String REPLICATIONTYPE_Reference = "R";
	/** Broadcast = B */
	public static final String REPLICATIONTYPE_Broadcast = "B";
	/** Set Replication Type.
		@param ReplicationType 
		Type of Data Replication
	  */
	public void setReplicationType (String ReplicationType)
	{

		set_Value (COLUMNNAME_ReplicationType, ReplicationType);
	}

	/** Get Replication Type.
		@return Type of Data Replication
	  */
	public String getReplicationType () 
	{
		return (String)get_Value(COLUMNNAME_ReplicationType);
	}
}