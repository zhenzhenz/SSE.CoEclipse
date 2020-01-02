package sse.coeclipse.listeners;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.swt.custom.StyledText;

import sse.coeclipse.core.CentralProcessor;
import sse.coeclipse.core.CentralProcessorManager;
import sse.coeclipse.views.CoView;

// 完成对本地document变动的监听
// 每个document绑定一个CentralProcessor
public class CoDocumentListener implements IDocumentListener {
	CoView theView;
	CentralProcessor cp;

	StyledText st;


	public CoDocumentListener(CoView _view, CentralProcessor _cp,
			StyledText _st)
	{
		theView = _view;
		cp = _cp;
		st = _st;
	}

	// DocumentEvent为捕获的document变化
	public void documentAboutToBeChanged(DocumentEvent event)
	{
		// isRemotePlaying为资源锁，防止循环监听远端变更
		if (cp.isRemotePlaying == true)
		{
			return;
		}

		System.out.println("documentAboutToBeChanged"); //debug
		System.out.println(event.toString());

		String strTemp;

		if (event.getLength() != 0)
		{
			try
			{
				strTemp = cp.doc.get(event.getOffset(), event.getLength());
			}
			catch (BadLocationException e)
			{
				return;
			}
		}
		else
		{
			strTemp = "";
		}
		// 捕获的事件调用editingOperationProcessor处理
		cp.editingOperationProcessor.processLocalOperation(event, strTemp);
		for (String key : CentralProcessorManager.manager.keySet()) {
		    System.out.println(key + " ：" + CentralProcessorManager.manager.get(key).docName);
		}

		//System.out.println("document about to be changed over"); //debug
	}


	public void documentChanged(DocumentEvent event)
	{
		if (cp.isRemotePlaying == true)
		{
			return;
		}

		System.out.println("document changed over"); //debug

		cp.isTransitionPeriod = false;

		// System.out.println("documentChanged");
		// System.out.println(event.toString());
	}
}
