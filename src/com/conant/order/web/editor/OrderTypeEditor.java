/**
 * OrderTypeEditor.java
 * 2008-11-22
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
import com.conant.order.vo.OrderType;

/**
 * ∂©µ•¿‡–Õeditor°£
 * @author Administrator
 *
 */
public class OrderTypeEditor extends PropertyEditorSupport
{
	private static final Logger log = Logger.getLogger(
			"OrderTypeEditor", Logger.DEBUG, true);	
	private final boolean allowEmpty;
	private final Map<Integer, String> items;
	
	public OrderTypeEditor(boolean allowEmpty, Locale locale, MessageSource source)
	{
		this.allowEmpty = allowEmpty;
		items = new HashMap<Integer, String>();
		items.put(OrderType.TYPE_LS, source.getMessage("ordertype." + OrderType.TYPE_LS, null, locale));
		items.put(OrderType.TYPE_FM, source.getMessage("ordertype." + OrderType.TYPE_FM, null, locale));
		items.put(OrderType.TYPE_FM_LS, source.getMessage("ordertype." + OrderType.TYPE_FM_LS, null, locale));
	}
	
	public Map<Integer, String> getMapType()
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
		log.info("OrderTypeEditor getAsText " + getValue());
		Integer typeId = (Integer)getValue();
		return typeId != null ? items.get(typeId) : "";
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException
	{
		log.info("OrderTypeEditor setAsText " + text);
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
