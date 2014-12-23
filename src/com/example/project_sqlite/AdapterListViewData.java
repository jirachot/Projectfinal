package com.example.project_sqlite;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class AdapterListViewData extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context context;
	private indexdoc control;
	
	private ArrayList<MemberData> listData = new ArrayList<MemberData>();
	
	public AdapterListViewData(indexdoc indexdoc, ArrayList<MemberData> listData) {
		this.control = indexdoc;
		this.context = indexdoc.getBaseContext();
		this.mInflater = LayoutInflater.from(context);
		this.listData = listData;
	}
	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View converView, ViewGroup parent) {
		HolderListAdapter holderListAdapter;
		
		if(converView == null){
			converView = mInflater.inflate(R.layout.adapter_listview, null);
			
			holderListAdapter = new HolderListAdapter();
			
			holderListAdapter.txtfname = (TextView) converView.findViewById(R.id.txtName);
			holderListAdapter.txtlname = (TextView) converView.findViewById(R.id.txtSurname);
			holderListAdapter.txtage = (TextView) converView.findViewById(R.id.txtAge);
			//holderListAdapter.txtstatus = (TextView) converView.findViewById(R.id.txtstatus);
			holderListAdapter.txtaddress = (TextView) converView.findViewById(R.id.txtaddress);
			holderListAdapter.txtblood = (TextView) converView.findViewById(R.id.txtblood);
			holderListAdapter.txtweight = (TextView) converView.findViewById(R.id.txtweight);
			holderListAdapter.txtheight = (TextView) converView.findViewById(R.id.txtheight);
			holderListAdapter.btnedit = (Button) converView.findViewById(R.id.btnEdit);
			holderListAdapter.btndelete = (Button) converView.findViewById(R.id.btnDelete);
			
			converView.setTag(holderListAdapter);
		}else{
			holderListAdapter = (HolderListAdapter) converView.getTag();
		}
		
		final int id = listData.get(position).getId();
		final String fname = listData.get(position).getFname();
		final String lname = listData.get(position).getLname();
		final int age = listData.get(position).getAge();
		//final String status = listData.get(position).getStatus();
		final String address = listData.get(position).getAddress();
		final String blood = listData.get(position).getBloodgroup();
		final String weight = listData.get(position).getWeight();
		final String height = listData.get(position).getHeight();
		
		holderListAdapter.txtfname.setText("Fname : " + fname);
		holderListAdapter.txtlname.setText("Lname : " + lname);
		holderListAdapter.txtage.setText("Age : " + age);
		//holderListAdapter.txtstatus.setText("Status : " + status);
		holderListAdapter.txtaddress.setText("Address : " + address);
		holderListAdapter.txtblood.setText("BloodGroup : " + blood);
		holderListAdapter.txtweight.setText("Weight : " + weight);
		holderListAdapter.txtheight.setText("Height : " + height);
		
		holderListAdapter.btndelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				control.deletemember(id);
				
			}
		});
		holderListAdapter.btnedit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				control.showEdit(id, fname, lname, age, address, blood, weight, height);
				
			}
		});
		return converView;
	}

}
