package sse.coeclipse.processor;

import org.eclipse.jface.text.BadLocationException;

import sse.coeclipse.core.CentralProcessor;
import sse.coeclipse.core.CentralProcessorManager;

// 响应远端代码变化
public class RemoteOperationProcessor {
	// filePath:文件名，EditingOperation:对应的操作
	public static int processRemoteOperation(String filePath, EditingOperation eo)
	{
		
		CentralProcessor cp = CentralProcessorManager.findCentralProcessor(filePath);
		if (cp == null) {
			return -1;
		}

		System.out.println("Begin to process remote operation"); //debug
		
		// isRemotePlaying为资源锁，防止本地listener循环监听远端变更
		cp.isRemotePlaying = true;

		if (eo.type == EditingOperation.INSERT) // 插入
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
		else if (eo.type == EditingOperation.DELETE)  // 删除
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

		System.out.println("End of processing remote operation"); //debug

		return 0;
	}
}
