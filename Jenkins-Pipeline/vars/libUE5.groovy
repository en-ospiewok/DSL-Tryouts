import org.jenkinsci.plugins.p4.workspace.ManualWorkspaceImpl

List<String> getChangeList() {
    println 'Gathering SCM changes'
    List<String> results = []
    for(int i = 0; i < currentBuild.changeSets.size(); i++) {
        for (int j = 0; j < currentBuild.changeSets[i].items.size(); j++) {
            def cl = currentBuild.changeSets[i].items[j]
            results.push("@${cl.author.getFullName()} - *${cl.commitId.take(6)} - ```${cl.msg}```")
        }
    }

    return results
}

def sync() {
    println 'sync p4'
    ws(env.workspaceRoot) {
        p4sync(
            charset: 'none',
            credential: 'p4Cred', // where is this from?
            changelog: true,
            poll: false,
            populate: syncOnly(
                delete: true,
                modtime: false,
                parallel: [enable: false, minbytes: '1024', minfiles: '1', threads: '4'],
                pin: '',
                quiet: true,
                replace: true,
                tidy: false
            ),
            source: streamSource('//streams/st-main')
        )
    }
}
