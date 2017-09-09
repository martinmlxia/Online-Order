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
import com.conant.order.vo.LensTreat;

public class LensTreatEditor extends PropertyEditorSupport{
	private static final Logger log = Logger.getLogger(
			"LensTreatEditor", Logger.DEBUG, true);
	private final Map<Integer, String> items;
	public LensTreatEditor (Locale locale, MessageSource message){
		items = new HashMap<Integer, String>();
		items.put(LensTreat.TREAT_UNC, message.getMessage("treatment.treat." + LensTreat.TREAT_UNC, null, locale));
		items.put(LensTreat.TREAT_HC, message.getMessage("treatment.treat." + LensTreat.TREAT_HC, null, locale));
		items.put(LensTreat.TREAT_THC, message.getMessage("treatment.treat." + LensTreat.TREAT_THC, null, locale));
		items.put(LensTreat.TREAT_HMC, message.getMessage("treatment.treat." + LensTreat.TREAT_HMC, null, locale));
		items.put(LensTreat.TREAT_SHMC, message.getMessage("treatment.treat." + LensTreat.TREAT_SHMC, null, locale));
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
		log.info("LensTreatEditor getAsText " + getValue());
		Integer typeId = (Integer)getValue();
		String typeName = items.get(typeId);
		return (typeName == null) ? "" : typeName;
	}
	@Override
	public void setAsText(String text)
	{
		log.info("LensTreatEditor setAsText " + text);
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
