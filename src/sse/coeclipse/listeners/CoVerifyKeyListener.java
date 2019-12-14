package sse.coeclipse.listeners;

import java.util.ArrayList;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.MessageBox;

import sse.coeclipse.core.CentralProcessor;
import sse.coeclipse.views.CoView;


public class CoVerifyKeyListener implements VerifyKeyListener {
	CoView theView;
	CentralProcessor cp;

	StyledText st;

	ISelectionProvider sel;


	public CoVerifyKeyListener(CoView _view, CentralProcessor _cp,
			StyledText _st, ISelectionProvider _sel)
	{
		theView = _view;
		cp = _cp;
		st = _st;
		sel = _sel;
	}


	public void verifyKey(VerifyEvent event)
	{
//		if (cp.dalProcessor.isDebugingMode == true)
//		{
//			cp.isTransitionPeriod = true;
//			return;
//		}

		// System.out.println(event.toString());

		if (event.keyCode >= 16777217 && event.keyCode <= 16777222)
		{
			// up, down, left, right, page up, page down
			return;
		}
		else if (event.keyCode == 262144 || event.keyCode == 65536
				|| event.keyCode == 131072)
		{
			// ctrl, alt, shift
			return;
		}
		else if (event.keyCode == 99)
		{
			if (event.stateMask == 262144)
			{
				return;
			}
		}

		cp.isTransitionPeriod = false;

		// Cannot be set to false, because DAL processor may set it to true
		// cp.isRequireDALLockInNextEditingOperation = false;

		// System.out.println("start to verify");

		synchronized (cp.lockCentral)
		{
			// System.out.println("#3");

			int start = ((ITextSelection) (sel.getSelection())).getOffset();
			int length = ((ITextSelection) (sel.getSelection())).getLength();
			//int sid = cp.sm.sid;

			int result = -1;

			//result = cp.dalProcessor.PermissionCheck(start, length, sid);

			theView.stNotificationArea.setText("Notification Area");

			switch (result)
			{
				case 1:
				{
					//cp.dalProcessor.releaseLockForLocalSite(sid, -1);

					event.doit = true;
					cp.isTransitionPeriod = true;
				}
					break;

				case 2:
				{
					event.doit = false;
					cp.isTransitionPeriod = false;

					MessageBox messageBox = new MessageBox(theView.label
							.getShell(), SWT.OK | SWT.ICON_WARNING);
					messageBox
							.setMessage("Your editing operation involves more than one basic regions.");
					messageBox.setText("Operation Denied");
					messageBox.open();
				}
					break;

				case 3:
				{
					event.doit = true;
					cp.isTransitionPeriod = true;
				}
					break;

				case 4:
				{
					event.doit = false;
					cp.isTransitionPeriod = false;

					/* Option 1 - basic approach */

					if (theView.radioPopupNotification.getSelection() == true)
					{
						MessageBox messageBox = new MessageBox(theView.label
								.getShell(), SWT.OK | SWT.ICON_WARNING);
						messageBox
								.setMessage("You attempt to edit a region locked by other programmer(s).\r\nEditing denied.");
						messageBox.setText("Editing denied");
						messageBox.open();
					}
					else
					{
						theView.stNotificationArea
								.setText("You attempt to edit a region locked by other programmer(s).\r\nEditing denied.");
					}

				}
					break;

				case 5:
				{
//					cp.dalProcessor.releaseLockForLocalSite(sid, -1);
//					st.redraw();
//					cp.dalProcessor.awareness.updateRCPTable(theView);
//
//					ArrayList<BasicRegion> w = cp.dalProcessor.supporter
//							.GetWorkingRegion(start, length);
//					ArrayList<BasicRegion> ds = null;
//					if (w.size() != 0)
//					{
//						try
//						{
//							ds = cp.dalProcessor.supporter.GetDepRegionSet(w
//									.get(0));
//						}
//						catch (JavaModelException e)
//						{
//							e.printStackTrace();
//						}
//					}
//					cp.dalProcessor.dalData.grantLocks(w.get(0), ds, sid);

					event.doit = true;
					cp.isTransitionPeriod = true;
				}
					break;

				case -1:
				{
					event.doit = false;
					cp.isTransitionPeriod = false;

					MessageBox messageBox = new MessageBox(theView.label
							.getShell(), SWT.OK | SWT.ICON_ERROR);

					messageBox.setMessage("RCPEclipse error.");
					messageBox.setText("Sorry");
					messageBox.open();
				}
					break;
			}

			// System.out.println("#4");
		}
	}

}
