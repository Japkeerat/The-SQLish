import os

from nltk import word_tokenize as WT
from nltk import pos_tag

import sqlite3

file = open("temp.txt", "r")
content = file.read()
file.close()

os.remove("temp.txt")

tempList = list(content)
tempList[0] = tempList[0].lower()
content = "".join(tempList)
tempList.clear()

text = WT(content)
tagging = pos_tag(text)

table_attributes = ['name', 'job', 'salary', 'age']

age = 0
salary = 0
name = ""
job = ""


def database(A, S, N, J, condition):
    SQL = ""
    if A!=0:
        if condition == 0:
            SQL = "DELETE FROM APPLICATION WHERE Age="+str(A)+";"
        elif condition == 1:
            SQL = "DELETE FROM APPLICATION WHERE Age>"+str(A)+";"
        else:
            SQL = "DELETE FROM APPLICATION WHERE Age<"+str(A)+";"
    elif S!=0:
        if condition == 0:
            SQL = "DELETE FROM APPLICATION WHERE Salary="+str(S)+";"
        elif condition == 1:
            SQL = "DELETE FROM APPLICATION WHERE Salary>"+str(S)+";"
        else:
            SQL = "DELETE FROM APPLICATION WHERE Salary<"+str(S)+";"
    elif N!="":
        SQL = "DELETE FROM APPLICATION WHERE Name="+str(N)+";"
    elif J!="":
        SQL = "DELETE FROM APPLICATION WHERE Job="+str(J)+";"

    db = "../AppDB.sqlite"
    connection = sqlite3.connect(db)

    file1 = open("temp.txt", "w")
    file1.write(SQL)
    file1.close()

    with connection:
        connection.cursor().execute(SQL)


for t in tagging:
    if t[1] == 'CD':
        if int(t[0])/100 < 1:
            age = int(t[0])
            tempList = list(set(tagging.keys()).intersection(['greater', 'less', 'more', 'higher', 'lower']))
            if len(tempList) == 0:
                database(age, salary, name, job, 0)
            else:
                x = tempList[0]
                if x == 'greater' | x == 'higher' | x == 'more':
                    database(age,salary,name,job,1)
                else:
                    database(age,salary,name,job,-1)
        else:
            salary = int(t[0])
            tempList = list(set(tagging.keys()).intersection(['greater', 'less', 'more', 'higher', 'lower']))
            if len(tempList) == 0:
                database(age, salary, name, job, 0)
            else:
                x = tempList[0]
                if x == 'greater' | x == 'higher' | x == 'more':
                    database(age, salary, name, job, 1)
                else:
                    database(age, salary, name, job, -1)

    if t[1] == 'NNP':
        tempList = list(set(tagging.keys()).intersection(table_attributes))
        if tempList.__contains__('name'):
            name = t[0]
        elif tempList.__contains__('job'):
            job = t[0]
        database(age,salary,name,job,0)