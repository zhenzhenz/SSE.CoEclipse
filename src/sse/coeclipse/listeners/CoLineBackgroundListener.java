package sse.coeclipse.listeners;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.swt.custom.LineBackgroundEvent;
import org.eclipse.swt.custom.LineBackgroundListener;
import org.eclipse.swt.custom.StyledText;

import sse.coeclipse.core.CentralProcessor;
import sse.coeclipse.views.CoView;


public class CoLineBackgroundListener implements LineBackgroundListener{
	CoView theView;
	CentralProcessor cp;

	StyledText st;


	public CoLineBackgroundListener(CoView _view, CentralProcessor _cp,
			StyledText _st)
	{
		theView = _view;
		cp = _cp;
		st = _st;
	}


	// public int a = 0;

	public void lineGetBackground(LineBackgroundEvent event)
	{
		// System.out.println("LineBackgroundEvent");

//		try
//		{
//			// a++;
//			// System.out.println("[[[" + a + "]]]");
//			cp.dalProcessor.awareness.drawLineBackground(st, event, -1);
//		}
//		catch (JavaModelException e)
//		{
//			e.printStackTrace();
//		}
	}

}
