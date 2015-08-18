package com.jcuentas.example.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.ForeignCollection;
import com.jcuentas.example.R;
import com.jcuentas.example.data.dao.AccountDao;
import com.jcuentas.example.data.dao.OrderDao;
import com.jcuentas.example.data.model.Account;
import com.jcuentas.example.data.model.Order;

import java.util.List;

public class ActivityCollection extends BaseActivity{

		AccountDao accountDao;
		OrderDao orderDao;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);

			accountDao= new AccountDao(getDbHelper(), Account.class);
			orderDao= new OrderDao(getDbHelper(), Order.class);
			try {
				readWriteData();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * Setup our database and DAOs
		 */

		private void readWriteData() throws Exception {

			// create an instance of Account
			String name = "Buzz Lightyear";
			Account account = new Account(name);

			// persist the account object to the database
			accountDao.getDao().create(account);

			// create an associated Order for the Account
			// Buzz bought 2 of item #21312 for a price of $12.32
			int quantity1 = 2;
			int itemNumber1 = 21312;
			float price1 = 12.32F;
			Order order1 = new Order(account, itemNumber1, price1, quantity1);
			orderDao.getDao().create(order1);

			// create another Order for the Account
			// Buzz also bought 1 of item #785 for a price of $7.98
			int quantity2 = 1;
			int itemNumber2 = 785;
			float price2 = 7.98F;
			Order order2 = new Order(account, itemNumber2, price2, quantity2);
			orderDao.getDao().create(order2);

			Account accountResult = accountDao.getDao().queryForId(account.getId());
			ForeignCollection<Order> orders = accountResult.getOrders();



			for (Order order : orders) {
				Log.d("DEBUG",""+ order.getPrice());
			}
			
			// sanity checks
			CloseableIterator<Order> iterator = orders.closeableIterator();
			
			try {
				Log.d("DEBUG",""+ iterator.hasNext());
				//assertTrue(iterator.hasNext());
				Order order = iterator.next();
				Log.d("DEBUG",""+ order.getItemNumber());
				Log.d("DEBUG",""+ order.getAccount());
				Log.d("DEBUG",""+ iterator.hasNext());
				
				//assertEquals(itemNumber1, order.getItemNumber());
				//assertSame(accountResult, order.getAccount());
				//assertTrue(iterator.hasNext());
				order = iterator.next();
				Log.d("DEBUG",""+ order.getItemNumber());
				Log.d("DEBUG",""+ iterator.hasNext());
				//assertEquals(itemNumber2, order.getItemNumber());
				//assertFalse(iterator.hasNext());
			} finally {
				// must always close our iterators otherwise connections to the database are held open
				iterator.close();
			}

			// create another Order for the Account
			// Buzz also bought 1 of item #785 for a price of $7.98
			int quantity3 = 50;
			int itemNumber3 = 78315;
			float price3 = 72.98F;
			Order order3 = new Order(account, itemNumber3, price3, quantity3);

			// now let's add this order via the foreign collection
			orders.add(order3);
			
			// now there are 3 of them in there
			//assertEquals(3, orders.size());
			Log.d("DEBUG",""+ orders.size());

			List<Order> orderList = orderDao.getDao().queryForAll();
			// and 3 in the database
			//assertEquals(3, orderList.size());
			
			Log.d("DEBUG",""+ orderList.size());
			Log.d("DEBUG",""+ orderList.get(0).getAccount().getName());
		}
}
