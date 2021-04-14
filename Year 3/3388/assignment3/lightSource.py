import numpy as np
from matrix import matrix


class lightSource:

    def __init__(self, position=matrix(np.zeros((4, 1))), color=(0, 0, 0), intensity=(1.0, 1.0, 1.0)):
        self.__position = position
        self.__color = color
        self.__intensity = intensity

    # get position of light source
    def getPosition(self):
        return self.__position

    # get colour of light source
    def getColor(self):
        return self.__color

    # get intensity of light source
    def getIntensity(self):
        return self.__intensity

    # set position of light source
    def setPosition(self, position):
        self.__position = position

    # set colour of light source
    def setColor(self, color):
        self.___color = color

    # set intesity of light source
    def setIntensity(self, intensity):
        self.__intensity = intensity
