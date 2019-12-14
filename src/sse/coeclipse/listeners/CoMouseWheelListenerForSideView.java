package sse.coeclipse.listeners;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;

import sse.coeclipse.core.CentralProcessor;
import sse.coeclipse.views.CoView;



public class CoMouseWheelListenerForSideView implements MouseWheelListener{
	CoView theView;
	CentralProcessor cp;

	StyledText st;


	public CoMouseWheelListenerForSideView(CoView _view,
			CentralProcessor _cp, StyledText _st)
	{
		theView = _view;
		cp = _cp;
		st = _st;
	}


	@Override
	public void mouseScrolled(MouseEvent e)
	{
		// TODO Auto-generated method stub
//		try
//		{
//			cp.dalProcessor.awareness.drawSideView(st);
//		}
//		catch (JavaModelException e1)
//		{
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
	}


}
