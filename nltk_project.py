import nltk
from nltk.tokenize import word_tokenize
from nltk.corpus import stopwords
import sqlite3

check_into={}

check_into['who']=0;
check_into['what']=0;
check_into['how']=0;
check_into['where']=0;
check_into['select']=0;
check_into['get']=0;
check_into['give']=0;
check_into['change']=1;
check_into['alter']=1;
check_into['update']=1;


inputt=input("ENTER QUERY")
input_token = word_tokenize(inputt);

a=2
input_token[0]=input_token[0].lower()
j=input_token[0]
for i in check_into.keys():
    if(j==i):
       a=check_into[i]
if a==2:
    b=int(input("WHAT KIND OF STATEMENT IS IT 0.SELECT 1.UPDATE"))
    check_into[input_token[0]]=b
    a=b

input_token=input_token[1:]
stop_words = set(stopwords.words('english'))
print(stop_words)
filtered_sentence = []

for w in input_token:
    if w not in stop_words:
        filtered_sentence.append(w)



#we will bw making/updating and retrieving data from the database usig sqlite which comes pre installed. 
#below is the way it works
'''
connection = sqlite3.connect("C:/Users/HP/Desktop/Table.db")
crsr = connection.cursor()
 
sql_command = """CREATE TABLE emp ( 
staff_number INTEGER PRIMARY KEY, 
fname VARCHAR(20), 
lname VARCHAR(30), 
gender CHAR(1), 
joining DATE);"""

# execute the statement
crsr.execute(sql_command)
sql_command = """INSERT INTO emp VALUES (23, "Rishabh", "Bansal", "M", "2014-03-28");"""
crsr.execute(sql_command)
 
# another SQL command to insert the data in the table
sql_command = """INSERT INTO emp VALUES (1, "Bill", "Gates", "M", "1980-10-28");"""
crsr.execute(sql_command)
 
# To save the changes in the files. Never skip this. 
# If we skip this, nothing will be saved in the database.
connection.commit()
 
# close the connection
connection.close()


connection = sqlite3.connect("C:/Users/HP/Desktop/Table.db")
sql_command="""SELECT * FROM emp"""
crsr = connection.cursor()
crsr.execute(sql_command)
ans= crsr.fetchall() 
 
# loop to print all the data
for i in ans:
    print(i)
connection.close()


'''


