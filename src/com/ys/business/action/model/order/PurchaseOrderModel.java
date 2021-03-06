package com.ys.business.action.model.order;

import java.util.ArrayList;
import java.util.List;

import com.ys.business.action.model.common.ListOption;
import com.ys.business.db.data.B_PurchaseOrderData;
import com.ys.business.db.data.B_PurchaseOrderDetailData;
import com.ys.util.basequery.common.BaseModel;

public class PurchaseOrderModel extends BaseModel {
	
	/**
	 * author:renang
	 * 采购方案
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ListOption> supplierList = new ArrayList<ListOption>();
	private List<B_PurchaseOrderDetailData> detailList;
	private B_PurchaseOrderData contract;
	
	public ArrayList<ListOption> getSupplierList() {
		return this.supplierList;
	}
	public void setSupplierList(ArrayList<ListOption> supplierList) {
		this.supplierList = supplierList;
	}
	
	private String YSId;
	private String shortName;

	public String getShortName() {
		return this.shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getYSId() {
		return this.YSId;
	}
	public void setYSId(String YSId) {
		this.YSId = YSId;
	}
	
	public List<B_PurchaseOrderDetailData> getDetailList() {
		return this.detailList;
	}
	public void setDetailList(List<B_PurchaseOrderDetailData> detailList) {
		this.detailList = detailList;
	}
	
	public B_PurchaseOrderData getContract() {
		return this.contract;
	}
	public void setContract(B_PurchaseOrderData contract) {
		this.contract = contract;
	}

	
	
}
