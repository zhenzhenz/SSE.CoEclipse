package sse.coeclipse.listeners;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

import sse.coeclipse.views.CoView;

public class WorkspacePartListener implements IPartListener {
	public CoView view;
	
	
	public boolean flag = false;
	
	
	public boolean isOpened = false;
	

	@Override
	public void partActivated(IWorkbenchPart part)
	{
		// TODO Auto-generated method stub
		
		if ( part instanceof IEditorPart)
		{
			System.out.println("partActivated");

			if ( flag == true )
			{
    			System.out.println("yes, editor!");
    		
    			final ITextEditor ed = (ITextEditor)(part.getAdapter(ITextEditor.class));
    			final IDocumentProvider documentProvider = ed.getDocumentProvider();
    			final IDocument doc = documentProvider.getDocument(ed.getEditorInput());
    			
    			//System.out.println(doc.get(3, 4));
    			ITextViewer viewer = (ITextViewer) (ed.getAdapter(ITextOperationTarget.class));
    			
    			IFile file = (IFile)(ed.getEditorInput().getAdapter(IFile.class));
    			
    			String docName;
    			docName = file.getLocation().toString();
    			
    			//String pro = "SampleApplication/";
    			//int pos = docName.indexOf(pro, 0);
    			//pos += pro.length();
    			//docName = docName.substring(pos, docName.length());
    			
    			System.out.println(docName);
    			
    			view.OpenDocument(docName);
    			
    			flag = false;
    			
    			isOpened = true;
			}
		}

	}

	@Override
	public void partBroughtToTop(IWorkbenchPart part)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void partClosed(IWorkbenchPart part)
	{
		if ( part instanceof IEditorPart )
		{
			//System.out.println(((IEditorPart)part).getTitle());
			
			if ( isOpened == true )
			{
				view.CloseDocument();				
				
				isOpened = false;
			}
		}
		
	}

	@Override
	public void partDeactivated(IWorkbenchPart part)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void partOpened(IWorkbenchPart part)
	{
		
		
		if ( part instanceof IEditorPart)
		{
			System.out.println("partOpened");
			
			//System.out.println("yes, editor!");
			flag = true;
		}		
	}
}
