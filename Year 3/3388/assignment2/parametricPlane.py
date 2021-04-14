import numpy as np
from matrix import matrix
from parametricObject import parametricObject


class parametricPlane(parametricObject):

    def __init__(self, T=matrix(np.identity(4)), length=20.0, width=20.0, color=(255, 255, 0),
                 reflectance=(0.2, 0.4, 0.4, 1.0), uRange=(0.0, 1), vRange=(0.0, 1), uvDelta=(1.0 / 10.0, 1.0 / 10.0)):
        super().__init__(T, color, reflectance, uRange, vRange, uvDelta)
        self.__length = length
        self.__width = width

    # define the matrix for a plane
    def getPoint(self, u, v):
        P = matrix(np.ones((4, 1)))
        P.set(0, 0, u * self.__width)
        P.set(1, 0, v * self.__length)
        P.set(2, 0, 0)
        return P

    # Define the set method for width
    def setWidth(self, width):
        self.__width = width

    # Define the set method for length
    def setLength(self, length):
        self.__length = length

    # Define the get method for width
    def getWidth(self):
        return self.__width

    # Define the get method for length
    def getLength(self):
        return self.__length
