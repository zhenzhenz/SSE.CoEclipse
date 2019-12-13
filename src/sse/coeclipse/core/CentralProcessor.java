package sse.coeclipse.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.custom.StyledText;

import sse.coeclipse.views.CoView;
import sse.coeclipse.views.SideView;



public class CentralProcessor {
	public CoView view;
	public SideView sideView;
	
	public StyledText st;
	public ISelectionProvider sel;

	//public SessionManager sm;
	//public OTEngine ot;
	//public MessageProcessor mp;
	//public MessageReceiver mr;

	// Sub processors

	// public RCPEditor editor = null;

	public IDocument doc = null;
	//public EditingOperationProcessor editingOperationProcessor;
	//public DALProcessor dalProcessor;

	// Status of the collaboration session
	// Used for synchronization control
	public byte[] lockRemotePlayingStatus;
	public boolean isRemotePlaying;

	public byte[] lockCentral;
	public boolean isTransitionPeriod;
	public int iDALTagForNextEditingOperation;

	//public RemoteOperationProcessingTimer timer;

	public String docName;	

	public int start()
	{
		lockRemotePlayingStatus = new byte[0];
		isRemotePlaying = false;

		lockCentral = new byte[0];
		isTransitionPeriod = false;
		
		//sm = new SessionManager(view);
		
//		if (sm.joinSession("155.69.144.156", 10029, docName, view.pm.projectSiteID) == 0)
//		{
//			//ot = new OTEngine(sm.sid);
//
//			mp = new MessageProcessor(this, sm, ot);
//			timer = new RemoteOperationProcessingTimer(this);
//			timer.startTimer();

//			mr = new MessageReceiver(sm, mp);

//			ot.startEngine();

//			mr.startThread();

			// Sub processors

//			editingOperationProcessor = new EditingOperationProcessor(this);
//			dalProcessor = new DALProcessor(this);

//			return 0;
//		}
//		else
//		{

			return -1;
//		}
	}


	public int stop()
	{
		//sm.quitSession();

		//mr.stopThread();

		//ot.stopEngine();

		return 0;
	}


//	public int sendMessageGroup(MessageGroup mg)
//	{
//		SMMessage smm = new SMMessage();
//
//		smm.type = SMMessage.PROPAGATE;
//		smm.sid = sm.sid;
//
//		try
//		{
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			ObjectOutputStream oos = new ObjectOutputStream(baos);
//
//			oos.writeObject(mg);
//			oos.close();
//
//			smm.content = baos.toByteArray();
//			smm.size = smm.content.length;
//		}
//		catch (IOException e)
//		{
//			return -1;
//		}
//
//		sm.send(smm);
//
//		return 0;
//	}
//
//
//	public int processMessageGroup(MessageGroup mg)
//	{
//		for (int i = 0; i < mg.list.size(); ++i)
//		{
//			processMessage(mg.list.get(i));
//		}
//
//		return 0;
//	}
//
//
//	public int processMessage(Message msg)
//	{
//		if (msg.type == Message.INVALID)
//		{
//			return -1;
//		}
//
//		if (msg.type == Message.EDITING)
//		{
//			try
//			{
//				ByteArrayInputStream bais = new ByteArrayInputStream(
//						msg.content);
//				ObjectInputStream ois = new ObjectInputStream(bais);
//
//				EditingOperation eo = (EditingOperation) (ois.readObject());
//
//				ois.close();
//
//				editingOperationProcessor.processRemoteOperation(eo);
//			}
//			catch (IOException e)
//			{
//				return -1;
//			}
//			catch (ClassNotFoundException e)
//			{
//				return -1;
//			}
//		}
//
//		if (msg.type == Message.DAL)
//		{
//			try
//			{
//				ByteArrayInputStream bais = new ByteArrayInputStream(
//						msg.content);
//				ObjectInputStream ois = new ObjectInputStream(bais);
//
//				DALOperation dalo = (DALOperation) (ois.readObject());
//
//				ois.close();
//
//				dalProcessor.processRemoteDALOperation(dalo);
//			}
//			catch (IOException e)
//			{
//				return -1;
//			}
//			catch (ClassNotFoundException e)
//			{
//				return -1;
//			}
//		}
//
//		return 0;
//	}
//
//
//	public int replayEditingOperation(EditingOperation eo)
//	{
//
//		return 0;
//	}


}
