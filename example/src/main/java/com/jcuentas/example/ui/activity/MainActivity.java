package com.jcuentas.example.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.jcuentas.example.R;
import com.jcuentas.example.data.dao.AccountDao;
import com.jcuentas.example.data.dao.OrderDao;
import com.jcuentas.example.data.model.Account;
import com.jcuentas.example.data.model.Order;

public class MainActivity extends BaseActivity {
    public static final String TAG = "MainActivity";
    AccountDao mAccountDao;
    OrderDao mOrderDao;
    int idAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAccountDao = new AccountDao(getDbHelper(), Account.class);
        mOrderDao = new OrderDao(getDbHelper(), Order.class);
        setupInformation();
        selectInformation();
    
    }

    private void selectInformation() {
//        selectAccount();
        selectOrde();
    }

    private void selectOrde() {
        Log.d(TAG, "selectOrde()");
        Order order = mOrderDao.obtenerPorId(7);
        Log.d(TAG, "selectOrde:order " +
                order.getId() + ", "+
                order.getPrice() + ", " +
                order.getItemNumber() + ", "+
                order.getQuantity());
        Account account = order.getAccount();
        Log.d(TAG, "selectOrde:account "+
                        account.getId() +", "+
                        account.getName() + "," );

        for (Order orderin  : account.getOrders()) {
            Log.d(TAG, "selectOrde:order:for " +
                    orderin.getId() + ", " +
                    orderin.getPrice() + ", " +
                    orderin.getItemNumber() + ", " +
                    orderin.getQuantity());
        }

    }

    private void selectAccount() {
        Log.d(TAG, "selectAccount()");
        Account account = mAccountDao.obtenerPorId(idAccount);
        Log.d(TAG, "selectAccount:account " +/* account.getId() + ", "+*/
                account.getName());
        for (Order order  : account.getOrders()) {
            Log.d(TAG, "selectAccount:order " +
                    order.getId() + ", "+
                    order.getPrice() + ", " +
                    order.getItemNumber() + ", "+
                    order.getQuantity());
        }
    }

    private void setupInformation() {
        Log.d(TAG, "setupInformation()");
        //Creamos una cuenta
        String name = "José Cuentas";
        Account account = new Account(name);
        mAccountDao.crear(account);
        idAccount = account.getId();
        Log.d(TAG, "idAccount " + idAccount);
        //TODO:Creacion de ordenes
        //Creacion de Orden1;
        int quantity1 = 2;
        int itemNumber1 = 21312;
        float price1 = 12.32F;
        Order order = new Order(account, itemNumber1,price1,quantity1);
        mOrderDao.crear(order);
        //Creacion de orden2;
        int quantity2 = 1;
        int itemNumber2 = 785;
        float price2 = 7.98F;
        Order order2 = new Order(account, itemNumber2, price2, quantity2);
        mOrderDao.crear(order2);
    }


}
