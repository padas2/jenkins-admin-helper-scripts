/* 
* - Groovy script has to be copied from here and run in Jenkins script console. 
* - Env variables values have been hardcoded as of now. 
* - Tried and tested on Jenkins version 2.150
*/
  
import hudson.slaves.EnvironmentVariablesNodeProperty  
import jenkins.model.Jenkins  
import java.util.regex.Pattern  
import java.util.regex.Matcher  
  
class KeyValue {  
    String key  
    String value  
}  
  
def getKeyValueOfEnvVariable(String env) {  
    KeyValue keyValue = new KeyValue()  
    Pattern pattern = Pattern.compile(": *");  
    Matcher matcher = pattern.matcher(env);  
    if (matcher.find()) {  
        keyValue.key = env.substring(0, matcher.start());  
        keyValue.value = env.substring(matcher.end());  
    }  
    return keyValue  
}  
  
instance = Jenkins.getInstance()  
globalNodeProperties = instance.getGlobalNodeProperties()  
envVarsNodePropertyList = globalNodeProperties.getAll(EnvironmentVariablesNodeProperty.class)  
  
newEnvVarsNodeProperty = null  
envVars = null  
  
if (envVarsNodePropertyList == null || envVarsNodePropertyList.size() == 0) {  
    newEnvVarsNodeProperty = new EnvironmentVariablesNodeProperty();  
    globalNodeProperties.add(newEnvVarsNodeProperty)  
    envVars = newEnvVarsNodeProperty.getEnvVars()  
} else {  
    envVars = envVarsNodePropertyList.get(0).getEnvVars()  
}  
  
envsToBeAdded = "[A:B, C:D, E:F, G:H]"  

envsAsArray = envsToBeAdded.substring(1, envsToBeAdded.length() - 1).tokenize(',')  
for (String env: envsAsArray) {  
    def keyValue = getKeyValueOfEnvVariable(env)  
    String key = keyValue.key  
    String value = keyValue.value  
    if(envVars.get(key))  
        println "Not adding environment variable with ${key} as it already exists"  
    else {  
         println "Adding environment variable with key ${key} and value : ${value}"  
         envVars.put(key.trim(), value.trim())  
    }  
  
}  
instance.save() 
