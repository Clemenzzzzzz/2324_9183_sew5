import os.path

from openpyxl import *

""" Beispiel Excel auslesen 
wb = load_workbook(xlsfilename, read_only=True)
ws = wb[wb.sheetnames[0]]
for row in ws.iter_rows(min_row=7):
x = row[3] # Element in der Zeile lesen -- Objekt
y = x.value # Wert in der Zelle
z = x.coordinate # "Name" der Zelle
ws['A1'] = 42 # Schreiben mit Zellenname
"""


def read_name_excel(filename):
    """
    liest file ein und gibt es mit yield zeilenweise weiter

    :param filename:
    :return:
    """
    lines = []
    wb = load_workbook(filename, read_only=True)
    ws = wb[wb.sheetnames[0]]
    for row in ws.iter_rows(min_row=7):
        a = row[0]  # Element in der Zeile lesen -- Objekt
        b = row[1]
        c = row[2]
        d = row[3]  # Wert in der Zelle
        #z = x.coordinate  # "Name" der Zelle
        lines.append((a.value, b.value, c.value, d.value))
    #ws['A1'] = 42  # Schreiben mit Zellenname
    return lines

def read_class_excel(filename):
    lines = []
    wb = load_workbook(filename, read_only=True)
    ws = wb[wb.sheetnames[0]]
    for row in ws.iter_rows(min_row=7):
        a = row[0]  # Element in der Zeile lesen -- Objekt
        b = row[1]
        c = row[2]
        lines.append((a.value, b.value, c.value))
    return lines

def create_files(excel_file):
    """
    erstellt die 4 Files und lässt sie von eigenen Methoden erstellen

    :return:
    """
    lines = read_class_excel(excel_file)
    class_user_script = create_class_users_script(lines) #quasi ein String den man dann einfach in ein File schreibt
    with open('ressources/class_user_file.txt', 'w') as f1:
        f1.write(class_user_script)
    #print(line, 'ressources/class_user_script.txt')
    pass


def create_class_users_script(lines):
    script = ''
    # zuerst Variablen für alle Werte die man so braucht
    # dann eine Zeile die in ein File schreibt, und wirklich die script Zeilen erstellt
    script += ''
    for line in lines:
        username = 'k' + str(line[0]).lower()
        #TODO Umlaute ersetzen
        gecos_field = str(line[0]).lower() + '_' + str(line[2]).lower()
        home_directory = '/home/klassen/' + username
        #TODO am anfang von script nötige verzeichnisse wie /home/klassen erstellen
    return script


create_files('ressources/Namen.xlsx')


