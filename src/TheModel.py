import random

from nltk import FreqDist as FD
from nltk import NaiveBayesClassifier as NBC
from nltk import classify
from nltk.classify.scikitlearn import SklearnClassifier
from nltk.classify import ClassifierI
from nltk.corpus.reader import PlaintextCorpusReader as PCR
from nltk import pos_tag

import pickle

from sklearn.naive_bayes import MultinomialNB, GaussianNB, BernoulliNB
from sklearn.linear_model import LogisticRegression, SGDClassifier
from sklearn.svm import SVC, LinearSVC, NuSVC
import numpy, scipy

from os import listdir
from os.path import isfile, join

corpus_dir = '../Dataset/'
new_corpus = PCR(corpus_dir, '.*')

documents = [f for f in listdir(corpus_dir) if isfile(join(corpus_dir, f))]

random.shuffle(documents)

all_words = []
for w in new_corpus.words():
    all_words.append(w)

all_words = FD(all_words)

word_features = list(all_words.keys())
tagging = pos_tag(word_features)
word_features.clear()
word_features = list(s for s in tagging if s[1]!='NNP' if s[1]!='CD' if s[1]!='IN' if s[0]!='.' if s[0]!='\'' if s[1]!='DT')


def find_features(document):
    words = set(document)
    features = {}
    for w in word_features:
        features[w] = (w in words)

    return features


print((find_features(new_corpus.words('Delete_1.txt'))))
#
# featuresets = [(find_features(rev), category) for (rev, category) in documents]