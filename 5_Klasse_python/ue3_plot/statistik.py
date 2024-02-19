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

def plot_commit_activity(commit_times):
    weekdays = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
    weekday_counts = [0] * 7
    hour_counts = [0] * 24

    for commit_time in commit_times:
        weekday_counts[commit_time.weekday()] += 1
        hour_counts[commit_time.hour] += 1

    fig, axes = plt.subplots(1, 2, figsize=(12, 6))
    title = 'Clemens Hodina, Commits: ' + str(weekday_counts.count())
    fig.suptitle(title)
    axes[0].scatter(weekdays, weekday_counts)
    axes[0].set_title('Commits nach Wochentag')
    axes[0].set_xlabel('Wochentag')
    axes[0].set_ylabel('Commits')

    axes[1].scatter(range(24), hour_counts)
    axes[1].set_title('Commits nach Uhrzeit')
    axes[1].set_xlabel('Stunde')
    axes[1].set_ylabel('Commits')

    plt.tight_layout()
    plt.savefig('git_stats_hodina.png')
    plt.show()










