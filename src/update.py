# -*- coding: utf-8 -*-
"""
Created on Thu Sep 20 11:54:49 2018

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


b_where=[]    #this list will contain the attributes before where condition
a_where=[]    #this will contain the attributes after where condition
#firstly we will compare values present in query noun to that of table values.
#table value here means the data of table(excluding column names) 
for i in range(len(synsets_noun)):
    for k in range(len(synsets_table_values)):
        for j in k:
            if (synsets_noun[i].wup_similarity(j)>.9):  #if similarity greater than 90%,means that that word is present in table value
                attribute_of_value=names[k]             #column corresponding to that value
                value=j.lemmas()[0].name()   
                if (attribute_of _value in new_noun):
                    list=[]
                    list.append(value)          
                    list.append(attribute_of_value)
                    b_where.append(list)                
                else:
                    if(noun[i])
                  list=[]
                  list.append(value)
                  list.append(attribute_of_value)
                  a_where.append(list)


#update b_where where a_where
