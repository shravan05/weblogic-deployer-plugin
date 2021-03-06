package org.hudsonci.plugins.deploy.weblogic;

import hudson.model.Action;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;

import java.util.List;

import org.hudsonci.plugins.deploy.weblogic.data.WebLogicDeployment;
import org.hudsonci.plugins.deploy.weblogic.data.WebLogicDeploymentStatus;


public class PrintingWebLogicDeploymentLastSuccessResultAction implements Action  {

	private WebLogicDeployment lastDeploymentSucessfull;
	
	/**
	 * 
	 */
	public PrintingWebLogicDeploymentLastSuccessResultAction(){
		super();
	}
	
	/**
	 * 
	 * @param project
	 */
	public PrintingWebLogicDeploymentLastSuccessResultAction(AbstractProject<?, ?> project){
		super();
		
		List<AbstractBuild<?, ?>> builds = (List<AbstractBuild<?, ?>>) project.getBuilds();
		for (AbstractBuild<?, ?> build : builds) {
			WatchingWeblogicDeploymentLogsAction deploymentAction = build.getAction(WatchingWeblogicDeploymentLogsAction.class);
			if(deploymentAction != null && WebLogicDeploymentStatus.SUCCEEDED.equals(deploymentAction.deploymentActionStatus)){
				lastDeploymentSucessfull = new WebLogicDeployment(build.getNumber(), build.getTime(), deploymentAction.getTarget());
				break;
			}
		}
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see hudson.model.Action#getDisplayName()
	 */
	public String getDisplayName() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see hudson.model.Action#getIconFileName()
	 */
	public String getIconFileName() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see hudson.model.Action#getUrlName()
	 */
	public String getUrlName() {
		return null;
	}
	
	/**
	 * 
	 * @return
	 */
	public WebLogicDeployment getLastDeploymentSucessfull() {
		return lastDeploymentSucessfull;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean hasAtLeastOneDeploymentSuccessfull(){
		return lastDeploymentSucessfull != null;
	}
	

}
