# The-Wierdest-Database-App

## Report submitted to Honeywell for the Hackathon earlier

### Broad Idea
Using NLTK module to create a new method to communicate with a database more effectively using natural language (English) without explicit knowledge of SQL. We are planning to use machine learning (SKLearn) for the task to make it robust, unlike Chat80 class of NLTK module which only works when English is used with specific grammatical ways. For the Graphical User Interface, we are planning to use JavaFX and integrating it with machine learning models built in python at backend.

### About the GUI
Depending on our skill sets, we have divided the work among us. Reason for choosing JavaFX over Swing GUI is that CSS can be integrated with JavaFX to make GUI more beautiful.
We have a demo SQLite database for the application on which all the command needs to be executed. Presently, the database consists of only 1 table. Table’s preview:

| Name | Age | Salary | Job Role |
| -------- | -------- | -------- | --------- |
| Japkeerat Singh | 20 | 25000 | Software Engineer |
| Raghav Mittal | 20 | 25000 | Database Administrator |
| XYZ person | 25 | 30000 | Data Analyst |

On the main window, we have placed a tab where one can type in plain english what he/she wants to access in the database. A SQL version and output is then displayed on the interface itself without a pop up.

![Imgur](https://i.imgur.com/hmDH6U9l.png?1)

For training and improving our machine learning model, we have placed those buttons at bottom which user needs to click (won’t be there in later stages). Also the backend has not been integrated as of today and that is the reason for the output lines.
### How backend works?
With a custom made corpus which is then loaded to make a dataset for machine learning algorithm that is trained to classify whether a query belongs to one of the following types:
* SELECT
* INSERT
* UPDATE
* DELETE

(As of now, only these functions work are planned)
Before classifying, NLTK is used to preprocess the data. During preprocessing, numbers, nouns, connecting words, etc. are removed and training is done on the remaining words of the dataset (because these characters are common in all type of inputs and would only degrade model’s accuracy).
Once we know what category it belongs to, it looks for keywords (nouns, numbers in this case) depending on the requirement of the above 4 cases. Then those keywords are structured as required.
To prevent training again and again, we are using pickle to store trained machine learning model that can be accessed later on while querying through interface. However, as we have placed buttons of asking user the correctness of the output, depending on the user’s click model may or may not rebuild itself in background on a different thread. The first thread only gets access to pickle which doesn’t change itself until thread 2 completes its work, i.e., it isn’t synchronised.

### Which machine learning model is used?
We are trying to approach this question via many ways. Because of low size of dataset, we have thought of ensembling various models multithreaded but the known issue is that on a quad core processor, two are already busy and ensembling would require three-four cores thus it may cause trouble to user’s computer for a while. Currently, it isn’t very clear what would be the final model.

### Known issues
Python and Java integration is becoming a costly affair as we were using temporary files and a lot of extra code was required on both sides. Jython can be used to do so in efficient way but because of average documentation, it is taking a lot of time.
Machine Learning model (issue discussed above)
Only works for a specific database, thus hindering robustness of the application.

### What more can be done?
Moving out from a specific database to any database based on user requirement.
Not restricting it to just 4 type of SQL commands.
The dataset presently is also restricted to present database. It can be modified to create a dataset from database itself. (SQL to Natural language in the back to train the model)

## About this repository

This repository, as of 18 September, contains the GUI side of the application only. Although the work on it mostly done, because of other participants in the competition, whole of the project can't be made publicly available on Github before 29th of September.

To run this application on your system, you need to have
* JDK 10
* Python 3
* SQLite jar
* And multiple Python dependencies, list of which would be added later with whole project.

