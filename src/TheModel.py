import os
import subprocess

fileRead = open("temp.txt", "r")

englishStatement = fileRead.read().strip()
fileRead.close()

os.remove("temp.txt")

insertList = ['Insert', 'Add']
selectList = ['Select', 'Show', 'Display', 'Get', 'Tell']
updateList = ['Update', 'Modify', 'Change']
deleteList = ['Delete', 'Remove']


import nltk
from nltk.tokenize import word_tokenize
tokenised=word_tokenize(englishStatement)
if i in tokenised:
    if i in insertList:
        subprocess.call("insert.py", shell=True)
        break
    if i in selectList:
        
        subprocess.call("select.py", shell=True)
        break
    if i in updateList:
        
        subprocess.call("update.py", shell=True)
        break
    if i in deleteList:
        
        subprocess.call("delete.py", shell=True)
        break