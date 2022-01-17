#include "accountdetails.h"
#include "ui_accountdetails.h"
#include "user.h"
#include <iostream>
#include <string>
#include <iostream>
#include <fstream>
#include <QString>

using namespace std;

accountdetails::accountdetails(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::accountdetails)
{
    User = new user(1,200,"lol","lol","lol");
    int userBalance;
    string userEmail, userName, userAddress;

    userAddress.assign(User->getAddress());
    userName.assign(User->getName());
    userBalance = User->getBalance();
    userEmail.assign(User->getEmail());

    ui->setupUi(this);

}

accountdetails::~accountdetails()
{
    delete ui;
}
void accountdetails::on_pushButton_clicked()
{
    hide();
}

