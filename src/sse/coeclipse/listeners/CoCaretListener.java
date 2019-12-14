package sse.coeclipse.listeners;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.custom.CaretEvent;
import org.eclipse.swt.custom.CaretListener;
import org.eclipse.swt.custom.StyledText;

import sse.coeclipse.core.CentralProcessor;
import sse.coeclipse.views.CoView;

public class CoCaretListener implements CaretListener{
	CoView theView;
	CentralProcessor cp;
	ISelectionProvider sel;
	StyledText st;

	public CoCaretListener(CoView _view, CentralProcessor _cp, StyledText _st, ISelectionProvider _sel)
	{
		theView = _view;
		cp = _cp;
		st = _st;
		sel = _sel;
	}
	

	@Override
	public void caretMoved(CaretEvent event)
	{
		//System.out.println("selectionChanged"); //debug
		int start = ((ITextSelection) (sel.getSelection())).getOffset();
		int length = ((ITextSelection) (sel.getSelection())).getLength();
		
		String strEP = "Selection\r\n Offset:";
		strEP += start;
		strEP += "\r\nLength:";
		strEP += length;
		strEP += "\r\n";
		
		theView.stCaretPosition.setText(strEP);
	}


}
