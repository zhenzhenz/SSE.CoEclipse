package sse.coeclipse.listeners;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.swt.custom.StyledText;

import sse.coeclipse.core.CentralProcessor;
import sse.coeclipse.views.CoView;

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


	public void documentAboutToBeChanged(DocumentEvent event)
	{
		// st.setStyleRange(null);

		if (cp.isRemotePlaying == true)
		{
			return;
		}

		System.out.println("documentAboutToBeChanged"); //debug
		// System.out.println(event.toString());

		synchronized (cp.lockCentral)
		{
			// System.out.println("#1"); //debug

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

//			cp.editingOperationProcessor.processLocalOperation(event, strTemp,
//					cp.iDALTagForNextEditingOperation);

			
			// cp.isTransitionPeriod = false;

			// cp.lockCentral.notify();

			// System.out.println("#2"); //debug
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
