/**
 * OrderStatusEditor.java
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
import com.conant.order.vo.OrderStatus;

/**
 * ¶©µ¥×´Ì¬editor¡£
 * @author Administrator
 *
 */
public class OrderStatusEditor extends PropertyEditorSupport
{
	private static final Logger log = Logger.getLogger(
			"OrderStatusEditor", Logger.DEBUG, true);
	
	private final boolean allowEmpty;
	private final Map<Integer, String> items;
	
	public OrderStatusEditor(boolean allowEmpty, Locale locale, MessageSource message)
	{
		this.allowEmpty = allowEmpty;
		items = new HashMap<Integer, String>();
		items.put(OrderStatus.TYPE_AUDITING, message.getMessage("orderstatus." + OrderStatus.TYPE_AUDITING, null, locale));
		items.put(OrderStatus.TYPE_PRODUCING, message.getMessage("orderstatus." + OrderStatus.TYPE_PRODUCING, null, locale));
		items.put(OrderStatus.TYPE_DELIVERED, message.getMessage("orderstatus." + OrderStatus.TYPE_DELIVERED, null, locale));
		//items.put(OrderStatus.TYPE_CHECKING, message.getMessage("orderstatus." + OrderStatus.TYPE_CHECKING, null, locale));
		items.put(OrderStatus.TYPE_COMPLETE, message.getMessage("orderstatus." + OrderStatus.TYPE_COMPLETE, null, locale));		
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
		log.info("OrderStatusEditor getAsText " + getValue());
		Integer typeId = (Integer)getValue();
		String typeName = items.get(typeId);
		return (typeName == null) ? "none" : typeName;
	}

	@Override
	public void setAsText(String text)
	{
		log.info("OrderStatusEditor setAsText " + text);
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
