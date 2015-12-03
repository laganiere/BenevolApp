package ca.uottawa.eecs.seg2505.benevolapp;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Francis on 2015-11-22.
 */
public class SkillAdapter extends ArrayAdapter<String> {

    List<CreerUneOffreActivity.ListViewSkill> list;

    public SkillAdapter(android.content.Context context, int resource, List<String> objects, List<CreerUneOffreActivity.ListViewSkill> list){

        super(context, resource, objects);
        this.list = list;

    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view = super.getView(position, convertView, parent);
        if(list.get(position).isSelected()){
            view.setBackgroundColor(Color.parseColor("#A9BCF5"));
        }
        else{
            view.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        return view;
    }

}
