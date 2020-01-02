package sse.coeclipse.processor;

import java.util.ArrayList;

// 定义 insert delete 事件
// 每个事件包含type，position，length，content四个属性
@SuppressWarnings("serial")
public class EditingOperation implements java.io.Serializable
{
	public static int INSERT = 1;
	public static int DELETE = 2;
	public static int INVALID = -1;
	
	public int type;
	public int sid;
	
	public int position;
	public int length;
	public String content;

	public ArrayList<Integer> vec;
	
	public int iDALTag;
	
	
	public EditingOperation()
	{
		type = INVALID;
		vec = new ArrayList<Integer>();
	}
	
	public static EditingOperation CreateInsertOperation(int position, String content)
	{
		EditingOperation eo = new EditingOperation();
		
		eo.type = INSERT;
		eo.position = position;
		eo.length = content.length();
		eo.content = content;
		
		
		return eo;
	}
	
	public static EditingOperation CreateDeleteOperation(int position, String content)
	{
		EditingOperation eo = new EditingOperation();
		
		eo.type = DELETE;
		eo.position = position;
		eo.length = content.length();
		eo.content = content;
		
		return eo;
	}
	

}
