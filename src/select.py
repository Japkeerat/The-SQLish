# -*- coding: utf-8 -*-
"""
Created on Wed Sep 19 12:45:42 2018

@author: HP
"""

import nltk
from nltk.tokenize import word_tokenize
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
    all_rows=list(set(all_rows))
    all_rows=all_rows.append(i)
    values.append(all_rows) #table values (unique) stored in values
# Closing the connection to the database file
conn.close()
new_noun=[]
new_number=[]
for i in range(len(tagged)):
    if(tagged[i][1]=='NN'):
          new_noun.append(tagged[i][0]) #extracting nouns from entered query
    if(tagged[i][1]=='CD'):
            new_number.append(tagged[i][0]) #extracting numbers
            
            
#now we will find similarity in the extracted nouns and the existing column names and values present in the table
from nltk.corpus import wordnet
synsets_noun=[]
for i in range(len(new_noun)):
    synset=wordnet.synsets(new_noun[i],'n')[0]
    synsets_noun.append(synset)         #synsets of nouns of entered query

synsets_table_attribute=[]
for i in range(len(names)):
    synset=wordnet.synsets(names[i],'n')[0]
    synsets_noun.append(synset)         #synsets of column names
    
synsets_table_values=[]
for column in values:
    col=[]
    for value in column:
        synsets=wordnet.synsets(value,'n')[0]
        col.append(synsets)
    synsets_table_values.append(col)    #sysets of values


#now we will make 2 lists
condition=[]    #this list will contain the attributes corresponding to condition in sql query
reqd_attributes=names#this will contain the attributes which are to be queried
#firstly we will compare values present in query noun to that of table values.
#table value here means the data of table(excluding column names) 
for i in range(len(synsets_noun)):
    for k in range(len(synsets_table_values)):
        for j in k:
            if (synsets_noun[i].wup_similarity(j)>.9):  #if similarity greater than 90%,means that that word is present in table value
                attribute_of_value=names[k]             #column corresponding to that value
                value=j.lemmas()[0].name()              
                list=[]
                list.append(value)          
                list.append(attribute_of_value)
                #we have added the matching value and corresponding column in the new list and we will remove 
                #that value and column name from the reqd_attribute list
                reqd_attributes.remove(attribute_of_value)
                reqd_attributes.remove(value)
                condition.append(list)                


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

                

