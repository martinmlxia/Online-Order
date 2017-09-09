/**
 * FormUtils.java
 * 2009-2-26
 * Administrator
 */
package com.conant.order.web.form;

import org.springframework.util.StringUtils;

import com.conant.order.dao.OnlineOrderFacade;
import com.conant.order.util.Logger;
import com.conant.order.util.NumericUtils;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.LensChannel;
import com.conant.order.vo.LensDesign;
import com.conant.order.vo.LensMaterial;
import com.conant.order.vo.LensTint;
import com.conant.order.vo.LensType;
import com.conant.order.vo.OrderType;
import com.conant.order.vo.OrderUtils;
import com.conant.order.vo.OrsOrder;
import com.conant.order.vo.TintColor;
import com.conant.order.web.editor.LensChannelEditor;
import com.conant.order.web.editor.LensDesignEditor;
import com.conant.order.web.editor.LensMaterialEditor;
import com.conant.order.web.editor.LensTintEditor;
import com.conant.order.web.editor.LensTypeEditor;
import com.conant.order.web.editor.TintColorEditor;

/**
 * 订单form工具类。
 * @author Administrator
 *
 */
public class FormUtils
{
	private static final Logger log = Logger.getLogger("FormUtils", Logger.ALL, true);	
	
	private static String convertToString(Object value)
	{
		return (value == null) ? null : value.toString();
	}
	
	private static String convertToString(Float value, int precision)
	{
		return (value == null) ? null : String.format("%." + precision + "f", value);
	}
	
	private static String convertToString(Float value, boolean showSign, int precision)
	{
		if(value == null)
		{
			return null;
		}
		String formattedValue = convertToString(value, precision);
		if(showSign && value > 0f)
		{
			formattedValue = "+" + formattedValue;
		}
		return formattedValue;
	}
	
	private static boolean getBooleanValue(Boolean value)
	{
		return (value == null) ? false : value.booleanValue();
	}
	
	/**
	 * 从订单实体对象更新Form对象的内容。
	 * @param order 订单实体对象
	 * @param form form对象
	 * @param onlineOrder 业务对象
	 * @return 更新后的form对象
	 * @throws ProcessException
	 */
	public static FrameLensOrder synchronizeFrom(OrsOrder order, FrameLensOrder form, OnlineOrderFacade onlineOrder) throws ProcessException
	{
		//------------------------------------
		// 处理订单字段
		//------------------------------------
		form.setId(order.getId());
		form.setPatient(order.getPatient());
		form.setTray(order.getTray());
		form.setRemarks(order.getRemarks());
		form.setEmergent(order.getEmergent());
		//------------------------------------
		// 处理镜架字段
		//------------------------------------
		if(order.getFramedetail() != null)
		{
			form.setRboxasize(convertToString(order.getFramedetail().getRboxasize()));
			form.setRboxbsize(convertToString(order.getFramedetail().getRboxbsize()));
			form.setDbl(convertToString(order.getFramedetail().getDbl()));
			form.setEd(convertToString(order.getFramedetail().getEd()));
			form.setFramestyle(order.getFramedetail().getFramestyle());
			form.setFrametype(order.getFramedetail().getFrametype());
			form.setFramepattern(order.getFramedetail().getFramepattern());
		}
		//------------------------------------
		// 处理镜片字段
		//------------------------------------
		if(order.getLensdetail() != null)
		{
			form.setDiameter(convertToString(order.getLensdetail().getDiameter()));
			form.setDiameterUnit(order.getLensdetail().getDiameterUnit());
			// 球镜保留两位小数
			form.setRsphere(convertToString(order.getLensdetail().getRsphere(), true, 2));
			form.setLsphere(convertToString(order.getLensdetail().getLsphere(), true, 2));
			// 柱镜保存两位小数
			form.setRcylinder(convertToString(order.getLensdetail().getRcylinder(), true, 2));
			form.setLcylinder(convertToString(order.getLensdetail().getLcylinder(), true, 2));
			form.setRadd(convertToString(order.getLensdetail().getRadd()));
			form.setLadd(convertToString(order.getLensdetail().getLadd()));
			form.setRaxis(convertToString(order.getLensdetail().getRaxis()));
			form.setLaxis(convertToString(order.getLensdetail().getLaxis()));
			form.setRhprism(convertToString(order.getLensdetail().getRhprism()));
			form.setLhprism(convertToString(order.getLensdetail().getLhprism()));
			form.setRhpd(order.getLensdetail().getRhpd());
			form.setLhpd(order.getLensdetail().getLhpd());
			form.setVerdis(order.getLensdetail().getVerdis());
			form.setPantilt(order.getLensdetail().getPantilt());
			form.setWrapangle(order.getLensdetail().getWrapangle());
			//add 2.22
			form.setRbhprism(convertToString(order.getLensdetail().getRbhprism()));
			form.setLbhprism(convertToString(order.getLensdetail().getLbhprism()));
			form.setRbhpd(order.getLensdetail().getRbhpd());
			form.setLbhpd(order.getLensdetail().getLbhpd());
			//add end
			form.setRbasecurve(convertToString(order.getLensdetail().getRbasecurve()));
			form.setLbasecurve(convertToString(order.getLensdetail().getLbasecurve()));
			form.setRsegheight(convertToString(order.getLensdetail().getRsegheight()));
			form.setLsegheight(convertToString(order.getLensdetail().getLsegheight()));
			form.setRfarpd(convertToString(order.getLensdetail().getRfarpd()));
			form.setLfarpd(convertToString(order.getLensdetail().getLfarpd()));
			form.setRnearpd(convertToString(order.getLensdetail().getRnearpd()));
			form.setLnearpd(convertToString(order.getLensdetail().getLnearpd()));
			form.setRochgt(convertToString(order.getLensdetail().getRochgt()));
			form.setLochgt(convertToString(order.getLensdetail().getLochgt()));
			form.setPrice(order.getPrice());
			form.setTframe(order.getLensdetail().getTframe());
			form.setUserid(order.getUser().getUser_id());
			// 加工程度
			form.setUv(getBooleanValue(order.getLensdetail().getUv()) ? "uv" : null);
			form.setTreat(order.getLensdetail().getTreat());
			// 处理镜片材料、镜片类型、染色类型、染色颜色等外键字段
			// 使用编辑器处理外键
			LensMaterialEditor materialEditor = new LensMaterialEditor(onlineOrder.getLensMaterials());
			LensMaterialEditor materialEditor2 = new LensMaterialEditor(onlineOrder.getLensMaterials2());
			LensTypeEditor typeEditor = new LensTypeEditor(onlineOrder.getLensTypes());
			LensTintEditor tintEditor = new LensTintEditor(onlineOrder.getTintTypes());
			TintColorEditor colorEditor = new TintColorEditor(true, onlineOrder.getTintColors());
			LensDesignEditor designEditor = new LensDesignEditor(onlineOrder.getLensDesigns());
			LensChannelEditor channelEditor = new LensChannelEditor(onlineOrder.getLensChannels());
			// 镜片材料1
			if(order.getLensdetail().getMaterial() != null)
			{
				materialEditor.setValue(order.getLensdetail().getMaterial().getId());
				form.setMaterial(materialEditor.getAsText());
			}
			// 镜片材料2
			if(order.getLensdetail().getMaterial2() != null)
			{
				materialEditor2.setValue(order.getLensdetail().getMaterial2().getId());
				form.setMaterial2(materialEditor2.getAsText());
			}
			// 镜片类型(RX)
			if(order.getLensdetail().getLenstype() != null)
			{
				typeEditor.setValue(order.getLensdetail().getLenstype().getId());
				form.setLenstype(typeEditor.getAsText());
			}
			// 设计类型(FreeForm)
			if(order.getLensdetail().getLensdesign() != null)
			{
				designEditor.setValue(order.getLensdetail().getLensdesign().getId());
				form.setLensdesign(designEditor.getAsText());
			}
			// 通道长度(FreeForm)
			if(order.getLensdetail().getChannellength() != null)
			{
				channelEditor.setValue(order.getLensdetail().getChannellength().getId());
				form.setChannellength(channelEditor.getAsText());
			}
			// 染色类型
			if(order.getLensdetail().getTinttype() != null)
			{
				tintEditor.setValue(order.getLensdetail().getTinttype().getId());
				form.setTinttype(tintEditor.getAsText());
			}
			// 染色颜色
			if(order.getLensdetail().getTintcolor1() != null)
			{
				colorEditor.setValue(order.getLensdetail().getTintcolor1().getId());
				form.setTintcolor1(colorEditor.getAsText());
			}
		}
		
		return form;
	}
	
	/**
	 * 从Form对象更新订单实体OrsOrder对象的内容。
	 * @param form form对象
	 * @param order 订单实体对象
	 * @param onlineOrder 业务对象
	 * @return 更新后的订单实体对象
	 * @throws ProcessException
	 */
	public static OrsOrder updateFrom(FrameLensOrder form, OrsOrder order, OnlineOrderFacade onlineOrder) throws ProcessException
	{		
		//------------------------------------
		// 处理订单字段
		//------------------------------------
		order.setPatient(form.getPatient());
		order.setTray(form.getTray());
		order.setRemarks(form.getRemarks());
		order.setEmergent(form.getEmergent());
		//------------------------------------
		// 处理镜架字段
		//------------------------------------
		if(order.getFramedetail() != null)
		{
			order.getFramedetail().setRboxasize(NumericUtils.parseFloat(form.getRboxasize()));
			order.getFramedetail().setRboxbsize(NumericUtils.parseFloat(form.getRboxbsize()));
			order.getFramedetail().setDbl(NumericUtils.parseFloat(form.getDbl()));
			order.getFramedetail().setEd(NumericUtils.parseFloat(form.getEd()));
			order.getFramedetail().setFramestyle(form.getFramestyle());
			order.getFramedetail().setFrametype(form.getFrametype());
			order.getFramedetail().setFramepattern(form.getFramepattern());
		}
		//------------------------------------
		// 处理镜片字段
		//------------------------------------
		if(order.getLensdetail() != null)
		{
			order.getLensdetail().setDiameter(NumericUtils.parseInteger(form.getDiameter()));
			order.getLensdetail().setDiameterUnit(form.getDiameterUnit());
			order.getLensdetail().setRsphere(NumericUtils.parseFloat(form.getRsphere()));
			order.getLensdetail().setLsphere(NumericUtils.parseFloat(form.getLsphere()));
			order.getLensdetail().setRcylinder(NumericUtils.parseFloat(form.getRcylinder()));
			order.getLensdetail().setLcylinder(NumericUtils.parseFloat(form.getLcylinder()));
			order.getLensdetail().setRadd(NumericUtils.parseFloat(form.getRadd()));
			order.getLensdetail().setLadd(NumericUtils.parseFloat(form.getLadd()));
			order.getLensdetail().setRaxis(NumericUtils.parseInteger(form.getRaxis()));
			order.getLensdetail().setLaxis(NumericUtils.parseInteger(form.getLaxis()));
			order.getLensdetail().setRhprism(NumericUtils.parseFloat(form.getRhprism()));
			order.getLensdetail().setLhprism(NumericUtils.parseFloat(form.getLhprism()));
			order.getLensdetail().setRhpd(form.getRhpd());
			order.getLensdetail().setLhpd(form.getLhpd());
			order.getLensdetail().setVerdis(form.getVerdis());
			order.getLensdetail().setPantilt(form.getPantilt());
			order.getLensdetail().setWrapangle(form.getWrapangle());
			//add 2.22
			order.getLensdetail().setRbhprism(NumericUtils.parseFloat(form.getRbhprism()));
			order.getLensdetail().setLbhprism(NumericUtils.parseFloat(form.getLbhprism()));
			order.getLensdetail().setRbhpd(form.getRbhpd());
			order.getLensdetail().setLbhpd(form.getLbhpd());
			//add end
			order.getLensdetail().setRbasecurve(NumericUtils.parseInteger(form.getRbasecurve()));
			order.getLensdetail().setLbasecurve(NumericUtils.parseInteger(form.getLbasecurve()));
			order.getLensdetail().setRsegheight(NumericUtils.parseFloat(form.getRsegheight()));
			order.getLensdetail().setLsegheight(NumericUtils.parseFloat(form.getLsegheight()));
			order.getLensdetail().setRfarpd(NumericUtils.parseFloat(form.getRfarpd()));
			order.getLensdetail().setLfarpd(NumericUtils.parseFloat(form.getLfarpd()));
			order.getLensdetail().setRnearpd(NumericUtils.parseFloat(form.getRnearpd()));
			order.getLensdetail().setLnearpd(NumericUtils.parseFloat(form.getLnearpd()));
			order.getLensdetail().setRochgt(NumericUtils.parseFloat(form.getRochgt()));
			order.getLensdetail().setLochgt(NumericUtils.parseFloat(form.getLochgt()));
			order.setPrice(form.getPrice());
			order.getLensdetail().setTframe(form.getTframe());
			// 镜片数量，判断右球镜和左球镜，2个都有数值就是1，只有1个就是0.5
			// 两个都为空就为0
			if(order.getLensdetail().getRsphere() != null 
					&& order.getLensdetail().getLsphere() != null)
			{
				order.getLensdetail().setQuantity(1f);
			}
			else if(order.getLensdetail().getRsphere() != null 
					|| order.getLensdetail().getLsphere() != null)
			{
				order.getLensdetail().setQuantity(0.5f);
			}
			else
			{
				order.getLensdetail().setQuantity(0f);
			}
			// 加工程度
			order.getLensdetail().setUv(StringUtils.hasText(form.getUv()) ? Boolean.TRUE : Boolean.FALSE);
			order.getLensdetail().setTreat(form.getTreat());
			// 处理镜片材料、镜片类型、染色类型、染色颜色等外键字段
			// 使用编辑器处理外键
			LensMaterialEditor materialEditor = new LensMaterialEditor(onlineOrder.getLensMaterials());
			LensMaterialEditor materialEditor2 = new LensMaterialEditor(onlineOrder.getLensMaterials2());
			LensTypeEditor typeEditor = new LensTypeEditor(onlineOrder.getLensTypes());
			LensTintEditor tintEditor = new LensTintEditor(onlineOrder.getTintTypes());
			TintColorEditor colorEditor = new TintColorEditor(true, onlineOrder.getTintColors());
			LensDesignEditor designEditor = new LensDesignEditor(onlineOrder.getLensDesigns());
			LensChannelEditor channelEditor = new LensChannelEditor(onlineOrder.getLensChannels());			
			materialEditor.setAsText(form.getMaterial());
			typeEditor.setAsText(form.getLenstype());
			tintEditor.setAsText(form.getTinttype());
			colorEditor.setAsText(form.getTintcolor1());
			designEditor.setAsText(form.getLensdesign());
			channelEditor.setAsText(form.getChannellength());
			// 镜片材料1
			if(materialEditor.getValue() != null)
			{
				if(order.getLensdetail().getMaterial() != null)
				{
					order.getLensdetail().setMaterial(null);
				}
				order.getLensdetail().setMaterial(new LensMaterial());
				order.getLensdetail().getMaterial().setId(materialEditor.getValue());
			}
			else
			{
				order.getLensdetail().setMaterial(null);
			}
			// 镜片材料2
			materialEditor2.setAsText(form.getMaterial2());
			if(materialEditor2.getValue() != null)
			{
				if(order.getLensdetail().getMaterial2() != null)
				{
					order.getLensdetail().setMaterial2(null);
				}
				order.getLensdetail().setMaterial2(new LensMaterial());
				order.getLensdetail().getMaterial2().setId(materialEditor2.getValue());
			}
			else
			{
				order.getLensdetail().setMaterial2(null);
			}			
			// 镜片类型(RX)
			if(typeEditor.getValue() != null)
			{
				if(order.getLensdetail().getLenstype() != null)
				{
					order.getLensdetail().setLenstype(null);
				}
				order.getLensdetail().setLenstype(new LensType());
				order.getLensdetail().getLenstype().setId(typeEditor.getValue());
			}
			else
			{
				order.getLensdetail().setLenstype(null);
			}
			// 设计类型(FreeForm)
			if(designEditor.getValue() != null)
			{
				if(order.getLensdetail().getLensdesign() != null)
				{
					order.getLensdetail().setLensdesign(null);
				}
				order.getLensdetail().setLensdesign(new LensDesign());
				order.getLensdetail().getLensdesign().setId(designEditor.getValue());				
			}
			else
			{
				order.getLensdetail().setLensdesign(null);
			}
			// 通道长度(FreeForm)
			if(channelEditor.getValue() != null)
			{
				if(order.getLensdetail().getChannellength() != null)
				{
					order.getLensdetail().setChannellength(null);
				}
				order.getLensdetail().setChannellength(new LensChannel());
				order.getLensdetail().getChannellength().setId(channelEditor.getValue());				
			}
			else
			{
				order.getLensdetail().setChannellength(null);
			}
			// 染色类型		
			if(tintEditor.getValue() != null)
			{
				if(order.getLensdetail().getTinttype() != null)
				{
					order.getLensdetail().setTinttype(null);
				}
				order.getLensdetail().setTinttype(new LensTint());
				order.getLensdetail().getTinttype().setId(tintEditor.getValue());
			}
			else
			{
				order.getLensdetail().setTinttype(null);
			}
			// 无染色或客户色板不处理染色颜色
			if(order.getLensdetail().getTinttype() == null || order.getLensdetail().getTinttype().getId() == 3)
			{
				order.getLensdetail().setTintcolor1(null);
			}
			else
			{
				// 染色颜色
				if(colorEditor.getValue() != null)
				{
					if(order.getLensdetail().getTintcolor1() != null)
					{
						order.getLensdetail().setTintcolor1(null);
					}
					order.getLensdetail().setTintcolor1(new TintColor());
					order.getLensdetail().getTintcolor1().setId(colorEditor.getValue());
				}
				else
				{
					order.getLensdetail().setTintcolor1(null);
				}
			}
		}
		
		return order;
	}
	
	public static OrsOrder convertRXOrderFrom(FrameLensOrder form, OnlineOrderFacade onlineOrder) throws ProcessException
	{
		OrsOrder order = new OrsOrder(OrderType.TYPE_FM_LS);
		OrderUtils.completeOrder(order);
		updateFrom(form, order, onlineOrder);
		
		return order;
	}
	
	public static OrsOrder convertFreeFormOrderFrom(FrameLensOrder form, OnlineOrderFacade onlineOrder) throws ProcessException
	{
		OrsOrder order = new OrsOrder(OrderType.TYPE_FREEFORM);
		OrderUtils.completeOrder(order);
		updateFrom(form, order, onlineOrder);
		
		return order;
	}
}
