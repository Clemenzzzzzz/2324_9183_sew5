



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


def main():
    lab = read_lab('C:\\Schule\\5_Klasse\\SEW\python\\5_Klasse_python\\ue_02_labyrinth\\l1.txt')
    #for line in lab:
    #    print(line[0])
    print(lab[11][6]) # --> that is the A
    print_lab(lab)


if __name__ == "__main__":
    main()

