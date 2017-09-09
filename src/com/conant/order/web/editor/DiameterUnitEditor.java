/**
 * PrismDirectionEditor.java
 * 2009-3-3
 * Administrator
 */
package com.conant.order.web.editor;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;

import com.conant.order.util.Logger;
import com.conant.order.vo.DiameterUnitType;

/**
 * Ö±¾¶µ¥Î»editor¡£
 * @author Administrator
 * 
 */
public class DiameterUnitEditor extends PropertyEditorSupport
{
	private static final Logger log = Logger.getLogger(
			"DiameterUnitEditor", Logger.DEBUG, true);
	
	private final boolean allowEmpty;
	private final Map<Integer, String> items;
	
	public DiameterUnitEditor(boolean allowEmpty, Locale locale, MessageSource message)
	{
		this.allowEmpty = allowEmpty;
		items = new HashMap<Integer, String>();
		items.put(DiameterUnitType.TYPE_EDGE, message.getMessage("diameterunit." + DiameterUnitType.TYPE_EDGE, null, locale));
	}
	
	public Map<Integer, String> getMapItems()
	{
		return items;
	}
	
	public List<String> getListItems()
	{
		List<String> listItems = new ArrayList<String>();
        for(Map.Entry<Integer, String> item : items.entrySet())
        {
        	listItems.add(item.getValue());
        }
        
        return listItems;
	}
	
	@Override
	public String getAsText()
	{
		log.info("DiameterUnitEditor getAsText " + getValue());
		Integer typeId = (Integer)getValue();
		String typeName = items.get(typeId);
		return (typeName == null) ? "" : typeName;
	}

	@Override
	public void setAsText(String text)
	{
		log.info("DiameterUnitEditor setAsText " + text);
		if(this.allowEmpty && !StringUtils.hasText(text))
		{
			// Treat empty String as null value.
			setValue(null);
			return;
		}
        for(Map.Entry<Integer, String> item : items.entrySet())
        {
            if(item.getValue().equalsIgnoreCase(text))
            {
            	setValue(item.getKey());
            	break;
            }
        }
	}
}
