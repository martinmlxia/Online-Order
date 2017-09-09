/**
 * OrderUtils.java
 * 2008-12-11
 * Administrator
 */
package com.conant.order.vo;

/**
 * ����ʵ�幤���ࡣ
 * @author Administrator
 *
 */
public class OrderUtils
{
	/**
	 * ��ʼ������ʵ������
	 * @param order OrsOrder����
	 * @return OrsOrder����
	 */
	public static OrsOrder completeProcessInstance(OrsOrder order)
	{
		if(order.getProcessInstance() == null)
		{
			ProcessInstance processInstance = new ProcessInstance();
			order.setProcessInstance(processInstance);
		}
		
		return order;
	}
	
	/**
	 * ��ʼ������ʵ������ͬʱ��ʼ�����̽ڵ㡣
	 * @param order OrsOrder����
	 * @return OrsOrder����
	 */
	public static OrsOrder initializeProcessInstance(OrsOrder order)
	{
		if(order.getProcessInstance() == null)
		{
			completeProcessInstance(order);
		}
		order.getProcessInstance().initializeNodeInstances();
		
		return order;
	}
	
	/**
	 * ��ʼ����Ƭ��������
	 * @param order OrsOrder����
	 * @return OrsOrder����
	 */
	public static OrsOrder completeLensdetail(OrsOrder order)
	{
		if(order.getLensdetail() == null)
		{
			LensDetail detail = new LensDetail();
			detail.setMaterial(new LensMaterial());
			detail.setMaterial2(new LensMaterial());
			detail.setLenstype(new LensType());
			detail.setOrsorder(order); 
			detail.setId(order.getId());
			order.setLensdetail(detail);
			if(order.getOrdertype() == null)
			{
				order.setOrdertype(OrderType.TYPE_LS);
			}
		}
		
		return order;
	}
	
	public static OrsOrder completeFreeFormLensdetail(OrsOrder order)
	{
		if(order.getLensdetail() == null)
		{
			LensDetail detail = new LensDetail();
			detail.setMaterial(new LensMaterial());
			detail.setMaterial2(new LensMaterial());
			// added on 2009-10-05 16:58
			detail.setLensdesign(new LensDesign());
			detail.setChannellength(new LensChannel());				
			detail.setOrsorder(order); 
			detail.setId(order.getId());
			order.setLensdetail(detail);
			if(order.getOrdertype() == null)
			{
				order.setOrdertype(OrderType.TYPE_FREEFORM);
			}
		}
		
		return order;	
	}
	
	/**
	 * ��ʼ�����ܲ�������
	 * @param order OrsOrder����
	 * @return OrsOrder����
	 */
	public static OrsOrder completeFramedetail(OrsOrder order)
	{
		if(order.getFramedetail() == null)
		{
			FrameDetail detail = new FrameDetail();
			//detail.setFramemodel(new FrameModel());
			detail.setOrsorder(order);
			detail.setId(order.getId());
			order.setFramedetail(detail);
			if(order.getOrdertype() == null)
			{
				order.setOrdertype(OrderType.TYPE_FM);
			}
		}
		
		return order;
	}
	
	/**
	 * ��ʼ���������󣬸��ݶ������ͳ�ʼ����Ƭ��������򾵼ܲ�������
	 * @param order OrsOrder����
	 * @return OrsOrder����
	 */
	public static OrsOrder completeOrder(OrsOrder order)
	{
		Integer orderType = order.getOrdertype();
		
		if(orderType == OrderType.TYPE_LS)
		{
			completeLensdetail(order);
		}
		else if(orderType == OrderType.TYPE_FM)
		{
			completeFramedetail(order);
		}
		else if(orderType == OrderType.TYPE_FM_LS)
		{
			completeLensdetail(order);
			completeFramedetail(order);
		}
		else if(orderType == OrderType.TYPE_FREEFORM)
		{
			completeFreeFormLensdetail(order);
			completeFramedetail(order);
		}
		
		return order;
	}
}
