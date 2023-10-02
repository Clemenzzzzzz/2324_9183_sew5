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


def read_excel(filename):
    """
    liest file ein und gibt es mit yield zeilenweise weiter

    :param filename:
    :return:
    """
    wb = load_workbook(filename, read_only=True)
    ws = wb[wb.sheetnames[0]]
    for row in ws.iter_rows(min_row=7):
        a = row[0]  # Element in der Zeile lesen -- Objekt
        b = row[1]
        c = row[2]
        d = row[3]  # Wert in der Zelle
        #z = x.coordinate  # "Name" der Zelle
        yield (a.value, b.value, c.value, d.value,)
    #ws['A1'] = 42  # Schreiben mit Zellenname


def create_files(excel_file):
    """
    erstellt die 4 Files und lässt sie von eigenen Methoden erstellen

    :return:
    """
    for line in read_excel(excel_file):
        print(line)
        #jede line verarbeiten
    pass



create_files('ressources/Namen.xlsx')


