#Clemens Hodina, 5CN
from math import floor


class Britishweight:

    def __init__(self, stones, pounds):
        self._pounds = pounds + (stones * 14)

    def stones(self):
        if self.pounds() >= 0:
            return floor(self.pounds() / 14)
        else:
            return -1 * floor(-1 * self.pounds() / 14)

    def pounds(self):
        return self._pounds

    def __str__(self):
        return str(str(self.stones) + ' st ' + str(self.pounds) + ' lb')

    def __repr__(self):
        return str(str(self.stones) + ' st ' + str(self.pounds) + ' lb')


    def __add__(self, other):
        if isinstance(Britishweight, other):
            return Britishweight(0, self._pounds + other._pounds)













