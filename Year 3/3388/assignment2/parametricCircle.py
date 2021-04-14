from math import *
import numpy as np
from matrix import matrix
from parametricObject import parametricObject


class parametricCircle(parametricObject):

    def __init__(self, T=matrix(np.identity(4)), radius=10.0, color=(255, 255, 0),
                 reflectance=(0.2, 0.4, 0.4, 1.0), uRange=(0.0, 1), vRange=(0.0, 2 * pi),
                 uvDelta=(1.0 / 10.0, pi / 18.0)):
        super().__init__(T, color, reflectance, uRange, vRange, uvDelta)
        self.__radius = radius

    # define the matrix for a circle
    def getPoint(self, u, v):
        P = matrix(np.ones((4, 1)))
        P.set(0, 0, self.__radius * u * cos(v))
        P.set(1, 0, self.__radius * u * sin(v))
        P.set(2, 0, 0)
        return P

    # define the set method for radius
    def setRadius(self, radius):
        self.__radius = radius

    # define the get method for radius
    def getRadius(self):
        return self.__radius
