pipelineJob('DSL-Tryout-Job') {
    disabled(true)
    parameters {
        stringParam('nodeName', 'Target Node the job is builded')
        booleanParam('Clean Project')
    }
}