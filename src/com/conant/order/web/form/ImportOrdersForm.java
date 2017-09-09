/**
 * ImportOrdersForm.java
 * 2009-4-4
 * Administrator
 */
package com.conant.order.web.form;

import org.springframework.web.multipart.MultipartFile;

/**
 * 批量导入订单Form。
 * @author Administrator
 * 
 */
public class ImportOrdersForm
{
	private MultipartFile file;

	public void setFile(MultipartFile file)
	{
		this.file = file;
	}

	public MultipartFile getFile()
	{
		return file;
	}
}
