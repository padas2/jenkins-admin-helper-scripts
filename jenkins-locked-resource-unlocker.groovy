/* 
* - Groovy script has to be copied from here and run in Jenkins script console. 
* - Tried and tested on Jenkins version 2.150
* - DISCLAIMER : This script will unlock all locked resources. So think twice before you run this script.
*/

import org.jenkins.plugins.lockableresources.LockableResourcesManager
import org.jenkins.plugins.lockableresources.LockableResource

LockableResourcesManager lockableResourceManager = LockableResourcesManager.get()
List<LockableResource> LockableResource = lockableResourceManager.getResources()
for(LockableResource resource : LockableResource) {
	println "Is resource ${resource.toString()} locked ? ${resource.isLocked()}"
	if(resource.isLocked()) {
		println "Unlocking ${resource.toString()}"
		lockableResourceManager.unlockNames([resource.toString()], null, true)
	}	
}
