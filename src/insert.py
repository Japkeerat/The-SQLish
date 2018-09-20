import os

from nltk import word_tokenize as WT
from nltk import pos_tag

import sqlite3

# Only backlash: Name of the person must appear before job description

file = open("temp.txt", "r")
content = file.read().strip()
file.close()

os.remove("temp.txt")

tempList = list(content)
tempList[0] = tempList[0].lower()
content = "".join(tempList)
tempList.clear()

text = WT(content)
tagging = pos_tag(text)

age = 0
salary = 0
name = ""
job = ""
count = 0

n = len(tagging)

for i in range(0, n):
    if tagging[i][1] == 'CD':
        if int(tagging[i][0]) / 100 < 1:
            age = int(tagging[i][0])
        else:
            salary = int(tagging[i][0])
    elif tagging[i][1] == 'NNP':
        if count == 0:
            name = name.join(tagging[i][0])
            if tagging[i + 1][1] == 'NNP':
                name = name.join(tagging[i + 1][0])
                i += 1
            count += 1
        else:
            job = job.join(tagging[i][0])


# Error with SQL
def database(A, N, S, J):
    db = "../AppDB.sqlite"

    connection = sqlite3.connect(db)
    SQL = "INSERT INTO APPLICATION VALUES(" + str(N) + "," + str(A) + "," + str(S) + "," + str(J) + ")"

    file1 = open("temp.txt", "w")
    file1.write(SQL)
    file1.close()

    with connection:
        connection.cursor().execute(SQL)


database(age, name, salary, job)
