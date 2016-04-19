package com.uksapps.custombooklist;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;



public class CustomAdapter extends BaseAdapter implements Filterable{


    //Global class variables
    Context ctx;
    ArrayList<Book> filteredBooks;
    CustomFilter searcher;
    ArrayList<Book> unfilteredBooks;
    TextView tvResults;
    ListView lv;

    //The constructor
    public CustomAdapter( Context ctx , ArrayList<Book> books, TextView tvResults,ListView l) {
        this.filteredBooks = books;
        this.ctx = ctx;
        this.unfilteredBooks = books;
        this.lv=l;
        this.tvResults= tvResults;

    }

    //Some default methods
    @Override
    public int getCount() {

        return filteredBooks.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredBooks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return filteredBooks.indexOf(getItem(position));
    }



    //To save some time
    static class ViewHolder {
        TextView tvName;
        TextView tvAuth;
        TextView tvPub;
        ImageView imView;
    }


    //The main method of the adapter that sets the view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView==null){
            LayoutInflater inflater = LayoutInflater.from(ctx);
            convertView = inflater.inflate(R.layout.custom_row,null);

            holder = new ViewHolder();

            //View objects of custom_row.xml are referenced
            holder.tvName =(TextView)convertView.findViewById(R.id.tvName);
            holder.tvAuth =(TextView)convertView.findViewById(R.id.tvAuth);
            holder.tvPub =(TextView)convertView.findViewById(R.id.tvPub);
            holder.imView = (ImageView)convertView.findViewById(R.id.imView);

            convertView.setTag(holder);

        }
        else{holder = (ViewHolder) convertView.getTag();}

        //Setting value to these view objects

        holder.tvName.setText(filteredBooks.get(position).getNames());
        holder.tvAuth.setText("Author: "+ filteredBooks.get(position).getAuthor());
        holder.tvPub.setText("Publisher: "+filteredBooks.get(position).getPublisher());
        holder.imView.setImageResource(filteredBooks.get(position).getImages());

        return convertView;

    }

    @Override
    public Filter getFilter() {
        if (searcher==null){searcher = new CustomFilter();}
        return searcher;
    }

    //Inner class for custom filtering
    class CustomFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if ( constraint != null && constraint.length() > 0) {


                //Splitting the search query(constraint) so that searching is done word by word
                final String[] words= constraint.toString().toLowerCase().split(" ");
                final int length = words.length;

                //for the filtered items
                ArrayList<Book> filtered = new ArrayList<Book>();

                //implementing search algorithm

                //the name results first

                for (int i = 0; i < unfilteredBooks.size(); i++) {

                    for (String word : words)
                        if (unfilteredBooks.get(i).getNames().toLowerCase().contains(word)) {

                            Book b = new Book(unfilteredBooks.get(i).getNames(), unfilteredBooks.get(i).getAuthor(), unfilteredBooks.get(i).getPublisher(),
                                    unfilteredBooks.get(i).getImages(),unfilteredBooks.get(i).getUrl());
                            filtered.add(b);
                            break;

                        }


                }

                //then the authors

                for (int i = 0; i < unfilteredBooks.size(); i++) {

                    for (String word : words)
                        if (unfilteredBooks.get(i).getAuthor().toLowerCase().contains(word)) {
                            Book b = new Book(unfilteredBooks.get(i).getNames(), unfilteredBooks.get(i).getAuthor(),
                                    unfilteredBooks.get(i).getPublisher(), unfilteredBooks.get(i).getImages(),
                                    unfilteredBooks.get(i).getUrl());
                            filtered.add(b);

                            break;
                        }
                }
                //finally the publishers
                for (int i = 0; i < unfilteredBooks.size(); i++) {
                    for (String word : words)
                        if (unfilteredBooks.get(i).getPublisher().toLowerCase().contains(word)) {
                            Book b = new Book(unfilteredBooks.get(i).getNames(), unfilteredBooks.get(i).getAuthor(),
                                    unfilteredBooks.get(i).getPublisher(), unfilteredBooks.get(i).getImages()
                                    ,unfilteredBooks.get(i).getUrl());
                            filtered.add(b);

                            break;
                        }

                }
                //setting the result that will be returned
                results.count=filtered.size();
                results.values=filtered;

            }
            //when user hasn't typed anything
            else{results.count=unfilteredBooks.size();
                results.values=unfilteredBooks;}
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredBooks = (ArrayList<Book>) results.values;

            //to show the no. of results
            if(unfilteredBooks.size()!=filteredBooks.size())
                tvResults.setText("Found "+ filteredBooks.size()+" results");
            else{tvResults.setText("Result");}

            notifyDataSetChanged();

            //Hiding the soft keyboard
            lv.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    hideKeyboardFrom(ctx,lv);
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                }
            });
        }

    }

    //keyboard hiding method
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
