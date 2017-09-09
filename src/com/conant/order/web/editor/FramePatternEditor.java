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
import com.conant.order.vo.FramePattern;

public class FramePatternEditor extends PropertyEditorSupport{
	private static final Logger log = Logger.getLogger(
			"FramePatternEditor", Logger.DEBUG, true);
	private final Map<Integer, String> items;
	public FramePatternEditor(Locale locale, MessageSource message){
		items = new HashMap<Integer, String>();
		items.put(FramePattern.DRILL_RIMLESS, message.getMessage("framepattern." + FramePattern.DRILL_RIMLESS, null, locale));
		items.put(FramePattern.SEMI_RINLESS, message.getMessage("framepattern." + FramePattern.SEMI_RINLESS, null, locale));
		items.put(FramePattern.METAL, message.getMessage("framepattern." + FramePattern.METAL, null, locale));
		items.put(FramePattern.ZYL, message.getMessage("framepattern." + FramePattern.ZYL, null, locale));
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
		log.info("FramePatternEditor getAsText " + getValue());
		Integer typeId = (Integer)getValue();
		String typeName = items.get(typeId);
		return (typeName == null) ? "" : typeName;
	}
	@Override
	public void setAsText(String text)
	{
		log.info("FramePatternEditor setAsText " + text);
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
