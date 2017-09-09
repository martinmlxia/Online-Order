/**
 * FrameTypeEditor.java
 * 2009-6-24
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
import com.conant.order.vo.FrameType;

/**
 * æµº‹¿‡–Õeditor°£
 * @author Administrator
 *
 */
public class FrameTypeEditor extends PropertyEditorSupport
{
	private static final Logger log = Logger.getLogger(
			"FrameTypeEditor", Logger.DEBUG, true);
	
	private final Map<Integer, String> items;

	public FrameTypeEditor(Locale locale, MessageSource message)
	{
		items = new HashMap<Integer, String>();
		items.put(FrameType.TYPE_OPTICAL, message.getMessage("frametype." + FrameType.TYPE_OPTICAL, null, locale));
		items.put(FrameType.TYPE_SUNGLASS, message.getMessage("frametype." + FrameType.TYPE_SUNGLASS, null, locale));
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
		log.info("FrameTypeEditor getAsText " + getValue());
		Integer typeId = (Integer)getValue();
		String typeName = items.get(typeId);
		return (typeName == null) ? "" : typeName;
	}

	@Override
	public void setAsText(String text)
	{
		log.info("FrameTypeEditor setAsText " + text);
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
