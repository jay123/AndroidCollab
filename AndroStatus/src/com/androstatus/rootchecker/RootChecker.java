package com.androstatus.rootchecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.ninadjayandrostatus.R;

import android.os.Bundle;
import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class RootChecker extends Activity {
	
	TextView rootResultsView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_root_checker);
		rootResultsView = (TextView)findViewById(R.id.rootResultsView);
		rootResultsView.setText(rootCheck());
		rootResultsView.setMovementMethod(new ScrollingMovementMethod());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.root_checker, menu);
		return true;
	}
	
	//Executes a given command on the Android shell and returns output
	public String runCommand(String cmd){
		String output = "";
		Runtime run = Runtime.getRuntime();
		Process pr;
		try {
			pr = run.exec(cmd);
			pr.waitFor();

			BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			String line = "";
		
			while ((line=buf.readLine())!=null) {
				output=output+"\n"+line;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		return output;
		
	}

	//Checks for root and returns a description of the result	
	public String rootCheck(){
		String result;
		result = runCommand("ls");
		Log.d("TEST_MESSAGES",result);
			
		return result;
	}

}
