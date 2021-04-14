import operator
from PIL import Image, ImageDraw

class graphicsWindow:

    def __init__(self,width=640,height=480):
        self.__mode = 'RGB'
        self.__width = width
        self.__height = height
        self.__canvas = Image.new(self.__mode,(self.__width,self.__height))
        self.__image = self.__canvas.load()

    def drawPoint(self,point,color):
        if 0 <= point[0] < self.__width and 0 <= point[1] < self.__height:
            self.__image[point[0],point[1]] = color

    def drawLine(self, point1, point2, color):
            draw = ImageDraw.Draw(self.__canvas)
            draw.line((point1.get(0,0),point1.get(1,0),point2.get(0,0),point2.get(1,0)),fill=color)

    def drawWireMesh(self,faceList):
        faceList.sort(key = operator.itemgetter(0),reverse=True)
        for face in faceList:
            self.drawPolygon(face[1],face[2])

    def drawPolygon(self,pointList,color):
        for i in range(len(pointList)-1):
            self.drawLine(pointList[i],pointList[i+1],color)
        self.drawLine(pointList[-1],pointList[0],color)

    def saveImage(self,fileName):
        self.__canvas.save(fileName)

    def showImage(self):
        self.__canvas.show()

    def getWidth(self):
        return self.__width

    def getHeight(self):
        return self.__height