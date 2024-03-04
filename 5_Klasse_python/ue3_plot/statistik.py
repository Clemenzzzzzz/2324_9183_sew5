import logging
import subprocess
import datetime
import sys
from logging.handlers import RotatingFileHandler

import matplotlib.pyplot as plt






def setup_logging(verbose, quiet):
    """
    configures the parameters for logging

    :param verbose: if logging should be everything
    :param quiet: if logging should be as quiet as possible
    :return:
    """
    handler = logging.handlers.RotatingFileHandler("create_class.log", maxBytes=10000, backupCount=5)
    formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(message)s')
    handler.setFormatter(formatter)

    stream_handler = logging.StreamHandler()
    stream_handler.setFormatter(formatter)

    if verbose and quiet:
        raise ValueError("Verbose und Quiet können nicht gleichzeitig aktiviert sein.")

    if verbose:
        logger.setLevel(logging.DEBUG)
    elif quiet:
        logger.setLevel(logging.WARNING)
    else:
        logger.setLevel(logging.INFO)

    logger.addHandler(handler)
    logger.addHandler(stream_handler)
    #logging.basicConfig(filename=logfile, level=log_level, format='%(asctime)s - %(levelname)s - %(message)s')

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
    logger.info("Commit times read!")
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
    axes[0].grid(True)
    logger.info("left graphic made")

    axes[1].scatter(range(24), hour_counts)
    axes[1].set_title('Commits nach Uhrzeit')
    axes[1].set_xlabel('Stunde')
    axes[1].set_ylabel('Commits')
    axes[1].grid(True)
    logger.info("right graphic made")

    plt.tight_layout()
    #plt.grid(True)
    plt.savefig('git_stats_hodina.png')
    plt.show()
    logger.info("graph can be seen")


logger = logging.getLogger(__name__)
if __name__ == "__main__":
    import argparse
    parser = argparse.ArgumentParser(description="Git Statistics")
    parser.add_argument("git_folder", help="Path to the Git repository folder")
    parser.add_argument("-v", "--verbose", action="store_true", help="Verbose mode")
    parser.add_argument("-q", "--quiet", action="store_true", help="Quiet mode")
    #parser.add_argument("-l", "--logfile", help="Log file path")
    args = parser.parse_args()

    logging.info("Script started!", args)

    setup_logging(args.verbose, args.quiet) #args.logfile,

    commit_times = parse_git_log(args.git_folder)
    plot_commit_activity(commit_times)
    logging.info("Script finished succesfully!")







