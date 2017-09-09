/**
 * LensTreatmentEditor.java
 * 2009-2-23
 * Administrator
 */
package com.conant.order.web.editor;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.conant.order.util.Logger;
import com.conant.order.vo.LensTreatment;

/**
 * 镜片加工方式editor。
 * @author Administrator
 * 
 */
public class LensTreatmentEditor extends PropertyEditorSupport
{
	private static final Logger log = Logger.getLogger("LensTreatmentEditor",
			Logger.DEBUG, true);

	private List<LensTreatment> items;

	public LensTreatmentEditor(List items)
	{
		this.items = items;
	}

	public List<String> getListItems()
	{
		List<String> listItems = new ArrayList<String>();
		for(LensTreatment item : items)
		{
			listItems.add(item.getTypeName());
		}

		return listItems;
	}

	public Integer getValue()
	{
		return (Integer)super.getValue();
	}

	@Override
	public String getAsText()
	{
		Integer typeId = getValue();
		String typeName = null;
		if(typeId != null)
		{
			for(LensTreatment item : items)
			{
				if(item.getId().equals(typeId))
				{
					typeName = item.getTypeName();
					break;
				}
			}
		}
		return (typeName == null) ? "" : typeName;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException
	{
		log.info("LensTreatmentEditor: setAsText-" + text);
		if(!StringUtils.hasText(text))
		{
			// Treat empty String as null value.
			setValue(null);
			return;
		}
		Integer typeId = null;
		for(LensTreatment item : items)
		{
			if(item.getTypeName().equalsIgnoreCase(text))
			{
				typeId = item.getId();
				break;
			}
		}
		setValue(typeId);
	}
}
