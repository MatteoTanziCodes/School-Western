#ifndef ACCOUNTDETAILS_H
#define ACCOUNTDETAILS_H

#include <QDialog>
#include "user.h"

namespace Ui {
class accountdetails;
}

class accountdetails : public QDialog
{
    Q_OBJECT

public:
    explicit accountdetails(QWidget *parent = nullptr);
    ~accountdetails();

private slots:
    void on_pushButton_clicked();

private:
    Ui::accountdetails *ui;
    user *User;
};

#endif // ACCOUNTDETAILS_H
