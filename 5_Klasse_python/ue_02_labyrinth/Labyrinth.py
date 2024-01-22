import argparse
import time


def read_lab(file_path):
    """
    reads a labyrinth from a file

    :param file_path: path of the file that needs to be read
    :return: a list of strings of the labyrinth
    """
    lab = []
    with open(file_path, 'r') as file:
        # Zeilenweise auslesen
        for line in file.readlines():
            lab.append(line.strip())
    return lab


def print_lab(lab):
    """
    prints a string that is a labyrinth

    :param lab: thw lab to print out
    :return: -
    """
    for line in lab:
        print(line)

def suchen(zeile, spalte, lab):
    """
    searches for an exit in labyrinths

    :param zeile: start on x-axis
    :param spalte: start on y-axis
    :param lab: lab to search in
    :return: if an exit could be found in this labyrinth
    """
    if lab[zeile][spalte] == 'A':
        return True
    lab = [list(row) for row in lab]
    lab[zeile][spalte] = 'X'
    #result_list = ["".join(row) for row in lab]
    #print_lab(result_list)
    if lab[zeile + 1][spalte] != '#' and lab[zeile + 1][spalte] != 'X':
        if suchen(zeile + 1, spalte, lab):
            return True

    if lab[zeile][spalte + 1] != '#' and lab[zeile][spalte + 1] != 'X':
        if suchen(zeile, spalte + 1, lab):
            return True

    if lab[zeile - 1][spalte] != '#' and lab[zeile - 1][spalte] != 'X':
        if suchen(zeile - 1, spalte, lab):
            return True

    if lab[zeile][spalte - 1] != '#' and lab[zeile][spalte - 1] != 'X':
        if suchen(zeile, spalte - 1, lab):
            return True
    return False


def suchen_alle(zeile, spalte, lab, counter):
    """
    searches for every possible way to find an exit and counts them

    :param zeile: start point x
    :param spalte: start point y
    :param lab: labyrinth
    :param counter: how many ways there are to solve the lab
    :return: number of ways to get to an exit
    """
    global print_true
    global delay
    if lab[zeile][spalte] == 'A':
        return counter + 1

    lab = [list(row) for row in lab]
    lab[zeile][spalte] = 'X'
    if print_true:
        result_list = ["".join(row) for row in lab]
        print_lab(result_list)
    if delay:
        time.sleep(delay)

    if lab[zeile + 1][spalte] != '#' and lab[zeile + 1][spalte] != 'X':
        counter = suchen_alle(zeile + 1, spalte, lab, counter)

    if lab[zeile][spalte + 1] != '#' and lab[zeile][spalte + 1] != 'X':
        counter = suchen_alle(zeile, spalte + 1, lab, counter)


    if lab[zeile - 1][spalte] != '#' and lab[zeile - 1][spalte] != 'X':
        counter = suchen_alle(zeile - 1, spalte, lab, counter)


    if lab[zeile][spalte - 1] != '#' and lab[zeile][spalte - 1] != 'X':
        counter = suchen_alle(zeile, spalte - 1, lab, counter)
    lab = [list(row) for row in lab]
    lab[zeile][spalte] = ' '
    return counter


global print_true
global delay

def main():
    global print_true
    global delay
    #lab1 = read_lab('C:\\Schule\\5_Klasse\\SEW\python\\5_Klasse_python\\ue_02_labyrinth\\l3.txt')
    #lab2 = read_lab('C:\\Schule\\5_Klasse\\SEW\python\\5_Klasse_python\\ue_02_labyrinth\\l2.txt')
    #lab3 = read_lab('C:\\Schule\\5_Klasse\\SEW\python\\5_Klasse_python\\ue_02_labyrinth\\l3.txt')
    ##for line in lab:
    ##    print(line[0])
    ##print(lab[11][6]) # --> that is the A
    ##print_lab(lab)
    ##print(suchen(1,1, lab))
    #start_time = time.time()
    #print(suchen_alle(1, 1, lab1, 0))
    #print(suchen_alle(1, 1, lab2, 0))
    #print(suchen_alle(1, 1, lab3, 0))
    #end_time = time.time()
    #print('Time: ', (end_time - start_time))
    """
    arg-parser for the program to be runnable

    :return: -
    """
    parser = argparse.ArgumentParser(description="Calculate number of ways through a labyrinth")
    parser.add_argument("filename", help="File containing the labyrinth to solve", type=str)
    parser.add_argument("-x", "--xstart", type=int, help="X-coordinate to start", default=1)
    parser.add_argument("-y", "--ystart", type=int, help="Y-coordinate to start", default=1)
    parser.add_argument("-p", "--print", action="store_true", help="Print output of every solution")
    parser.add_argument("-t", "--time", action="store_true", help="Print total calculation time (in milliseconds)")
    parser.add_argument("-d", "--delay", type=int, help="Delay after printing a solution (in seconds)")

    args = parser.parse_args()
    if args.filename:
        if args.print:
            print_true = True
        else:
            print_true = False
        if args.delay:
            delay = args.delay
        else:
            delay = 0
        if args.time:
            start_time = time.time()
            erg = suchen_alle(args.xstart, args.ystart, read_lab(args.filename), 0)
            end_time = time.time()
            erg_time = end_time - start_time
            print(erg, 'Ways, with a time of: ', erg_time)
        else:
            print(suchen_alle(args.xstart, args.ystart, read_lab(args.filename), 0))


if __name__ == "__main__":
    main()

