job('example') {
    description('A very first sample of creating a job dsl')
    steps {
        batchFile('echo HelloWorld')
    }
}