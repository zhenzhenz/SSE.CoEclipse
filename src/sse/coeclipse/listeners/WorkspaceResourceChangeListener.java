package sse.coeclipse.listeners;

import java.io.IOException;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;

//检测资源的变换
public class WorkspaceResourceChangeListener implements IResourceChangeListener {

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		// TODO Auto-generated method stub
		if (event.getType() != IResourceChangeEvent.POST_CHANGE)
		{
			return;
		}
		
		IResourceDelta rootDelta = event.getDelta();
		// 分别处理三类资源变化
		IResourceDeltaVisitor visitor = new IResourceDeltaVisitor()
		{
			public boolean visit(IResourceDelta delta)
			{
				if (delta.getKind() == IResourceDelta.CHANGED)
				{
					if ((delta.getFlags() & IResourceDelta.CONTENT) == 0)
					{
						return true;
					}
					
					IResource resource = delta.getResource();
					System.out.println("CHANGED");
					String path = resource.getLocation().toString();
					System.out.println(path);
					
				}
				else if (delta.getKind() == IResourceDelta.ADDED)
				{
					
					IResource resource = delta.getResource();
					String path = resource.getLocation().toString();
					System.out.println("ADDED");
					System.out.println(path);
						
				}
				else if (delta.getKind() == IResourceDelta.REMOVED)
				{
					
					IResource resource = delta.getResource();
					
					String path = resource.getLocation().toString();
					System.out.println("REMOVED");
					System.out.println(path);
					
				}
				else
				{
					return true;
				}

				return true;
			}
		};

		try
		{
			rootDelta.accept(visitor);
		}
		catch (CoreException e)
		{
			// open error dialog with syncExec or print to plugin log file
		}
		
        
        System.out.println("Resource change event"); 
	}
	

}
