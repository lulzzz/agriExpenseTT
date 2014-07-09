package fragments;

import helper.DHelper;
import helper.DbHelper;
import helper.DbQuery;

import java.util.ArrayList;
import java.util.Iterator;

import uwi.dcit.agriexpensett.UseResource;
import uwi.dcit.agriexpensett.ViewCycleUsege;
import uwi.dcit.agriexpensett.localCycle;
import uwi.dcit.agriexpensett.localCycleUse;
import android.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.agriexpensett.R;
import com.example.agriexpensett.rpurchaseendpoint.model.RPurchase;

import fragments.FragmentCycleUseCategory.Click;

public class FragmentGeneralCategory extends Fragment{
	TextView totalLbl;
	TextView cat_pm;//planting material
	TextView cat_fer;//fertilizer
	TextView cat_soilam;//soil amendment
	TextView cat_chem;//chemical
	TextView cat_labr;//labour
	
	SQLiteDatabase db;
	DbHelper dbh;
	
	double catTotal=0,pm=0,fer=0,soilam=0,chem=0,labr=0;//totals
	View view;
	localCycle currCycle;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.fragment_general_cat, container, false);
		dbh=new DbHelper(getActivity());
		db=dbh.getReadableDatabase();
		calcTotals();
		setup();
		return view;
	}
	
	private void setup() {
		totalLbl=(TextView)view.findViewById(R.id.tv_catTotal_lbl);
		cat_pm=(TextView)view.findViewById(R.id.tv_catTotal_pm);
		cat_fer=(TextView)view.findViewById(R.id.tv_catTotal_fertilizer);
		cat_soilam=(TextView)view.findViewById(R.id.tv_catTotal_soilam);
		cat_chem=(TextView)view.findViewById(R.id.tv_catTotal_chemical);
		cat_labr=(TextView)view.findViewById(R.id.tv_catTotal_labour);
		
		
		cat_pm.setText("Planting Material:$"+pm);
		cat_fer.setText("Fertilizer:$"+fer);
		cat_soilam.setText("Soil Amendment:$"+soilam);
		cat_chem.setText("Chemical:$"+chem);
		cat_labr.setText("Labour:$"+labr);
		
		TextView sum=(TextView)view.findViewById(R.id.tv_catTotal_sum);
		sum.setText("Total:$"+currCycle.getTotalSpent());
	}
	private void calcTotals(){
		currCycle=getArguments().getParcelable("cycle");
		ArrayList<localCycleUse> list=new ArrayList<localCycleUse>();
		DbQuery.getCycleUse(db, dbh, currCycle.getId(), list, DHelper.cat_plantingMaterial);
		pm=getTotal(list);
		
		list=new ArrayList<localCycleUse>();
		DbQuery.getCycleUse(db, dbh, currCycle.getId(), list, DHelper.cat_fertilizer);
		fer=getTotal(list);
		
		list=new ArrayList<localCycleUse>();
		DbQuery.getCycleUse(db, dbh, currCycle.getId(), list, DHelper.cat_soilAmendment);
		soilam=getTotal(list);
		
		list=new ArrayList<localCycleUse>();
		DbQuery.getCycleUse(db, dbh, currCycle.getId(), list, DHelper.cat_chemical);
		chem=getTotal(list);
		
		list=new ArrayList<localCycleUse>();
		DbQuery.getCycleUse(db, dbh, currCycle.getId(), list, DHelper.cat_labour);
		labr=getTotal(list);
	}

	private double getTotal(ArrayList<localCycleUse> list) {
		Iterator<localCycleUse> i=list.iterator();
		double total=0;
		while(i.hasNext()){
			total+=i.next().getUseCost();
		}
		return total;
	}

	
}