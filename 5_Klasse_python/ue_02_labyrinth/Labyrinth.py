



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
    result_list = ["".join(row) for row in lab]
    print_lab(result_list)
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

def main():
    lab = read_lab('C:\\Schule\\5_Klasse\\SEW\python\\5_Klasse_python\\ue_02_labyrinth\\l1.txt')
    #for line in lab:
    #    print(line[0])
    print(lab[11][6]) # --> that is the A
    print_lab(lab)
    print(suchen(1,1, lab))


if __name__ == "__main__":
    main()

