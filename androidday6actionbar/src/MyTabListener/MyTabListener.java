package MyTabListener;

import android.R;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;

public class MyTabListener implements ActionBar.TabListener {
	private Fragment mFragment;
	
	public MyTabListener (Fragment fragment) {
	    mFragment = fragment;
    }
	
	@Override
	public void onTabSelected(Tab tab , FragmentTransaction ft){
		ft.add(R.id.fragment_content, mFragment,null);
	}

	@Override
	public void onTabUnselected(Tab tab,FragmentTransaction ft){
		ft.remove(mFragment);
	}
	
	@Override
	public void onTabReselected(Tab tab,FragmentTransaction ft){
		
	}
}
