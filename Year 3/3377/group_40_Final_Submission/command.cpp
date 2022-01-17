#include "command.h"
#include <iostream>
#include <string>

using namespace std;

Game::Game(int gamestock, string gamename, double gameprice)
{
    stock = gamestock;
    name = gamename;
    price = gameprice;
}

void Game::setStock (int count){
    stock = count;
}

int Game::getStock(){
    return stock;
}

double Game::getPrice(){
    return price;
}

string Game::getName(){
    return name;
}

Game::~Game(void){ //Deconstructor
    cout << "Deconstructing..." << endl;
}

