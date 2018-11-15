package com.raywenderlich.babybookcase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BooksAdapter extends BaseAdapter {

    private final Context mContext;
    private final Book[] books;


    // This constructor instantiates BookAdapter
    public BooksAdapter(Context context, Book[] book) {
        this.mContext = context;
        this.books = book;
    }


    // Method that returns number of cells to render
    @Override
    public int getCount() {
        return books.length;
    }


    // Method that returns the item by position, unused, required
    @Override
    public Object getItem(int position) {
        return null;
    }


    // Method that returns the Item Id by position, unused, required
    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Book book = books[position];

        // view holder pattern
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayout_books, null);

            final ImageView imageViewCoverArt = (ImageView)convertView.findViewById(R.id.imageview_cover_art);
            final TextView nameTextView = (TextView)convertView.findViewById(R.id.textview_book_name);
            final TextView authorTextView = (TextView)convertView.findViewById(R.id.textview_book_author);
            final ImageView imageViewFavorite = (ImageView)convertView.findViewById(R.id.imageview_favorite);

            final ViewHolder viewHolder = new ViewHolder(nameTextView, authorTextView, imageViewCoverArt, imageViewFavorite);
            convertView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        viewHolder.imageViewCoverArt.setImageResource(book.getImageResource());
        viewHolder.nameTextView.setText(mContext.getString(book.getName()));
        viewHolder.authorTextView.setText(mContext.getString(book.getAuthor()));
        viewHolder.imageViewFavorite.setImageResource(book.getIsFavorite() ? R.drawable.star_enabled : R.drawable.star_disabled);

        return convertView;
    }

    private class ViewHolder {
        private final TextView nameTextView;
        private final TextView authorTextView;
        private final ImageView imageViewCoverArt;
        private final ImageView imageViewFavorite;

        public ViewHolder(TextView nameTextView, TextView authorTextView,
                          ImageView imageViewCoverArt, ImageView imageViewFavorite) {
            this.nameTextView = nameTextView;
            this.authorTextView = authorTextView;
            this.imageViewCoverArt = imageViewCoverArt;
            this.imageViewFavorite = imageViewFavorite;
        }
    }
}
