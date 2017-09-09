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
 * 订单导出Excel文件工具类。
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
		String targetValue = (value == null) ? "" : (value ? "√" : "");
		cell.setCellValue(new HSSFRichTextString(targetValue));
	}

	private static String convertToString(Object value)
	{
		return (value == null) ? "" : value.toString();
	}

	/**
	 * 导出生产部门需要的订单excel报表。
	 * 
	 * @param orders
	 *            需要导出的订单列表
	 * @param response
	 *            用于下载excel文件的HttpServletResponse
	 * @return 操作结果
	 * @throws Exception
	 */
	public static boolean excelExportProduceReport(
			OnlineOrderFacade onlineOrder, List orders,
			HttpServletRequest request, HttpServletResponse response,
			ApplicationContext context) throws Exception
	{
		// 获取客户端的locale信息
		Locale locale = RequestContextUtils.getLocale(request);
		// 获取messageSource对象
		MessageSource source = (MessageSource)context.getBean("messageSource");

		// 表头
		// 序号 订单编号 订单日期 加急 镜架型号 数量 镜片类型 直径 加工程度 左右 球镜 柱镜 轴位 瞳距 瞳高 下加光 棱镜 基弯
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

		// 表头字体：12 粗体
		HSSFFont headerFont = workBook.createFont();
		headerFont.setFontHeightInPoints((short)12);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setColor(HSSFColor.RED.index);
		// 表体字体：12 正常
		HSSFFont cellFont = workBook.createFont();
		cellFont.setFontHeightInPoints((short)12);
		cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		cellFont.setColor(HSSFColor.BLACK.index);
		// 表头单元格样式
		HSSFCellStyle headerStyle = workBook.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		headerStyle.setFont(headerFont);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle
				.setFillForegroundColor(new HSSFColor.TURQUOISE().getIndex());
		// 表体单元格样式
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

		// 底向编辑器
		PrismDirectionEditor prismEditor = new PrismDirectionEditor();
		// 直径单位编辑器
		DiameterUnitEditor diameterUnitEditor = new DiameterUnitEditor(true,
				locale, source);
		// 材料编辑器
		LensMaterialEditor materialEditor = new LensMaterialEditor(onlineOrder
				.getLensMaterials(), locale, source);
		LensMaterialEditor materialEditor2 = new LensMaterialEditor(onlineOrder
				.getLensMaterials2(), locale, source);
		FrameTypeEditor frameTypeEditor = new FrameTypeEditor(locale, source);
		FramePatternEditor framePatternEditor=new FramePatternEditor(locale,source);
		LensTreatEditor lensTreatEditor=new LensTreatEditor(locale,source);
		// 类型编辑器
		LensTypeEditor lensTypeEditor = new LensTypeEditor(onlineOrder
				.getLensTypes(), locale, source);
		// 染色类型编辑器
		LensTintEditor tintEditor = new LensTintEditor(onlineOrder
				.getTintTypes(), locale, source);
		// 颜色编辑器
		TintColorEditor colorEditor = new TintColorEditor(true, onlineOrder
				.getTintColors(), locale, source);

		for(int i = 0; i < orders.size(); i++)
		{
			order = (OrsOrder)orders.get(i);
			lensDetail = order.getLensdetail();
			frameDetail = order.getFramedetail();
			// 每一个订单占两行
			thisRow = startRow + i * 2;
			thisCol = 0;
			row = orderSheet.createRow(thisRow);
			// 序号
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(i + 1);
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 订单编号
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getId());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 订单日期
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getOrdereddate());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 消费者名称
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getPatient());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 消费者编号
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getTray());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 加急
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getEmergent());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 镜架款式 + 镜架类型，需要使用editor进行翻译
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
			// 数量
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
			// 材料类型 + 材料类型2，需要使用editor进行翻译
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
			// 镜片类型，需要使用editor进行翻译
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
			// 直径 + 直径单位，需要使用editor进行翻译
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
			// 染色类型, 需要editor进行翻译
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
			// 颜色, 需要editor进行翻译
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
			// 加工程度
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
			// 镜片参数分两行并且不合并单元格，分别表示左右镜片参数，左使用row2，右使用row
			row2 = orderSheet.createRow(thisRow + 1);
			// 左右
			cell = row.createCell(thisCol);
			textValue = new HSSFRichTextString("R");
			cell.setCellValue(textValue);
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			textValue = new HSSFRichTextString("L");
			cell.setCellValue(textValue);
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 球镜
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
			// 柱镜
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
			// 轴位
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
			// 远瞳距
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
			// 近瞳距
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
			// 下加光
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
			// 棱镜
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
			// 底向,需要editor翻译为UP/DOWN/IN/OUT
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
			// 棱镜 2
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
			// 底向 2,需要editor翻译为UP/DOWN/IN/OUT
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
			// 基弯
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
			// 子片高度
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
			// 光学中心高度
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
			// 备注
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
	 * 导出销售部门需要的订单excel报表。
	 * 
	 * @param orders
	 *            需要导出的订单列表
	 * @param response
	 *            用于下载excel文件的HttpServletResponse
	 * @return 操作结果
	 * @throws Exception
	 */
	public static boolean excelExportSalesReport(OnlineOrderFacade onlineOrder,
			List orders, HttpServletRequest request,
			HttpServletResponse response, ApplicationContext context)
			throws Exception
	{
		// 获取客户端的locale信息
		Locale locale = RequestContextUtils.getLocale(request);
		// 获取messageSource对象
		MessageSource source = (MessageSource)context.getBean("messageSource");

		// 表头
		// 序号 客户名称 订单编号 订单日期 发货日期 加急 镜架型号
		// 数量 镜片类型 直径 加工程度 左右 球镜 柱镜 轴位 瞳距 瞳高 下加光 棱镜 基弯
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

		// 表头字体：12 粗体
		HSSFFont headerFont = workBook.createFont();
		headerFont.setFontHeightInPoints((short)12);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setColor(HSSFColor.RED.index);
		// 表体字体：12 正常
		HSSFFont cellFont = workBook.createFont();
		cellFont.setFontHeightInPoints((short)12);
		cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		cellFont.setColor(HSSFColor.BLACK.index);
		// 表头单元格样式
		HSSFCellStyle headerStyle = workBook.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		headerStyle.setFont(headerFont);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle
				.setFillForegroundColor(new HSSFColor.TURQUOISE().getIndex());
		// 表体单元格样式
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

		// 底向编辑器
		PrismDirectionEditor prismEditor = new PrismDirectionEditor();
		// 直径单位编辑器
		DiameterUnitEditor diameterUnitEditor = new DiameterUnitEditor(true,
				locale, source);
		// 材料编辑器
		LensMaterialEditor materialEditor = new LensMaterialEditor(onlineOrder
				.getLensMaterials(), locale, source);
		LensMaterialEditor materialEditor2 = new LensMaterialEditor(onlineOrder
				.getLensMaterials2(), locale, source);
		FrameTypeEditor frameTypeEditor = new FrameTypeEditor(locale, source);
		FramePatternEditor framePatternEditor=new FramePatternEditor(locale,source);
		LensTreatEditor lensTreatEditor=new LensTreatEditor(locale,source);
		// 类型编辑器
		LensTypeEditor lensTypeEditor = new LensTypeEditor(onlineOrder
				.getLensTypes(), locale, source);
		// 染色类型编辑器
		LensTintEditor tintEditor = new LensTintEditor(onlineOrder
				.getTintTypes(), locale, source);
		// 颜色编辑器
		TintColorEditor colorEditor = new TintColorEditor(true, onlineOrder
				.getTintColors(), locale, source);

		for(int i = 0; i < orders.size(); i++)
		{
			order = (OrsOrder)orders.get(i);
			lensDetail = order.getLensdetail();
			frameDetail = order.getFramedetail();
			process = order.getProcessInstance();
			// 每一个订单占两行
			thisRow = startRow + i * 2;
			thisCol = 0;
			row = orderSheet.createRow(thisRow);
			// 序号
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(i + 1);
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 用户名称
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getUser().getUser_name());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 消费者名称
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getPatient());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 消费者编号
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getTray());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 订单编号
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getId());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 订单日期
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getOrdereddate());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 发货日期
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			// 获取发货日期
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
			// 加急
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getEmergent());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 镜架款式 + 镜架类型，需要使用editor进行翻译
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
			// 数量
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getQuantity());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 材料类型 + 材料类型2，需要使用editor进行翻译
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
			// 镜片类型，需要使用editor进行翻译
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
			// 直径 + 直径单位，需要使用editor进行翻译
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
			// 染色类型, 需要进行翻译
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
			// 颜色, 需要editor进行翻译
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
			// 加工程度
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
			// 镜片参数分两行并且不合并单元格，分别表示左右镜片参数，左使用row2，右使用row
			row2 = orderSheet.createRow(thisRow + 1);
			// 左右
			cell = row.createCell(thisCol);
			textValue = new HSSFRichTextString("R");
			cell.setCellValue(textValue);
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			textValue = new HSSFRichTextString("L");
			cell.setCellValue(textValue);
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 球镜
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
			// 柱镜
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
			// 轴位
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
			// 远瞳距
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
			// 近瞳距
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
			// 下加光
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
			// 棱镜
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
			// 底向,需要editor翻译为UP/DOWN/IN/OUT
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
			// 棱镜2
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
			// 底向 2,需要editor翻译为UP/DOWN/IN/OUT
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
			// 基弯
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
			// 子片高度
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
			// 光学中心高度
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
			// 备注
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
	 * 导出客户需要的订单excel报表。
	 * 
	 * @param orders
	 *            需要导出的订单列表
	 * @param response
	 *            用于下载excel文件的HttpServletResponse
	 * @return 操作结果
	 * @throws Exception
	 */
	public static boolean excelExportClientReport(OnlineOrderFacade onlineOrder, List orders,
			HttpServletRequest request, HttpServletResponse response,
			ApplicationContext context) throws Exception
	{
		// 获取客户端的locale信息
		Locale locale = RequestContextUtils.getLocale(request);
		// 获取messageSource对象
		MessageSource source = (MessageSource)context.getBean("messageSource");

		// 表头
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

		// 表头字体：12 粗体
		HSSFFont headerFont = workBook.createFont();
		headerFont.setFontHeightInPoints((short)12);
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setColor(HSSFColor.RED.index);
		// 表体字体：12 正常
		HSSFFont cellFont = workBook.createFont();
		cellFont.setFontHeightInPoints((short)12);
		cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		cellFont.setColor(HSSFColor.BLACK.index);
		// 表头单元格样式
		HSSFCellStyle headerStyle = workBook.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		headerStyle.setFont(headerFont);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle
				.setFillForegroundColor(new HSSFColor.TURQUOISE().getIndex());
		// 表体单元格样式
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

		// 底向编辑器
		PrismDirectionEditor prismEditor = new PrismDirectionEditor();
		// 直径单位编辑器
		DiameterUnitEditor diameterUnitEditor = new DiameterUnitEditor(true,
				locale, source);
		// 材料编辑器
		LensMaterialEditor materialEditor = new LensMaterialEditor(onlineOrder
				.getLensMaterials(), locale, source);
		LensMaterialEditor materialEditor2 = new LensMaterialEditor(onlineOrder
				.getLensMaterials2(), locale, source);
		FrameTypeEditor frameTypeEditor = new FrameTypeEditor(locale, source);
		FramePatternEditor framePatternEditor=new FramePatternEditor(locale,source);
		LensTreatEditor lensTreatEditor=new LensTreatEditor(locale,source);
		// 类型编辑器
		LensTypeEditor lensTypeEditor = new LensTypeEditor(onlineOrder
				.getLensTypes(), locale, source);
		// 染色类型编辑器
		LensTintEditor tintEditor = new LensTintEditor(onlineOrder
				.getTintTypes(), locale, source);
		// 颜色编辑器
		TintColorEditor colorEditor = new TintColorEditor(true, onlineOrder
				.getTintColors(), locale, source);

		for(int i = 0; i < orders.size(); i++)
		{
			order = (OrsOrder)orders.get(i);
			lensDetail = order.getLensdetail();
			frameDetail = order.getFramedetail();
			process = order.getProcessInstance();
			// 每一个订单占两行
			thisRow = startRow + i * 2;
			thisCol = 0;
			row = orderSheet.createRow(thisRow);
			// 序号
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(i + 1);
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 消费者名称
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getPatient());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 消费者编号
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getTray());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 订单编号
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getId());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 订单日期
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			setCellValue(cell, order.getOrdereddate());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 发货日期
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			// 获取发货日期
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
			// 镜架款式 + 镜架类型，需要使用editor进行翻译
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
			// 数量
			orderSheet.addMergedRegion(new CellRangeAddress(thisRow,
					thisRow + 1, thisCol, thisCol));
			cell = row.createCell(thisCol);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			setCellValue(cell, (lensDetail == null) ? null : lensDetail
					.getQuantity());
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 材料类型 + 材料类型2，需要使用editor进行翻译
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
			// 镜片类型，需要使用editor进行翻译
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
			// 直径 + 直径单位，需要使用editor进行翻译
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
			// 染色类型, 需要进行翻译
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
			// 颜色, 需要editor进行翻译
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
			// 加工程度
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
			// 镜片参数分两行并且不合并单元格，分别表示左右镜片参数，左使用row2，右使用row
			row2 = orderSheet.createRow(thisRow + 1);
			// 左右
			cell = row.createCell(thisCol);
			textValue = new HSSFRichTextString("R");
			cell.setCellValue(textValue);
			cell.setCellStyle(cellStyle);
			cell = row2.createCell(thisCol);
			textValue = new HSSFRichTextString("L");
			cell.setCellValue(textValue);
			cell.setCellStyle(cellStyle);
			thisCol++;
			// 球镜
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
			// 柱镜
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
			// 轴位
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
			// 远瞳距
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
			// 近瞳距
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
			// 下加光
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
			// 棱镜
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
			// 底向,需要editor翻译为UP/DOWN/IN/OUT
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
			// 棱镜 2
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
			// 底向 2,需要editor翻译为UP/DOWN/IN/OUT
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
			// 基弯
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
			// 子片高度
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
			// 光学中心高度
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
			// 备注
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
