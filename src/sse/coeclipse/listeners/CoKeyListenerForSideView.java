package sse.coeclipse.listeners;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;

import sse.coeclipse.core.CentralProcessor;
import sse.coeclipse.views.CoView;


public class CoKeyListenerForSideView implements KeyListener{
	CoView theView;
	CentralProcessor cp;

	StyledText st;


	public CoKeyListenerForSideView(CoView _view, CentralProcessor _cp,
			StyledText _st)
	{
		theView = _view;
		cp = _cp;
		st = _st;
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
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
