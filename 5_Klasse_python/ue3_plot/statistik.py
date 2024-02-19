import subprocess
import datetime
import matplotlib.pyplot as plt

def parse_git_log(git_folder):
    """
    sends a git log to the command line and analyzes the data it gets

    :param git_folder: path to the directory of the git folder
    :return: times of the commits in this git repo
    """
    command = ['git', 'log', '--pretty=format:%ad', '--date=iso']
    process = subprocess.Popen(command, cwd=git_folder, stdout=subprocess.PIPE, stderr=subprocess.DEVNULL, universal_newlines=True)
    output, _ = process.communicate()
    commits = output.strip().split('\n')
    commit_times = [datetime.datetime.fromisoformat(commit) for commit in commits]
    return commit_times

def plot_commit_activity(commit_times):
    """
    generates a plot with two figures that give data about the git activity of a person

    :param commit_times: from the parse_git_log method to know when which commits happened
    :return: the plot as an image
    """
    weekdays = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']
    weekday_counts = [0] * 7
    hour_counts = [0] * 24

    for commit_time in commit_times:
        weekday_counts[commit_time.weekday()] += 1
        hour_counts[commit_time.hour] += 1

    fig, axes = plt.subplots(1, 2, figsize=(12, 6))
    title = 'Clemens Hodina, Commits: ' + str(sum(weekday_counts))
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

if __name__ == "__main__":
    import argparse

    parser = argparse.ArgumentParser(description="Git Statistics")
    parser.add_argument("git_folder", help="Path to the Git repository folder")
    parser.add_argument("-v", "--verbose", action="store_true", help="Verbose mode")
    parser.add_argument("-q", "--quiet", action="store_true", help="Quiet mode")
    parser.add_argument("--log", help="Log file path")
    args = parser.parse_args()

    if args.verbose:
        print("Verbose mode enabled")
    if args.quiet:
        print("Quiet mode enabled")

    commit_times = parse_git_log(args.git_folder)
    plot_commit_activity(commit_times)







