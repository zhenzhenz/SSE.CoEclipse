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

import sse.coeclipse.core.CentralProcessorManager;
import sse.coeclipse.views.CoView;

public class WorkspacePartListener implements IPartListener {
	public CoView view;
	
	
	public boolean flag = false;
	
	
	public boolean isOpened = false;
	
	// 当document第一次被打开时，OpenDocument启动一个CentralProcessor
	// flag标记文件是否被打开
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
    			
    			ITextViewer viewer = (ITextViewer) (ed.getAdapter(ITextOperationTarget.class));
    			
    			IFile file = (IFile)(ed.getEditorInput().getAdapter(IFile.class));
    			
    			String docName;
    			docName = file.getFullPath().toString();
    			//docName = file.getLocation().toString();
    					
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
	
	//Document关闭时，从CentralProcessorManager中删除对应的CentralProcessor
	@Override
	public void partClosed(IWorkbenchPart part)
	{
		if ( part instanceof IEditorPart )
		{
			ITextEditor tempEd = (ITextEditor)(part.getAdapter(ITextEditor.class));
			String fileName = ((IFile)(tempEd.getEditorInput().getAdapter(IFile.class))).getFullPath().toString();
			//System.out.println(((IEditorPart)part).getTitle());
			//System.out.println(fileName);
			CentralProcessorManager.deleteCentralProcessor(fileName);
			
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
