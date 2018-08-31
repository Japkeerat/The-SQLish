import random

from nltk import FreqDist as FD
from nltk import NaiveBayesClassifier as NBC
from nltk import classify
from nltk.classify.scikitlearn import SklearnClassifier
from nltk.classify import ClassifierI
from nltk.corpus import PlaintextCorpusReader as PCR

import pickle

from sklearn.naive_bayes import MultinomialNB, GaussianNB, BernoulliNB
from sklearn.linear_model import LogisticRegression, SGDClassifier
from sklearn.svm import SVC, LinearSVC, NuSVC
import numpy, scipy

corpus_dir = '../Dataset/'
new_corpus = PCR(corpus_dir, '.*')

documents = [(list(new_corpus.words(fileid)), category)
             for category in new_corpus.categories()
             for fileid in new_corpus.fileids(category)]

random.shuffle(documents)

for i in documents:
    print(i)