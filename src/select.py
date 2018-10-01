# -*- coding: utf-8 -*-
"""
Created on Wed Sep 19 12:45:42 2018

@author: HP
"""

import nltk
from nltk.tokenize import word_tokenize
from nltk.corpus import wordnet
sample=input("enter query")  #query input
tok = word_tokenize(sample)  #toknisation
tagged = nltk.pos_tag(tok)  #tag attached
print(tagged)

#to get information about schema of table and values in it and storing it in sets(becz it contains unique values only)
import sqlite3

sqlite_file = 'C:/Users/HP/Downloads/AppDB.sqlite'
table_name = 'APPLICATION'

# Connecting to the database file
conn = sqlite3.connect(sqlite_file)
c = conn.cursor()

# Retrieve column information
# Every column will be represented by a tuple with the following attributes:
# (id, name, type, notnull, default_value, primary_key)
c.execute('PRAGMA TABLE_INFO({})'.format(table_name))

# collect names in a list
names = [tup[1] for tup in c.fetchall()]
print(names)    #column names stored in names
# e.g., ['id', 'date', 'time', 'date_time']

values=[]
for i in names:
    c.execute('SELECT ({coi}) FROM {tn} '.\
        format(coi=i, tn=table_name))
    all_rows = c.fetchall()
    row=[]
    for element in all_rows:
        a=''.join(str(element))
        a=a.replace("(","")
        a=a.replace("'","")
        a=a.replace(")","")
        a=a.replace(",","")
        row.append(a)
    values.append(row) #table values (unique) stored in values
# Closing the connection to the database file
conn.close()


new_noun=[]
new_number=[]
for i in range(len(tagged)):
    if(tagged[i][1]=='NNP' or tagged[i][1]=='NN' or tagged[i][1]=='JJ' ):
          new_noun.append(tagged[i][0]) #extracting nouns from entered query
    if(tagged[i][1]=='CD'):
            new_number.append(tagged[i][0]) #extracting numbers
            

condition=[]    #this list will contain the attributes corresponding to condition in sql query
reqd_attributes=[]#this will contain the attributes which are to be queried
for i in new_noun:
    for j in range(len(values)):
        if i in values[j]:
            cond=[]
            cond.append(names[j])
            cond.append(i)
            condition.append(cond)
            new_noun.remove(i)
            for element in new_noun:
                if (wordnet.synsets(element,'n')[0]).wup_similarity(wordnet.synsets(names[j],'n')[0])>.8:
                    if element in new_noun:
                        new_noun.remove(element)

for i in range(len(new_number)):
    if(int(new_number[i])<100):
         list=[]
         list.append('age')
         list.append(new_number[i])
         for element in new_noun:
                if (wordnet.synsets(element,'n')[0]).wup_similarity(wordnet.synsets('age','n')[0])>.8:
                    if element in new_noun:
                        new_noun.remove(element)
         
         condition.append(list)
    if(int(new_number[i])>100):
         list=[]
         list.append('salary')
         list.append(new_number[i])
         condition.append(list)
         for element in new_noun:
                if wordnet.synsets(element,'n')[0].wup_similarity(wordnet.synsets('salary','n')[0])>.8:
                    if element in new_noun:
                        new_noun.remove(element)


reqd_attributes=new_noun

if len(reqd_attributes)==0:
    reqd_attributes.append('*')

condition_final=[]
functions=['higher','greater','more','lower','lesser','less','equal','average','maximum','minimum','number']
for i in range(len(tok)):
    if tok[i] in functions:
        if tok[i] in functions[:7]:
            
            while tagged[i][1]!='NN':
                i=i-1
                if tok[i] in functions[:3]:
                    symbol='>'
                    
                if tok[i] in functions[3:6]:
                    symbol='<'
                    
                else:
                    symbol='='
                
                    for v in condition:
                        if v[0]==tagged[i][0]:
                            v.append(symbol)
                            c=v[1]
                            v[1]=v[2]
                            v[2]=c
                    
        else:
            while tagged[i][1]!='NN':
                i=i+1
            for tok[i] in reqd_attributes:
                if  tok[i]=='maximum':
                    condition.append('max('+tok[i]+')')
                    reqd_attributes.remove(tok[i])
                
                if tok[i]=='minimum':
                    condition.append('min('+tok[i]+')')
                    reqd_attributes.remove(tok[i])
                    
                if tok[i]=='number':
                    condition.append('count('+tok[i]+')')
                    reqd_attributes.remove(tok[i])
    

#work pending:putting the generated lists in the sql queries
sqlite_file = 'C:/Users/HP/Downloads/AppDB.sqlite'
table_name = 'APPLICATION'  # name of the table to be queried
'''
id_column = 
some_id = 123456
column_2 = 'my_2nd_column'
column_3 = 'my_3rd_column'

# Connecting to the database file
conn = sqlite3.connect(sqlite_file)
c = conn.cursor()

# 1) Contents of all columns for row that match a certain value in 1 column
c.execute('SELECT * FROM {tn} WHERE {cn}="Hi World"'.\
        format(tn=table_name, cn=column_2))
all_rows = c.fetchall()
print('1):', all_rows)

# 2) Value of a particular column for rows that match a certain value in column_1
c.execute('SELECT ({coi}) FROM {tn} WHERE {cn}="Hi World"'.\
        format(coi=column_2, tn=table_name, cn=column_2))
all_rows = c.fetchall()
print('2):', all_rows)

# 3) Value of 2 particular columns for rows that match a certain value in 1 column
c.execute('SELECT {coi1},{coi2} FROM {tn} WHERE {coi1}="Hi World"'.\
        format(coi1=column_2, coi2=column_3, tn=table_name, cn=column_2))
all_rows = c.fetchall()
print('3):', all_rows)

# 4) Selecting only up to 10 rows that match a certain value in 1 column
c.execute('SELECT * FROM {tn} WHERE {cn}="Hi World" LIMIT 10'.\
        format(tn=table_name, cn=column_2))
ten_rows = c.fetchall()
print('4):', ten_rows)

# 5) Check if a certain ID exists and print its column contents
c.execute("SELECT * FROM {tn} WHERE {idf}={my_id}".\
        format(tn=table_name, cn=column_2, idf=id_column, my_id=some_id))
id_exists = c.fetchone()
if id_exists:
    print('5): {}'.format(id_exists))
else:
    print('5): {} does not exist'.format(some_id))

# Closing the connection to the database file
'''
conn.close()

                

