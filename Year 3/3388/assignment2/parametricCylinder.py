from math import *
import numpy as np
from matrix import matrix
from parametricObject import parametricObject


class parametricCylinder(parametricObject):

    def __init__(self, T=matrix(np.identity(4)), height=20.0, radius=10.0, color=(255, 255, 0),
                 reflectance=(0.2, 0.4, 0.4, 1.0), uRange=(0.0, 1), vRange=(0.0, 2 * pi),
                 uvDelta=(1.0 / 10.0, pi / 18.0)):
        super().__init__(T, color, reflectance, uRange, vRange, uvDelta)
        self.__height = height
        self.__radius = radius

    # define the matrix for a cylinder
    def getPoint(self, u, v):
        P = matrix(np.ones((4, 1)))
        P.set(0, 0, self.__radius * sin(v))
        P.set(1, 0, self.__radius * cos(v))
        P.set(2, 0, self.__height * u)
        return P

    # define the set method for height
    def setHeight(self, height):
        self.__height = height

    # define the set method for radius
    def setRadius(self, radius):
        self.__radius = radius

    # define the get method for height
    def getHeight(self):
        return self.__height

    # define the get method for radius
    def getRadius(self):
        return self.__radius
