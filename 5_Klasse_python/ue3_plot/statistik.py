#from subprocess import Popen, PIPE

#GIT_COMMIT_FIELDS = ['id', 'author_name', 'author_email', 'date', 'message']
#GIT_LOG_FORMAT = ['%H', '%an', '%ae', '%ad', '%s']
#GIT_LOG_FORMAT = '%x1f'.join(GIT_LOG_FORMAT) + '%x1e'
#p = Popen('git log --format="%s"' % GIT_LOG_FORMAT, shell=True, stdout=PIPE)
#(log, _) = p.communicate()
#log = log.strip('\n\x1e').split("\x1e")
#log = [row.strip().split("\x1f") for row in log]
#log = [dict(zip(GIT_COMMIT_FIELDS, row)) for row in log]

import subprocess
import datetime
import matplotlib.pyplot as plt

def parse_git_log(git_folder):
    command = ['git', 'log', '--pretty=format:%ad', '--date=iso']
    process = subprocess.Popen(command, cwd=git_folder, stdout=subprocess.PIPE, stderr=subprocess.DEVNULL, universal_newlines=True)
    output, _ = process.communicate()
    commits = output.strip().split('\n')
    commit_times = [datetime.datetime.fromisoformat(commit) for commit in commits]
    return commit_times










