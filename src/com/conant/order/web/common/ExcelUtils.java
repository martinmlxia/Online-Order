/**
 * ExcelUtils.java
 * 2009-2-13
 * Administrator
 */
package com.conant.order.web.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.conant.order.dao.OnlineOrderFacade;
import com.conant.order.vo.FrameDetail;
import com.conant.order.vo.LensDetail;
import com.conant.order.vo.NodeInstance;
import com.conant.order.vo.OrderStatus;
import com.conant.order.vo.OrsOrder;
import com.conant.order.vo.ProcessInstance;
import com.conant.order.web.editor.DiameterUnitEditor;
import com.conant.order.web.editor.FramePatternEditor;
import com.conant.order.web.editor.FrameTypeEditor;
import com.conant.order.web.editor.LensMaterialEditor;
import com.conant.order.web.editor.LensTintEditor;
import com.conant.order.web.editor.LensTreatEditor;
import com.conant.order.web.editor.LensTypeEditor;
import com.conant.order.web.editor.PrismDirectionEditor;
import com.conant.order.web.editor.TintColorEditor;

/**
 * ��������Excel�ļ������ࡣ
 * @author Administrator
 * 
 */
public class ExcelUtils
{
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	private static void setCellValue(HSSFCell cell, Float value)
	{
		String targetValue = (value == null) ? "" : String.valueOf(value);
		cell.setCellValue(new HSSFRichTextString(targetValue));
	}

	private static void setCellValue(HSSFCell cell, Integer value)
	{
		String targetValue = (value == null) ? "" : String.valueOf(value);
		cell.setCellValue(new HSSFRichTextString(targetValue));
	}

	private static void setCellValue(HSSFCell cell, String value)
	{
		String targetValue = (value == null) ? "" : value;
		cell.setCellValue(new HSSFRichTextString(targetValue));
	}

	private static void setCellValue(HSSFCell cell, Date value)
	{
		String targetValue = (value == null) ? "" : dateFormat.format(value);
		cell.setCellValue(new HSSFRichTextString(targetValue));
	}

	private static void setCellValue(HSSFCell cell, Boolean value)
	{
		String targetValue = (value == null) ? "" : (value ? "��" : "");
		cell.setCellValue(new HSSFRichTextString(targetValue));
	}

	private static String convertToString(Object value)
	{
		return (value == null) ? "" : value.toString();
	}

	/**
	 * ��������������Ҫ�Ķ���excel����
	 * 
	 * @param orders
	 *            ��Ҫ�����Ķ����б�
	 * @param response
	 *            ��������excel�ļ���HttpServletResponse
	 * @return �������
	 * @throws Exception
	 */
	public static boolean excelExportProduceReport(
			OnlineOrderFacade onlineOrder, List orders,
			HttpServletRequest request, HttpServletResponse response,
			ApplicationContext context) throws Exception
	{
		// ��ȡ�ͻ��˵�locale��Ϣ
		Locale locale = RequestContextUtils.getLocale(request);
		// ��ȡmessageSource����
		MessageSource source = (MessageSource)context.getBean("messageSource");

		// ��ͷ
		// ��� ������� �������� �Ӽ� �����ͺ� ���� ��Ƭ���� ֱ�� �ӹ��̶� ���� �� ���� ��λ ͫ�� ͫ�� �¼ӹ� �⾵ ����
		String[] header = new String[]
		{ "sno", "order.orderNo", "order.ordereddate", "order.patient",
				"order.tray", "order.emergent.short", "frame.style", "frame.a",
				"frame.b", "frame.ed", "frame.dbl", "lens.quantity",
				"lens.material.full", "lens.type.full", "lens.diameter.full",
				"lens.tint", "lens.color", "lens.treatment", "leftright", "lens.sphere", "lens.cylinder",
				"lens.axis", "lens.farpd", "lens.nearpd", "lens.add",
				"lens.prism", "lens.prism.direction","lens.prism.bshort","lens.prism.bdirection",
				"lens.basecurve","lens.seght", "lens.ochgt", "order.remarks" };

		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet orderSheet = workBook.createSheet("orders");

		HSSFRow row;
		HSSFCell cell;
		HSSFRichTextString textValue;

		// ��ͷ���壺12 ����
		HSSFFont headerFont = workBook.createFont();
		headerFont.setFontHeightInPoints((short)12);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setColor(HSSFColor.RED.index);
		// �������壺12 ����
		HSSFFont cellFont = workBook.createFont();
		cellFont.setFontHeightInPoints((short)12);
		cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		cellFont.setColor(HSSFColor.BLACK.index);
		// ��ͷ��Ԫ����ʽ
		HSSFCellStyle headerStyle = workBook.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		headerStyle.setFont(headerFont);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle
				.setFillForegroundColor(new HSSFColor.TURQUOISE().getIndex());
		// ���嵥Ԫ����ʽ
		HSSFCellStyle cellStyle = workBook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setFont(cellFont);

		row = orderSheet.createRow(0);
		String headerText;
		for(int i = 0; i < header.length; i++)
		{
			headerText = source.getMessage(header[i], null, locale);
			orderSheet.setColumnWidth(i,
					(headerText.getBytes().length + 4) * 256);
			cell = row.createCell(i);
			textValue = new HSSFRichTextString(headerText);
			cell.setCellValue(textValue);
			cell.setCellStyle(headerStyle);
		}

		HSSFRow row2;
		OrsOrder order;
		LensDetail lensDetail;
		FrameDetail frameDetail;
		int startRow = 1;
		int thisRow, thisCol;
		String value = null;

		// ����༭��
		PrismDirectionEditor prismEditor = new PrismDirectionEditor();
		// ֱ����λ�༭��
		DiameterUnitEditor diameterUnitEditor = new DiameterUnitEditor(true,
				locale, source);
		// ���ϱ༭��
		LensMaterialEditor materialEditor = new LensMaterialEditor(onlineOrder
				.getLensMaterials(), locale, source);
		LensMaterialEditor materialEditor2 = new LensMaterialEditor(onlineOrder
				.getLensMaterials2(), locale, source);
		FrameTypeEditor frameTypeEditor = new FrameTypeEditor(locale, source);
		FramePatternEditor framePatternEditor=new FramePatternEditor(locale,source);
		LensTreatEditor lensTreatEditor=new LensTreatEditor(locale,source);
		// ���ͱ༭��
		LensTypeEditor lensTypeEditor = new LensTypeEditor(onlineOrder
				.getLensTypes(), locale, source);
		// Ⱦɫ���ͱ༭��
		LensTintEditor tintEditor = new LensTintEditor(onlineOrder
				.getTintTypes(), locale, source);
		// ��ɫ�༭��
		TintColorEditor colorEditor = new TintColorEditor(true, onlineOrder
				.getTintColors(), locale, source);

		for(int i = 0; i < orders.size(); i++)
		{
			order = (OrsOrder)orders.get(i);
			lensDetail = order.getLensdetail();
			frameDetail = order.getFramedetail();
			// ÿһ������ռ����
			thisRow = startRow + i * 2;
			thisCol = 0;
			row = orderSheet.createRow(thisRow);
			// ���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(i + 1);
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �������
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getId());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��������
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getOrdereddate());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ����������
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getPatient());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �����߱��
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getTray());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �Ӽ�
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getEmergent());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ���ܿ�ʽ + �������ͣ���Ҫʹ��editor���з���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(frameDetail != null)
			{
				value = frameDetail.getFramestyle();
				frameTypeEditor.setValue(frameDetail.getFrametype());
				framePatternEditor.setValue(frameDetail.getFramepattern());
				value = value + " " + frameTypeEditor.getAsText();
				value=value+" "+framePatternEditor.getAsText();
				setCellValue(cell, value);
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// A
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(frameDetail != null)
			{
				setCellValue(cell, frameDetail.getRboxasize());
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// B
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(frameDetail != null)
			{
				setCellValue(cell, frameDetail.getRboxbsize());
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// ED
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(frameDetail != null)
			{
				setCellValue(cell, frameDetail.getEd());
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// DBL
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(frameDetail != null)
			{
				setCellValue(cell, frameDetail.getDbl());
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// ����
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				setCellValue(cell, lensDetail.getQuantity());
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// �������� + ��������2����Ҫʹ��editor���з���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				materialEditor
						.setValue((lensDetail.getMaterial() == null) ? null
								: lensDetail.getMaterial().getId());
				materialEditor2
						.setValue((lensDetail.getMaterial2() == null) ? null
								: lensDetail.getMaterial2().getId());
				value = materialEditor.getAsText() + " "
						+ materialEditor2.getAsText();
				setCellValue(cell, value);
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// ��Ƭ���ͣ���Ҫʹ��editor���з���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				lensTypeEditor
						.setValue((lensDetail.getLenstype() == null) ? null
								: lensDetail.getLenstype().getId());
				setCellValue(cell, lensTypeEditor.getAsText());
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// ֱ�� + ֱ����λ����Ҫʹ��editor���з���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				value = convertToString(lensDetail.getDiameter());
				diameterUnitEditor.setValue(lensDetail.getDiameterUnit());
				value = value + " " + diameterUnitEditor.getAsText();
				setCellValue(cell, value);
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// Ⱦɫ����, ��Ҫeditor���з���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				tintEditor.setValue((lensDetail.getTinttype() == null) ? null
						: lensDetail.getTinttype().getId());
				setCellValue(cell, tintEditor.getAsText());
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// ��ɫ, ��Ҫeditor���з���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				colorEditor
						.setValue((lensDetail.getTintcolor1() == null) ? null
								: lensDetail.getTintcolor1().getId());
				setCellValue(cell, colorEditor.getAsText());
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// �ӹ��̶�
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				lensTreatEditor.setValue(lensDetail.getTreat());
				value=lensTreatEditor.getAsText();
				if(lensDetail.getUv()) value=value+"  "+source.getMessage("treatment.uv", null, locale);
				setCellValue(cell,value);
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// ��Ƭ���������в��Ҳ��ϲ���Ԫ�񣬷ֱ��ʾ���Ҿ�Ƭ��������ʹ��row2����ʹ��row
			row2 = orderSheet.createRow(thisRow + 1);
			// ����
			cell = row.createCell(thisCol);
			textValue = new HSSFRichTextString("R");
			cell.setCellValue(textValue);
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			textValue = new HSSFRichTextString("L");
			cell.setCellValue(textValue);
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRsphere());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLsphere());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ����
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRcylinder());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLcylinder());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��λ
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRaxis());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLaxis());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// Զͫ��
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRfarpd());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLfarpd());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��ͫ��
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRnearpd());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLnearpd());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �¼ӹ�
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRadd());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLadd());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �⾵
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRhprism());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLhprism());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ����,��Ҫeditor����ΪUP/DOWN/IN/OUT
			cell = row.createCell(thisCol);
			prismEditor.setValue((lensDetail == null) ? null : lensDetail
					.getRhpd());
			setCellValue(cell, prismEditor.getAsText());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			prismEditor.setValue((lensDetail == null) ? null : lensDetail
					.getLhpd());
			setCellValue(cell, prismEditor.getAsText());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �⾵ 2
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRbhprism());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLbhprism());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ���� 2,��Ҫeditor����ΪUP/DOWN/IN/OUT
			cell = row.createCell(thisCol);
			prismEditor.setValue((lensDetail == null) ? null : lensDetail
					.getRbhpd());
			setCellValue(cell, prismEditor.getAsText());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			prismEditor.setValue((lensDetail == null) ? null : lensDetail
					.getLbhpd());
			setCellValue(cell, prismEditor.getAsText());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ����
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRbasecurve());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLbasecurve());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��Ƭ�߶�
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRsegheight());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLsegheight());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��ѧ���ĸ߶�
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRochgt());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLochgt());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��ע
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getRemarks());
			cell.setCellStyle(cellStyle);
			thisCol++;
		}

		ServletOutputStream output = response.getOutputStream();
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition",
				"attachment;filename=ProduceExcelReport.xls");
		workBook.write(output);
		output.flush();
		output.close();

		return true;
	}

	/**
	 * �������۲�����Ҫ�Ķ���excel����
	 * 
	 * @param orders
	 *            ��Ҫ�����Ķ����б�
	 * @param response
	 *            ��������excel�ļ���HttpServletResponse
	 * @return �������
	 * @throws Exception
	 */
	public static boolean excelExportSalesReport(OnlineOrderFacade onlineOrder,
			List orders, HttpServletRequest request,
			HttpServletResponse response, ApplicationContext context)
			throws Exception
	{
		// ��ȡ�ͻ��˵�locale��Ϣ
		Locale locale = RequestContextUtils.getLocale(request);
		// ��ȡmessageSource����
		MessageSource source = (MessageSource)context.getBean("messageSource");

		// ��ͷ
		// ��� �ͻ����� ������� �������� �������� �Ӽ� �����ͺ�
		// ���� ��Ƭ���� ֱ�� �ӹ��̶� ���� �� ���� ��λ ͫ�� ͫ�� �¼ӹ� �⾵ ����
		String[] header = new String[]
		{ "sno", "order.clientname", "order.patient", "order.tray",
				"order.orderNo", "order.ordereddate", "order.delivereddate",
				"order.emergent.short", "frame.style", "frame.a", "frame.b",
				"frame.ed", "frame.dbl", "lens.quantity", "lens.material.full",
				"lens.type.full", "lens.diameter.full", "lens.tint",
				"lens.color", "lens.treatment",
				"leftright", "lens.sphere", "lens.cylinder", "lens.axis",
				"lens.farpd", "lens.nearpd", "lens.add", "lens.prism",
				"lens.prism.direction", "lens.prism.bshort","lens.prism.bdirection","lens.basecurve", "lens.seght",
				"lens.ochgt", "order.remarks" };

		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet orderSheet = workBook.createSheet("orders");

		HSSFRow row;
		HSSFCell cell;
		HSSFRichTextString textValue;

		// ��ͷ���壺12 ����
		HSSFFont headerFont = workBook.createFont();
		headerFont.setFontHeightInPoints((short)12);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setColor(HSSFColor.RED.index);
		// �������壺12 ����
		HSSFFont cellFont = workBook.createFont();
		cellFont.setFontHeightInPoints((short)12);
		cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		cellFont.setColor(HSSFColor.BLACK.index);
		// ��ͷ��Ԫ����ʽ
		HSSFCellStyle headerStyle = workBook.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		headerStyle.setFont(headerFont);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle
				.setFillForegroundColor(new HSSFColor.TURQUOISE().getIndex());
		// ���嵥Ԫ����ʽ
		HSSFCellStyle cellStyle = workBook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setFont(cellFont);

		row = orderSheet.createRow(0);
		String headerText;
		for(int i = 0; i < header.length; i++)
		{
			headerText = source.getMessage(header[i], null, locale);
			orderSheet.setColumnWidth(i,
					(headerText.getBytes().length + 4) * 256);
			cell = row.createCell(i);
			textValue = new HSSFRichTextString(headerText);
			cell.setCellValue(textValue);
			cell.setCellStyle(headerStyle);
		}

		HSSFRow row2;
		OrsOrder order;
		LensDetail lensDetail;
		FrameDetail frameDetail;
		ProcessInstance process;
		int startRow = 1;
		int thisRow, thisCol;
		String value = null;

		// ����༭��
		PrismDirectionEditor prismEditor = new PrismDirectionEditor();
		// ֱ����λ�༭��
		DiameterUnitEditor diameterUnitEditor = new DiameterUnitEditor(true,
				locale, source);
		// ���ϱ༭��
		LensMaterialEditor materialEditor = new LensMaterialEditor(onlineOrder
				.getLensMaterials(), locale, source);
		LensMaterialEditor materialEditor2 = new LensMaterialEditor(onlineOrder
				.getLensMaterials2(), locale, source);
		FrameTypeEditor frameTypeEditor = new FrameTypeEditor(locale, source);
		FramePatternEditor framePatternEditor=new FramePatternEditor(locale,source);
		LensTreatEditor lensTreatEditor=new LensTreatEditor(locale,source);
		// ���ͱ༭��
		LensTypeEditor lensTypeEditor = new LensTypeEditor(onlineOrder
				.getLensTypes(), locale, source);
		// Ⱦɫ���ͱ༭��
		LensTintEditor tintEditor = new LensTintEditor(onlineOrder
				.getTintTypes(), locale, source);
		// ��ɫ�༭��
		TintColorEditor colorEditor = new TintColorEditor(true, onlineOrder
				.getTintColors(), locale, source);

		for(int i = 0; i < orders.size(); i++)
		{
			order = (OrsOrder)orders.get(i);
			lensDetail = order.getLensdetail();
			frameDetail = order.getFramedetail();
			process = order.getProcessInstance();
			// ÿһ������ռ����
			thisRow = startRow + i * 2;
			thisCol = 0;
			row = orderSheet.createRow(thisRow);
			// ���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(i + 1);
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �û�����
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getUser().getUser_name());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ����������
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getPatient());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �����߱��
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getTray());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �������
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getId());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��������
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getOrdereddate());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��������
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			// ��ȡ��������
			if(process.getToken() < OrderStatus.TYPE_DELIVERED)
			{
				setCellValue(cell, "");
			}
			else
			{
				Set<NodeInstance> nodes = process.getNodeInstances();
				int index = 0;
				for(Iterator<NodeInstance> iter = nodes.iterator(); iter
						.hasNext(); index++)
				{
					if(index == OrderStatus.TYPE_PRODUCING)
					{
						NodeInstance node = iter.next();
						setCellValue(cell, node.getLeavedate());
						break;
					}
				}
			}
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �Ӽ�
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getEmergent());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ���ܿ�ʽ + �������ͣ���Ҫʹ��editor���з���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(frameDetail != null)
			{
				value = frameDetail.getFramestyle();
				frameTypeEditor.setValue(frameDetail.getFrametype());
				framePatternEditor.setValue(frameDetail.getFramepattern());
				value = value + " " + frameTypeEditor.getAsText();
				value=value+" "+framePatternEditor.getAsText();
				setCellValue(cell, value);
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// A
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, (frameDetail == null) ? null : frameDetail
					.getRboxasize());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// B
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, (frameDetail == null) ? null : frameDetail
					.getRboxbsize());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ED
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, (frameDetail == null) ? null : frameDetail
					.getEd());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// DBL
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, (frameDetail == null) ? null : frameDetail
					.getDbl());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ����
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getQuantity());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �������� + ��������2����Ҫʹ��editor���з���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				materialEditor
						.setValue((lensDetail.getMaterial() == null) ? null
								: lensDetail.getMaterial().getId());
				materialEditor2
						.setValue((lensDetail.getMaterial2() == null) ? null
								: lensDetail.getMaterial2().getId());
				value = materialEditor.getAsText() + " "
						+ materialEditor2.getAsText();
				setCellValue(cell, value);
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// ��Ƭ���ͣ���Ҫʹ��editor���з���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				lensTypeEditor
						.setValue((lensDetail.getLenstype() == null) ? null
								: lensDetail.getLenstype().getId());
				setCellValue(cell, lensTypeEditor.getAsText());
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// ֱ�� + ֱ����λ����Ҫʹ��editor���з���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				value = convertToString(lensDetail.getDiameter());
				diameterUnitEditor.setValue(lensDetail.getDiameterUnit());
				value = value + " " + diameterUnitEditor.getAsText();
				setCellValue(cell, value);
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// Ⱦɫ����, ��Ҫ���з���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				tintEditor.setValue((lensDetail.getTinttype() == null) ? null
						: lensDetail.getTinttype().getId());
				setCellValue(cell, tintEditor.getAsText());
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// ��ɫ, ��Ҫeditor���з���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				colorEditor
						.setValue((lensDetail.getTintcolor1() == null) ? null
								: lensDetail.getTintcolor1().getId());
				setCellValue(cell, colorEditor.getAsText());
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// �ӹ��̶�
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				lensTreatEditor.setValue(lensDetail.getTreat());
				value=lensTreatEditor.getAsText();
				if(lensDetail.getUv()) value=value+"  "+source.getMessage("treatment.uv", null, locale);
				setCellValue(cell,value);
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// ��Ƭ���������в��Ҳ��ϲ���Ԫ�񣬷ֱ��ʾ���Ҿ�Ƭ��������ʹ��row2����ʹ��row
			row2 = orderSheet.createRow(thisRow + 1);
			// ����
			cell = row.createCell(thisCol);
			textValue = new HSSFRichTextString("R");
			cell.setCellValue(textValue);
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			textValue = new HSSFRichTextString("L");
			cell.setCellValue(textValue);
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRsphere());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLsphere());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ����
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRcylinder());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLcylinder());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��λ
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRaxis());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLaxis());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// Զͫ��
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRfarpd());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLfarpd());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��ͫ��
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRnearpd());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLnearpd());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �¼ӹ�
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRadd());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLadd());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �⾵
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRhprism());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLhprism());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ����,��Ҫeditor����ΪUP/DOWN/IN/OUT
			cell = row.createCell(thisCol);
			prismEditor.setValue((lensDetail == null) ? null : lensDetail
					.getRhpd());
			setCellValue(cell, prismEditor.getAsText());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			prismEditor.setValue((lensDetail == null) ? null : lensDetail
					.getLhpd());
			setCellValue(cell, prismEditor.getAsText());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �⾵2
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRbhprism());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLbhprism());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ���� 2,��Ҫeditor����ΪUP/DOWN/IN/OUT
			cell = row.createCell(thisCol);
			prismEditor.setValue((lensDetail == null) ? null : lensDetail
					.getRbhpd());
			setCellValue(cell, prismEditor.getAsText());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			prismEditor.setValue((lensDetail == null) ? null : lensDetail
					.getLbhpd());
			setCellValue(cell, prismEditor.getAsText());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ����
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRbasecurve());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLbasecurve());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��Ƭ�߶�
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRsegheight());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLsegheight());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��ѧ���ĸ߶�
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRochgt());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLochgt());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��ע
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getRemarks());
			cell.setCellStyle(cellStyle);
			thisCol++;
		}

		ServletOutputStream output = response.getOutputStream();
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition",
				"attachment;filename=SalesExcelReport.xls");
		workBook.write(output);
		output.flush();
		output.close();

		return true;
	}

	/**
	 * �����ͻ���Ҫ�Ķ���excel����
	 * 
	 * @param orders
	 *            ��Ҫ�����Ķ����б�
	 * @param response
	 *            ��������excel�ļ���HttpServletResponse
	 * @return �������
	 * @throws Exception
	 */
	public static boolean excelExportClientReport(OnlineOrderFacade onlineOrder, List orders,
			HttpServletRequest request, HttpServletResponse response,
			ApplicationContext context) throws Exception
	{
		// ��ȡ�ͻ��˵�locale��Ϣ
		Locale locale = RequestContextUtils.getLocale(request);
		// ��ȡmessageSource����
		MessageSource source = (MessageSource)context.getBean("messageSource");

		// ��ͷ
		String[] header = new String[]
		{ "sno", "order.patient", "order.tray", "order.orderNo",
				"order.ordereddate", "order.delivereddate", "frame.style",
				"frame.a", "frame.b", "frame.ed", "frame.dbl", "lens.quantity",
				"lens.material.full", "lens.type.full", "lens.diameter.full",
				"lens.tint", "lens.color", "lens.treatment", "leftright", "lens.sphere", "lens.cylinder",
				"lens.axis", "lens.farpd", "lens.nearpd", "lens.add",
				"lens.prism", "lens.prism.direction", "lens.prism.bshort",
				"lens.prism.bdirection","lens.basecurve",
				"lens.seght", "lens.ochgt", "order.remarks" };

		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet orderSheet = workBook.createSheet("orders");

		HSSFRow row;
		HSSFCell cell;
		HSSFRichTextString textValue;

		// ��ͷ���壺12 ����
		HSSFFont headerFont = workBook.createFont();
		headerFont.setFontHeightInPoints((short)12);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setColor(HSSFColor.RED.index);
		// �������壺12 ����
		HSSFFont cellFont = workBook.createFont();
		cellFont.setFontHeightInPoints((short)12);
		cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		cellFont.setColor(HSSFColor.BLACK.index);
		// ��ͷ��Ԫ����ʽ
		HSSFCellStyle headerStyle = workBook.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		headerStyle.setFont(headerFont);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle
				.setFillForegroundColor(new HSSFColor.TURQUOISE().getIndex());
		// ���嵥Ԫ����ʽ
		HSSFCellStyle cellStyle = workBook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setFont(cellFont);

		row = orderSheet.createRow(0);
		String headerText;
		for(int i = 0; i < header.length; i++)
		{
			headerText = source.getMessage(header[i], null, locale);
			orderSheet.setColumnWidth(i,
					(headerText.getBytes().length + 4) * 256);
			cell = row.createCell(i);
			cell.setCellStyle(headerStyle);
			textValue = new HSSFRichTextString(headerText);
			cell.setCellValue(textValue);
		}

		HSSFRow row2;
		OrsOrder order;
		LensDetail lensDetail;
		FrameDetail frameDetail;
		ProcessInstance process;
		int startRow = 1;
		int thisRow, thisCol;
		String value = null;

		// ����༭��
		PrismDirectionEditor prismEditor = new PrismDirectionEditor();
		// ֱ����λ�༭��
		DiameterUnitEditor diameterUnitEditor = new DiameterUnitEditor(true,
				locale, source);
		// ���ϱ༭��
		LensMaterialEditor materialEditor = new LensMaterialEditor(onlineOrder
				.getLensMaterials(), locale, source);
		LensMaterialEditor materialEditor2 = new LensMaterialEditor(onlineOrder
				.getLensMaterials2(), locale, source);
		FrameTypeEditor frameTypeEditor = new FrameTypeEditor(locale, source);
		FramePatternEditor framePatternEditor=new FramePatternEditor(locale,source);
		LensTreatEditor lensTreatEditor=new LensTreatEditor(locale,source);
		// ���ͱ༭��
		LensTypeEditor lensTypeEditor = new LensTypeEditor(onlineOrder
				.getLensTypes(), locale, source);
		// Ⱦɫ���ͱ༭��
		LensTintEditor tintEditor = new LensTintEditor(onlineOrder
				.getTintTypes(), locale, source);
		// ��ɫ�༭��
		TintColorEditor colorEditor = new TintColorEditor(true, onlineOrder
				.getTintColors(), locale, source);

		for(int i = 0; i < orders.size(); i++)
		{
			order = (OrsOrder)orders.get(i);
			lensDetail = order.getLensdetail();
			frameDetail = order.getFramedetail();
			process = order.getProcessInstance();
			// ÿһ������ռ����
			thisRow = startRow + i * 2;
			thisCol = 0;
			row = orderSheet.createRow(thisRow);
			// ���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(i + 1);
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ����������
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getPatient());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �����߱��
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getTray());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �������
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getId());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��������
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getOrdereddate());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��������
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			// ��ȡ��������
			if(process.getToken() < OrderStatus.TYPE_DELIVERED)
			{
				setCellValue(cell, "");
			}
			else
			{
				Set<NodeInstance> nodes = process.getNodeInstances();
				int index = 0;
				for(Iterator<NodeInstance> iter = nodes.iterator(); iter
						.hasNext(); index++)
				{
					if(index == OrderStatus.TYPE_PRODUCING)
					{
						NodeInstance node = iter.next();
						setCellValue(cell, node.getLeavedate());
						break;
					}
				}
			}
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ���ܿ�ʽ + �������ͣ���Ҫʹ��editor���з���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(frameDetail != null)
			{
				value = frameDetail.getFramestyle();
				frameTypeEditor.setValue(frameDetail.getFrametype());
				framePatternEditor.setValue(frameDetail.getFramepattern());
				value = value + " " + frameTypeEditor.getAsText();
				value=value+" "+framePatternEditor.getAsText();
				setCellValue(cell, value);
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// A
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, (frameDetail == null) ? null : frameDetail
					.getRboxasize());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// B
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, (frameDetail == null) ? null : frameDetail
					.getRboxbsize());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ED
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, (frameDetail == null) ? null : frameDetail
					.getEd());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// DBL
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, (frameDetail == null) ? null : frameDetail
					.getDbl());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ����
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getQuantity());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �������� + ��������2����Ҫʹ��editor���з���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				materialEditor
						.setValue((lensDetail.getMaterial() == null) ? null
								: lensDetail.getMaterial().getId());
				materialEditor2
						.setValue((lensDetail.getMaterial2() == null) ? null
								: lensDetail.getMaterial2().getId());
				value = materialEditor.getAsText() + " "
						+ materialEditor2.getAsText();
				setCellValue(cell, value);
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// ��Ƭ���ͣ���Ҫʹ��editor���з���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				lensTypeEditor
						.setValue((lensDetail.getLenstype() == null) ? null
								: lensDetail.getLenstype().getId());
				setCellValue(cell, lensTypeEditor.getAsText());
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// ֱ�� + ֱ����λ����Ҫʹ��editor���з���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				value = convertToString(lensDetail.getDiameter());
				diameterUnitEditor.setValue(lensDetail.getDiameterUnit());
				value = value + " " + diameterUnitEditor.getAsText();
				setCellValue(cell, value);
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// Ⱦɫ����, ��Ҫ���з���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				tintEditor
						.setValue((lensDetail.getTinttype() == null) ? null
								: lensDetail.getTinttype().getId());
				setCellValue(cell, tintEditor.getAsText());
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// ��ɫ, ��Ҫeditor���з���
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				colorEditor
						.setValue((lensDetail.getTintcolor1() == null) ? null
								: lensDetail.getTintcolor1().getId());
				setCellValue(cell, colorEditor.getAsText());
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// �ӹ��̶�
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			if(lensDetail != null)
			{
				lensTreatEditor.setValue(lensDetail.getTreat());
				value=lensTreatEditor.getAsText();
				if(lensDetail.getUv()) value=value+"  "+source.getMessage("treatment.uv", null, locale);
				setCellValue(cell,value);
				cell.setCellStyle(cellStyle);
			}
			thisCol++;
			// ��Ƭ���������в��Ҳ��ϲ���Ԫ�񣬷ֱ��ʾ���Ҿ�Ƭ��������ʹ��row2����ʹ��row
			row2 = orderSheet.createRow(thisRow + 1);
			// ����
			cell = row.createCell(thisCol);
			textValue = new HSSFRichTextString("R");
			cell.setCellValue(textValue);
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			textValue = new HSSFRichTextString("L");
			cell.setCellValue(textValue);
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRsphere());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLsphere());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ����
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRcylinder());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLcylinder());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��λ
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRaxis());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLaxis());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// Զͫ��
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRfarpd());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLfarpd());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��ͫ��
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRnearpd());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLnearpd());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �¼ӹ�
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRadd());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLadd());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �⾵
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRhprism());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLhprism());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ����,��Ҫeditor����ΪUP/DOWN/IN/OUT
			cell = row.createCell(thisCol);
			prismEditor.setValue((lensDetail == null) ? null : lensDetail
					.getRhpd());
			setCellValue(cell, prismEditor.getAsText());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			prismEditor.setValue((lensDetail == null) ? null : lensDetail
					.getLhpd());
			setCellValue(cell, prismEditor.getAsText());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// �⾵ 2
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRbhprism());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLbhprism());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ���� 2,��Ҫeditor����ΪUP/DOWN/IN/OUT
			cell = row.createCell(thisCol);
			prismEditor.setValue((lensDetail == null) ? null : lensDetail
					.getRbhpd());
			setCellValue(cell, prismEditor.getAsText());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			prismEditor.setValue((lensDetail == null) ? null : lensDetail
					.getLbhpd());
			setCellValue(cell, prismEditor.getAsText());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ����
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRbasecurve());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLbasecurve());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��Ƭ�߶�
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRsegheight());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLsegheight());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��ѧ���ĸ߶�
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getRochgt());
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getLochgt());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// ��ע
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getRemarks());
			cell.setCellStyle(cellStyle);
			thisCol++;
		}
		
		ServletOutputStream output = response.getOutputStream();
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition",
				"attachment;filename=ClientExcelReport.xls");
		workBook.write(output);
		output.flush();
		output.close();
		
		return true;
	}
}
