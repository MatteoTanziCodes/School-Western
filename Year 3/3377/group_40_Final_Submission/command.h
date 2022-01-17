#ifndef COMMAND_CLASS_H
#define COMMAND_CLASS_H
#include <string>

class Ga
{
    private:
        int stock;
        std::string name;
        double price;

    public:
        Game(int,std::string,double);
        ~Game();
        void setStock (int);
        int getStock();
        double getPrice();
        std::string getName();
};
#endif

