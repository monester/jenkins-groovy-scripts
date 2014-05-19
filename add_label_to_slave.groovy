import hudson.model.*

// get current thread / Executor
def thr = Thread.currentThread()
def currentBuild = Thread.currentThread().executable 
// get current build
def build = thr?.executable

def resolver = build.buildVariableResolver
def node_name = resolver.resolve("NODE_NAME")
def label_name = resolver.resolve("LABEL_NAME")

println("\nAdded to node : <" + node_name + "> label: <" + label_name + ">\n\n")

for (slave in jenkins.model.Jenkins.instance.slaves) {
    if(slave.name == node_name) {
        oldLabelName = slave.getLabelString()
        if (! oldLabelName.contains(label_name)) {
            newLabelName = oldLabelName + " " + label_name
            slave.setLabelString(newLabelName)
        }
    }
}

currentBuild.setDescription("<a href=\"/label/" + label_name + "/?\">" + label_name + "</a>")
