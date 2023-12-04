



def read_lab(file_path):
    lab = []
    with open(file_path, 'r') as file:
        # Zeilenweise auslesen
        for line in file.readlines():
            lab.append(line.strip())
    return lab


def print_lab(lab):
    for line in lab:
        print(line)

def suchen(zeile, spalte, lab):
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
    if lab[zeile][spalte] == 'A':
        return counter + 1

    lab = [list(row) for row in lab]
    lab[zeile][spalte] = 'X'
    #result_list = ["".join(row) for row in lab]
    #print_lab(result_list)

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


# TODO's:
#   Zeitmessung und ausgabe
#   argparse
#   docstring
#   alles nochmal gescheit testen und mit java vergleichen



def main():
    lab = read_lab('C:\\Schule\\5_Klasse\\SEW\python\\5_Klasse_python\\ue_02_labyrinth\\l3.txt')
    #for line in lab:
    #    print(line[0])
    print(lab[11][6]) # --> that is the A
    print_lab(lab)
    print(suchen(1,1, lab))
    print(suchen_alle(1, 1, lab, 0))


if __name__ == "__main__":
    main()

