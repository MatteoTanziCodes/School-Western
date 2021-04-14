import operator
import numpy as np
from matrix import matrix
from PIL import Image

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

    def clipLineCohen(self,point1,point2): #Points are tuples

        def enCode(p):
            code = 0b0000
            if p[0] < 0:
                code |= 0b0001  #left
            elif p[0] >= self.__width:
                code |= 0b0010  #right
            if p[1] < 0:
                code |= 0b0100  #bottom
            elif p[1] >= self.__height:
                code |= 0b1000  #top
            return code

        p = [[point1[0],point1[1]],[point2[0],point2[1]]]
        code = [enCode(p[0]),enCode(p[1])]
        if code[0] | code[1] == 0b0000: #No clip required
            return((p[0][0],p[0][1]),(p[1][0],p[1][1]))
        elif code[0] & code[1] != 0b0000: #Segment is completely outside
            return None
        else:
            for i in range (len(p)):
                while code[i] != 0b0000:
                    if code[i] & 0b1000: #top
                        p[i][0] = p[0][0] + (p[1][0] - p[0][0])*(self.__height - p[0][1] - 1)/(p[1][1] - p[0][1])
                        p[i][1] = self.__height - 1
                    elif code[i] & 0b0100: #bottom
                        p[i][0] = p[0][0] + (p[1][0] - p[0][0])*(-p[0][1])/(p[1][1] - p[0][1])
                        p[i][1] = 0
                    elif code[i] & 0b0010: #right
                        p[i][1] = p[0][1] + (p[1][1] - p[0][1])*(self.__width - p[0][0] - 1)/(p[1][0] - p[0][0])
                        p[i][0] = self.__width - 1
                    else: #left
                        p[i][1] = p[0][1] + (p[1][1] - p[0][1])*(-p[0][0])/(p[1][0] - p[0][0])
                        p[i][0] = 0
                    code[i] = enCode(p[i])
            return ((round(p[0][0]),round(p[0][1])),(round(p[1][0]),round(p[1][1])))

    def clipLineBarsky(self,point1,point2): #Points are tuples
        x1, y1, x2, y2 = point1[0], point1[1], point2[0], point2[1]
        lmin, lmax = [1], [0]
        p = [x1 - x2, x2 - x1, y1 -  y2, y2 - y1]
        q = [x1, self.__width-x1 - 1, y1, self.__height - y1 - 1]
        if (p[0] == 0 and (q[0] < 0 or q[1] < 0)) or (p[2] == 0 and (q[2] < 0 or q[3] < 0)):
            return None
        for i in range(len(p)):
            if p[i] < 0:
                lmax.append(q[i]/p[i])
            elif p[i] > 0:
                lmin.append(q[i]/p[i])
        u1 = max(lmax)
        u2 = min(lmin)
        if u1 > u2:
            return None
        else:
            return ((round(x1 + (x2 - x1)*u1), round(y1 + (y2 - y1)*u1)), (round(x1 + (x2 - x1)*u2), round(y1 + (y2 - y1)*u2)))

    def drawLine(self,point1,point2,color): #Points are column vectors coded as matrices
        segment = self.clipLineBarsky((point1.get(0,0),point1.get(1,0)),(point2.get(0,0),point2.get(1,0)))
        if segment != None:
            exchange = False
            inc1stCoord = 1
            inc2ndCoord = 1
            TransX = -int(segment[0][0])
            TransY = -int(segment[0][1])
            x1 = 0
            y1 = 0
            x2 = int(segment[1][0]) + TransX
            y2 = int(segment[1][1]) + TransY
            Dx = x2
            Dy = y2
            twoDx = 2*x2
            twoDy = 2*y2
            if Dy < 0:
                inc2ndCoord = -1
                Dy *= -1
                twoDy *= -1
            if Dx < 0:
                inc1stCoord = -1
                Dx *= -1
                twoDx *= -1
            if Dy > Dx:
                exchange = True
                twoDx, twoDy = twoDy, twoDx
                Dx, Dy, = Dy, Dx
                inc1stCoord, inc2ndCoord = inc2ndCoord, inc1stCoord
            Pi = twoDy - Dx
            if exchange:
                self.drawPoint((y1-TransX,x1-TransY),color)
            else:
                self.drawPoint((x1-TransX,y1-TransY),color)
            for i in range(Dx):
                if Pi < 0:
                    Pi += twoDy
                else:
                    Pi += twoDy - twoDx
                    y1 += inc2ndCoord
                x1 += inc1stCoord
                if exchange:
                    self.drawPoint((y1-TransX,x1-TransY),color)
                else:
                    self.drawPoint((x1-TransX,y1-TransY),color)

    def drawCircle(self,center,radius,color): #Center is a column vector as a matrix
        c = (int(center.get(0,0)),int(center.get(1,0)))
        x, y = 0, radius
        d = 3 - 2*radius
        while x < y:
            x += 1
            if d < 0:
                d += 4*x + 6
            else:
                y -= 1
                d += 4*(x-y) + 10
            self.drawPoint((x+c[0],y+c[1]),color)
            self.drawPoint((-x+c[0],-y+c[1]),color)
            self.drawPoint((x+c[0],-y+c[1]),color)
            self.drawPoint((-x+c[0],y+c[1]),color)
            self.drawPoint((y+c[0],x+c[1]),color)
            self.drawPoint((-y+c[0],-x+c[1]),color)
            self.drawPoint((-y+c[0],x+c[1]),color)
            self.drawPoint((y+c[0],-x+c[1]),color)

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