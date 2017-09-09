/**
 * PrismDirectionEditor.java
 * 2009-3-3
 * Administrator
 */
package com.conant.order.web.editor;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.conant.order.util.Logger;
import com.conant.order.vo.PrismDirectionType;

/**
 * µ×Ïòeditor¡£
 * @author Administrator
 * 
 */
public class PrismDirectionEditor extends PropertyEditorSupport
{
	private static final Logger log = Logger.getLogger(
			"PrismDirectionEditor", Logger.DEBUG, true);
	
	private final Map<Integer, String> items;

	public PrismDirectionEditor()
	{
		items = new HashMap<Integer, String>();
		items.put(PrismDirectionType.TYPE_UP, "UP");
		items.put(PrismDirectionType.TYPE_DOWN, "DOWN");
		items.put(PrismDirectionType.TYPE_IN, "IN");
		items.put(PrismDirectionType.TYPE_OUT, "OUT");
	}

	@Override
	public String getAsText()
	{
		log.info("PrismDirectionEditor getAsText " + getValue());
		Integer typeId = (Integer)getValue();
		String typeName = items.get(typeId);
		return (typeName == null) ? "" : typeName;
	}

	@Override
	public void setAsText(String text)
	{
		log.info("PrismDirectionEditor setAsText " + text);
		if(!StringUtils.hasText(text))
		{
			// Treat empty String as null value.
			setValue(null);
			return;
		}
		Integer typeId = null;
		for(Map.Entry<Integer, String> item : items.entrySet())
		{
			if(item.getValue().equalsIgnoreCase(text))
			{
				typeId = item.getKey();
				break;
			}
		}
		setValue(typeId);
	}
}
