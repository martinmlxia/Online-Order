/**
 * BatchImportOrdersController.java
 * 2009-4-4
 * Administrator
 */
package com.conant.order.web.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.conant.order.common.PageMsg;
import com.conant.order.dao.OnlineOrderFacade;
import com.conant.order.util.NumericUtils;
import com.conant.order.util.ProcessException;
import com.conant.order.vo.LensTint;
import com.conant.order.vo.OrderStatus;
import com.conant.order.vo.OrderType;
import com.conant.order.vo.OrderUtils;
import com.conant.order.vo.OrsOrder;
import com.conant.order.vo.TintColor;
import com.conant.order.vo.UserInfo;
import com.conant.order.web.editor.DiameterUnitEditor;
import com.conant.order.web.editor.FramePatternEditor;
import com.conant.order.web.editor.FrameTypeEditor;
import com.conant.order.web.editor.LensMaterialEditor;
import com.conant.order.web.editor.LensTintEditor;
import com.conant.order.web.editor.LensTreatEditor;
import com.conant.order.web.editor.LensTypeEditor;
import com.conant.order.web.editor.PrismDirectionEditor;
import com.conant.order.web.editor.TintColorEditor;
import com.conant.order.web.form.ImportOrdersForm;

/**
 * 批量导入订单操作的控制器。
 * 
 * @author Administrator
 * 
 */
public class BatchImportOrdersController extends SimpleFormController
{
	private OnlineOrderFacade onlineOrder;
	private String errorView;
	private String returnUrl;

	public void setReturnUrl(String returnUrl)
	{
		this.returnUrl = returnUrl;
	}

	public void setErrorView(String errorView)
	{
		this.errorView = errorView;
	}

	public void setOnlineOrder(OnlineOrderFacade onlineOrder)
	{
		this.onlineOrder = onlineOrder;
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception
	{
		ImportOrdersForm command = new ImportOrdersForm();
		return command;
	}

	private String getStringCellValue(HSSFCell cell)
	{
		String value = "";
		
		try
		{
			switch(cell.getCellType())
			{
			case HSSFCell.CELL_TYPE_BLANK:
				value = "";
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				value = String.valueOf(cell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				value = String.valueOf(cell.getErrorCellValue());
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				value = cell.getCellFormula();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				Double dv = cell.getNumericCellValue();
				value = String.valueOf(dv.longValue());
				break;
			case HSSFCell.CELL_TYPE_STRING:
				value = cell.getRichStringCellValue().getString();
				break;
			}
		}
		catch(Exception exp)
		{
		}
		// 去除多余空格
		return value.trim();
	}

	private Double getDoubleCellValue(HSSFCell cell)
	{
		Double value = null;
		try
		{
			switch(cell.getCellType())
			{
			case HSSFCell.CELL_TYPE_BLANK:
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				value = Double.valueOf(cell.getBooleanCellValue() ? 0f : 1f);
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				value = Double.valueOf(cell.getErrorCellValue());
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				value = cell.getNumericCellValue();
				break;
			case HSSFCell.CELL_TYPE_STRING:
				value = NumericUtils.parseDouble(cell.getRichStringCellValue().getString().trim());
				break;
			}
		}
		catch(Exception exp)
		{
		}
		
		return value;
	}

	private Float getFloatCellValue(HSSFCell cell)
	{
		Float value = null;
		try
		{
			switch(cell.getCellType())
			{
			case HSSFCell.CELL_TYPE_BLANK:
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				value = Float.valueOf(cell.getBooleanCellValue() ? 0f : 1f);
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				value = Float.valueOf(cell.getErrorCellValue());
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				value = (float)cell.getNumericCellValue();
				break;
			case HSSFCell.CELL_TYPE_STRING:
				value = NumericUtils.parseFloat(cell.getRichStringCellValue().getString().trim());
				break;
			}
		}
		catch(Exception exp)
		{
		}
		
		return value;
	}
	
	private Integer getIntegerCellValue(HSSFCell cell)
	{
		Integer value = null;
		try
		{
			switch(cell.getCellType())
			{
			case HSSFCell.CELL_TYPE_BLANK:
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				value = Integer.valueOf(cell.getBooleanCellValue() ? 0 : 1);
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				value = Integer.valueOf(cell.getErrorCellValue());
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				value = (int)cell.getNumericCellValue();
				break;
			case HSSFCell.CELL_TYPE_STRING:
				value = NumericUtils.parseInteger(cell.getRichStringCellValue().getString().trim());
				break;
			}
		}
		catch(Exception exp)
		{
		}
		
		return value;
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException, Exception
	{
		// cast the bean
		ImportOrdersForm form = (ImportOrdersForm)command;

		PageMsg pageMsg = new PageMsg();
		pageMsg.setUrl(returnUrl);
		pageMsg.setTarget("_self");

		// let's see if there's content there
		MultipartFile file = form.getFile();
		if(file == null)
		{
			// hmm, that's strange, the user did not upload anything
			pageMsg.setCode("400026");
			return new ModelAndView(errorView, "error", pageMsg);
		}
		else
		{
			try
			{
				// 获取输入流
				InputStream is = file.getInputStream();
				batchImportOrders(request, is, errors);
				// 关闭流
				is.close();
				is = null;
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
				pageMsg.setCode("400027");
				return new ModelAndView(errorView, "error", pageMsg);
			}
		}

		// well, let's do nothing with the bean for now and return
		pageMsg.setCode("400028");
		return new ModelAndView(this.getSuccessView(), "success", pageMsg);
	}

	private void batchImportOrders(HttpServletRequest request, InputStream is,
			BindException errors) throws ProcessException
	{
		HSSFWorkbook workBook;
		HSSFSheet orderSheet;
		try
		{
			workBook = new HSSFWorkbook(is, false);
			orderSheet = workBook.getSheetAt(0);
		}
		catch(Exception exp)
		{
			// 读取输入文件错误
			throw new ProcessException(400029);
		}
		int startIndex = 3;
		int rowCount = orderSheet.getLastRowNum() + 1;
		System.out.println("rowCount:"+rowCount);
		if((rowCount - startIndex) <= 0)
		{
			// 没有订单数据
			throw new ProcessException(400030);
		}
		// 生成订单记录
		int columns = 48;
		HSSFRow row = orderSheet.getRow(startIndex);
		if(row.getLastCellNum() != columns)
		{
			// 文件格式不正确
			throw new ProcessException(400031);
		}
		// 用户名称
		String userId = (String)request.getSession().getAttribute("user_id");
		// 获取客户端的locale信息
		Locale locale = RequestContextUtils.getLocale(request);
		// 获取messageSource对象
		MessageSource source = (MessageSource)getApplicationContext().getBean(
				"messageSource");
		// 底向编辑器
		PrismDirectionEditor prismEditor = new PrismDirectionEditor();
		// 直径单位编辑器
		DiameterUnitEditor diameterEditor = new DiameterUnitEditor(true, locale, source);
		// boolean编辑器
		CustomBooleanEditor booleanEditor = new CustomBooleanEditor(source
				.getMessage("import.boolean.true", null, locale), source
				.getMessage("import.boolean.false", null, locale), true);
		// 材料编辑器
		LensMaterialEditor materialEditor = new LensMaterialEditor(onlineOrder
				.getLensMaterials(), locale, source);
		LensMaterialEditor materialEditor2 = new LensMaterialEditor(onlineOrder
				.getLensMaterials2(), locale, source);
		FrameTypeEditor frameTypeEditor = new FrameTypeEditor(locale, source);
		FramePatternEditor framePatternEditor=new FramePatternEditor(locale,source);
		LensTreatEditor lensTreatEditor=new LensTreatEditor(locale,source);
		// 类型编辑器
		LensTypeEditor typeEditor = new LensTypeEditor(onlineOrder
				.getLensTypes(), locale, source);
		// 染色类型编辑器
		LensTintEditor tintEditor = new LensTintEditor(onlineOrder
				.getTintTypes(), locale, source);
		// 颜色编辑器
		TintColorEditor colorEditor = new TintColorEditor(true, onlineOrder
				.getTintColors(), locale, source);
		HSSFCell cell;
		List<OrsOrder> orders = new ArrayList<OrsOrder>();
		OrsOrder order;
		int thisColumn = 0;
		for(int ri = startIndex; ri < rowCount; ri++)
		{
			// 每100条提交一次
			if(orders.size() == 100)
			{
				// 提交订单记录
				onlineOrder.batchImportOrders(orders);
				// 清除记录
				orders.clear();
				// 间隔200ns
				try
				{
					Thread.currentThread().sleep(200);
				}
				catch(InterruptedException e)
				{
				}
			}
			// 新建订单实体，同时初始化关联关系和流程对象
			try{
				order = new OrsOrder(OrderType.TYPE_FM_LS);
				OrderUtils.completeOrder(order);
				OrderUtils.initializeProcessInstance(order);
				order.getProcessInstance().setToken(OrderStatus.TYPE_AUDITING);
				row = orderSheet.getRow(ri);
				thisColumn = 0;
				// 用户名称
				UserInfo user = new UserInfo();
				user.setUser_id(userId);
				order.setUser(user);
				order.setOrdereddate(new Date());
				// 00 消费者名称
				cell = row.getCell(thisColumn++);
				order.setPatient(getStringCellValue(cell));	
				// 01 消费者编号
				cell = row.getCell(thisColumn++);
				order.setTray(getStringCellValue(cell));
				// 02 消费者地址
				cell = row.getCell(thisColumn++);
				order.setPatientAddress(getStringCellValue(cell));
				// 03 镜架款式
				cell = row.getCell(thisColumn++);
				order.getFramedetail().setFramestyle(getStringCellValue(cell));
				// 04镜架类型
				cell = row.getCell(thisColumn++);
				frameTypeEditor.setAsText(getStringCellValue(cell));
				order.getFramedetail().setFrametype((Integer)frameTypeEditor.getValue());
				// 镜架样式
				cell = row.getCell(thisColumn++);
				framePatternEditor.setAsText(getStringCellValue(cell));
				order.getFramedetail().setFramepattern((Integer)framePatternEditor.getValue());
				// 05水平宽度
				cell = row.getCell(thisColumn++);
				order.getFramedetail().setRboxasize(getFloatCellValue(cell));
				// 06 垂直高度
				cell = row.getCell(thisColumn++);
				order.getFramedetail().setRboxbsize(getFloatCellValue(cell));
				// 07 有效直径
				cell = row.getCell(thisColumn++);
				order.getFramedetail().setEd(getFloatCellValue(cell));
				// 08 鼻梁距
				cell = row.getCell(thisColumn++);
				order.getFramedetail().setDbl(getFloatCellValue(cell));
				// 09 材料
				cell = row.getCell(thisColumn++);
				materialEditor.setAsText(getStringCellValue(cell));
				if(materialEditor.getValue() == null)
				{
					order.getLensdetail().setMaterial(null);
				}
				else
				{
					order.getLensdetail().getMaterial().setId((Integer)materialEditor.getValue());
				}
				// 10材料2
				cell = row.getCell(thisColumn++);
				materialEditor2.setAsText(getStringCellValue(cell));
				if(materialEditor2.getValue() == null)
				{
					order.getLensdetail().setMaterial2(null);
				}
				else
				{
					order.getLensdetail().getMaterial2().setId((Integer)materialEditor2.getValue());
				}
				// 11 类型
				cell = row.getCell(thisColumn++);
				typeEditor.setAsText(getStringCellValue(cell));
				if(typeEditor.getValue() == null)
				{
					order.getLensdetail().setLenstype(null);
				}
				else
				{
					order.getLensdetail().getLenstype().setId((Integer)typeEditor.getValue());
				}
				// 12加工类型
				cell = row.getCell(thisColumn++);
				lensTreatEditor.setAsText(getStringCellValue(cell));
				order.getLensdetail().setTreat((Integer)lensTreatEditor.getValue());
				// 14 防紫外线
				cell = row.getCell(thisColumn++);
				booleanEditor.setAsText(getStringCellValue(cell));
				order.getLensdetail().setUv((Boolean)booleanEditor.getValue());
				// 15 染色类型
				cell = row.getCell(thisColumn++);
				tintEditor.setAsText(getStringCellValue(cell));
				if(tintEditor.getValue() == null)
				{
					order.getLensdetail().setTinttype(null);
				}
				else
				{
					order.getLensdetail().setTinttype(new LensTint());
					order.getLensdetail().getTinttype().setId((Integer)tintEditor.getValue());
				}
				// 16 颜色
				cell = row.getCell(thisColumn++);
				colorEditor.setAsText(getStringCellValue(cell));
				if(colorEditor.getValue() == null)
				{
					order.getLensdetail().setTintcolor1(null);
				}
				else
				{
					// 染色颜色必须要在选择了Solid或Gradient(!=3)才有效
					if(order.getLensdetail().getTinttype() != null 
							&& order.getLensdetail().getTinttype().getId() != 3)
					{
						order.getLensdetail().setTintcolor1(new TintColor());
						order.getLensdetail().getTintcolor1().setId((Integer)colorEditor.getValue());
					}
				}
				// 17 数量
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setQuantity(getFloatCellValue(cell));
				// 18 镜片直径
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setDiameter(getIntegerCellValue(cell));
				// 19 直径单位
				cell = row.getCell(thisColumn++);
				diameterEditor.setAsText(getStringCellValue(cell));
				order.getLensdetail().setDiameterUnit((Integer)diameterEditor.getValue());
				// 20 右球镜
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRsphere(getFloatCellValue(cell));
				// 21 左球镜
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLsphere(getFloatCellValue(cell));
				// 22 右柱镜
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRcylinder(getFloatCellValue(cell));
				// 23 左柱镜
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLcylinder(getFloatCellValue(cell));
				// 24 右轴位
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRaxis(getIntegerCellValue(cell));
				// 25 左轴位
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLaxis(getIntegerCellValue(cell));
				// 26 右加光
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRadd(getFloatCellValue(cell));
				// 27 左加光
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLadd(getFloatCellValue(cell));
				// 28 右远瞳距
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRfarpd(getFloatCellValue(cell));
				// 29 左远瞳距
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLfarpd(getFloatCellValue(cell));
				// 30 右近瞳距
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRnearpd(getFloatCellValue(cell));
				// 31 左近瞳距
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLnearpd(getFloatCellValue(cell));
				// 32 右光高
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRochgt(getFloatCellValue(cell));
				// 33 左光高
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLochgt(getFloatCellValue(cell));
				// 34 右子片高度
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRsegheight(getFloatCellValue(cell));
				// 35 左子片高度
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLsegheight(getFloatCellValue(cell));
				// 36 右棱镜
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRhprism(getFloatCellValue(cell));
				// 37 左棱镜
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLhprism(getFloatCellValue(cell));
				// 38 右底向
				cell = row.getCell(thisColumn++);
				prismEditor.setAsText(getStringCellValue(cell));
				order.getLensdetail().setRhpd((Integer)prismEditor.getValue());
				// 39 左底向
				cell = row.getCell(thisColumn++);
				prismEditor.setAsText(getStringCellValue(cell));
				order.getLensdetail().setLhpd((Integer)prismEditor.getValue());
				//  右棱镜 2
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRbhprism(getFloatCellValue(cell));
				//  左棱镜 2
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLbhprism(getFloatCellValue(cell));
				//  右底向 2
				cell = row.getCell(thisColumn++);
				prismEditor.setAsText(getStringCellValue(cell));
				order.getLensdetail().setRbhpd((Integer)prismEditor.getValue());
				// 左底向 2
				cell = row.getCell(thisColumn++);
				prismEditor.setAsText(getStringCellValue(cell));
				order.getLensdetail().setLbhpd((Integer)prismEditor.getValue());
				// 40 右基弯
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRbasecurve(getIntegerCellValue(cell));
				// 41 左基弯
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLbasecurve(getIntegerCellValue(cell));
				// 42 加急
				cell = row.getCell(thisColumn++);
				booleanEditor.setAsText(getStringCellValue(cell));
				order.setEmergent((Boolean)booleanEditor.getValue());
				// 43 备注
				cell = row.getCell(thisColumn++);
				order.setRemarks(getStringCellValue(cell));
				if(order.getLensdetail().getLenstype()!=null){
				   orders.add(order);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(orders.size() > 0)
		{
			// 提交订单记录
			onlineOrder.batchImportOrders(orders);
			// 清除记录
			orders.clear();
		}
	}
}
