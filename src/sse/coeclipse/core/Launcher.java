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

import sse.coeclipse.views.CoView;
import sse.coeclipse.views.SideView;;

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
	
	
	public int start()
	{
		final ITextEditor ed = (ITextEditor) (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor());
		
		final IDocumentProvider documentProvider = ed.getDocumentProvider();
		final IDocument doc = documentProvider.getDocument(ed.getEditorInput());
		
		viewer = (ITextViewer) (ed.getAdapter(ITextOperationTarget.class));

		CompilationUnitEditor cue = (CompilationUnitEditor) ed;
		st = cue.getViewer().getTextWidget();
		sel = ed.getSelectionProvider();
		
		// new-start
		IAnnotationModel model = cue.getViewer().getAnnotationModel();

		// new-end

		cp = new CentralProcessor();

		cp.st = st;
		cp.sel = sel;

		cp.view = view;
		cp.sideView = sideView;

		cp.doc = doc;

		RCPDocumentListener listener = new RCPDocumentListener(view, cp, st);

		doc.addDocumentListener(listener);
		// ed.getEditorSite().getSelectionProvider().addSelectionChangedListener(sel);

		RCPVerifyKeyListener verifyKeyListener = new RCPVerifyKeyListener(view,
				cp, st, sel);
		st.addVerifyKeyListener(verifyKeyListener);

		RCPModifyListener modifyListener = new RCPModifyListener(view, cp, st);
		st.addModifyListener(modifyListener);

		cp.docName = docName;
		cp.start();

		cp.dalProcessor.isDebugingMode = applyDAL ? false : true;

		RCPLineBackgroundListener lineBackgroundListener = new RCPLineBackgroundListener(
				view, cp, st);
		st.addLineBackgroundListener(lineBackgroundListener);

		st.addKeyListener(new RCPKeyListenerForSideView(view, cp, st));
		st.addMouseWheelListener(new RCPMouseWheelListenerForSideView(view, cp,
				st));

		RCPCaretListener caretListener = new RCPCaretListener(view, cp, st, sel);
		st.addCaretListener(caretListener);

		// System.out.println("started\r\n"); //debug

		view.label.setText("Session\r\nstarted.\r\nSite ID: " + cp.sm.sid);
		
		return 0;
	}
	
	
	
	public int stop()
	{
		cp.stop();
		cp = null;
		
		return 0;
	}

}
