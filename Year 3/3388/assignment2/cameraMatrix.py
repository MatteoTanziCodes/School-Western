from math import *
import numpy as np
from matrix import matrix


class cameraMatrix:

    def __init__(self, window, UP, E, G, nearPlane=10.0, farPlane=50.0, theta=90.0):
        self.__UP = UP.normalize()
        self.__E = E
        self.__G = G
        self.__np = nearPlane
        self.__fp = farPlane
        self.__width = window.getWidth()
        self.__height = window.getHeight()
        self.__theta = theta
        self.__aspect = self.__width / self.__height
        self.__npHeight = self.__np * (pi / 180.0 * self.__theta / 2.0)
        self.__npWidth = self.__npHeight * self.__aspect

        Mp = self.__setMp(self.__np, farPlane)
        T1 = self.__setT1(self.__np, self.__theta, self.__aspect)
        S1 = self.__setS1(self.__np, self.__theta, self.__aspect)
        T2 = self.__setT2()
        S2 = self.__setS2(self.__width, self.__height)
        W2 = self.__setW2(self.__height)

        self.__N = (self.__E - self.__G).removeRow(3).normalize()
        self.__U = self.__UP.removeRow(3).crossProduct(self.__N).normalize()
        self.__V = self.__N.crossProduct(self.__U)

        self.__Mv = self.__setMv(self.__U, self.__V, self.__N, self.__E)
        self.__C = W2 * S2 * T2 * S1 * T1 * Mp
        self.__M = self.__C * self.__Mv

    # Defines the matrix Mv
    def __setMv(self, U, V, N, E):
        # Set the matrix to 4x4
        Mv = matrix(np.zeros((4, 4)))

        # Set the zero vector for the last rows first three columns and 1 in the last element
        Mv.set(3, 0, 0)
        Mv.set(3, 1, 0)
        Mv.set(3, 2, 0)
        Mv.set(3, 3, 1)
        Mv.set(0, 0, U.get(0, 0))
        Mv.set(0, 1, U.get(1, 0))
        Mv.set(0, 2, U.get(2, 0))
        Mv.set(1, 0, V.get(0, 0))
        Mv.set(1, 1, V.get(1, 0))
        Mv.set(1, 2, V.get(2, 0))
        Mv.set(2, 0, N.get(0, 0))
        Mv.set(2, 1, N.get(1, 0))
        Mv.set(2, 2, N.get(2, 0))

        # Get negative E
        negE = -E
        negE = negE.removeRow(3)

        # Compute the dot product between u and -e
        ue = U.dotProduct(negE)
        # Compute the dot product between v and -e
        ve = V.dotProduct(negE)
        # Compute the dot product between n and -e
        ne = N.dotProduct(negE)

        # Set the dot product values to the last column in the matrix
        Mv.set(0, 3, ue)
        Mv.set(1, 3, ve)
        Mv.set(2, 3, ne)

        # Return Mv
        return Mv

    # Defines the matrix Mp
    def __setMp(self, nearPlane, farPlane):
        # Set the matrix to 4x4
        Mp = matrix(np.zeros((4, 4)))

        # Calc values a and b
        a = -(farPlane + nearPlane) / farPlane - nearPlane
        b = -2 * farPlane * nearPlane / (farPlane - nearPlane)

        # Set Matrix Value
        Mp.set(0, 0, nearPlane)
        Mp.set(1, 1, nearPlane)
        Mp.set(2, 2, a)
        Mp.set(2, 3, b)
        Mp.set(3, 2, -1)

        # Return Mp
        return Mp

    # Defines the matrix T1
    def __setT1(self, nearPlane, theta, aspect):
        # Set the matrix to 4x4
        T1 = matrix(np.zeros((4, 4)))

        # Calc values for t, r, l and b
        t = nearPlane * tan((pi / 180) * (theta / 2))
        r = aspect * t
        b = -t
        l = -r

        # Set Matrix values
        T1.set(0, 0, 1)
        T1.set(1, 1, 1)
        T1.set(2, 2, 1)
        T1.set(3, 3, 1)
        T1.set(0, 3, -(r + l) / 2)
        T1.set(1, 3, -(t + b) / 2)

        # Return T1
        return T1

    # Defines the matrix S1
    def __setS1(self, nearPlane, theta, aspect):
        # Set the matrix to 4x4
        S1 = matrix(np.zeros((4, 4)))

        # Calc values for t, r, l and b
        t = nearPlane * tan((pi / 180) * (theta / 2))
        r = aspect * t
        b = -t
        l = -r

        # Input matrix values
        S1.set(0, 0, 2 / (r - l))
        S1.set(1, 1, 2 / (t - b))
        S1.set(2, 2, 1)
        S1.set(3, 3, 1)

        # Return S1
        return S1

    # Defines the matrix T2
    def __setT2(self):
        # Set the matrix to 4x4
        T2 = matrix(np.zeros((4, 4)))

        # Set the matrix values
        T2.set(0, 0, 1)
        T2.set(1, 1, 1)
        T2.set(2, 2, 1)
        T2.set(3, 3, 1)
        T2.set(0, 3, 1)
        T2.set(1, 3, 1)

        # Return T2
        return T2

    # Defines the matrix S2
    def __setS2(self, width, height):
        # Set the matrix to 4x4
        S2 = matrix(np.zeros((4, 4)))

        # Set the matrix values
        S2.set(0, 0, width / 2)
        S2.set(1, 1, height / 2)
        S2.set(2, 2, 1)
        S2.set(3, 3, 1)

        # Return S2
        return S2

    # Defines the matrix W2
    def __setW2(self, height):
        # Set the matrix to 4x4
        W2 = matrix(np.zeros((4, 4)))

        # Set the matrix values
        W2.set(0, 0, 1)
        W2.set(1, 1, -1)
        W2.set(2, 2, 1)
        W2.set(3, 3, 1)
        W2.set(1, 3, height)

        # Return W2
        return W2

    def worldToViewingCoordinates(self, P):
        return self.__Mv * P

    def worldToImageCoordinates(self, P):
        return self.__M * P

    def worldToPixelCoordinates(self, P):
        return self.__M * P.scalarMultiply(1.0 / (self.__M * P).get(3, 0))

    def viewingToImageCoordinates(self, P):
        return self.__C * P

    def viewingToPixelCoordinates(self, P):
        return self.__C * P.scalarMultiply(1.0 / (self.__C * P).get(3, 0))

    def imageToPixelCoordinates(self, P):
        return P.scalarMultiply(1.0 / P.get(3, 0))

    def getUP(self):
        return self.__UP

    def getU(self):
        return self.__U

    def getV(self):
        return self.__V

    def getN(self):
        return self.__N

    def getE(self):
        return self.__E

    def getG(self):
        return self.__G

    def getMv(self):
        return self.__Mv

    def getC(self):
        return self.__C

    def getM(self):
        return self.__M

    def getNp(self):
        return self.__np

    def getFp(self):
        return self.__fp

    def getTheta(self):
        return self.__theta

    def getAspect(self):
        return self.__aspect

    def getWidth(self):
        return self.__width

    def getHeight(self):
        return self.__height

    def getNpHeight(self):
        return self.__npHeight

    def getNpWidth(self):
        return self.__npWidth
