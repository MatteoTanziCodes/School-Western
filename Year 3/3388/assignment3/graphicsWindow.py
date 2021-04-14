import operator
import numpy as np
from matrix import matrix
from PIL import Image, ImageDraw

class graphicsWindow:

    def __init__(self,width=640,height=480,color=(0,0,0)):
        self.__mode = 'RGB'
        self.__width = width
        self.__height = height
        self.__canvas = Image.new(self.__mode,(self.__width,self.__height),color=color)
        self.__image = self.__canvas.load()

    def drawPoint(self,point,color): #Point is a tuple
        if point[0] < self.__width and point[1] < self.__height:
            self.__image[point[0], point[1]] = color

    def drawLine(self,point1,point2,color):
            draw = ImageDraw.Draw(self.__canvas)
            draw.line((round(point1.get(0,0)),round(point1.get(1,0)),round(point2.get(0,0)),round(point2.get(1,0))),fill=color)

    def fillPolygon(self,pointList,color): #pointList is a list of column vectors as matrices
        segments = []
        self.drawPolygon(pointList,color) #To make sure horizontal segments are traced, and other degenerate cases
        minY = int(pointList[0].get(1,0))
        maxY = int(pointList[0].get(1,0))
        for i in range(len(pointList)): #Build segment list
            p1 = (round(pointList[i].get(0,0)),round(pointList[i].get(1,0)))
            p2 = (round(pointList[(i+1)%len(pointList)].get(0,0)),round(pointList[(i+1)%len(pointList)].get(1,0)))
            if p1[1] != p2[1]: #Exclude horizontal segments from list
                segments.append((p1,p2))
            if pointList[i].get(1,0) < minY: #Find min and max y-coordinates of polygon
                minY = int(pointList[i].get(1,0))
            if pointList[i].get(1,0) > maxY:
                maxY = int(pointList[i].get(1,0))
        for y in range(minY,maxY+1): #Find intersections with the scan line
            active = []
            for i in range(len(segments)): #Build list of active segments for scan line y
                if segments[i][0][1] <= y <= segments[i][1][1] or segments[i][0][1] >= y >= segments[i][1][1]:
                    active.append(segments[i])
            intersections = []
            for i in range(len(active)): #Build intersection list
                if active[i][1][0] - active[i][0][0] != 0: #Non vertical segment:
                    m = (active[i][1][1] - active[i][0][1])/(active[i][1][0] - active[i][0][0])
                    b = active[i][0][1] - m*active[i][0][0]
                    inter = int(round((y-b)/m))
                else:
                    inter = active[i][0][0] #Vertical segment
                if active[i][0][1] == y: #Find if intersection is with first endpoint of segment
                    y0 = active[i-1][0][1] #y-coordinate of preceding point
                    y1 = active[i][1][1] #y-coordinate of succeeding point
                    if y0 < y < y1 or y0 > y > y1: #Count one intersection
                        intersections.append(inter)
                    else: #Count two intersections
                        intersections.extend([inter,inter])
                elif active[i][1][1] != y: #If both endpoints do not intersect with scan line
                    intersections.append(inter)
            intersections.sort()
            point1 = matrix(np.zeros((2,1)))
            point2 = matrix(np.zeros((2,1)))
            for i in range(0,len(intersections)-1,2): #Trace the list of intersections pair-wise
                point1.set(0,0,intersections[i])
                point1.set(1,0,y)
                point2.set(0,0,intersections[i+1])
                point2.set(1,0,y)
                self.drawLine(point1,point2,color)

    def drawFaces(self,faceList):
        faceList.sort(key = operator.itemgetter(0),reverse=True)
        for face in faceList:
            self.fillPolygon(face[1],face[2])

    def drawWireMesh(self,faceList):
        faceList.sort(key = operator.itemgetter(0),reverse=True)
        for face in faceList:
            self.drawPolygon(face[1],face[2])

    def drawPolygon(self,pointList,color):
        for i in range(len(pointList)):
            self.drawLine(pointList[i],pointList[(i+1)%len(pointList)],color)

    def drawPolyline(self,pointList,color):
        for i in range(len(pointList)-1):
            self.drawLine(pointList[i],pointList[i+1],color)

    def saveImage(self,fileName):
        self.__canvas.save(fileName)

    def showImage(self):
        self.__canvas.show()

    def getWidth(self):
        return self.__width

    def getHeight(self):
        return self.__height