package sse.coeclipse.processor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;

import sse.coeclipse.core.CentralProcessor;

// 每个EditingOperationProcessor实例唯一对应一个CentralProcessor
// EditingOperationProcessor处理本地监听到的变化
public class EditingOperationProcessor {
	public CentralProcessor cp;


	public EditingOperationProcessor(CentralProcessor _cp)
	{
		cp = _cp;

	}


	public int processLocalOperation(DocumentEvent event,
			String strOriginalText)
	{
		//每次监听到的变化包含2次insert或delete操作，第2次可能为空
		EditingOperation[] eo = new EditingOperation[2];
		// 根据event特点，判断变化类型
		if (event.getLength() == 0 && event.getText().length() != 0)
		{
			// Insert Operation
			eo[0] = EditingOperation.CreateInsertOperation(event.getOffset(),
					event.getText());
			eo[1] = null;
		}
		else if (event.getLength() != 0 && event.getText().length() == 0)
		{
			// Delete Operation
			eo[0] = EditingOperation.CreateDeleteOperation(event.getOffset(),
					strOriginalText);
			eo[1] = null;
		}
		else
		{
			// Delete + Insert Operation
			eo[0] = EditingOperation.CreateDeleteOperation(event.getOffset(),
					strOriginalText);
			eo[1] = EditingOperation.CreateInsertOperation(event.getOffset(),
					event.getText());
		}

		for (int i = 0; i < eo.length; ++i)
		{
			if (eo[i] == null)
			{
				continue;
			}
			System.out.println(cp.docName); //debug
			System.out.println("type: " + eo[i].type); //debug
			System.out.println("position: " + eo[i].position); //debug
			System.out.println("length: " + eo[i].length); //debug
			System.out.println("content: " + eo[i].content); //debug
			
		}



		return 0;
	}

	// 该函数已被重构，弃用
	public int processRemoteOperation(final EditingOperation eo)
	{

			// System.out.println("Begin to process remote operation"); //debug

			cp.isRemotePlaying = true;

			if (eo.type == EditingOperation.INSERT)
			{
				try
				{
					cp.doc.replace(eo.position, 0, eo.content);
				}
				catch (BadLocationException e)
				{
					return -1;
				}
			}
			else if (eo.type == EditingOperation.DELETE)
			{
				try
				{
					cp.doc.replace(eo.position, eo.length, "");
				}
				catch (BadLocationException e)
				{
					return -1;
				}
			}
			else
			{
				return -1;
			}

			cp.isRemotePlaying = false;

			// System.out.println("End of processing remote operation"); //debug


		return 0;
	}
}
