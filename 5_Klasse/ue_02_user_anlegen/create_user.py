import argparse
import random
import re
import sys
from collections import Counter

import unicodedata
import unidecode
from openpyxl import load_workbook
import os.path

from logging.handlers import RotatingFileHandler
import logging



logger = logging.getLogger(__name__)
handler = logging.handlers.RotatingFileHandler("create_class.log", maxBytes=10000, backupCount=5)
formatter = logging.Formatter('%(asctime)s - %(levelname)s - %(message)s')
handler.setFormatter(formatter)

stream_handler = logging.StreamHandler(sys.stdout)
stream_handler.setFormatter(formatter)

logger.addHandler(handler)
logger.addHandler(stream_handler)





""" Beispiel Excel auslesen 
wb = load_workbook(xlsfilename, read_only=True)
ws = wb[wb.sheetnames[0]]
for row in ws.iter_rows(min_row=7):
x = row[3] # Element in der Zeile lesen -- Objekt
y = x.value # Wert in der Zelle
z = x.coordinate # "Name" der Zelle
ws['A1'] = 42 # Schreiben mit Zellenname
"""

user_to_password = []
existing_user_names = []
verbosity = False
def read_name_excel(filename):
    """
    liest file ein und gibt es mit yield zeilenweise weiter

    :param filename:
    :return:
    """
    lines = []
    wb = load_workbook(filename, read_only=True)
    ws = wb[wb.sheetnames[0]]
    for row in ws.iter_rows(min_row=2):
        if row[0].value == None:
            break
        #z = x.coordinate  # "Name" der Zelle
        lines.append((row[0].value, row[1].value, row[2].value, row[3].value))
    #ws['A1'] = 42  # Schreiben mit Zellenname
    return lines

def create_files(excel_file):
    """
    erstellt die 4 Files und lässt sie von eigenen Methoden erstellen

    :return:
    """
    global user_to_password
    global verbosity
    global logger
    lines = read_name_excel(excel_file)
    real_user_script = create_real_users_script(lines) #quasi ein String den man dann einfach in ein File schreibt
    real_delete_script = create_real_delete_script()
    with open('create_user_script.sh', 'w', encoding='utf-8') as f1:
        print(real_user_script)
        f1.write(real_user_script)
        logger.info("real_user_script created")
        if verbosity:
            print('real_user_script created')
    with open('delete_user_script.sh', 'w', encoding='utf-8') as f1:
        f1.write(real_delete_script)
        logger.info("real_delete_script created")
        if verbosity:
            print("real_delete_script created")
    with open('user_password_list.txt', 'w', encoding='utf-8') as f1:
        f1.write(get_user_to_passwd_list())
        logger.info("user_password_list created")
        if verbosity:
            print("user_password_list created")
    #print(line, 'ressources/class_user_script.txt')





def create_real_users_script(lines):
    global user_to_password
    global verbosity
    global logger
    script = ''
    # zuerst Variablen für alle Werte die man so braucht
    # dann eine Zeile die in ein File schreibt, und wirklich die script Zeilen erstellt
    systemgroups = 'cdrom,plugdev,sambashare'
    home_shell = '/bin/bash'
    script += 'mkdir /home\n'
    random_chars = ['!', '%', '\(', '\)', ',', '.', '_', '-', '=', '^', '#']
    for line in lines:
        print('hier is die line')
        print(line)
        firstname = str(line[0])
        lastname = str(line[1])
        group = str(line[2])
        user_class = str(line[3])
        username = get_valid_username(lastname)
        if username in existing_user_names:
            index = 1
            while f'{username}{index}' in existing_user_names:
                index += 1
            username = username + str(index)
            existing_user_names.append(username)
        else:
            existing_user_names.append(username)
        gecos_field = firstname + '_' + lastname
        home_directory = '/home/' + username
        password = username + random.choice(random_chars) + group + random.choice(random_chars) + user_class + random.choice(random_chars)
        user_to_password.append((username, password))
        #script += f'groupadd {username}\n'
        script += f'useradd -d {home_directory} -c \"{gecos_field}\" -m -g {username} -G {systemgroups},{group} -s {home_shell} {username}\n'
        script += f'echo {username}:{password} | chpasswd\n'
        logger.info(f'User {username} with password created!')
        if verbosity:
            script += f'echo User {username} with password created!\n'
            print('echo User {username} with password created!')
    return script


def get_valid_username(lastname):
    username = lastname.lower()
    username = shave_marks(username)
    #username = unidecode.unidecode(username)
    username = username.replace(' ', '_')
    username = username.replace('ä', 'ae') #TODO weirde Charactere (siehe letzten beiden Namen) --> vor allem der bug grade
    username = username.replace('ö', 'oe')
    username = username.replace('ü', 'ue')
    username = username.replace('ß', 'ss')
    username = re.sub(r'[^a-z0-9_]', '', username)
    return username

def shave_marks(txt):
    """Remove all diacritic marks"""
    norm_txt = unicodedata.normalize('NFD', txt)
    shaved = ''.join(c for c in norm_txt if not unicodedata.combining(c))
    return unicodedata.normalize('NFC', shaved)


# TODO tests siehe angbabe

def create_real_delete_script():
    global user_to_password
    global verbosity
    global logger
    script = ''
    for user in user_to_password:
        username = user[0]
        script += f'userdel -r {username}\n'
        logger.info(f'User {username} with password deleted!')
        if verbosity:
            script += f'echo User {username} with password deleted!\n'
            print('User {username} with password deleted!')
    return script

def get_user_to_passwd_list(): #TODO das auch als excel
    global user_to_password
    global verbosity
    global logger
    list = ''
    for user, password in user_to_password:
        list += str(user) + ';' + str(password) + '\n'
        logger.info(f'User {user} and its password in user_to_password list')
        if verbosity:
            print(f'User {user} and its password in user_to_password list')
    return list

#create_files('ressources/Klassenraeume_2023.xlsx')


def main():
    global verbosity
    global logger
    parser = argparse.ArgumentParser()
    parser.add_argument("filename", type=str, help="filename")
    group = parser.add_mutually_exclusive_group(required=True)
    group.add_argument("-v", "--verbose", help="add log verbosity", action="store_true")
    group.add_argument("-q", "--quiet", help="turn off log verbosity", action="store_true")

    args = parser.parse_args()
    logger.setLevel(logging.INFO)
    if args.verbose:
        #global verbosity
        verbosity = True
        print("Verbosity truned on")

    if args.quiet:
        #global verbosity
        verbosity = False
        print('Verbosity turned off')

    if args.filename:
        create_files(args.filename)


if __name__ == "__main__":
    main()