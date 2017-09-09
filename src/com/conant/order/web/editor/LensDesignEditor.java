/**
 * LensTypeEditor.java
 * 2009-2-23
 * Administrator
 */
package com.conant.order.web.editor;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;

import com.conant.order.util.Logger;
import com.conant.order.vo.LensDesign;

/**
 * æµ∆¨¿‡–Õeditor°£
 * @author Administrator
 * 
 */
public class LensDesignEditor extends PropertyEditorSupport
{
	private static final Logger log = Logger.getLogger("LensDesignEditor",
			Logger.DEBUG, true);

	private final Map<Integer, String> mapItems;

	public LensDesignEditor(List<LensDesign> items)
	{
		mapItems = new ConcurrentHashMap<Integer, String>();
		for(LensDesign item : items)
		{
			mapItems.put(item.getId(), item.getTypeName());
		}
	}

	public LensDesignEditor(List<LensDesign> items, Locale locale, MessageSource message)
	{
		mapItems = new ConcurrentHashMap<Integer, String>();
		for(LensDesign item : items)
		{
			mapItems.put(item.getId(), message.getMessage("lensdesign." + item.getId(), null, locale));
		}
	}
	
	public List<String> getListItems()
	{
		List<String> listItems = new ArrayList<String>();
		for(Map.Entry<Integer, String> item : mapItems.entrySet())
		{
			listItems.add(item.getValue());
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
			for(Map.Entry<Integer, String> item : mapItems.entrySet())
			{
				if(item.getKey().equals(typeId))
				{
					typeName = item.getValue();
					break;
				}
			}
		}
		
		log.info("LensDesignEditor getAsText " + typeName);
		
		return (typeName == null) ? "" : typeName;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException
	{
		log.info("LensDesignEditor: setAsText-" + text);
		if(!StringUtils.hasText(text))
		{
			// Treat empty String as null value.
			setValue(null);
			return;
		}
		Integer typeId = null;
		for(Map.Entry<Integer, String> item : mapItems.entrySet())
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
