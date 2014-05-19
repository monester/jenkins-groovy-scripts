// enter label to remove
def remove_label_name="test-label1"
  
def labelsList = []
def removeList = []

for (slave in jenkins.model.Jenkins.instance.slaves) {
  def oldLabelName = slave.getLabelString().tokenize(' ')
  if(remove_label_name == ""){
    labelsList += oldLabelName
  } else {
    def newLabelName = []
    for(i in oldLabelName) {
      if (i != remove_label_name) {
        newLabelName += i
      } else {
        removeList += slave.name
      }
    }
    slave.setLabelString(newLabelName.join(' '))
    labelsList += newLabelName
  }
}

println("Labels list:\n" + labelsList.unique().sort()+"\n\n")
if(remove_label_name != "") {
  println("*****\nRemoved label \""+remove_label_name+"\" from hosts:\n"+removeList+"\n*****")
}
