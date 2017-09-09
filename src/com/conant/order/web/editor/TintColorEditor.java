/**
 * OrderTypeEditor.java
 * 2008-11-22
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
import com.conant.order.vo.TintColor;

/**
 * È¾É«ÑÕÉ«editor¡£
 * @author Administrator
 * 
 */
public class TintColorEditor extends PropertyEditorSupport
{
	private static final Logger log = Logger.getLogger("TintColorEditor",
			Logger.DEBUG, true);
	private final boolean allowEmpty;
	private final Map<Integer, String> mapItems;

	public TintColorEditor(boolean allowEmpty, List<TintColor> items)
	{
		this.allowEmpty = allowEmpty;
		mapItems = new ConcurrentHashMap<Integer, String>();
		for(TintColor item : items)
		{
			mapItems.put(item.getId(), item.getTypeName());
		}
	}
	
	public TintColorEditor(boolean allowEmpty, List<TintColor> items, Locale locale, MessageSource message)
	{
		this.allowEmpty = allowEmpty;
		mapItems = new ConcurrentHashMap<Integer, String>();
		for(TintColor item : items)
		{
			mapItems.put(item.getId(), message.getMessage("tintcolor." + item.getId(), null, locale));
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
		log.info("TintColorEditor getAsText " + typeName);
		return (typeName == null) ? "" : typeName;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException
	{
		log.info("TintColorEditor: setAsText-" + text);
		if(this.allowEmpty && !StringUtils.hasText(text))
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
		log.info("TintColorEditor setAsText " + text);
	}
}
