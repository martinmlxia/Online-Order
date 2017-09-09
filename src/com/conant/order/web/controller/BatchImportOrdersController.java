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
 * �������붩�������Ŀ�������
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
		// ȥ������ո�
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
				// ��ȡ������
				InputStream is = file.getInputStream();
				batchImportOrders(request, is, errors);
				// �ر���
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
			// ��ȡ�����ļ�����
			throw new ProcessException(400029);
		}
		int startIndex = 3;
		int rowCount = orderSheet.getLastRowNum() + 1;
		System.out.println("rowCount:"+rowCount);
		if((rowCount - startIndex) <= 0)
		{
			// û�ж�������
			throw new ProcessException(400030);
		}
		// ���ɶ�����¼
		int columns = 48;
		HSSFRow row = orderSheet.getRow(startIndex);
		if(row.getLastCellNum() != columns)
		{
			// �ļ���ʽ����ȷ
			throw new ProcessException(400031);
		}
		// �û�����
		String userId = (String)request.getSession().getAttribute("user_id");
		// ��ȡ�ͻ��˵�locale��Ϣ
		Locale locale = RequestContextUtils.getLocale(request);
		// ��ȡmessageSource����
		MessageSource source = (MessageSource)getApplicationContext().getBean(
				"messageSource");
		// ����༭��
		PrismDirectionEditor prismEditor = new PrismDirectionEditor();
		// ֱ����λ�༭��
		DiameterUnitEditor diameterEditor = new DiameterUnitEditor(true, locale, source);
		// boolean�༭��
		CustomBooleanEditor booleanEditor = new CustomBooleanEditor(source
				.getMessage("import.boolean.true", null, locale), source
				.getMessage("import.boolean.false", null, locale), true);
		// ���ϱ༭��
		LensMaterialEditor materialEditor = new LensMaterialEditor(onlineOrder
				.getLensMaterials(), locale, source);
		LensMaterialEditor materialEditor2 = new LensMaterialEditor(onlineOrder
				.getLensMaterials2(), locale, source);
		FrameTypeEditor frameTypeEditor = new FrameTypeEditor(locale, source);
		FramePatternEditor framePatternEditor=new FramePatternEditor(locale,source);
		LensTreatEditor lensTreatEditor=new LensTreatEditor(locale,source);
		// ���ͱ༭��
		LensTypeEditor typeEditor = new LensTypeEditor(onlineOrder
				.getLensTypes(), locale, source);
		// Ⱦɫ���ͱ༭��
		LensTintEditor tintEditor = new LensTintEditor(onlineOrder
				.getTintTypes(), locale, source);
		// ��ɫ�༭��
		TintColorEditor colorEditor = new TintColorEditor(true, onlineOrder
				.getTintColors(), locale, source);
		HSSFCell cell;
		List<OrsOrder> orders = new ArrayList<OrsOrder>();
		OrsOrder order;
		int thisColumn = 0;
		for(int ri = startIndex; ri < rowCount; ri++)
		{
			// ÿ100���ύһ��
			if(orders.size() == 100)
			{
				// �ύ������¼
				onlineOrder.batchImportOrders(orders);
				// �����¼
				orders.clear();
				// ���200ns
				try
				{
					Thread.currentThread().sleep(200);
				}
				catch(InterruptedException e)
				{
				}
			}
			// �½�����ʵ�壬ͬʱ��ʼ��������ϵ�����̶���
			try{
				order = new OrsOrder(OrderType.TYPE_FM_LS);
				OrderUtils.completeOrder(order);
				OrderUtils.initializeProcessInstance(order);
				order.getProcessInstance().setToken(OrderStatus.TYPE_AUDITING);
				row = orderSheet.getRow(ri);
				thisColumn = 0;
				// �û�����
				UserInfo user = new UserInfo();
				user.setUser_id(userId);
				order.setUser(user);
				order.setOrdereddate(new Date());
				// 00 ����������
				cell = row.getCell(thisColumn++);
				order.setPatient(getStringCellValue(cell));	
				// 01 �����߱��
				cell = row.getCell(thisColumn++);
				order.setTray(getStringCellValue(cell));
				// 02 �����ߵ�ַ
				cell = row.getCell(thisColumn++);
				order.setPatientAddress(getStringCellValue(cell));
				// 03 ���ܿ�ʽ
				cell = row.getCell(thisColumn++);
				order.getFramedetail().setFramestyle(getStringCellValue(cell));
				// 04��������
				cell = row.getCell(thisColumn++);
				frameTypeEditor.setAsText(getStringCellValue(cell));
				order.getFramedetail().setFrametype((Integer)frameTypeEditor.getValue());
				// ������ʽ
				cell = row.getCell(thisColumn++);
				framePatternEditor.setAsText(getStringCellValue(cell));
				order.getFramedetail().setFramepattern((Integer)framePatternEditor.getValue());
				// 05ˮƽ���
				cell = row.getCell(thisColumn++);
				order.getFramedetail().setRboxasize(getFloatCellValue(cell));
				// 06 ��ֱ�߶�
				cell = row.getCell(thisColumn++);
				order.getFramedetail().setRboxbsize(getFloatCellValue(cell));
				// 07 ��Чֱ��
				cell = row.getCell(thisColumn++);
				order.getFramedetail().setEd(getFloatCellValue(cell));
				// 08 ������
				cell = row.getCell(thisColumn++);
				order.getFramedetail().setDbl(getFloatCellValue(cell));
				// 09 ����
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
				// 10����2
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
				// 11 ����
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
				// 12�ӹ�����
				cell = row.getCell(thisColumn++);
				lensTreatEditor.setAsText(getStringCellValue(cell));
				order.getLensdetail().setTreat((Integer)lensTreatEditor.getValue());
				// 14 ��������
				cell = row.getCell(thisColumn++);
				booleanEditor.setAsText(getStringCellValue(cell));
				order.getLensdetail().setUv((Boolean)booleanEditor.getValue());
				// 15 Ⱦɫ����
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
				// 16 ��ɫ
				cell = row.getCell(thisColumn++);
				colorEditor.setAsText(getStringCellValue(cell));
				if(colorEditor.getValue() == null)
				{
					order.getLensdetail().setTintcolor1(null);
				}
				else
				{
					// Ⱦɫ��ɫ����Ҫ��ѡ����Solid��Gradient(!=3)����Ч
					if(order.getLensdetail().getTinttype() != null 
							&& order.getLensdetail().getTinttype().getId() != 3)
					{
						order.getLensdetail().setTintcolor1(new TintColor());
						order.getLensdetail().getTintcolor1().setId((Integer)colorEditor.getValue());
					}
				}
				// 17 ����
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setQuantity(getFloatCellValue(cell));
				// 18 ��Ƭֱ��
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setDiameter(getIntegerCellValue(cell));
				// 19 ֱ����λ
				cell = row.getCell(thisColumn++);
				diameterEditor.setAsText(getStringCellValue(cell));
				order.getLensdetail().setDiameterUnit((Integer)diameterEditor.getValue());
				// 20 ����
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRsphere(getFloatCellValue(cell));
				// 21 ����
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLsphere(getFloatCellValue(cell));
				// 22 ������
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRcylinder(getFloatCellValue(cell));
				// 23 ������
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLcylinder(getFloatCellValue(cell));
				// 24 ����λ
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRaxis(getIntegerCellValue(cell));
				// 25 ����λ
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLaxis(getIntegerCellValue(cell));
				// 26 �Ҽӹ�
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRadd(getFloatCellValue(cell));
				// 27 ��ӹ�
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLadd(getFloatCellValue(cell));
				// 28 ��Զͫ��
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRfarpd(getFloatCellValue(cell));
				// 29 ��Զͫ��
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLfarpd(getFloatCellValue(cell));
				// 30 �ҽ�ͫ��
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRnearpd(getFloatCellValue(cell));
				// 31 ���ͫ��
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLnearpd(getFloatCellValue(cell));
				// 32 �ҹ��
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRochgt(getFloatCellValue(cell));
				// 33 ����
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLochgt(getFloatCellValue(cell));
				// 34 ����Ƭ�߶�
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRsegheight(getFloatCellValue(cell));
				// 35 ����Ƭ�߶�
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLsegheight(getFloatCellValue(cell));
				// 36 ���⾵
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRhprism(getFloatCellValue(cell));
				// 37 ���⾵
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLhprism(getFloatCellValue(cell));
				// 38 �ҵ���
				cell = row.getCell(thisColumn++);
				prismEditor.setAsText(getStringCellValue(cell));
				order.getLensdetail().setRhpd((Integer)prismEditor.getValue());
				// 39 �����
				cell = row.getCell(thisColumn++);
				prismEditor.setAsText(getStringCellValue(cell));
				order.getLensdetail().setLhpd((Integer)prismEditor.getValue());
				//  ���⾵ 2
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRbhprism(getFloatCellValue(cell));
				//  ���⾵ 2
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLbhprism(getFloatCellValue(cell));
				//  �ҵ��� 2
				cell = row.getCell(thisColumn++);
				prismEditor.setAsText(getStringCellValue(cell));
				order.getLensdetail().setRbhpd((Integer)prismEditor.getValue());
				// ����� 2
				cell = row.getCell(thisColumn++);
				prismEditor.setAsText(getStringCellValue(cell));
				order.getLensdetail().setLbhpd((Integer)prismEditor.getValue());
				// 40 �һ���
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setRbasecurve(getIntegerCellValue(cell));
				// 41 �����
				cell = row.getCell(thisColumn++);
				order.getLensdetail().setLbasecurve(getIntegerCellValue(cell));
				// 42 �Ӽ�
				cell = row.getCell(thisColumn++);
				booleanEditor.setAsText(getStringCellValue(cell));
				order.setEmergent((Boolean)booleanEditor.getValue());
				// 43 ��ע
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
			// �ύ������¼
			onlineOrder.batchImportOrders(orders);
			// �����¼
			orders.clear();
		}
	}
}
