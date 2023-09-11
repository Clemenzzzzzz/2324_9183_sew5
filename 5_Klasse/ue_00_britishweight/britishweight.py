#Clemens Hodina, 5CN
from math import floor


class Britishweight:
    """
    >>> x1 = Britishweight(0, 5)
    >>> x2 = Britishweight(1, 5)
    >>> x3 = Britishweight(-1, 5)
    >>> x4 = Britishweight(-1, 9)
    >>> print(x1)
    0 st 5 lb
    >>> print(x3)
    0 st -9 lb
    >>> x2
    1 st 5 lb
    >>> x4
    0 st -5 lb
    >>> y1 = x1 + x2
    >>> print(y1)
    1 st 10 lb
    >>> y2 = x3 + x1
    >>> print(y2)
    0 st -4 lb
    """

    def __init__(self, stones, pounds):
        self._pounds = pounds + (stones * 14)

    @property
    def stones(self):
        if self._pounds >= 0:
            return floor(self._pounds / 14)
        else:
            return -1 * floor(-1 * self._pounds / 14)

    @property
    def pounds(self):
        return self._pounds % 14 - (14 if self._pounds < 0 and self._pounds % 14 != 0 else 0)

    def __str__(self):
        return str(str(self.stones) + ' st ' + str(self.pounds) + ' lb')

    def __repr__(self):
        return str(str(self.stones) + ' st ' + str(self.pounds) + ' lb')


    def __add__(self, other):
        #if isinstance(Britishweight, other):
        return Britishweight(0, self._pounds + other._pounds)













