



def read_lab(file_path):
    lab = []
    with open(file_path, 'r') as file:
        # Zeilenweise auslesen
        for line in file.readlines():
            lab.append(line.strip())
    return lab




def main():
    lab = read_lab('C:\\Schule\\5_Klasse\\SEW\python\\5_Klasse_python\\ue_02_labyrinth\\l1.txt')
    for line in lab:
        print(line)


if __name__ == "__main__":
    main()

