package sse.coeclipse.core;

import org.eclipse.jdt.internal.ui.javaeditor.CompilationUnitEditor;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;


import sse.coeclipse.listeners.CoDocumentListener;

import sse.coeclipse.views.CoView;
import sse.coeclipse.views.SideView;

public class Launcher {
	public CentralProcessor cp = null;

	CoView view;
	SideView sideView;

	public boolean applyDAL;

	public ITextViewer viewer;

	public StyledText st;
	public ISelectionProvider sel;

	public String docName;

	public Launcher(CoView _view, SideView _sideView, String _docName)
	{
		view = _view;
		sideView = _sideView;
		docName = _docName;
	}
	
	// Launcher启动一个CentralProcessor，并为CentralProcessor挂载DocumentListener
	public int start()
	{
		final ITextEditor ed = (ITextEditor) (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor());
		
		final IDocumentProvider documentProvider = ed.getDocumentProvider();
		final IDocument doc = documentProvider.getDocument(ed.getEditorInput());
		
		viewer = (ITextViewer) (ed.getAdapter(ITextOperationTarget.class));
		st = viewer.getTextWidget();
		
		// 此处关于st的获取方式暂且略过，用不到
		//CompilationUnitEditor cue = (CompilationUnitEditor) ed;
		//st = cue.getViewer().getTextWidget();
		sel = ed.getSelectionProvider();

		cp = new CentralProcessor();

		cp.st = st;
		cp.sel = sel;

		cp.view = view;
		cp.sideView = sideView;

		cp.doc = doc;

		CoDocumentListener listener = new CoDocumentListener(view, cp, st);

		doc.addDocumentListener(listener);

		cp.docName = docName;
		// 将新建的CentralProcessor加入CentralProcessorManager中
		CentralProcessorManager.addCentralProcessor(docName, cp); 
		cp.start();
		
		return 0;
	}
	
	
	
	public int stop()
	{
		cp.stop();
		cp = null;
		
		return 0;
	}

}
