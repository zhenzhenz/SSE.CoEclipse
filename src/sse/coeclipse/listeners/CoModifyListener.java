package sse.coeclipse.listeners;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

import sse.coeclipse.core.CentralProcessor;
import sse.coeclipse.views.CoView;



public class CoModifyListener implements ModifyListener{
	CoView theView;
	CentralProcessor cp;

	StyledText st;


	public CoModifyListener(CoView _view, CentralProcessor _cp, StyledText _st)
	{
		theView = _view;
		cp = _cp;
		st = _st;
	}


	public void modifyText(ModifyEvent e)
	{
//		if (cp.dalProcessor.isDebugingMode == true)
//		{
//			return;
//		}
//
//		//System.out.println("modifyText"); //debug
//
//		try
//		{
//			JavaCore.getWorkingCopies(null)[0].save(null, false);
//			st.redraw();
//			cp.dalProcessor.awareness.updateRCPTable(theView);
//
//		}
//		catch (Exception e1)
//		{
//			e1.printStackTrace();
//		}
	}

}
