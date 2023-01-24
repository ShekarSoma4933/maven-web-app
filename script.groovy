def buildApp(){
    echo "this is build stage"
}

def testStage(){
    echo "this is test stage"
}

def pushStage(){
    echo "this is push stage"
}

def deployStage(){
    echo "this is deploy stage ${params.VERSION}"
}
