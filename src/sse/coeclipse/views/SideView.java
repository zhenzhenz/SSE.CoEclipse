package sse.coeclipse.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.part.ViewPart;

public class SideView extends ViewPart implements Listener {
	
	public static final String ID = "SSE.CoEclipse.views.SideView";
	public Composite top = null;

	public SideView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		top = new Composite(parent, SWT.NONE);
		top.setLayout(null);
		top.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

}
