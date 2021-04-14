import operator
from PIL import Image


class graphicsWindow:

    def __init__(self, width=640, height=480):
        self.__mode = 'RGB'
        self.__width = width
        self.__height = height
        self.__canvas = Image.new(self.__mode, (self.__width, self.__height))
        self.__image = self.__canvas.load()

    def drawPoint(self, point, color):
        if 0 <= point[0] < self.__width and 0 <= point[1] < self.__height:
            self.__image[point[0], point[1]] = color

    # returns the x value of the line point as an integer
    def getX(self, point):
        return int(point.get(0, 0))

    # returns the y value of the line point as an integer
    def getY(self, point):
        return int(point.get(1, 0))

    # This method uses bresenham's line algorithm to draw a line given two points and the line colour.
    def drawLine(self, point1, point2, color):
        # Get the x and y coords of each point passed to the draw line method
        x1 = self.getX(point1)
        x2 = self.getX(point2)
        y1 = self.getY(point1)
        y2 = self.getY(point2)

        # Define the rise and run of the line
        rise = y2 - y1
        run = x2 - x1

        # If the line is vertical, the run is 0, therefore, no m value is calculated. First determine if the line goes
        # up or down and then draw each point.
        if run == 0:
            if y2 < y1:
                y1, y2 = (y2, y1)
            for y in range(y1, y2 + 1):
                self.drawPoint((x1, y), color)
        else:
            m = float(rise) / run
            # if the slope is positive, aka the line goes up, make the adjust value positive, else negative, also set
            # the offset to 0
            adjust = 1 if m >= 0 else -1
            offset = 0
            # if the abs value of the run is larger than the rise, determine the line direction and plot each line point
            # based on the run, the offset/adjust will account will account for changes in the y-value based on the
            # line slope
            if 1 >= m >= -1:
                delta = abs(rise) * 2
                threshold = abs(run)
                thresholdInc = abs(run) * 2
                y = y1
                if x2 < x1:
                    x1, x2 = (x2, x1)
                    y = y2
                for x in range(x1, x2 + 1):
                    self.drawPoint((x, y), color)
                    offset += delta
                    if offset >= threshold:
                        y += adjust
                        threshold += thresholdInc
            # if the abs value of the rise is larger than the run, determine the line direction and plot each line point
            # based on the rise, the offset/adjust will account will account for changes in the x-value based on the
            # line slope
            else:
                delta = abs(run) * 2
                threshold = abs(rise)
                thresholdInc = abs(rise) * 2
                x = x1
                if y2 < y1:
                    y1, y2 = (y2, y1)
                    x = x2
                for y in range(y1, y2 + 1):
                    self.drawPoint((x, y), color)
                    offset += delta
                    if offset >= threshold:
                        x += adjust
                        threshold += thresholdInc

    def saveImage(self, fileName):
        self.__canvas.save(fileName)

    def showImage(self):
        self.__canvas.show()

    def getWidth(self):
        return self.__width

    def getHeight(self):
        return self.__height
