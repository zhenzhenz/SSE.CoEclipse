package sse.coeclipse.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.custom.StyledText;

import sse.coeclipse.processor.EditingOperationProcessor;
import sse.coeclipse.views.CoView;
import sse.coeclipse.views.SideView;


// 每个打开的document对应一个CentralProcessor，CentralProcessor将document和其listener以及EditingOperationProcessor绑定在一起
public class CentralProcessor {
	public CoView view;
	public SideView sideView;
	
	public StyledText st;
	public ISelectionProvider sel;

	public IDocument doc = null;
	public EditingOperationProcessor editingOperationProcessor;

	public byte[] lockRemotePlayingStatus;
	public boolean isRemotePlaying;

	public byte[] lockCentral;
	public boolean isTransitionPeriod;
	public int iDALTagForNextEditingOperation;

	public String docName;	

	public int start()
	{
		lockRemotePlayingStatus = new byte[0];
		isRemotePlaying = false;

		lockCentral = new byte[0];
		isTransitionPeriod = false;
		// EditingOperationProcessor处理listener监听到的事件
		editingOperationProcessor = new EditingOperationProcessor(this);
		
		return 0;
	}


	public int stop()
	{
		return 0;
	}

}
